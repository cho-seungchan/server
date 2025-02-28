package com.app.pickcourse.customerService.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer-service/*")
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    @GetMapping("/customer-service")
    public void getCustomerService() {}

}
