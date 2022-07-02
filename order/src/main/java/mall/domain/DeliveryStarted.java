package mall.domain;

import mall.domain.*;
import mall.infra.AbstractEvent;
import lombok.Data;
import java.util.Date;
@Data
public class DeliveryStarted extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long productId;
    private String productName;
    private String deliveryStatus;

}

