package wind.yang.tctdemosub.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
public class MessageListener {
    CountDownLatch latch = new CountDownLatch(3);

    @KafkaListener(topics = "${message.topic.name}", groupId="foo", containerFactory = "fooKafkaListenerContainerFactory")
    public void listenGroupFoo(String message){
        log.info("Received Message in group 'foo' : {}", message);
        latch.countDown();
    }

    @KafkaListener(topics = "${message.topic.name}", groupId="bar", containerFactory = "barKafkaListenerContainerFactory")
    public void listenGroupBar(String message){
        log.info("Received Message in group 'bar' : {}", message);
        latch.countDown();
    }

}
