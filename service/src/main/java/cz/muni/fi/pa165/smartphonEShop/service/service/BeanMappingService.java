package cz.muni.fi.pa165.smartphonEShop.service.service;

import java.util.Collection;
import java.util.List;

import com.github.dozermapper.core.Mapper;

public interface BeanMappingService
{
    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);
    <T> T mapTo(Object u, Class<T> mapToClass);
    Mapper getMapper();
}