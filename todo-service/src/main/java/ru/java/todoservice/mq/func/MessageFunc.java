package ru.java.todoservice.mq.func;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import ru.java.todoservice.service.TestDataService;

import java.util.function.Consumer;

@Configuration
public class MessageFunc {
    // для заполнения тестовых данных
    private TestDataService testDataService;

    public MessageFunc(TestDataService testDataService) {
        this.testDataService = testDataService;
    }

    @Bean
    public Consumer<Message<String>> newUserActionConsume() {
        return message -> testDataService.initTestData(message.getPayload());
    }

}
