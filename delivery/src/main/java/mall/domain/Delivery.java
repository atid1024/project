package mall.domain;

import mall.domain.DeliveryStarted;
import mall.domain.DeliveryCancelled;
import mall.DeliveryApplication;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Delivery_table")
@Data

public class Delivery  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    private Long id;
    
    
    private Long orderId;
    
    
    private Long productId;
    
    
    private String productName;
    
    
    private String deliveryStatus;

    @PostPersist
    public void onPostPersist(){
        DeliveryStarted deliveryStarted = new DeliveryStarted();
        BeanUtils.copyProperties(this, deliveryStarted);
        deliveryStarted.publishAfterCommit();

        // DeliveryCancelled deliveryCancelled = new DeliveryCancelled();
        // BeanUtils.copyProperties(this, deliveryCancelled);
        // deliveryCancelled.publishAfterCommit();

    }
    @PreRemove
    public void onPreRemove(){
    }


    public static DeliveryRepository repository(){
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(DeliveryRepository.class);
        return deliveryRepository;
    }


    public static void start(OrderPlaced orderPlaced){

        Delivery delivery = new Delivery();
        /*
        LOGIC GOES HERE
        */
        // repository().save(delivery);


    }


}
