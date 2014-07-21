package tema3;

public class LazyStudent extends Student {

	LazyStudent(String name, int age){
		super(name, age);
	}
	
	LazyStudent(){
		super();
	}
	
	@Override
	public int hashCode(){
		return 21;
	}
}
