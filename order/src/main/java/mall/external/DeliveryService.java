package mall.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="delivery", url="${api.path.delivery}")
public interface DeliveryService {
    @RequestMapping(method= RequestMethod.DELETE, path="/deliveries/{id}")
    public void cancel(@PathVariable String id);

}