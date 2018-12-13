package cz.muni.fi.pa165.smartphonEShop.rest.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.PhoneDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.StockCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.StockDTO;
import cz.muni.fi.pa165.smartphonEShop.facade.StockFacade;
import cz.muni.fi.pa165.smartphonEShop.rest.ApiUris;
import cz.muni.fi.pa165.smartphonEShop.rest.exceptions.InvalidParameterException;
import cz.muni.fi.pa165.smartphonEShop.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.smartphonEShop.rest.exceptions.ResourceNotFoundException;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

/**
 * Created by Roman Nahalka
 * Class represents: Rest Controller for Stocks.
 * @author xnahalka
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_STOCKS)
public class StocksController
{
    @Inject
    private StockFacade stockFacade;

    /**
     * Get collection of stocks.
     * @return StockDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<StockDTO> getStocks()
    {
        return stockFacade.getAllStocks();
    }

    /**
     * Get stock by id.
     * @param id primary key for stock.
     * @return StockDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final StockDTO getStock(@PathVariable("id") long id)
    {
        StockDTO stockDTO = stockFacade.findStockById(id);

        if(stockDTO != null)
            return stockDTO;

        else
            throw new ResourceNotFoundException();
    }

    /**
     * Get stock by name.
     * @param name of stock.
     * @return StockDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "byName/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final StockDTO getStockByName(@PathVariable("name") String name)
    {
        StockDTO stockDTO = stockFacade.findStockByName(name);

        if(stockDTO != null)
            return stockDTO;

        else
            throw new ResourceNotFoundException();
    }

    /**
     * Get stock by address
     * @param id primary key for address.
     * @return StockDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "byAddress/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final StockDTO getStockByAddress(@PathVariable("id") long id)
    {
        StockDTO stockDTO = stockFacade.findStockByAddressId(id);

        if(stockDTO != null)
            return stockDTO;

        else
            throw new ResourceNotFoundException();
    }

    /**
     * Get stock by phone.
     * @param id primary key for phone.
     * @return StockDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "byPhone/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final StockDTO getStockByPhone(@PathVariable("id") long id)
    {
        StockDTO stockDTO = stockFacade.findStockByPhoneId(id);

        if(stockDTO != null)
            return stockDTO;

        else
            throw new ResourceNotFoundException();
    }

    /**
     * Create a new stock.
     * @param stockDTO with required field for creation.
     * @return created stock.
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public final StockDTO createStock(@RequestBody StockCreateDTO stockDTO)
    {
        try
        {
            Long id = stockFacade.createStock(stockDTO);
            return stockFacade.findStockById(id);
        }

        catch (Exception ex)
        {
            throw new ResourceAlreadyExistingException();
        }
    }

    /**
     * Add a new phone to stock.
     * @param id primary key for stock.
     * @param phoneDTO to be added.
     * @return updated stock.
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "{id}/phones", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public final StockDTO addPhone(@PathVariable("id") long id, @RequestBody PhoneDTO phoneDTO)
    {
        try
        {
            stockFacade.addPhone(id, phoneDTO.getId());
            return stockFacade.findStockById(id);
        }

        catch (Exception ex)
        {
            throw new InvalidParameterException();
        }
    }

    /**
     * Remove a phoned from stock.
     * @param id primary key fro stock.
     * @param phoneDTO to be removed.
     * @return updated stock.
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "{id}/phones", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final StockDTO removePhone(@PathVariable("id") long id, @RequestBody PhoneDTO phoneDTO)
    {
        try
        {
            stockFacade.removePhone(id, phoneDTO.getId());
            return stockFacade.findStockById(id);
        }

        catch (Exception ex)
        {
            throw  new InvalidParameterException();
        }
    }
}
