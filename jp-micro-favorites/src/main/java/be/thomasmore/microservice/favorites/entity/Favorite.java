package be.thomasmore.microservice.favorites.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection="favorites")
@Getter
@Setter
public class Favorite {

    @Id
    private String  favoriteID;
    private String productID;
    private String userID;

}
