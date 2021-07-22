package study.demo.di;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {

	public static <T> T getObject(Class<T> classType) {
		T instance = createInstance(classType);
		Arrays.stream(classType.getDeclaredFields())
			.filter(field -> field.getAnnotation(Inject.class) != null)
			.forEach(field -> {
				Class<?> type = field.getType();
				Object fieldInstance = createInstance(type);
				field.setAccessible(true);
				try {
					field.set(instance, fieldInstance);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			});
		return instance;
	}

	private static <T> T createInstance(Class<T> classType) {
		try {
			return classType.getConstructor(null).newInstance();
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}
}