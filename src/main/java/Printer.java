package main.java;
import java.util.HashMap;

public class Printer  {
	private static int tabs=0;
	private static HashMap<String, String> map=new HashMap<String, String>();
	public static void PrintCall(String print) {
		for(int i=0; i<tabs; i++)
			System.out.print(" ");
		System.out.println("->"+print);
		tabs+=2;
	}
	public static void PrintReturn(String print) {
		tabs-=2;
		for(int i=0; i<tabs; i++)
			System.out.print(" ");
		System.out.println("<-"+print);
	}
	public static void put(String key, String value) {
		map.put(key,  value);
	}
	public static String get(String key) {
		return map.get(key);
	}
	public static void clear() {
		map.clear();
	}

}
