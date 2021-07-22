package study.proxy.sample;

import org.junit.jupiter.api.Test;

class BookServiceTest {

	BookService proxy = new BookServiceProxy(new DefaultBookService());

	@Test
	void proxyTest() {
		Book book = new Book("프록시", "isbn");

		proxy.rent(book);
	}
}