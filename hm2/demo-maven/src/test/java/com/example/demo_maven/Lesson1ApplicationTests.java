package com.example.demo_maven;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo_maven.service.HelloWorldService;
import com.example.demo_maven.web.SampleController;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class Lesson1ApplicationTests {

    public Lesson1ApplicationTests() {
        service = new HelloWorldService();
        controller = new SampleController(service);
    }
    private final SampleController controller;
    private final HelloWorldService service;

    @Autowired
    private SampleController contextController;
    @Test
    public void contextLoads() {
        assertThat(contextController)
                .as("Контроллер должен быть доступен из контекста спринга")
                .isNotNull();
    }

    @Test
    void controllerTest() {
        assertThat(controller.helloWorld())
                .as("значение контроллера и сервиса должны совпадать")
                .isEqualTo(service.getHelloMessage());
    }

    @Test
    void serviceTest() {
        assertThat(service.getHelloMessage())
                .as("Сервис должен вернуть ожидаемую строку")
                .isEqualTo("Мы использовали два сборщика: Maven и Gradle. " +
                        "Maven показался проще для данной задачи, так как он предоставляет более детализированную документацию " +
                        "и интеграцию с различными IDE, включая IntelliJ IDEA. " +
                        "Его конфигурационные файлы XML легче читаются и модифицируются. ");
    }

}