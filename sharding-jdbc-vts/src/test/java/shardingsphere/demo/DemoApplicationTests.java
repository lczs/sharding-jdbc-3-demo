package shardingsphere.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shardingsphere.demo.entity.Order;
import shardingsphere.demo.mapper.OrderMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void add() {
        Order order = new Order();
        for (int i =110; i <=112; i++) {
            for (int j = 110; j <= 112; j++) {
                order.setUserId(i);
                order.setOrderId(j);
                orderMapper.insert(order);
            }
        }
    }


    @Test
    public void getByMultiId(){
        Map<String,Integer> param=new HashMap<>(2);
        param.put("userId",231);
        param.put("orderId",23);
        List<Order> byMultiId = orderMapper.findByUserId(param.get("userId"));
        for (Order order : byMultiId) {
            System.out.println(order);
        }
    }



}
