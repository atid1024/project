오더:

DeliveryService.java:                     

@FeignClient(name="delivery", url="${api.path.delivery}")
public interface DeliveryService {
    @RequestMapping(method= RequestMethod.DELETE, path="/deliveries/{id}")
    public void cancel(@PathVariable String id);

}

PolicyHandler.java:      
       
        java.util.Optional<Order> optionalOrder = orderRepository.findById(deliveryStarted.getOrderId());

        Order order = optionalOrder.get();
        order.setDeliveryId(String.valueOf(deliveryStarted.getId()));
        order.setDeliveryStatus(deliveryStarted.getDeliveryStatus());
        orderRepository.save(order);



Order.java:
                       
@PreRemove
    public void onPreRemove(){
        OrderCancelled orderCancelled = new OrderCancelled();
        BeanUtils.copyProperties(this, orderCancelled);
        orderCancelled.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        mall.external.Delivery delivery = new mall.external.Delivery();
        // mappings goes here
        OrderApplication.applicationContext.getBean(mall.external.DeliveryService.class)
            .cancel(this.getDeliveryId());

    }
-----------------------------------------------------------------------------------------
딜리버리:
PolicyHandler.java:                        
		Delivery delivery = new Delivery();

        delivery.setOrderId(orderPlaced.getId());
        delivery.setProductId(orderPlaced.getProductId());
        delivery.setProductName(orderPlaced.getProductName());
        delivery.setDeliveryStatus("DeliveryStarted");
        deliveryRepository.save(delivery);

-----------------------------------------------------------------------------------------
명령어 모음
주문:
http http://localhost:8081/orders productId=2 quantity=3 customerId=1@uengine.org

확인:
http :8081/orders
http :8082/deliveries

주문취소:
http DELETE :8081/orders/1
----------------------------------------------------------------------------------------
연계부분 

order Application.yaml 호출 URL 확인 (delivery 서비스)
----------------------------------------------------------------------------------------
이벤트 스토밍 URL
https://labs.msaez.io/#/storming/mtdpaYOxHdOxP12lHHATtHiDjU73/4e6a31bddc3d5a9dd0fc56caef48ebaa