package be.thomasmore.edge.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Links a user to a product, that product is one of their favorites")
public class Favorite {
    @ApiModelProperty(notes = "Auto generated id for the Favorite")
    private String favoriteID;
    @ApiModelProperty(notes = "The id of the product that is the favorite")
    private String productID;
    @ApiModelProperty(notes = "The id of the user, to whom the favorite belongs")
    private int userID;

    public Favorite() {
    }

    public Favorite(String favoriteID, String productID, int userID) {
        this.favoriteID = favoriteID;
        this.productID = productID;
        this.userID = userID;
    }

    public String getFavoriteID() {
        return favoriteID;
    }

    public void setFavoriteID(String favoriteID) {
        this.favoriteID = favoriteID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
