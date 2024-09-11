package com.mycompany.custdatpkg.cucumber;

import com.mycompany.custdatpkg.CustdatmicroApp;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = CustdatmicroApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
