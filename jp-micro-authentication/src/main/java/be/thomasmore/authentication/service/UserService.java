package be.thomasmore.authentication.service;

import be.thomasmore.authentication.model.GenericResponseWrapper;
import be.thomasmore.authentication.model.AuthenticatableUser;
import be.thomasmore.authentication.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserService implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;


    public UserDetails findUserByEmail(String email) {

        GenericResponseWrapper wrapper = restTemplate.getForObject("http://jp-micro-users/users/search/findUserByEmail?email=" + email, GenericResponseWrapper.class);

        List<User> users = new ArrayList<>();

        if (wrapper != null) {
            users = objectMapper.convertValue(wrapper.get_embedded().get("users"), new TypeReference<List<User>>() { });
        }

        if(users.size() > 0) {

            Collection<GrantedAuthority> permissions = new ArrayList<GrantedAuthority>();

            permissions.add((GrantedAuthority) new SimpleGrantedAuthority("ROLE_CUSTOMER"));

            if(users.get(0).getIs_admin()) {
                permissions.add((GrantedAuthority) new SimpleGrantedAuthority("ROLE_ADMIN"));
            }

            return new AuthenticatableUser(
                    users.get(0).getEmail(),
                    users.get(0).getPassword(),
                    permissions
            );
        }

        throw new UsernameNotFoundException(String.format("User with username '%s', was not found.", email));
    }



    public UserDetails loadUserByUsername(String username) {
        return findUserByEmail(username);
    }

    /*public UserDetails loadUserByUsername(String username) {
        //Fake a user account (Username: me@example.com | Password: p@ssword)
        if("me@example.com".equals(username)) {
            Collection<GrantedAuthority> permissions = new ArrayList<GrantedAuthority>();

            permissions.add((GrantedAuthority) new SimpleGrantedAuthority("ROLE_CUSTOMER"));

            return new User(
                    "me@example.com",
                    "$2y$12$hdhuqxL/fL4D9GcNNSsZgepn1fzCL7lZz3bme6hMubwxWONmVz/q2",
                    permissions
            );
        }

        throw new UsernameNotFoundException(String.format("User with username '%s', was not found.", username));
    }*/

}
