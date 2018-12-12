package cz.muni.fi.pa165.smartphonEShop.rest;

import cz.muni.fi.pa165.smartphonEShop.RootWebContext;
import cz.muni.fi.pa165.smartphonEShop.dto.PersonDTO;
import cz.muni.fi.pa165.smartphonEShop.facade.PersonFacade;
import cz.muni.fi.pa165.smartphonEShop.rest.controllers.PeopleController;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by Roman Nahalka
 * Class represents: Tests for order mvc controller.
 * @author xnahalka
 */
@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
public class PeopleControllerTest extends AbstractTestNGSpringContextTests
{
    @Mock
    private PersonFacade personFacade;

    @Autowired
    @InjectMocks
    private PeopleController peopleController;

    private MockMvc mockMvc;

    private PersonDTO person;

    @BeforeClass
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(peopleController).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @BeforeMethod
    public void testPrepare()
    {
        PersonDTO person = new PersonDTO();

        person.setId(10L);
        person.setEmail("neco@tamto.cz");
        person.setFirstName("Roman");
        person.setLastName("Nahalka");
        person.setPhoneNumber("777666999");
    }

    @Test
    public void getPeopleTest() throws Exception
    {
        when(personFacade.getAllPeople()).thenReturn(Collections.singletonList(person));

        this.mockMvc.perform(get("/people"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==10)].lastName").value("Nahalka"));
    }

    @Test
    public void getPersonTest() throws Exception
    {
        when(personFacade.findPersonById(person.getId())).thenReturn(person);

        this.mockMvc.perform(get("/people/10"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.lastName").value("Nahalka"));
    }

    @Test
    public void getPersonByMailTest() throws Exception
    {
        when(personFacade.findPersonByEmail(person.getEmail())).thenReturn(person);

        this.mockMvc.perform(get("/people/neco@tamto.cz"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.email").value("neco@tamto.cz"));
    }

    @Test
    public void getPersonByPhoneNumberTest() throws Exception
    {
        when(personFacade.findPersonByPhoneNumber(person.getPhoneNumber())).thenReturn(person);

        this.mockMvc.perform(get("/people/777666999"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.phoneNumber").value("777666999"));
    }
}
