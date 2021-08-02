package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }

    // 현재 클래스레벨의 컨트를러가 @RestController가 아닌 @Controller이다.
    // 이경우 String으로 retrun시 뷰 리졸버가 "ok"라는 이름의 뷰를 찾게 된다.
    // @Responsebody를 해당 메서드레벨에 선언해주면 뷰 리졸버가 "ok"를 http 응답메시지에 넣어서 반환한다. = @RestController와 같은 효과

    @ResponseBody
    @RequestMapping("/request-param-v2")
    // 매개변수로 바로 받을 수 있다.
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) throws IOException {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    // HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="xx") 생략 가능
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) throws IOException {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    // String , int , Integer 등의 단순 타입이면 @RequestParam 도 생략 가능
    public String requestParamV4(String username, int age) throws IOException {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    // default = true
    // true - 없어도 된다, false = 없으면 안된다(에러)
    // null 을 int 에 입력하는 것은 불가능(500 예외 발생 -> 따라서 null 을 받을 수 있는 Integer 로 변경하거나, 또는 다음에 나오는 defaultValue 사용
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) throws IOException {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    // defaultValue를 사용하게 되면 required는 필요없다.
    // "" 빈문자도 알아서 기본값으로 보내준다.
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") Integer age) throws IOException {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) throws IOException {

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    // ModelAttribute 사용
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) throws IOException {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    // ModelAttribute 생략
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) throws IOException {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
