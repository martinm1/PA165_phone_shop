package cz.muni.fi.pa165.smartphonEShop.mvc.security;

import cz.muni.fi.pa165.smartphonEShop.dto.PersonAuthDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.PersonDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import cz.muni.fi.pa165.smartphonEShop.facade.PersonFacade;
import cz.muni.fi.pa165.smartphonEShop.mvc.exceptions.AuthException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonAuthProvider implements AuthenticationProvider
{

    @Inject
    private PersonFacade personFacade;

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException
    {
        String name = a.getName();
        String pass = a.getCredentials().toString();

        PersonDTO person = personFacade.findPersonByEmail(name);

        if(person == null)
            throw new AuthException("Bad email or password!");

        PersonAuthDTO personAuthDTO = new PersonAuthDTO();
        personAuthDTO.setPass(pass);
        personAuthDTO.setEmail(name);

        if(!personFacade.auth(personAuthDTO))
            throw new AuthException("Bad email or password!");

        List<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority("ROLE_USER"));

        if(person.getPersonType() == PersonType.ADMIN)
            auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        UserDetails user = new PersonIdContainingUser(name, pass, auths, person.getId(), person.getFirstName(), person.getLastName());

        return new UsernamePasswordAuthenticationToken(user, pass, auths);
    }

    @Override
    public boolean supports(Class<?> aClass)
    {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
