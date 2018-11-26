package cz.muni.fi.pa165.smartphonEShop.service.config;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import cz.muni.fi.pa165.smartphonEShop.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.smartphonEShop.dto.OrderDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.service.facade.OrderFacadeImpl;
import cz.muni.fi.pa165.smartphonEShop.service.service.AddressServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Roman Nahalka
 * Class represents: Service configuration.
 * @author xnahalka
 */
@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses = {AddressServiceImpl.class, OrderFacadeImpl.class})
public class ServiceConfiguration
{
    @Bean
    public Mapper dozer()
    {
        Mapper dozer = DozerBeanMapperBuilder.buildDefault();

        return dozer;
    }

    public class DozerCustomConfig extends BeanMappingBuilder
    {
        @Override
        protected void configure()
        {
            mapping(Order.class, OrderDTO.class);
        }
    }
}
