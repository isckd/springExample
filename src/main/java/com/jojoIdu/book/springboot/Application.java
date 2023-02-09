package com.jojoIdu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @SpringBootApplication -> 스프링부트의 자동 설정, Bean 읽기와생성을 모두 자동으로 설정함.
// 프로젝트의 최상단에 위치해야만 한다.
@EnableJpaAuditing  // JPA Auditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // SpringApplication.run 으로 내장 WAS를 실행한다.
        // 언제 어디서나 같은 환경에서 Spring boot를 배포할 수 있기 때문에 WAS 사용을 권장한다.
        SpringApplication.run(Application.class, args);
    }

}
