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

---

# 클래스의 프록시가 필요할 경우에는?

다이나믹 프록시기술에는 인터페이스단위로만 구현이 가능하다는 제약사항이 존재한다.

클래스의 프록시를 다이나믹하게 생성하려면 서브 클래스를 다룰 수 있는,

바이트코드 조작이 가능한 라이브러리들을 이용해 프록시를 생성할 수 있다.

`CGlib`, `ByteBuddy` 와 같은 라이브러리로 프록시를 생성해보자

```java
// CGlib 예시
class DynamicProxyTest {

	@Test
	void createProxyInstanceWithDynamic() throws Exception {
		MethodInterceptor handler = new MethodInterceptor() {
			DefaultBookService bookService = new DefaultBookService();

			@Override
			public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy)
				throws Throwable {
				if (method.getName().equals("rent")) {
					System.out.println("프록시에서 호출");
					Object invoke = method.invoke(bookService, args);
					System.out.println("프록시에서 호출 완료");
					return invoke;
				}
				return method.invoke(bookService, args);
			}
		};
		BookService bookService = (BookService) Enhancer.create(BookService.class, handler);

		Book book = new Book("프록시", "isbn");
		bookService.rent(book);
		bookService.returnBook(book);
	}
}
```

```java
// ByteBuddy 예시
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
```

## 서브 클래스를 만드는 방법의 단점

* 상속을 사용하지 못하는 경우 프록시를 만들 수 없다.
    * `Private` 생성자만 있는 경우
    * `Final` 클래스인 경우
* 인터페이스가 있을 때는 인터페이스의 프록시를 만들어 사용할 것.
