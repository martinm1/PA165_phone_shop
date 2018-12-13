package cz.muni.fi.pa165.smartphonEShop.rest.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.ClaimCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.ClaimDTO;
import cz.muni.fi.pa165.smartphonEShop.facade.ClaimFacade;
import cz.muni.fi.pa165.smartphonEShop.rest.ApiUris;
import cz.muni.fi.pa165.smartphonEShop.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.smartphonEShop.rest.exceptions.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

@RestController
@RequestMapping(ApiUris.ROOT_URI_CLAIMS)
public class ClaimsController {

    @Inject
    private ClaimFacade claimFacade;

    /**
     * Get collection of claims.
     * @return ClaimDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<ClaimDTO> getClaims()
    {
        return claimFacade.getAllClaims();
    }

    /**
     * Get claim by id.
     * @param id primary key for claim.
     * @return ClaimDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ClaimDTO getClaim(@PathVariable("id") long id)
    {
        ClaimDTO claimDTO = claimFacade.findClaimById(id);

        if(claimDTO != null)
            return claimDTO;

        else
            throw new ResourceNotFoundException();
    }

    /**
     * Create a new claim.
     * @param claimDTO with required field for creation.
     * @return created claim.
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ClaimDTO createClaim(@RequestBody ClaimCreateDTO claimDTO)
    {
        try
        {
            Long id = claimFacade.createClaim(claimDTO);
            return claimFacade.findClaimById(id);
        }

        catch (Exception ex)
        {
            throw new ResourceAlreadyExistingException();
        }
    }
}
