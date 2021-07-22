package study.reflectionapi;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Application {

	public static void main(String[] args) {
		System.out.println("Hello, world!");
		Class<Book> bookClass = Book.class;

		Book book = new Book();
		Class<? extends Book> aClass = book.getClass();

		try {
			Class<?> aClass1 = Class.forName("study.reflectionapi.Book");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Field[] fields = bookClass.getDeclaredFields();
		System.out.println("===================필드 출력===================");
		Arrays.stream(fields)
			//.forEach(System.out::println);
			.forEach(field -> {
				try {
					int modifiers = field.getModifiers();
					System.out.printf("%s is private %b\n", field, Modifier.isPrivate(modifiers));
					System.out.printf("%s is modifier %b\n", field, Modifier.isStatic(modifiers));
					field.setAccessible(true);
					System.out.printf("%s %s\n", field, field.get(book));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			});

		Method[] methods = bookClass.getMethods();
		System.out.println("==================메소드 출력===================");
		Arrays.stream(methods)
			//.forEach(System.out::println);
			.forEach(method -> {
				System.out
					.printf("\t================== [%s] METHOD INFO ===================\n", method);
				int modifiers = method.getModifiers();
				System.out.printf("\t%s is static %b\n", method, Modifier.isStatic(modifiers));
				System.out.printf("\t%s is private %b\n", method, Modifier.isPrivate(modifiers));
				System.out.printf("\t%s is final %b\n", method, Modifier.isFinal(modifiers));
				System.out.printf("\t%s is public %b\n", method, Modifier.isPublic(modifiers));
				System.out
					.printf("\t%s is interface %b\n", method, Modifier.isInterface(modifiers));
				System.out
					.printf("\t================== [%s] METHOD INFO ===================\n", method);
			});

		Constructor<?>[] constructors = bookClass.getConstructors();
		System.out.println("==================생성자 출력===================");
		Arrays.stream(constructors)
			.forEach(System.out::println);
		System.out.println("===================부모 출력===================");
		System.out.println(MyBook.class.getSuperclass());
		System.out.println("==================인터페이스 출력=================");
		Arrays.stream(MyBook.class.getInterfaces())
			.forEach(System.out::println);

		System.out.println("==================어노테이션 출력=================");
		Arrays.stream(Book.class.getAnnotations())
			.forEach(System.out::println);

		System.out.println("==================어노테이션 출력=================");
		Arrays.stream(MyBook.class.getAnnotations())
			.forEach(System.out::println);

		System.out.println("==================어노테이션 출력=================");
		Arrays.stream(MyBook.class.getDeclaredAnnotations())
			.forEach(System.out::println);
	}
}