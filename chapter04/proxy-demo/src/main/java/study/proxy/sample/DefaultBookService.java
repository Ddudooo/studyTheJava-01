package study.proxy.sample;

public class DefaultBookService implements BookService {

	@Override
	public void rent(Book book) {
		System.out.println("RENT " + book);
	}

	@Override
	public void returnBook(Book book) {
		System.out.println("RETURN " + book);
	}
}