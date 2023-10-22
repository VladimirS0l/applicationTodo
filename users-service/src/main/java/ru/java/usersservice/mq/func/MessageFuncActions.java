package ru.java.usersservice.mq.func;

import lombok.Getter;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
@Getter
public class MessageFuncActions {
    private MessageFunc messageFunc;

    public MessageFuncActions(MessageFunc streamFunctions) {
        this.messageFunc = streamFunctions;
    }

    public void sendNewUserMessage(Long id) {
        messageFunc.getInnerBus().emitNext(MessageBuilder.withPayload(id).build(), Sinks.EmitFailureHandler.FAIL_FAST);
        System.out.println("Message sent: " + id);
    }

}
