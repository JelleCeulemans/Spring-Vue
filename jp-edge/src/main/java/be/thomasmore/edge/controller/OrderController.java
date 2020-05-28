package be.thomasmore.edge.controller;

import be.thomasmore.edge.model.GenericResponseWrapper;
import be.thomasmore.edge.model.Order;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;


    //http://localhost:8050/orders/all
    //@Secured({"ROLE_CUSTOMER"})
    @GetMapping("all")
    @ApiOperation(value = "Lists all of the orders", response = Order.class, responseContainer = "List")
    public List<Order> getAll() {
        GenericResponseWrapper wrapper = restTemplate.getForObject("http://jp-micro-orders/orders/", GenericResponseWrapper.class);
        List<Order> orders = objectMapper.convertValue(wrapper.get_embedded().get("orders"),  new TypeReference<List<Order>>() { });

        return orders;
    }

    //http://localhost:8050/orders/order/1
    //@Secured({"ROLE_CUSTOMER"})
    @GetMapping("order/{userID}")
    @ApiOperation(value = "Gets all of the orders of a certain user", response = Order.class, responseContainer = "list")
    public List<Order> getOrdersByUserId(@ApiParam(value = "The id of the user whose orders we want to know", required = true) @PathVariable("userID") Integer userID) {
        GenericResponseWrapper wrapper =
                restTemplate.getForObject("http://jp-micro-orders/orders/search/findOrdersByUserID?userID=" + userID,
                        GenericResponseWrapper.class);
        List<Order> orders = objectMapper.convertValue(wrapper.get_embedded().get("orders"),  new TypeReference<List<Order>>() { });

        return orders;
    }

    //http://localhost:8050/orders/order
    @PostMapping("order")
    //@Secured({"ROLE_CUSTOMER"})
    @ApiOperation(value = "Adding a new order")
    public ResponseEntity<String> postOrder(@ApiParam(value = "The Order object that has to be added", required = true) @RequestBody Order order) {
        restTemplate.postForEntity("http://jp-micro-orders/orders/", order, String.class);
        return ResponseEntity.ok().build();
    }


    //http://localhost:8050/orders/order
    @PutMapping("order")
    //@Secured({"ROLE_CUSTOMER"})
    @ApiOperation(value = "Updating an order")
    public ResponseEntity<String> putOrder(@ApiParam(value = "The updated Order object", required = true) @RequestBody Order order) {
        restTemplate.put("http://jp-micro-orders/orders/" + order.getOrderID(), order, String.class);
        return ResponseEntity.ok().build();
    }

    //http://localhost:8050/orders/order
    @DeleteMapping("order/{orderID}")
    //@Secured({"ROLE_CUSTOMER"})
    @ApiOperation(value = "Deleting a certain Order")
    public ResponseEntity deleteOrder(@ApiParam(value = "The id of the Order object that has to be deleted") @PathVariable("orderID") String orderID) {
        restTemplate.delete("http://jp-micro-orders/orders/" + orderID);
        return ResponseEntity.ok().build();
    }
}
