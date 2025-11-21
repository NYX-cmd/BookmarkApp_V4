package az.bookmark;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServletInitializer {
    @GetMapping("/")
    @ResponseBody
    
    public String sayHello() {
        return "ハロー！Spring Boot からのご挨拶です！";
    }
}