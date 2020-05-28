package be.thomasmore.edge.controller;

import be.thomasmore.edge.model.GenericResponseWrapper;
import be.thomasmore.edge.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;


    @GetMapping("all")
    @ApiOperation(value = "Lists all of the products", response = Product.class, responseContainer = "List")
    public List<Product> getProducts() {
        GenericResponseWrapper wrapper = restTemplate.getForObject("http://jp-micro-products/products/", GenericResponseWrapper.class);
        List<Product> products = objectMapper.convertValue(wrapper.get_embedded().get("products"), new TypeReference<List<Product>>() {
        });

        return products;
    }

    @GetMapping("product/{productID}")
    @ApiOperation(value = "Getting a product object by the id", response = Product.class)
    public Product getProduct(@ApiParam(value = "The id of the product we want",required = true) @PathVariable("productID") String productID) {
        Product product = restTemplate.getForObject("http://jp-micro-products/products/search/findProductByProductID?productID=" + productID, Product.class);
        return product;
    }

//    @GetMapping("byBrand/{shortBrand}")
//    public List<Product> getProductsBrand(@PathVariable("shortBrand") String shortBrand) {
//        GenericResponseWrapper wrapper = restTemplate.getForObject("http://product-service/products/search/findProductsByShortBrand?shortBrand=" + shortBrand, GenericResponseWrapper.class);
//        List<Product> products = objectMapper.convertValue(wrapper.get_embedded().get("products"), new TypeReference<List<Product>>() {
//        });
//
//        return products;
//    }

//    @GetMapping("byBrewery/{shortBrewery}")
//    public List<Product> getProductsBrewery(@PathVariable("shortBrewery") String shortBrewery) {
//        GenericResponseWrapper wrapper = restTemplate.getForObject("http://product-service/products/search/findProductsByShortBrewery?shortBrewery=" + shortBrewery, GenericResponseWrapper.class);
//        List<Product> products = objectMapper.convertValue(wrapper.get_embedded().get("products"), new TypeReference<List<Product>>() {
//        });
//
//        return products;
//    }


    //@Secured({"ROLE_ADMIN"})
    @PostMapping("product")
    @ApiOperation(value = "Adding a new product")
    public ResponseEntity<String> postProduct(@ApiParam(value = "The Product object we would like to add.", required = true) @RequestBody Product product) {
        ResponseEntity<String> result = restTemplate.postForEntity("http://jp-micro-products/products/", product, String.class);
        return ResponseEntity.ok().build();
    }

    //@Secured({"ROLE_ADMIN"})
    @PutMapping("product")
    @ApiOperation(value = "Updating an existing product")
    public ResponseEntity<String> putProduct(@ApiParam(value = "The updated Product object", required = true) @RequestBody Product product) {
        restTemplate.put("http://jp-micro-products/products/" + product.getProductID(), product, String.class);
        return ResponseEntity.ok().build();
    }

    //@Secured({"ROLE_ADMIN"})
    @DeleteMapping("product/{productID}")
    @ApiOperation(value ="Deleting a product by their id" )
    public ResponseEntity deleteProduct(@ApiParam(value = "The id of the Product object that has to be deleted", required = true) @PathVariable("productID") String productID) {
        restTemplate.delete("http://jp-micro-products/products/" + productID);
        return ResponseEntity.ok().build();
    }
}
