// 이 파일이 들어 있는 패키지(폴더) 이름. 서버를 켜는 InquiryAutomationApplication과 같은 패키지여야
// Spring이 이 클래스를 자동으로 찾아요.
package com.hanbit.inquiry;

// 아래에서 쓸 이름표(어노테이션) 2개를 Spring에서 불러오기 (Python의 import와 같음)
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController: "이 클래스는 웹 요청을 받아서 대답하는 창구다"라고 Spring에 알리는 이름표.
// 이 이름표 덕분에 서버가 켜질 때 Spring이 이 클래스를 자동으로 등록해요.
@RestController
public class HelloController {

    // @GetMapping("/hello"): 누군가 http://localhost:8080/hello 로 GET 요청을 보내면
    // 바로 아래 hello() 메서드를 실행하라는 뜻 ("/hello 담당 창구")
    @GetMapping("/hello")
    public String hello() {                              // 돌려줄 값이 글자(String)라서 반환 타입이 String
        return "안녕하세요, 한빛상사 문의 자동화 API입니다";   // 이 글자가 요청한 사람(브라우저)에게 그대로 전달됨
    }
}