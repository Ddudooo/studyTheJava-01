# 어노테이션 프로세서 1부

로컬 환경에서 빌드한 어노테이션 프로세스 사용을 위해 `build.gradle`에 추가

```groovy
repositories {
    mavenLocal()
}

dependencies {
    implementation 'study:magic-annotation:latest'
}
```