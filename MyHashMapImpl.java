package tema3;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

class MyHashMapImpl<K, V> implements MyHashMap<K, V> {
	
	Vector<BucketImpl> hashmap;
	int size;
	
	
	/***
	 * Constructorul creeaza tabela hash ca pe un vector bucket-uri.
	 * Fiecare bucket din vector este o  lista de entry-uri.
	 * De asemenea, numarul de elemente din tabela(size) este 0.
	 * 
	 * @param numBuckets - numarul de bucket-uri cu care va fi intializata
	 * tabela hash
	 */
	MyHashMapImpl(int numBuckets){		
		
		hashmap = new Vector<BucketImpl>(numBuckets);
		
		for(int i = 0; i < numBuckets;i++)
			hashmap.add(new BucketImpl());
		
		size = 0;
	}
	
	/**
	 * 
	 * Clasa interna BucketImpl defineste comportamentul unui bucket.
	 * Contine un camp(bucket), care este o lista de entry-uri.	 *
	 */
	class BucketImpl implements Bucket<K, V>{
		private ArrayList<EntryImpl> bucket;
		
		/***
		 * Contructorul BucketImpl initializeaza un bucket cu o lista vida.
		 */
		BucketImpl(){
			bucket = new ArrayList<EntryImpl>();
			
		}
		
				
		@Override
		public List<? extends Entry<K, V>> getEntries() {
			
			return bucket;
		}
		
	}
	
	
	/****
	 * 
	 *Clasa EntryImpl defineste comportamentul unui entry din tabela hash.
	 *Ea contine getteri si setteri pentru campurile sale.
	 *
	 */
	class EntryImpl implements Entry<K, V>{
		
		private K key;
		private V value;
		
		public void setKey(K key){
			this.key = key;
		}
		
		public K getKey(){
			return key;
		}
		
		public void setValue(V value){
			this.value = value;
		}
		
		public V getValue(){
			return value;
		}
		
	}
	
	/***
	 * 
	 * Metoda get functioneaza astfel: calculeaza index-ul bucket-ului
	 * in care ar trebui sa se afle cheia pe care o caut, apoi cauta in
	 * bucket-ul respectiv daca se gaseste cheia. Daca o gaseste, returneaza
	 * valoarea asociata, altfel returneaza null.
	 */
	@Override
	public V get(K key){
		
		//calculez hash-ul cheii cautate
		int hc = key.hashCode();
		//calculez index-ul bucket-ului in care s-ar putea afla cheia cautata
		int index = Math.abs(hc) % hashmap.capacity();
		
		V val = null;	
		int pos = -1;
		
		boolean ok = false;//nu am gasit cheia cautata
		
		ArrayList<EntryImpl> b = hashmap.get(index).bucket;
		
		for(int i = 0; i < b.size(); i++){
				
			if(b.get(i).getKey().equals(key)){
				
				ok = true;//am gasit cheia cautata
				pos = i;//pastrez pozitia din bucket la care
						//am gasit cheia cautata					
			}
						
		}
			//daca am gasit cheia cautata
			if(ok == true)
				//pastrez valoarea asociata cu cheia cautata spre a o returna
				val =	hashmap.get(index).bucket.get(pos).getValue();
			
		return val;		
	}
	
	/**
	 *Metoda put functioneaza astfel: calculeaza index-ul bucket-ului
	 * in care ar trebui sa adaug noua cheie, apoi cauta in
	 * bucket-ul respectiv daca se gaseste cheia. Daca o gaseste, returneaza
	 * vechea valoarea asociata, altfel returneaza null. 
	 */
	 
	@Override
	public V put(K key, V value){
		
		//calculez hash-ul cheii cautate
		int hc = key.hashCode();
		//calculez index-ul bucket-ului in care s-ar putea afla cheia cautata
		int index = Math.abs(hc) % hashmap.capacity();
		
		EntryImpl entry = new EntryImpl();
		entry.setKey(key);
		entry.setValue(value);
		
		//cheia pe care urmeaza sa o inserez nu se afla in tabela hash
		boolean ok = false;
		int pos = -1;
		V v = null;
		//daca bucket-ul in care urmeaza sa introduc entry-ul este gol,
		//adaug noul entry si cresc numarul de entr-uri(size)
		if (hashmap.get(index).bucket.size() == 0){
			hashmap.get(index).bucket.add(entry);	
			
			size++;
			
		}
		else{	
			//parcurg entry-urile unui bucket
			ArrayList<EntryImpl> b = hashmap.get(index).bucket;
			
			for(int i = 0; i < b.size(); i++){
				//verific daca cheia pe care urmeaza sa o inserez se afla
				//in tabela hash
				if(b.get(i).getKey().equals(key)){
					//cheia pe care urmeaza sa o inserez
					//se afla in tabela hash
					ok = true;
					//pastrez pozitia din bucket la care
					//am gasit cheia cautata
					pos = i;
					
					
				}
			}	
			
			
			//cheia  care urmeaza sa fie inserata
			//se afla in tabela hash
			if(ok == true){
				//pastrez vechea valoare asociata cu cheia care
				//urmeaza sa fie inserata				
				v = hashmap.get(index).bucket.get(pos).getValue();
				//setez valoarea cheii la noua valoare
				hashmap.get(index).bucket.get(pos).setValue(value);	
				
				return v;
				
			}
			//daca cheia care urmeaza sa fie inserata
			//nu se afla in tabela hash
			else{
				//adaug cheia in tabela hash
				hashmap.get(index).bucket.add(entry);
				//cresc numarul de entry-uri
				size ++;	
			
			}
		}
				
		return v;
	}
	
	/**
	 *Metoda remove functioneaza astfel: calculeaza index-ul bucket-ului
	 * in care ar trebui sa se afle cheia care urmeaza sa fie stearsa,
	 * apoi cauta in bucket-ul respectiv daca se gaseste cheia.
	 * Daca o gaseste, sterge entry-ul din tabela hash, altfel returneaza null. 
	 */
	@Override
	public V remove(K key){
		
		 
		int hc = key.hashCode();
		int index = Math.abs(hc) % hashmap.capacity();
		
		V val = null;
		//daca bucket-ul in care urmeaza sa introduc entry-ul este gol,
		//returnez null
		if (hashmap.get(index).bucket.isEmpty()){
			return null;
		}
		//daca bucket-ul in care urmeaza sa introduc entry-ul nu este gol
		else{
			//parcurg entry-urile din bucket
			for(int i = 0; i < hashmap.get(index).bucket.size(); i++){
				//daca gasesc cheia
				if(hashmap.get(index).bucket.get(i).getKey().equals(key)){
					//pastrez valoarea asociata cheii spre a o returna
					val = hashmap.get(index).bucket.get(i).getValue();
					//sterg entry-ul care are cheia data ca parametru
					hashmap.get(index).bucket.remove(i);
					//scad numarul de entry-uri din tabela hash
					size--;
				}			
			}	
		}
		return val;
	}
	
	
	public int size(){
		
		return size;
	}



	@Override
	public List<? extends Bucket<K, V>> getBuckets() {
		
		return hashmap;
		
	}
	
	public void display(){
		for(int i = 0; i < hashmap.capacity() ;i++ ){
			System.out.print(i + " ");		
			for(int j = 0; j <hashmap.get(i).bucket.size() ;j++ ){
				
				System.out.print(hashmap.get(i).bucket.get(j).getValue() + 
						" ");
			}
			System.out.println();
	}
}}

