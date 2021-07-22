package study.proxy.sample;

public class BookServiceProxy implements BookService {

	private final DefaultBookService defaultBookService;

	public BookServiceProxy(DefaultBookService defaultBookService) {
		this.defaultBookService = defaultBookService;
	}

	@Override
	public void rent(Book book) {
		System.out.println("실제 객체를 호출하여 처리합니다.");
		defaultBookService.rent(book);
		System.out.println("처리 완료");
	}

	@Override
	public void returnBook(Book book) {
		defaultBookService.returnBook(book);
	}
}