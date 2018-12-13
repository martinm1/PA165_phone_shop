package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.StockCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.StockDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.entity.Stock;
import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
import cz.muni.fi.pa165.smartphonEShop.facade.StockFacade;
import cz.muni.fi.pa165.smartphonEShop.service.service.BeanMappingService;
import cz.muni.fi.pa165.smartphonEShop.service.service.PhoneService;
import cz.muni.fi.pa165.smartphonEShop.service.service.StockService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author martin
 */
@Service
@Transactional
public class StockFacadeImpl implements StockFacade {

    @Autowired
    private StockService stockService;

    
    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public StockDTO findStockById(Long id) {
        Stock stock = stockService.findStockById(id);
        return (stock == null) ? null : beanMappingService.mapTo(stock,StockDTO.class);
    }

    @Override
    public StockDTO findStockByName(String name) {
        return beanMappingService.mapTo(stockService.findStockByName(name),StockDTO.class);
    }

    @Override
    public StockDTO findStockByAddressId(Long addressId) {
        return beanMappingService.mapTo(stockService.findStockByAddressId(addressId),StockDTO.class);
    }

    @Override
    public StockDTO findStockByPhoneId(Long phoneId) {
        return beanMappingService.mapTo(stockService.findStockByPhoneId(phoneId),StockDTO.class);
    }

    @Override
    public Collection<StockDTO> getAllStocks() {
        return beanMappingService.mapTo(stockService.getAllStocks(),StockDTO.class);
    }

    @Override
    public void addPhone(Long stockId, Long phoneId) {
        stockService.addPhone(stockId, phoneId);
    }

    @Override
    public Long createStock(StockCreateDTO stockDTO) {
        List<Phone> phones = new ArrayList();
        Stock newstock = new Stock();
        
        Address address = new Address();

        address.setCity(stockDTO.getAddress().getCity());
        address.setCountry(stockDTO.getAddress().getCountry());
        address.setStreetName(stockDTO.getAddress().getStreetName());
        address.setStreetNumber(stockDTO.getAddress().getStreetNumber());

        newstock.setAddress(address);
        newstock.setName(stockDTO.getName());
        newstock.setPhones(phones);
        stockService.createStock(newstock);
        return newstock.getId();
    }

    @Override
    public void removePhone(Long stockId, Long phoneId) {
        stockService.removePhone(stockId, phoneId);
    }
}
