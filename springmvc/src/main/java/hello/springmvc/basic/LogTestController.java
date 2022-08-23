package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //logger 코드 자동으로 넣어주는 어노테이션
@RestController //restAPI의 그 rest. @responseBody + @Controller 기능
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass()); //getClass 대신 LogTestController.class 해도 됨

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";
        System.out.println("name = " + name); //쓰면 안됨. 운영로고에 다 남음

        log.info(" info log = {}", name);// 만약 name2, name3, ...있을 경우 그냥 name, name2, name3, ..쭉쭉 출력 가능

        //운영로고에 중요한 부분만 남길거야. 할 때 사용.
        //log.trace("trace my log="+name); >> string 연산이 일어남. 잘못된 log 표현 사용!
        log.trace("trace log = {}",name);
        log.debug("debug log = {}",name);
        log.info("info log = {}",name);
        log.warn("warn log = {}",name);
        log.error("error log = {}",name);

        return "ok"; //body에 해당 text를 그대로 넣어줌
    }

}
