package cz.muni.fi.pa165.smartphonEShop.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.PersonDTO;

import java.util.Collection;

public interface PersonFacade
{
    PersonDTO findPersonById(Long id);

    PersonDTO findPersonByEmail(String email);

    PersonDTO findPersonByPhoneNumber (String phoneNumber);

    Collection<PersonDTO> getAllPersons();
}
