package be.thomasmore.microservice.products.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "product")
@Getter
@Setter
public class Product {
    @Id
    private String productID;
    private String name;
    private String brand;
    private String shortBrand;
    private String brewery;
    private String shortBrewery;
    private String percentage;
    private double price;
}
