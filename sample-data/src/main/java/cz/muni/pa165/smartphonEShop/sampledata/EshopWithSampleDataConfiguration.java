package cz.muni.pa165.smartphonEShop.sampledata;

import cz.muni.fi.pa165.smartphonEShop.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class EshopWithSampleDataConfiguration
{
    @Autowired
    SampleDataLoadingFacade sampleData;

    @PostConstruct
    public void dataLoading() throws IOException
    {
        sampleData.loadData();
    }
}
