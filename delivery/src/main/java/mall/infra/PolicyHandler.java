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
    @Autowired DeliveryRepository deliveryRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderPlaced_Start(@Payload OrderPlaced orderPlaced){

        if(!orderPlaced.validate()) return;
        OrderPlaced event = orderPlaced;
        System.out.println("\n\n##### listener Start : " + orderPlaced.toJson() + "\n\n");

/*
        // Sample Logic //
        Delivery.start(event); */


        Delivery delivery = new Delivery();

        delivery.setOrderId(event.getId());
        delivery.setProductId(event.getProductId());
        delivery.setProductName(event.getProductName());
        delivery.setDeliveryStatus("DeliveryStarted");


        System.out.println("\n\n##### listener End : " + delivery + "\n\n");

        deliveryRepository.save(delivery);
    }


}


