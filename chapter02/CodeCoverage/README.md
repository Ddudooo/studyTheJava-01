# 코드 커버리지는 어떻게 측정할까?

커버리지 측정을 위해 빌드 툴에서 측정 플러그인 설정을 추가한다.

`Jacoco` 플러그인을 사용하여 커버리지를 측정하였다.

[Jacoco 홈페이지](https://www.jacoco.org/jacoco/trunk/index.html)

[Jacoco Gradle Plugin](https://docs.gradle.org/current/userguide/jacoco_plugin.html)

```groovy
        plugins {
    id 'jacoco'
}
```

## 커버리지 측정을 통한 빌드 실패 설정

자세한 내용은 `Jacoco` 플러그인 설정 법을 참고하자.

```groovy
// 커버리지 검증 수준 정의 설정
tasks.jacocoTestCoverageVerification {
    violationRules {
        // rule 은 여러개 사용 가능
        rule {
            enabled = true    // 사용 여부
            element = 'CLASS' // 측정 단위

            limit { // 룰의 상세 설정
                counter = 'LINE' // 커버리 측정의 최소 단위
                value = 'COVERAGERATIO' // 측정한 단위의 표기 방식
                minimum = 0.50 // 커버리지의 최소값
            }

            excludes = [] // 커버리지 측정 제외 범위
        }
    }
}
```

## 코드 커버리지란?

소프트웨어의 테스트를 논할 때 얼마나 테스트가 충분한가를 나타내는 지표중 하나.

소프트웨어 테스트를 진행했을 때 코드 자체가 얼마나 실행되었는지 숫자로 볼 수 있다.

## 어떻게 측정할까?

논문 등으로 자세한 측정방법을 알아볼 수 있으나...

내용 자체가 방대하여 간략하게 요약하자면

자바코드에서 코드 커버리지 측정은

바이트 코드를 라인별로 분석하여  
실제 코드와 매칭후 테스트 코드를 실행하여 해당 라인이 실행되는지 검사한다.

## 참고

http://www.semdesigns.com/Company/Publications/TestCoverage.pdf