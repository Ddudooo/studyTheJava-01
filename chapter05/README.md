# 롬복은 어떻게 동작할까?

## 롬복이란?

`@Getter`, `@Setter`, `@Builder` 등의 애노테이션과 애노테이션 프로세서를 제공하여

표준적으로 사용하는 라이브러리

개발을 하면서 반복적이고 공통적인 코드작성을 애노테이션을 통해 자동화해주는 라이브러리

### 동작 원리

핵심은 __애노테이션 프로세서__

애노테이션 프로세서는 컴파일 시점에 끼어들어

특정한 애노테이션이 붙어있는 소스코드를 참조해서 새로운 소스코드를 만들어 낼 수 있는 기능

이 때 새로생성되는 소스코드는 자바일수도있고 다른 어떤 코드일수도 있다

애노테이션 프로세서는 애노테이션이 붙어있는 소스코드의 정보를 트리구조([`AST`](https://javaparser.org/inspecting-an-ast/)) 로 참조할 수
있다

이런 트리를 원래는 참조만 가능하고 조작은 불가능한데,  
실제 컴파일을 한 뒤 바이트코드를 보면 변경된 코드를 볼 수 있다.

기존 공개된 API를 보면 원래는 `RoundEnvironment`와 `TypeElement`만 써야한다.  
두 타입은 실제로 참조만 가능하다

### 논란 거리

* 공개된 API가 아닌 컴파일러 내부 클래스를 사용하여 기존 소스 코드를 조작한다.
* 특히 이클립스의 경우엔 `java agent`를 사용하여 컴파일러 클래스까지 조작하여 사용한다.  
  해당 클래스들 역시 공개된 API가 아니다보니  
  버전 호환성에 문제가 생길 수 있고 언제라도 그런 문제가 발생해도 이상하지 않다.
* 그럼에도 불구하고 엄청난 편리함 때문에 널리 쓰이고 있으며  
  대안이 몇가지 있지만 롬복의 모든 기능과 편의성을 대체하진 못하는 현실이다.
    * AutoValue
        * https://github.com/google/auto/blob/master/value/userguide/index.md
    * Immutables
        * https://immutables.github.io

### 참고

* https://docs.oracle.com/javase/8/docs/api/javax/annotation/processing/Processor.html
* https://projectlombok.org/contributing/lombok-execution-path
* https://stackoverflow.com/questions/36563807/can-i-add-a-method-to-a-class-from-a-compile-time-annotation
* http://jnb.ociweb.com/jnb/jnbJan2010.html#controversy
* https://www.oracle.com/technetwork/articles/grid/java-5-features-083037.html

---

# 애노테이션 프로세서 정리

## 애노테이션 프로세서 사용 예

* 롬복(Lombok)
* AutoService: java.util.ServiceLoader용 파일 생성 유틸리티
* @Override
* Dagger2: 컴파일 타임 DI제공
* 안드로이드 라이브러리
    * BUtterKinfe: @BindVIew (뷰 아이디와 애노테이션 붙인 필드 바인딩)
    * DeepLinkDispatch: 특정 URI 링크를 Activity로 연결할 때 사용

## 애노테이션 프로세서 장점

* 런타임 비용이 제로

## 애노테이션 프로세서 단점

* 기존 클래스 코드를 변경할 때는 약간의 hack이 필요하다(ex: Lombok)
