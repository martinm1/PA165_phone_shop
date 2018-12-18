package cz.muni.fi.pa165.smartphonEShop.mvc.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class PersonIdContainingUser  extends User implements PersonUserDetails
{
    private final Long personId;

    @Override
    public Long getPersonId()
    {
        return personId;
    }

    public PersonIdContainingUser(
            String username, String password, boolean enabled,
            boolean accountNonExpired, boolean credentialsNonExpired,
            boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
            Long trainerId) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.personId = trainerId;
    }

    public PersonIdContainingUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long personId) {
        super(username, password, authorities);
        this.personId = personId;
    }
}
