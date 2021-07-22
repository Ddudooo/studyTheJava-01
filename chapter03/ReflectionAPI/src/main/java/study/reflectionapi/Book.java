package study.reflectionapi;

@MyAnnotation
public class Book {

	private static String B = "BOOK";
	private static final String C = "BOOK";

	@AnotherAnnotation
	private String A = "a";

	public String d = "d";
	@AnotherAnnotation
	protected String e = "e";

	public Book() {
	}

	@AnotherAnnotation
	public Book(String a, String d, String e) {
		A = a;
		this.d = d;
		this.e = e;
	}

	@AnotherAnnotation
	private void f() {
		System.out.println("F");
	}

	public void g() {
		System.out.println("g");
	}

	public int h() {
		return 100;
	}
}