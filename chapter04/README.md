# 다이나믹 프록시

## 스프링 데이터 JPA는 어떻게 동작하나?

> 스프링 데이터 JPA에서 인터페이스 타입의 인스턴스는 누가 만들어 주는것인가?

스프링 데이터 JPA에서 DB와 데이터를 주고받는 `Repository` 인터페이스가 있습니다.

여기서 신기한점은 이 리포지토리는  
따로 애노테이션을 통해 `Dependency Injection`이 되는것도 아닌 것 같고,  
구현체 정의를 한적도 없는데 `JpaRepository`를 상속받기만하면  
해당 타입의 객체도 만들어지고 해당 타입의 빈(`bean`)도 등록이 됩니다.

과연 스프링 데이터 JPA에서 인터페이스 타입의 인스턴스는 누가 만들어주는 것일까?  
`JpaRepository` 역시 인터페이스이고 사용하게 되는 기능(메서드)들도 정의만 되어있는데  
실제로 동작을 하는걸 볼 수 있습니다.

### Proxy

스프링 데이터 JPA 에서는 스프링 AOP를 쓰며

스프링 AOP에서는 프록시 패턴을 추상화한 `ProxyFactory`를 제공하고 있다

이 `ProxyFactory`를 통해, 다이나믹 프록시의 핵심 객체인 프록시 객체를 만들어낸다.

스프링 데이터 JPA에서는 `RepositoryFactorySupport`에서 사용되고 있다.

## 프록시 패턴

> 어떤 객체에 대한 접근을 제어하는 용도로 대리인이나  
> 대변인에 해당하는 객체를 제공하는 패턴
>
> 다른 객체에 대한 접근 제어나 가상 객체로 실제 처리시에만 처리 가능하게 하는 패턴

### 참고

* https://www.oodesign.com/proxy-pattern.html
* https://en.wikipedia.org/wiki/Proxy_pattern
* https://en.wikipedia.org/wiki/Single_responsibility_principle