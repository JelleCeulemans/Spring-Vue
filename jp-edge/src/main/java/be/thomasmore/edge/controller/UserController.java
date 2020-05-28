package be.thomasmore.edge.controller;

import be.thomasmore.edge.model.User;
import be.thomasmore.edge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@ApiIgnore
public class UserController {

    @Autowired
    private UserService userService;

    //@Secured({"ROLE_CUSTOMER"})
    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> show(Authentication authentication, @PathVariable String username)
            throws BadCredentialsException {

        User user;

        if(username.equals(authentication.getName())) {
            user = (User) userService.findUserProfileByEmail(username);

            return ResponseEntity.ok(user);
        }

        throw new BadCredentialsException("Requested user does not match currently logged in user.");
    }

}
