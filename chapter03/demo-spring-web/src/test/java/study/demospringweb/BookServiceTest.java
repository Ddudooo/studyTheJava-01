package study.demospringweb;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookServiceTest {

	@Autowired
	BookService bookService;

	@Test
	void autoWiredTest() {
		assertNotNull(bookService);
		assertNotNull(bookService.bookRepo);
	}
}