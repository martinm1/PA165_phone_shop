package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dao.StockDao;
import cz.muni.fi.pa165.smartphonEShop.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;

    @Override
    public Long createStock(Stock stock) {
        stockDao.create(stock);
        return stock.getId();
    }

    @Override
    public Stock findStockById(Long id) {
        return stockDao.findById(id);
    }

    @Override
    public Stock findStockByName(String name) {
        //TODO:: add method in stockDAO
        return null;
    }

    @Override
    public Stock findStockByAddressId(Long addressId) {
        //TODO:: add method in stockDAO
        return null;
    }

    @Override
    public Stock findStockByPhoneId(Long phoneId) {
        //TODO:: add method in stockDAO
        return null;
    }

    @Override
    public List<Stock> findAllStocks() {
        return stockDao.findAll();
    }

    @Override
    public void addPhone(Long stockId, Long phoneId) {
        //TODO:: add method in stockDAO
    }

    @Override
    public void removePhone(Long stockId, Long phoneId) {
        //TODO:: add method in stockDAO
    }
}
