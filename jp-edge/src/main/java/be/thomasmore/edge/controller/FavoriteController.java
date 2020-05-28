package be.thomasmore.edge.controller;


import be.thomasmore.edge.model.Favorite;
import be.thomasmore.edge.model.GenericResponseWrapper;
import be.thomasmore.edge.model.Order;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
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
@RequestMapping("/favorites")
public class FavoriteController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    //http://localhost:8050/favorites/all
    @GetMapping("all")
    //@Secured({"ROLE_CUSTOMER"})
    @ApiOperation(value = "Lists all of the favorites", response = Favorite.class, responseContainer = "List")
    public List<Favorite> getAll() {
        GenericResponseWrapper wrapper = restTemplate.getForObject("http://jp-micro-favorites/favorites/", GenericResponseWrapper.class);
        List<Favorite> favorites = objectMapper.convertValue(wrapper.get_embedded().get("favorites"),  new TypeReference<List<Favorite>>() { });

        return favorites;
    }

    @GetMapping("user/{userID}")
    //@Secured({"ROLE_CUSTOMER"})
    @ApiOperation(value = "Lists all of the favorites of a user", response = Favorite.class, responseContainer = "List")
    public List<Favorite> getFavoritesByUserID(@ApiParam(value = "The id of the user whose favorites we want", required=true) @PathVariable("userID") String userID) {

        GenericResponseWrapper wrapper = restTemplate.getForObject("http://jp-micro-favorites/favorites/search/findAllByUserID?userID=" + userID, GenericResponseWrapper.class);
        List<Favorite> favorites = objectMapper.convertValue(wrapper.get_embedded().get("favorites"),  new TypeReference<List<Favorite>>() { });

        return favorites;
    }

    @GetMapping("product/{productID}")
    //@Secured({"ROLE_CUSTOMER"})
    @ApiOperation(value = "Lists all of the favorites of a product", response = Favorite.class, responseContainer = "List")
    public List<Favorite> getFavoritesByProductId(@ApiParam(value = "The id of the product whose favorites we want", required=true) @PathVariable("productID") String productID) {
        GenericResponseWrapper wrapper = restTemplate.getForObject("http://jp-micro-favorites/favorites/search/findAllByProductID?productID=" + productID, GenericResponseWrapper.class);
        List<Favorite> favorites = objectMapper.convertValue(wrapper.get_embedded().get("favorites"),  new TypeReference<List<Favorite>>() { });

        return favorites;
    }

    @PostMapping("favorite")
    //@Secured({"ROLE_CUSTOMER"})
    @ApiOperation(value = "Adds a new favorite.")
    public ResponseEntity<String> postFavoritesByUserID(@ApiParam(value = "The user object that has to be added.") @RequestBody Favorite favorite){
        ResponseEntity<String> result = restTemplate.postForEntity("http://jp-micro-favorites/favorites/", favorite, String.class);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("favorite/{favoriteID}")
    //@Secured({"ROLE_CUSTOMER"})
    @ApiOperation(value = "Deleting a favorite by their favoriteID")
    public ResponseEntity deleteFavorite(@ApiParam(value="The ID of the favorite we want to delete", required = true) @PathVariable("favoriteID") String favoriteID) {
        restTemplate.delete("http://jp-micro-favorites/favorites/" + favoriteID);
        return ResponseEntity.ok().build();
    }

}
