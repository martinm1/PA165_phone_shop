package cz.muni.fi.pa165.smartphonEShop.rest.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.PersonDTO;
import cz.muni.fi.pa165.smartphonEShop.facade.PersonFacade;
import com.fasterxml.jackson.core.JsonProcessingException;
import cz.muni.fi.pa165.smartphonEShop.rest.ApiUris;
import cz.muni.fi.pa165.smartphonEShop.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collection;

/**
 * Created by Stefan Holecko
 * Class represents:
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_PEOPLE)
public class PeopleController {

    @Inject
    private PersonFacade personFacade;

    final static Logger logger = LoggerFactory.getLogger(PeopleController.class);

    /**
     * returns all people according to a Summary View
     *
     * @return list of PersonDTOs
     * @throws JsonProcessingException
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<PersonDTO> getPeople() throws JsonProcessingException {

        logger.debug("rest getPeople()");
        return personFacade.getAllPeople();
    }

    /**
     *
     * getting person according to id
     *
     * @param id person identifier
     * @return PersonDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PersonDTO getPerson(@PathVariable("id") long id) throws Exception {

        logger.debug("rest getPerson({})", id);
        PersonDTO personDTO = personFacade.findPersonById(id);
        if (personDTO == null){
            throw new ResourceNotFoundException();
        }
        return personDTO;
    }

    /**
     *
     * getting person according to email
     *
     * @param email person identifier
     * @return PersonDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "byEmail/{email:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) //To :.+ znamena, ze se z parametru neurizne napriklad .cz
    public final PersonDTO getPersonByEmail(@PathVariable("email") String email) throws Exception {

        logger.debug("rest getPersonByEmail({})", email);
        PersonDTO personDTO = personFacade.findPersonByEmail(email);
        if (personDTO == null){
            throw new ResourceNotFoundException();
        }
        return personDTO;
    }

    /**
     *
     * getting person according to phone number
     *
     * @param phoneNumber person identifier
     * @return PersonDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "byPhoneNumber/{phone_number}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PersonDTO getPersonByPhoneNumber(@PathVariable("phone_number") String phoneNumber) throws Exception {

        logger.debug("rest getPersonByEmail({})", phoneNumber);
        PersonDTO personDTO = personFacade.findPersonByPhoneNumber(phoneNumber);
        if (personDTO == null){
            throw new ResourceNotFoundException();
        }
        return personDTO;
    }



    //TODO GetPeopleByPersonType??
}

