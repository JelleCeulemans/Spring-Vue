package be.thomasmore.microservice.favorites.repository;

import be.thomasmore.microservice.favorites.entity.Favorite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends MongoRepository<Favorite, String> {
    List<Favorite> findAllByProductID(@Param("productID") String productID);
    List<Favorite> findAllByUserID(@Param("userID") String userID);
}
