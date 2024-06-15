package com.example.demo_maven.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldService {

    public HelloWorldService() {

    }

    public String getHelloMessage() {
        return "Мы использовали два сборщика: Maven и Gradle. " +
                "Maven показался проще для данной задачи, так как он предоставляет более детализированную документацию " +
                "и интеграцию с различными IDE, включая IntelliJ IDEA. " +
                "Его конфигурационные файлы XML легче читаются и модифицируются. ";
    }

}