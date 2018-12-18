package cz.muni.fi.pa165.smartphonEShop.mvc.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class PersonIdContainingUser  extends User implements PersonUserDetails
{
    private final Long personId;

    private final String name;

    private final String lastName;

    public PersonIdContainingUser(
            String username, String password, boolean enabled,
            boolean accountNonExpired, boolean credentialsNonExpired,
            boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
            Long trainerId, String firstName, String lastName) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.personId = trainerId;
        this.name = firstName;
        this.lastName = lastName;
    }

    public PersonIdContainingUser(String username, String password, Collection<? extends GrantedAuthority> authorities
            , Long personId, String firstName, String lastName)
    {
        super(username, password, authorities);
        this.personId = personId;
        this.name = firstName;
        this.lastName = lastName;
    }
}
