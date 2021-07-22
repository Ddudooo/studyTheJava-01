package study.proxy.sample;

import java.util.StringJoiner;

public class Book {

	private String name;
	private String isbn;

	public Book(String name, String isbn) {
		this.name = name;
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Book.class.getSimpleName() + "[", "]")
			.add("name='" + name + "'")
			.add("isbn='" + isbn + "'")
			.toString();
	}
}