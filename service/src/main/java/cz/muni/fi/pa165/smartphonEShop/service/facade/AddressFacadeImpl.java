/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.AddressCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.AddressDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.entity.Person;
import cz.muni.fi.pa165.smartphonEShop.enums.AddressEnum;
import cz.muni.fi.pa165.smartphonEShop.facade.AddressFacade;
import cz.muni.fi.pa165.smartphonEShop.service.service.BeanMappingService;
import cz.muni.fi.pa165.smartphonEShop.service.service.AddressService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
public class AddressFacadeImpl implements AddressFacade{
    
    @Autowired
    private AddressService addressService;
    
    @Autowired
    private AddressService stockService;

    @Autowired
    private BeanMappingService beanMappingService;
    
    @Override
    public AddressDTO findAddressById(Long id) {
        Address address = addressService.findAddressById(id);
        return (address == null) ? null : beanMappingService.mapTo(address,AddressDTO.class);
    }

    @Override
    public Collection<AddressDTO> findAllAddressesBy(HashMap<AddressEnum, String> specificator) {
        return beanMappingService.mapTo(addressService.findAllAddressesBy(specificator),AddressDTO.class);
    }

    @Override
    public Collection<AddressDTO> getAllAddresses() {
        return beanMappingService.mapTo(addressService.getAllAddresses(),AddressDTO.class);
    }

    @Override
    public Long createAddress(AddressCreateDTO addressDTO) {
        List<Person> people = new ArrayList();
        
        Address address = new Address();
        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        address.setPeople(people);
        address.setStock(null);
        address.setStreetName(addressDTO.getStreetName());
        address.setStreetNumber(address.getStreetNumber());
        
        addressService.createAddress(address);
        
        return address.getId();
    }


}
