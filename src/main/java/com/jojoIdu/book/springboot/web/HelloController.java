package com.jojoIdu.book.springboot.web;
import com.jojoIdu.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// RestController : 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 줌.
// 예전의 @ResponseBody를 각 메소드마다 선언했던 것을 한번에 사용할 수 있게 해줌.
@RestController
public class HelloController {
    // @GetMapping : Get HTTP Method를 사용할 수 있게 해줌.
    // 예전의 @RequestMapping을 대체함.
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount")int amount) {
        return new HelloResponseDto(name, amount);
    }
}
