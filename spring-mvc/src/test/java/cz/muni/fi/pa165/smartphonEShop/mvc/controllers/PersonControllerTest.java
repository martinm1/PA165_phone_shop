package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.PersonDTO;
import cz.muni.fi.pa165.smartphonEShop.facade.PersonFacade;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Jakub Ondrusek
 * Class represents: Tests for Person mvc controller.
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
        personDTO.setEmail("haha@mail.com");
        personDTO.setPhoneNumber("0909123123");
    }

    @Test
    public void testView () throws Exception {
        when(personFacade.findPersonById(personDTO.getId())).thenReturn(personDTO);

        this.mockMvc.perform(get("/person/view/111")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("person", personDTO))
                .andExpect(forwardedUrl("person/view"));

    }

    @Test
    public void testNewPerson () throws Exception{
        this.mockMvc.perform(get("/person/new")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("personCreate"))
                .andExpect(forwardedUrl("person/new"));
    }

    @Test
    public void testListAll () throws Exception {
        List<PersonDTO> people = Collections.singletonList(personDTO);

        when(personFacade.getAllPeople()).thenReturn(people);
        this.mockMvc.perform(get("/person/list/all")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("people", people))
                .andExpect(forwardedUrl("person/list"));
    }

    @Test
    public void testListByEmail () throws Exception{


        when(personFacade.findPersonByEmail("haha@mail.com")).thenReturn(personDTO);

        this.mockMvc.perform(get("/person/list/byEmail?email=haha@mail.com")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("person", personDTO))
                .andExpect(forwardedUrl("person/list"));

    }

    @Test
    public void testListByPhoneNumber () throws Exception{
        when(personFacade.findPersonByPhoneNumber("0909123123")).thenReturn(personDTO);

        this.mockMvc.perform(get("/person/list/byPhoneNumber?phoneNumber=0909123123")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("person", personDTO))
                .andExpect(forwardedUrl("person/list"));
    }

    @Test
    public void testListByPersonTypeUnsigned () {

    }

    @Test
    public void testListByPersonTypeSigned () {

    }

    @Test
    public void testListByPersonTypeAdmin () {

    }

    @Test
    public void testListByPersonTypeEmployee () {

    }

}
