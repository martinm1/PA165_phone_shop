package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dao.AddressDao;
import cz.muni.fi.pa165.smartphonEShop.dto.AddressDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.enums.AddressEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Stefan Holecko
 * Class represents: implementation of AddressService interface
 */

public class AddressServiceImpl implements AddressService{


    @Autowired
    private AddressDao addressDao;

    @Override
    public Address findAddressById(Long id) {
        return addressDao.findById(id);
    }

    @Override
    public List<Address> findAllAdressesBy(HashMap<AddressEnum, String> specificator) {
        return addressDao.findAllAddressesBy(specificator);
    }

    @Override
    public List<Address> getAllAdresses() {
        return addressDao.findAll();
    }
}
