package be.thomasmore.microservice.products.repository;

import be.thomasmore.microservice.products.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findProductsByShortBrand(@Param("shortBrand") String shortBrand);

    List<Product> findProductsByShortBrewery(@Param("shortBrewery") String shortBrewery);

    Product findProductByProductID(@Param("productID") String productID);
}