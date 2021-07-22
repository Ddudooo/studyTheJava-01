package study.proxy.sample;

import static net.bytebuddy.matcher.ElementMatchers.named;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import org.junit.jupiter.api.Test;

public class DynamicProxyTest {

	@Test
	void createProxyInstanceWithDynamic() throws Exception {
		Class<? extends DefaultBookService> proxyClass = new ByteBuddy()
			.subclass(DefaultBookService.class)
			.method(named("rent"))
			.intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
				BookService bookService = new DefaultBookService();

				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					System.out.println("프록시 호출");
					Object invoke = method.invoke(bookService, args);
					System.out.println("프록시 호출 완료");
					return invoke;
				}
			}))
			.make()
			.load(DefaultBookService.class.getClassLoader())
			.getLoaded();

		DefaultBookService bookService = proxyClass.getConstructor(null)
			.newInstance();

		Book book = new Book("프록시", "isbn");

		bookService.rent(book);
		bookService.returnBook(book);
	}
}