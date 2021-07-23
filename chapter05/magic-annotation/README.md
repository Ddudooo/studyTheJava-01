# 어노테이션 프로세서 1부

`manifest` 파일을 자동으로 생성하기 위해
`AutoService` 라이브러리 의존성을 추가하였다

```groovy
dependencies {
    implementation "com.google.auto.service:auto-service:1.0-rc6"
}
```

```java

@Target(ElementType.TYPE) // 어노테이션이 적용될 타입 지정
@Retention(RetentionPolicy.SOURCE) // 소스 레벨에서만 적용
public @interface Magic {

}
```

```java

@AutoService(Processor.class) // AutoService를 통해 지정한 어노테이션 프로세서
public class MagicHatInRabbit extends AbstractProcessor {

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		return Set.of(Magic.class.getName()); // 지원할 어노테이션
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported(); // 소스 지원 범위
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		// getSupportedAnnotationTypes 에 따라 Magic 어노테이션이 부착된 Elements 를 가져온다
		Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Magic.class);
		for (Element element : elements) {
			Name elementName = element.getSimpleName();

			if (element.getKind() != ElementKind.INTERFACE) {
				// 추가적으로 인터페이스가 아닐경우 컴파일 에러를 발생 시킴
				processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
					"Magic annotation can not be used on " + elementName);
			} else {
				processingEnv.getMessager()
					.printMessage(Diagnostic.Kind.NOTE, "Processing " + elementName);
			}
		}
		return true;
	}
}
```

로컬 환경에서 저장소를 통해 사용하기 위해 `build.gradle`에 추가

```groovy
plugins {
    id 'maven'
}
```