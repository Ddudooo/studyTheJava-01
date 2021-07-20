# Javaagent 실습

클래스 로딩당시에 변경된 바이트 코드를 읽어 들이는 방식

javaagent jar를 통해 바이트 코드를 조작해보자

Javaagent는 `premain` 과 `agentmain` 방식이 있음

`instrumentation`을 사용

```java
package study;

import static net.bytebuddy.implementation.FixedValue.value;
import static net.bytebuddy.matcher.ElementMatchers.any;
import static net.bytebuddy.matcher.ElementMatchers.named;

import java.lang.instrument.Instrumentation;
import net.bytebuddy.agent.builder.AgentBuilder;

public class JavaAgent {

	public static void premain(String agentArgs, Instrumentation inst) {
		System.out.println("토끼 넣기!");
		new AgentBuilder.Default()
			.type(any())
			.transform(
				((builder, typeDescription, classLoader, module) ->
					builder.method(named("pullOut"))
						.intercept(value("Rabbit!"))))
			.installOn(inst);
	}
}
```

앞선 예제에서 바이트 코드를 수정했던 ByteBuddy API를 활용하여 구현

## MANIFEST 작성

Gradle 설정을 통해 `MANIFEST` 파일을 `jar`패키징시에 넣어준다.

```groovy
jar {
    manifest {
        attributes 'Premain-Class': 'study.JavaAgent'
        attributes 'Can-Redefine-Classes': true
        attributes 'Can-Retransform-Classes': true
    }
}
```

## 실행

실행전에 VM OPTIONS 으로 `jar` 패키징된 `javaagent`경로를 넣어주자

`-javaagent:패키징된경로.jar`

```java
package study;

public class Application {

	public static void main(String[] args) {

		System.out.println(new Hat().pullOut());
	}
}
```

```shell
토끼 넣기!
Rabbit!
```

---

## 참고

https://docs.oracle.com/javase/8/docs/api/java/lang/instrument/package-summary.html