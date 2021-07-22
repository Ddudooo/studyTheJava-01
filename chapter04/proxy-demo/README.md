# 다이나믹 프록시

런타임에 특정 인터페이스들을 구현하는 클래스 또는 인스턴스를 만드는 기술
> “an application can use a dynamic proxy class to create an object that implements multiple arbitrary event listener interfaces”

- https://docs.oracle.com/javase/8/docs/technotes/guides/reflection/proxy.html

프록시 인스턴스 만들기

* Object Proxy.newProxyInstance(ClassLoader, Interfaces, InvocationHandler)

```java
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
```

* 유연한 구조가 아니다. 그래서 스프링 AOP 등장!
* 스프링 AOP에 대한 더 자세한 토비의 스프링 3.1, 6장 AOP를 참고하세요. 참고
* https://docs.oracle.com/javase/8/docs/technotes/guides/reflection/proxy.html
* https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Proxy.html#newProxyInstance-java.lang.ClassLoader-java.lang.Class:A-java.lang.reflect.InvocationHandler-