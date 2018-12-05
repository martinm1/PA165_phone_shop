package cz.muni.fi.pa165.smartphonEShop.rest.constrollers;

import cz.muni.fi.pa165.smartphonEShop.dto.AddressDTO;
import cz.muni.fi.pa165.smartphonEShop.facade.AddressFacade;
import cz.muni.fi.pa165.smartphonEShop.rest.ApiUris;
import cz.muni.fi.pa165.smartphonEShop.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.smartphonEShop.rest.exceptions.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

/**
 * Created by Roman Nahalka
 * Class represents: Rest Controller for Addresses.
 * @author xnahalka
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_ADDRESSES)
public class AddressesController
{
    @Inject
    private AddressFacade addressFacade;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<AddressDTO> getAddresses()
    {
        return addressFacade.getAllAddresses();
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final AddressDTO getAddress(@PathVariable("id") long id)
    {
        AddressDTO addressDTO = addressFacade.findAddressById(id);

        if(addressDTO != null)
            return addressDTO;

        else
            throw new ResourceNotFoundException();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE
                    , produces = MediaType.APPLICATION_JSON_VALUE)
    public final AddressDTO createAddress(@RequestBody AddressDTO addressDTO) throws Exception
    {
        try
        {
            Long id = addressFacade.createAddress(addressDTO); //TODO: CreateDTO
            return addressFacade.findAddressById(id);
        }

        catch (Exception ex)
        {
            throw new ResourceAlreadyExistingException();
        }
    }
}
