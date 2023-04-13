package eu.jitpay.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("api/helth")
    public String healthCheck(){
        return "work successfully";
    }
}
