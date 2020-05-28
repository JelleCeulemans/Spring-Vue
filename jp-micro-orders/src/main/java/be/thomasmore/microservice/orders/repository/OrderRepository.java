package be.thomasmore.microservice.orders.repository;

import be.thomasmore.microservice.orders.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository  extends MongoRepository<Order, String> {
    List<Order> findOrdersByUserID(@Param("userID") Integer userID);
}
