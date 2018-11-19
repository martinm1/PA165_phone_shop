package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.AddressDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.PhoneDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.StockDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
import cz.muni.fi.pa165.smartphonEShop.entity.Stock;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import cz.muni.fi.pa165.smartphonEShop.facade.PhoneFacade;
import cz.muni.fi.pa165.smartphonEShop.service.BeanMappingService;
import cz.muni.fi.pa165.smartphonEShop.service.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by Stefan Holecko
 * Class represents: implementation of PhoneFacade
 */
@Service
@Transactional
public class PhoneFacadeImpl implements PhoneFacade {

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public PhoneDTO findPhoneById(Long id) {
        Phone phone = phoneService.findPhoneById(id);
        return (phone == null) ? null : beanMappingService.mapTo(phone,PhoneDTO.class);
    }

    @Override
    public Collection<PhoneDTO> findPhonesByModelName(String modelName) {
        return beanMappingService.mapTo(phoneService.findPhonesByModelName(modelName),PhoneDTO.class);
    }

    @Override
    public Collection<PhoneDTO> findPhonesByPrice(int price) {
        return beanMappingService.mapTo(phoneService.findPhonesByPrice(price),PhoneDTO.class);
    }

    @Override
    public Collection<PhoneDTO> findPhonesByTechnicalInfo(String technicalInfo) {
        return beanMappingService.mapTo(phoneService.findPhonesByTechnicalInfo(technicalInfo),PhoneDTO.class);
    }

    @Override
    public Collection<PhoneDTO> findPhonesByManufacturer(Manufacturer manufacturer) {
        return beanMappingService.mapTo(phoneService.findPhonesByManufacture(manufacturer),PhoneDTO.class);
    }

    @Override
    public Collection<PhoneDTO> findPhonesByStockID(Long stockID) {
        return beanMappingService.mapTo(phoneService.findPhonesByStock(stockID),PhoneDTO.class);
    }

    @Override
    public Collection<PhoneDTO> getAllPhones() {
        return beanMappingService.mapTo(phoneService.findAllPhones(),PhoneDTO.class);
    }
    //TODO
    @Override
    public void registerPhone(PhoneDTO phoneDTO) {
        Phone phone = new Phone();
        phone.setManufacturer(phoneDTO.getManufacturer());
        phone.setModelName(phoneDTO.getModelName());
        phone.setOrder(phone.getOrder());
        phone.setPrice(phoneDTO.getPrice());
        phone.setStock(phoneDTO.getStock());
        phone.setTechnicalInfo(phoneDTO.getTechnicalInfo());
        phoneService.createPhone(phone);
        return phone.getId();
    }


    //TODO
    public Long registerPhone(PhoneDTO phoneDTO, StockDTO stockDTO, AddressDTO addressDTO) {
        Phone phone = new Phone();
        Stock stock = new Stock();
        Address address = new Address();

        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        address.setStreetName(addressDTO.getStreetName());
        address.setStreetNumber(addressDTO.getStreetNumber());

        stock.setAddress(address);
        stock.setName(stockDTO.getName());
        //stock.setPhones(stockDTO.getPhones());

        phone.setManufacturer(phoneDTO.getManufacturer());
        phone.setModelName(phoneDTO.getModelName());
        phone.setOrder(phone.getOrder());
        phone.setPrice(phoneDTO.getPrice());
        phone.setStock(stock);
        phone.setTechnicalInfo(phoneDTO.getTechnicalInfo());
        phoneService.createPhone(phone);
        return phone.getId();
    }
}
