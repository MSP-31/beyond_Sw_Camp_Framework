package com.beyond.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    어노테이션
       - JDK 1.5부터 추가된 기능으로 자바 코드에 추가적인 정보를 제공하는 메타 데터이다.
       - 비즈니스 로직에 영향을 주지 않지만 컴파일 과정에서 유효성 체크, 코드를 어떻게 컴파일하고
         처리할지 알려주는 정보를 제공한다.
       - 어노테이션은 클래스, 메소드, 필드, 매개변수 등에 추가할 수 있다.
 */

// 어노테이션을 적용할 위치를 지정한다. (아래는 메소드와 필드에만 지정함)
@Target({ElementType.METHOD,ElementType.TYPE})
/*
    @Retention
    어노테이션의 유효 범위를 지정할 때 사용한다.
    예를 들어서 @Override 는 컴파일 할때만 유효하기에 컴파일 이후에는 지워진다.
    @Component 의 경우 컴파일 이후에도 유효하기에 남아있게 된다.
    마치 dependency 의 scope 와 같다고 보면 된다.
 */
// @Retention(RetentionPolicy.SOURCE) // 코드상에서만 유효함
// @Retention(RetentionPolicy.CLASS) // 클래스를 참조할 때까지 유효하다.
@Retention(RetentionPolicy.RUNTIME) // JVM에 의해서 참조가 가능함

// 부모 클래스에서 어노테이션을 선언하면 자식 클래스에도 상속된다.
// @Inherited

public @interface NoLogging {
}
