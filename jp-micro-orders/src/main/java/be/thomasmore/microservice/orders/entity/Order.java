package be.thomasmore.microservice.orders.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "order")
@Getter
@Setter
public class Order {

    @Id
    private String  orderID;
    private String productName;
    private Double price;
    private int amount;
    private int userID;
}
