package be.thomasmore.edge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "The details of a Product.")
public class Product {
    @ApiModelProperty(notes = "Auto generated id for the product.")
    private String productID;
    @ApiModelProperty(notes = "The name of the product.")
    private String name;
    @ApiModelProperty(notes = "The brand of the product.")
    private String brand;
    @ApiModelProperty(notes = "The short form of the brand name.")
    private String shortBrand;
    @ApiModelProperty(notes = "The brewery where the product is made.")
    private String brewery;
    @ApiModelProperty(notes = "The short form of the brewery name.")
    private String shortBrewery;
    @ApiModelProperty(notes = "The alcohol percentage in the product.")
    private String percentage;
    @ApiModelProperty(notes = "The price a single product.")
    private double price;

    public Product() {
    }

    public Product(String productID, String name, String brand, String shortBrand, String brewery, String shortBrewery, String percentage, double price) {
        this.productID = productID;
        this.name = name;
        this.brand = brand;
        this.shortBrand = shortBrand;
        this.brewery = brewery;
        this.shortBrewery = shortBrewery;
        this.percentage = percentage;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getShortBrand() {
        return shortBrand;
    }

    public void setShortBrand(String shortBrand) {
        this.shortBrand = shortBrand;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public String getShortBrewery() {
        return shortBrewery;
    }

    public void setShortBrewery(String shortBrewery) {
        this.shortBrewery = shortBrewery;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
