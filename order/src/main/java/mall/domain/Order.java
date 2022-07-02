package mall.domain;

import mall.domain.OrderPlaced;
import mall.domain.OrderCancelled;
import mall.OrderApplication;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Order_table")
@Data

public class Order  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    private Long id;
    
    
    private Long productId;
    
    
    private Integer qty;
    
    
    private String deliveryId;

    private String productName;
    
    private String deliveryStatus;

    @PostPersist
    public void onPostPersist(){
        OrderPlaced orderPlaced = new OrderPlaced();
        BeanUtils.copyProperties(this, orderPlaced);

        orderPlaced.publishAfterCommit();

    }
    @PreRemove
    public void onPreRemove(){
        OrderCancelled orderCancelled = new OrderCancelled();
        BeanUtils.copyProperties(this, orderCancelled);
        // orderCancelled.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        mall.external.Delivery delivery = new mall.external.Delivery();
        // mappings goes here
        OrderApplication.applicationContext.getBean(mall.external.DeliveryService.class)
            .cancel(this.getDeliveryId());

        orderCancelled.publishAfterCommit();


    }


    public static OrderRepository repository(){
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(OrderRepository.class);
        return orderRepository;
    }


    public static void updateStatus(DeliveryStarted deliveryStarted){

        Order order = new Order();
        /*
        LOGIC GOES HERE
        */
        // repository().save(order);


    }


}
