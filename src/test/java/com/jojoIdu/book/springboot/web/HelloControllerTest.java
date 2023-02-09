package com.jojoIdu.book.springboot.web;
// 일반적으로 테스트코드는 test 폴더 하위에 기존 작성한 패키지와 동일하게 구성하고,
// 클래스명 같은 경우는 뒤에 Test를 붙인다.


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/* @RunWith(SpringRunner.class) : 테스트 진행 시 JUnit에 내장된 실행자 외에 다른 실행자 실행.
여기서는 SpringRunner라는 스프링 실행자를 사용.*/
/* @WebMvcTest : 여러 스프링 테스트 어노테이션 중,Web(Spring MVC)에 집중할 수 있는 어노테이션.
선언할 경우, @Controller, @ControllerAdvice 등을 사용할 수 있으나,
@Service, @Component, @Repository 등은 사용할 수 없음. 여기서는 컨트롤러만 사용하기 때문에 선언.
*/
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    // @Autowired : 스프링이 관리하는 빈(Bean)을 주입받는다.
    @Autowired
    private MockMvc mvc;  // 웹 API 테스트 시 사용. 스프링 MVC 테스트의 시작. HTTP, GET, POST 등에 대한 API 테스트 가능.

    @Test
    public void hello_가_리턴된다() throws Exception {
        String hello = "hello";
        // MockMvc를 통해 /hello 주소로 HTTP GET 요청, 체이닝이 지원되어 여러 검증 기능을 이어서 사용 가능
        mvc.perform(get("/hello"))
                // mvc.perform의 결과, HTTP Header의 Status를 검증. 흔히 알고 있는 200, 404, 500 등의 상태를 검증. 여기선 Ok, 즉 200인지 아닌지 검증
                .andExpect(status().isOk()).andExpect(content().string(hello)); //mvc.perform의 결과를 검증. 응답 본문의 내용을 검증.
        // Controller에서 'hello'를 리턴하기 때문에 이 값이 맞는지 검증
    }

    // API 테스트
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)    // param : API 테스트 시 사용될 요청 파라미터 설정. Stirng 만 가능.
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))    // JSON 응답값을 필드별로 검증할 수 있는 메서드
                .andExpect(jsonPath("$.amount", is(amount))); // $을 기준으로 필드명 명시
    }
}


