package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // 롬복이 자동으로 해줌.
@RestController // RESTAPI만들떄 핵심적인 컨트롤러
public class LogTestController {

    // @Slf4j 선언시 밑에 코드 없이 바로 사용가능.
    // private final Logger log = LoggerFactory.getLogger(getClass());


    @RequestMapping("/log-test")
    public String logTest(){
        // 단순 시스템 콘솔 출력
        String name = "String";
        System.out.println("name = " + name);

        //올바른 로그 사용법
        // log.trace("trace log=" + name);
        // -> 만약 log레벨이 debug인데 위처럼 사용할 경우 자바문법상 trace+name이라는 연산이 일어난 후 가지고 있게된다.
        // 즉 연산이 일어나게된다 (log레벨에 포함이 안되어 무시할테지만 이미 연산자체가 일어남)
        // 밑에 방식처럼 그냥 파라미터로 받을 수 있게 해놓고 log레벨이 포함되면 출력하고 아니면 무시하고 하도록 한다.

        // 로그 라이브러리 이용한 출력
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        //@RestController
        //@Controller 는 반환 값이 String 이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 랜더링 된다.
        //@RestController 는 반환 값으로 뷰를 찾는 것이 아니라, HTTP 메시지 바디에 바로 입력한다.
        //따라서 실행 결과로 ok 메세지를 받을 수 있다. @ResponseBody 와 관련이 있는데, 뒤에서 더 자세히 설명한다.
        return "ok";
    }
}
