package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dao.AddressDao;
import cz.muni.fi.pa165.smartphonEShop.dao.PhoneDao;
import cz.muni.fi.pa165.smartphonEShop.dao.StockDao;
import cz.muni.fi.pa165.smartphonEShop.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Jakub Ondrusek
 * Class represents: implementation of StockService interface
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;
    
    @Autowired
    private AddressDao addressDao;
    
    @Autowired
    private PhoneDao phoneDao;

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
        return stockDao.findByName(name);
    }

    @Override
    public Stock findStockByAddressId(Long addressId) {
        return addressDao.findById(addressId).getStock();
    }

    @Override
    public Stock findStockByPhoneId(Long phoneId) {
        return phoneDao.findById(phoneId).getStock();
    }

    @Override
    public List<Stock> findAllStocks() {
        return stockDao.findAll();
    }

    @Override
    public void addPhone(Long stockId, Long phoneId) {
        stockDao.findById(stockId).addPhone(phoneDao.findById(phoneId));
    }

    @Override
    public void removePhone(Long stockId, Long phoneId) {
        stockDao.findById(stockId).removePhone(phoneDao.findById(phoneId));
    }
}
