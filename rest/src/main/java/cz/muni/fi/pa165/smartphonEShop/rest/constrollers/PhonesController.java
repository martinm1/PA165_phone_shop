package cz.muni.fi.pa165.smartphonEShop.rest.constrollers;

import cz.muni.fi.pa165.smartphonEShop.dto.PhoneCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.PhoneDTO;
import cz.muni.fi.pa165.smartphonEShop.facade.PhoneFacade;
import cz.muni.fi.pa165.smartphonEShop.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.smartphonEShop.rest.exceptions.ResourceNotFoundException;
import java.util.Collection;
import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author martin
 */
public class PhonesController {
    @Inject
    private PhoneFacade phoneFacade;
    
    /**
     * Get collection of phones.
     * @return PhoneDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<PhoneDTO> getPhones()
    {
        return phoneFacade.getAllPhones();
    }
    
    /**
     * Get phone by id.
     * @param id primary key for phone.
     * @return PhoneDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PhoneDTO getPhone(@PathVariable("id") long id)
    {
        PhoneDTO phoneDTO = phoneFacade.findPhoneById(id);

        if(phoneDTO != null)
            return phoneDTO;

        else
            throw new ResourceNotFoundException();
    }
    
    /**
     * Create a new phone.
     * @param phoneDTO with required field for creation.
     * @return created phone.
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public final PhoneDTO createPhone(@RequestBody PhoneCreateDTO phoneDTO)
    {
        try
        {
            Long id = phoneFacade.createPhone(phoneDTO);
            return phoneFacade.findPhoneById(id);
        }

        catch (Exception ex)
        {
            throw new ResourceAlreadyExistingException();
        }
    }
}
