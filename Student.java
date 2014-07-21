package tema3;


public class Student {
	private String name;
	private int age;
	
	Student(String n, int a) {
		name = n;
		age = a;
	}
	
	Student(){
		
	}
	String getName(){
		return name;
	}
	
	int getAge(){
		return age;
	}
	
	public void setName(String n){
		name = n;
	}
	
	public void setAge(int a){
		age = a;
	}
	
	@Override
	public int hashCode(){
		int hC = 3;
		
		hC = hC + 20 * name.hashCode();
		hC = hC + 21 * age;
		
		return hC;
	}
	
	
	@Override
	public boolean equals(Object o) {
		
		int h1 = o.hashCode();
		int h2 = this.hashCode();
		
		if(h1 == h2){
			
			if (name.equals(((Student)o).name) && age == ((Student)o).age)
				return true;
		
		}
		return false;
		
	}
}
