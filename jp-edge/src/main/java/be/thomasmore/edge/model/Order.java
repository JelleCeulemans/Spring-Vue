package be.thomasmore.edge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "The details of an order.")
public class Order {
    @ApiModelProperty(notes = "Auto generated id of the order.")
    private String orderID;
    @ApiModelProperty(notes = "The name of the product in the order.")
    private String productName;
    @ApiModelProperty(notes = "The price of this order.")
    private Double price;
    @ApiModelProperty(notes = "The amount of products in the order.")
    private Integer amount;
    @ApiModelProperty(notes = "The id of the User who placed the order.")
    private Integer userID;

    public Order() {
    }

    public Order(String orderID, String productName, Double price, Integer amount, Integer userID) {
        this.orderID = orderID;
        this.productName = productName;
        this.price = price;
        this.amount = amount;
        this.userID = userID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
