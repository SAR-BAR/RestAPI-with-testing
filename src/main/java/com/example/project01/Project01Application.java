package com.example.project01;

//import com.example.project01.authorization.authorizationFilter;
import com.example.project01.authorization.MyApiClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class Project01Application {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        ConfigurableApplicationContext context =SpringApplication.run(Project01Application.class, args);
        //get the instance of myappclient bean from the application context
        MyApiClient myApiClient = context.getBean(MyApiClient.class);

        //call the method
        myApiClient.makeAPICall();
        //close the context
        context.close();





    }


}
