package study.demo.di;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ContainerServiceTest {

	@Test
	public void getObject() {
		BookRepo bookRepo = ContainerService.getObject(BookRepo.class);
		assertNotNull(bookRepo);
	}

	@Test
	public void getObject_BookService() {
		BookService bookService = ContainerService.getObject(BookService.class);
		assertNotNull(bookService);
		assertNotNull(bookService.bookRepo);
	}
}