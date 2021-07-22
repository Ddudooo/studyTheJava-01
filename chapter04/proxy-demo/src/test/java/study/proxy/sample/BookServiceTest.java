package study.proxy.sample;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.junit.jupiter.api.Test;

class BookServiceTest {

	BookService proxy = (BookService) Proxy
		.newProxyInstance(DefaultBookService.class.getClassLoader(),
			new Class[]{BookService.class}, new InvocationHandler() {
				BookService bookService = new DefaultBookService();

				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					if (method.getName().equals("rent")) {
						System.out.println("프록시 호출");
						Object invoke = method.invoke(bookService, args);
						System.out.println("프록시 호출 완료");
						return invoke;
					}
					return method.invoke(bookService, args);
				}
			});

	@Test
	void proxyTest() {
		Book book = new Book("프록시", "isbn");

		proxy.rent(book);
	}
}