package mall.domain;

import mall.domain.*;
import mall.infra.AbstractEvent;
import java.util.Date;
import lombok.Data;

@Data
public class OrderCancelled extends AbstractEvent {

    private Long id;
    private String deliveryId;
    private Long productId;
    private Integer qty;
    private String deliveryStatus;

    public OrderCancelled(){
        super();
    }
}
