# 스프링의 DI는 어떻게 동작할까

Spring Framework 를 사용하면서 자연스럽게 사용하는 기능인 DI

Dependency Injection은 어떻게 동작하길래

별도의 추가 코드 없이 객체를 가져올까

```java

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
```

객체를 생성하지 않았음에도 `null`이 아님을 검증하는 테스트코드이다

어떻게 이런 코드가 통과가 가능할까