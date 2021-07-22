package study.reflectionapi;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Application {

	public static void main(String[] args)
		throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
		Class<?> bookClass = Class.forName("study.reflectionapi.Book");
		//bookClass.newInstance();
		//Constructor<?> constructor = bookClass.getConstructor(null);
		Constructor<?> constructor = bookClass.getConstructor(String.class);
		Book book = (Book) constructor.newInstance("mybook");
		System.out.println(book);

		Field a = Book.class.getDeclaredField("A");
		System.out.println(a.get(null));
		a.set(null, "AAA");
		System.out.println(a.get(null));

		Field B = Book.class.getDeclaredField("B");
		B.setAccessible(true);
		System.out.println(B.get(book));
		B.set(book, "BBB");
		System.out.println(B.get(book));

		Method c = Book.class.getDeclaredMethod("c");
		c.setAccessible(true);
		c.invoke(book);

		Method d = Book.class.getDeclaredMethod("sum", int.class, int.class);

		int invoke = (int) d.invoke(book, 1, 2);
		System.out.println(invoke);
	}
}