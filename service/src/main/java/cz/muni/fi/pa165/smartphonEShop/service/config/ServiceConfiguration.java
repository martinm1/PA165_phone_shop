package cz.muni.fi.pa165.smartphonEShop.service.config;

import cz.muni.fi.pa165.smartphonEShop.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.smartphonEShop.dto.OrderDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.service.facade.OrderFacadeImpl;
import cz.muni.fi.pa165.smartphonEShop.service.service.PhoneServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses = {PhoneServiceImpl.class, OrderFacadeImpl.class})
public class ServiceConfiguration
{
    @Bean
    public Mapper dozer()
    {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    private class DozerCustomConfig extends BeanMappingBuilder
    {

        @Override
        protected void configure()
        {
            mapping(Order.class, OrderDTO.class);
        }
    }
}
