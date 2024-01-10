package RoughWork;

public class SystemPropertiesClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	System.getProperties().list(System.out);
	System.out.println(System.getProperty("os.version"));
	System.out.println(System.getProperty("java.version"));
	System.out.println(System.getProperty("user.name"));

	}

}
