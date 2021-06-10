package yatospace.server.boot;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;


/**
 * Spring boot is Server. Use Tomcat configuration.  
 * @author VM
 * @version 1.0
 */
@RestController
@EnableAutoConfiguration
public class SpringWebBoot {
	@RequestMapping("/rest")
    String home() {
        return "Hello World!";
    }
	
    public static void main(String[] args) throws Exception {
    	SpringApplication.run(SpringWebBoot.class, args);
    }
}