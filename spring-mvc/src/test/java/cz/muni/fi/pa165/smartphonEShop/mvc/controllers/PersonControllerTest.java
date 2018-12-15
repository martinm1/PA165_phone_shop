package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.PersonDTO;
import cz.muni.fi.pa165.smartphonEShop.facade.PersonFacade;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Jakub Ondrusek
 * Class represents: Tests for stock mvc controller.
 */
@WebAppConfiguration
public class PersonControllerTest {
    @Mock
    private PersonFacade personFacade;

    private PersonDTO personDTO;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);

        PersonController personController = new PersonController();
        personController.setPersonFacade(personFacade);

        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @BeforeMethod
    public void testPrepare() {
        personDTO = new PersonDTO();
        personDTO.setId(111L);
    }

    @Test
    public void testView () {

    }

    @Test
    public void testNewPerson () {

    }

    @Test
    public void testListAll () {

    }

    @Test
    public void testListByEmail () {

    }

    @Test
    public void testByPhoneNumber () {

    }

    @Test
    public void testByPersonTypeUnsigned () {

    }

    @Test
    public void testByPersonTypeSigned () {

    }

    @Test
    public void testByPersonTypeAdmin () {

    }

    @Test
    public void testByPersonTypeEmployee () {

    }

}
