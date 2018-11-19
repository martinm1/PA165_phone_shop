package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dao.PhoneDao;
import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Roman Nahalka
 * Class represents: Implementation of PhoneService.
 */

public class PhoneServiceImpl implements PhoneService
{
    @Autowired
    private PhoneDao phoneDao;

    @Override
    public void createPhone(Phone phone)
    {
        phoneDao.create(phone);
    }

    @Override
    public Phone findPhoneById(Long id)
    {
        return phoneDao.findById(id);
    }

    @Override
    public List<Phone> findPhonesByModelName(String modelName)
    {
        return phoneDao.findPhonesByModelName(modelName);
    }

    @Override
    public List<Phone> findPhonesByPrice(int price)
    {
        return phoneDao.findPhonesByPrice(price);
    }

    @Override
    public List<Phone> findPhonesByTechnicalInfo(String technicalInfo)
    {
        return phoneDao.findPhonesByTechnicalInfo(technicalInfo);
    }

    @Override
    public List<Phone> findPhonesByManufacture(Manufacturer manufacturer)
    {
        return phoneDao.findPhonesByManufacturer(manufacturer);
    }

    @Override
    public List<Phone> findPhonesByStock(Long stockId)
    {
        return phoneDao.findPhonesByStock(stockId);
    }

    @Override
    public List<Phone> findAllPhones()
    {
        return phoneDao.findAll();
    }
}