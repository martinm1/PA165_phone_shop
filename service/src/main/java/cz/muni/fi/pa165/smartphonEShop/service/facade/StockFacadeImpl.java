package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.StockDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Stock;
import cz.muni.fi.pa165.smartphonEShop.facade.StockFacade;
import cz.muni.fi.pa165.smartphonEShop.service.BeanMappingService;
import cz.muni.fi.pa165.smartphonEShop.service.service.StockService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author martin
 */
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
        return beanMappingService.mapTo(stockService.findAllStocks(),StockDTO.class);
    }

    @Override
    public void registerStock(StockDTO stockDTO) {
        //Stock stock = new Stock();
        //stock.setAddress(stockDTO.getAddress());
        //stock.setName(stockDTO.getName());
        //stock.setPhones(stockDTO.getPhones());
        //stockService.createStock(phone);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void addPhone(Long stockId, Long phoneId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
