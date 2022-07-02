package mall.domain;

import mall.domain.*;
import mall.infra.AbstractEvent;
import java.util.Date;
import lombok.Data;

@Data
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private Long productId;
    private Integer qty;
    private String deliveryId;
    private String deliveryStatus;
    private String productName;


    public OrderPlaced(){
        super();
    }
}
