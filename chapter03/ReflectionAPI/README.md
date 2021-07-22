# 리플렉션 API

Java 에서는 Reflection API를 통해 클래스의 정보를 알아낼 수 있다

클래스 정보의 경우 크게 세가지 방법으로 아래와 같은 방법이 있다

1. 클래스 타입을 직접 접근
2. 만들어진 인스턴스로부터 클래스 타입에 접근
3. FQCN을 통해 클래스 타입

```
Class<Book> bookClass=Book.class; //클래스 타입을 통해 직접 접근

Book book=new Book(); // 만들어진 인스턴스로 부터
Class<?extends Book> aClass=book.getClass(); // 클래스 타입 접근

try{
	Class<?> aClass1=Class.forName("study.reflectionapi.Book"); // FQCN을 통해 클래스 타입 접근
}catch(ClassNotFoundException e){
	e.printStackTrace();
}
```

클래스 타입 `Class<T>`으로 알 수 있는 정보는 대략적으로

해당 클래스의 필드 목록, 메소드 목록, 상위 클래스 정보, 인터페이스 목록 등이 있다.

# 참고

https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html

---

# 애노테이션과 리플렉션

애노테이션 그 자체로는 주석과 같이 아무런 영향을 끼치지 않음

중요 애노테이션

* `@Retention` : 해당 애노테이션을 언제까지 유지할 것인가?
    * 소스 `RetentionPolicy.SOURCE`
    * 클래스 `RetentionPolicy.CLASS`
    * 런타임 `RetentionPolicy.RUNTIME`
* `@Inherit` : 해당 애노테이션을 하위 클래스까지 전달할 것인가?
* `@Target` : 어디에 사용할 수 있는가?

리플렉션

* `getAnnotations()` : 상속받은 (`@Inherit`) 애노테이션까지 조회
* `getDeclaredAnnotations()` : 자기 자신에만 붙어있는 애노테이션 조회

---

# 리플렉션 API : 클래스 정보 수정 또는 실행

Class 인스턴스 만들기

* `Class.newInstance()`는 `deprecated` 됐으며 이제부터는
* 생성자를 통해서 만들어야 한다.

생성자로 인스턴스 만들기

* `Constructor.newInstance(params)`

```java
public class Application {

	public static void main(String[] args) {
		Class<?> bookClass = Class.forName("Book"); // FQCN

		Constructor<?> constructor = bookClass.getConstructor(null); // 생성자 파라미터
		Book book = (Book) constructor.newInstance(); // 캐스팅을 통해 변경가능
	}
}
```

필드 값 접근하기/설정하기

* 특정 인스턴스가 가지고 있는 값을 가져오는 것이기 때문에 인스턴스가 필요하다.
* `Field.get(object)`
* `Filed.set(object, value)`
* `Static` 필드를 가져올 때는 `object`가 없어도 되니까 `null`을 넘기면 된다.

메소드 실행하기

* `Object Method.invoke(object, params)`