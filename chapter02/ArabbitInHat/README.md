# 모자에서 토끼를 꺼내는 마술

바이트 코드 조작 라이브러리를 활용해서 구현

## 자바 바이트 코드 조작 라이브러리

* [ASM](https://asm.ow2.io/)
    * `Visitor`, `Adapter` 패턴을 알고 있어야 사용해볼 수 있음.
* [Javassist](https://www.javassist.org/)
* [ByteBuddy](https://bytebuddy.net/)

## 바이트 코드 조작

`ByteBuddy` API를 통해 컴파일된 `.class` 파일을 조작해보자

```
try{
  new ByteBuddy()
	.redefine(Hat.class)
	.method(named("pullOut"))
	.intercept(value("Rabbit!"))
	.make()
	.saveIn(new File("클래스 폴더 경로"));
}catch(IOException e){
  e.printStackTrace();
}
```

### 원본 파일

```java
package study;

public class Hat {

	public String pullOut() {
		return "";
	}
}
```

### 컴파일된 class 파일

```java
package study;

public class Hat {

	public Hat() {
	}

	public String pullOut() {
		return "Rabbit!";
	}
}

```

### 결과

```java
package study;

public class Application {

	public static void main(String[] args) {
		System.out.println(new Hat().pullOut());
	}
}
```

```shell
Rabbit!
```

바이트 코드 조작을 통해 빈 모자에서 토끼를 꺼내었다!

---

### ByteBuddy Gradle 설정

* [Byte Buddy Gradle Plugin](https://github.com/raphw/byte-buddy/tree/master/byte-buddy-gradle-plugin)
