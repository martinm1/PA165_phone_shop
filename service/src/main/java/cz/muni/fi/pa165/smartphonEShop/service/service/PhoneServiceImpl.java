package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dao.PhoneDao;
import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Roman Nahalka
 * Class represents: Implementation of PhoneService.
 */

@Service
public class PhoneServiceImpl implements PhoneService
{
    @Autowired
    private PhoneDao phoneDao;

    @Override
    public Long createPhone(Phone phone)
    {
        phoneDao.create(phone);
        return phone.getId();
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
    public List<Phone> findPhonesByPrice(int lowerBound, int upperBound)
    {
        return phoneDao.findPhonesByPrice(lowerBound, upperBound);
    }

    @Override
    public List<Phone> findPhonesByTechnicalInfo(String technicalInfo)
    {
        return phoneDao.findPhonesByTechnicalInfo(technicalInfo);
    }

    @Override
    public List<Phone> findPhonesByManufacturer(Manufacturer manufacturer)
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
