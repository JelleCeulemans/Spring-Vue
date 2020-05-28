package be.thomasmore.edge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@ApiModel(description = "Holds user data.")
public class AuthenticatableUser extends org.springframework.security.core.userdetails.User {

    @JsonIgnore
    private String password;

    public AuthenticatableUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AuthenticatableUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

}