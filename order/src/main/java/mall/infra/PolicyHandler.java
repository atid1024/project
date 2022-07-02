package mall.infra;

import javax.naming.NameParser;

import javax.naming.NameParser;

import mall.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import mall.domain.*;


@Service
public class PolicyHandler{
    @Autowired OrderRepository orderRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeliveryStarted_UpdateStatus(@Payload DeliveryStarted deliveryStarted){

        if(!deliveryStarted.validate()) return;
         DeliveryStarted event = deliveryStarted;
         
        System.out.println("\n\n##### listener UpdateStatus : " + deliveryStarted.toJson() + "\n\n");
        
/*
        // Sample Logic //
        Order.updateStatus(event); */

        java.util.Optional<Order> optionalOrder = orderRepository.findById(event.getOrderId());

        Order order = optionalOrder.get();
        order.setDeliveryId(String.valueOf(event.getId()));
        order.setDeliveryStatus(event.getDeliveryStatus());
        orderRepository.save(order);

        

        

    }


}


