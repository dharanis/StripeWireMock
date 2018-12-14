package com.example.stripe.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class StripeController {

    @PostMapping("/create")
    public void createStripeCall(){
        RestTemplate rest= new RestTemplate();
        rest.getForObject("https://api.stripe.com",String.class);
    }

}
