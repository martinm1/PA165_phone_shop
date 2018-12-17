package cz.muni.fi.pa165.smartphonEShop.mvc.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface PersonUserDetails extends UserDetails
{
    Long getPersonId();
}
