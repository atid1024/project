package mall.external;

import lombok.Data;
import java.util.Date;
@Data
public class Delivery {

    private Long id;
    private Long orderId;
    private Long productId;
    private String productName;
    private String deliveryStatus;


}
