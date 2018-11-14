package cz.muni.fi.pa165.smartphonEShop.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.AddressDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.AddressEnum;

import java.util.Collection;
import java.util.HashMap;

public interface AddressFacade {
    AddressDTO findAddressById(Long id);

    Collection<AddressDTO> findAllAdressesBy(HashMap<AddressEnum,String> specificator);

    Collection<AddressDTO> getAllAdresses();

}
