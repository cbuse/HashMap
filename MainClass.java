package tema3;

import java.util.ArrayList;
import java.util.Random;

public class MainClass {
	public static final int N = 2000;
	public static final int ACCESARI = 900;
	
	/****
	 * 
	 * @param n - numarul de caractere pe care il va contine string-ul 
	 * 			  generat aleator
	 * @param s -string-ul din  care se vor extrage caractere carevor forma
	 * 			sirul general aleator
	 * @return
	 */
	String next(int n, String s){
		//transforma string-ul in vector de caractere
		char[] a = s.toCharArray();
		//vector de caractere generate aleator
		char[] b = new char[n];
		
				
		Random generator = new Random(); 
		
		//se extrag aleator n caractere din vectorul de caractere b
		for (int i = 0; i < n; i++ ){
		int value = generator.nextInt(s.length());
			b[i] = a[value];
		}
		
		String bb = new String(b);
		
		return bb;
				
	}
	
	/***
	 * Genereaza o lista de N studenti cu varste si nume generate aleator.
	 * 
	 * @return lista de studenti generati aleator
	 */
	ArrayList<Student> randomStudentGenerator(){
		ArrayList<Student> students = new ArrayList<Student>();
		String str = "abcdefghijklmnopqrstuvwxyz!@$6789";
		
		
		Random r = new Random();
		Student stud = new Student();
		
		for(int i = 0; i < N; i++){
			
			//varsta studentului este un numar aleator <25
			stud.setAge(r.nextInt(25));
			//numele este un string cu lungimea maxima de 30, care a fost extras
			//din string-ul str
			String name = this.next(r.nextInt(25),str);			
			stud.setName(name);
			
			students.add(stud);
		}
		return students;
	}
	
	/****
	 * Genereaza o lista de N studenti cu varste si nume generate aleator.
	 * Procedeul este similar ca la crearea listei de studenti.
	 * @return lista de studenti lenesi generati aleator
	 */
	ArrayList<LazyStudent> randomLazyStudentGenerator(){
		ArrayList<LazyStudent> students = new ArrayList<LazyStudent>();
		String str = "abcdefghijklmnopqrstuvwxyz!@$6789";		
		
		Random r = new Random();
		LazyStudent stud = new LazyStudent();
		
		for(int i = 0; i < N; i++){
			
			stud.setAge(r.nextInt(25));
			String name = this.next(r.nextInt(30),str);
			stud.setName(name);
			
			students.add(stud);
		}
		return students;
	}
	
	public static void main(String[] args){
		
		MainClass t = new MainClass();
		
		/*for(int i = 0 ; i < t.randomStudentGenerator().size(); i++){
			System.out.println(t.randomStudentGenerator().get(i).getName());
			System.out.println(t.randomStudentGenerator().get(i).getAge());
		}*/
		
		 MyHashMapImpl<Student, Integer> hm = new MyHashMapImpl<Student, Integer>(1000);
		 Random r = new Random();
		 
		 //adaug in tabela hash lista de Studenti
		 for(int j = 0; j < t.randomStudentGenerator().size(); j ++){
			
			 Integer grade = new Integer(r.nextInt(10));
			 hm.put(t.randomStudentGenerator().get(j), grade);
			
		 }
		 
		long time_start = System.currentTimeMillis();
		//execut un numar mare de accesari ale tabelei hash
		for(int ii = 0; ii < ACCESARI; ii++)
			hm.get(t.randomStudentGenerator().get(r.nextInt(N)));			
				
		long time_stop = System.currentTimeMillis();
		 
		long time = time_stop- time_start;
		
		System.out.println("Student " + time);
		//hm.display();
		
		MyHashMapImpl<LazyStudent, Integer> hl = new MyHashMapImpl<LazyStudent, Integer>(1000);
				 
		 for(int j = 0; j < t.randomLazyStudentGenerator().size(); j ++){
			
			 Integer grade = new Integer(r.nextInt(10));
			 hl.put(t.randomLazyStudentGenerator().get(j),grade );
			 
		 }			
		 
		long time_start1 = System.currentTimeMillis();
		
		for(int ii = 0; ii < ACCESARI; ii++)
			hm.get(t.randomLazyStudentGenerator().get(r.nextInt(N)));
		
		
		long time_stop1 = System.currentTimeMillis();
		 
		long time1 = time_stop1- time_start1;
		System.out.println("Lazy Student" + time1);
		//hl.display();
		
	}
}
