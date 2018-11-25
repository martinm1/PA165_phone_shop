package cz.muni.fi.pa165.smartphonEShop.service.facade;

import com.github.dozermapper.core.inject.Inject;
import cz.muni.fi.pa165.smartphonEShop.service.service.BeanMappingService;
import cz.muni.fi.pa165.smartphonEShop.service.service.PersonService;
import org.mockito.Mock;
import org.mockito.Spy;

/**
 *
 * @author martin
 */
public class PersonFacadeTest {
    @Mock
    private PersonService personService;
    
    @Spy
    @Inject
    private BeanMappingService mappingService;
}
