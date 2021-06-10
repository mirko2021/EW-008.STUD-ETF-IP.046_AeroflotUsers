package yatospace.server.services.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * REST Spring Services Example
 * @author VM
 * @version 1.0
 */
@Controller
public class HelloController {
     @RequestMapping(value = "/spring/hello", method = RequestMethod.POST)
     public String get(ModelMap model) {
         return "greeting";
     }
}
