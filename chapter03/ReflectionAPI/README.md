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


---

# 참고

https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html