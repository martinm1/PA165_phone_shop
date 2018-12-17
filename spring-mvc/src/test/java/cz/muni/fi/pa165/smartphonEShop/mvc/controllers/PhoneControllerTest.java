package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.PhoneCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.PhoneDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.StockDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import cz.muni.fi.pa165.smartphonEShop.facade.PhoneFacade;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Roman Nahalka
 * Class represents: Tests for phone mvc controller.
 * @author xnahalka
 */
@WebAppConfiguration
public class PhoneControllerTest
{
    @Mock
    private PhoneFacade phoneFacade;

    private PhoneDTO phoneDTO;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        PhoneController phoneController = new PhoneController();
        phoneController.setPhoneFacade(phoneFacade);

        mockMvc = MockMvcBuilders.standaloneSetup(phoneController).build();
    }

    @BeforeMethod
    public void testPrepare()
    {
        phoneDTO = new PhoneDTO();
        phoneDTO.setId(10L);
    }

    @Test
    public void listByManufacturerTest() throws Exception
    {
        List<PhoneDTO> phones = Collections.singletonList(phoneDTO);

        when(phoneFacade.findPhonesByManufacturer(Manufacturer.HTC)).thenReturn(phones);

        this.mockMvc.perform(get("/phone/list/htc")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("phones", phones))
                .andExpect(forwardedUrl("phone/list"));
    }

    @Test
    public void nonExistingManufacturerTest() throws Exception
    {
        this.mockMvc.perform(get("/phone/list/pear")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("alert_danger"))
                .andExpect(forwardedUrl("phone/list"));
    }

    @Test
    public void listAllTest() throws Exception
    {
        List<PhoneDTO> phones = Collections.singletonList(phoneDTO);

        when(phoneFacade.getAllPhones()).thenReturn(phones);

        this.mockMvc.perform(get("/phone/list/all")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("phones", phones))
                .andExpect(forwardedUrl("phone/list"));
    }

    @Test
    public void listByTechnicalInfo() throws Exception
    {
        List<PhoneDTO> phones = Collections.singletonList(phoneDTO);

        when(phoneFacade.findPhonesByTechnicalInfo("je to mobil")).thenReturn(phones);

        this.mockMvc.perform(get("/phone/list/byTechnicalInfo?technicalInfo=je to mobil")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("phones", phones))
                .andExpect(forwardedUrl("phone/list"));
    }

    @Test
    public void listByStockIdTest() throws Exception
    {
        List<PhoneDTO> phones = Collections.singletonList(phoneDTO);

        when(phoneFacade.findPhonesByStockID(5L)).thenReturn(phones);

        this.mockMvc.perform(get("/phone/list/byStockId?stockId=5")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("phones", phones))
                .andExpect(forwardedUrl("phone/list"));
    }

    @Test
    public void listByPriceTest() throws Exception
    {
        List<PhoneDTO> phones = Collections.singletonList(phoneDTO);

        when(phoneFacade.findPhonesByPriceInterval(100, 500)).thenReturn(phones);

        this.mockMvc.perform(get("/phone/list/byPrice?priceLow=100&priceHigh=500")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("phones", phones))
                .andExpect(forwardedUrl("phone/list"));
    }

    @Test
    public void listByModelNameTest() throws Exception
    {
        List<PhoneDTO> phones = Collections.singletonList(phoneDTO);

        when(phoneFacade.findPhonesByModelName("model")).thenReturn(phones);

        this.mockMvc.perform(get("/phone/list/byModelName?modelName=model")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("phones", phones))
                .andExpect(forwardedUrl("phone/list"));
    }

    @Test
    public void newTest() throws Exception
    {
        this.mockMvc.perform(get("/phone/new")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("phoneCreate"))
                .andExpect(forwardedUrl("phone/new"));
    }

    @Test
    public void viewTest() throws Exception
    {
        when(phoneFacade.findPhoneById(phoneDTO.getId())).thenReturn(phoneDTO);

        this.mockMvc.perform(get("/phone/view/10")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("phone", phoneDTO))
                .andExpect(forwardedUrl("phone/view"));
    }

    @Test
    public void createTest() throws Exception
    {
        PhoneCreateDTO phone = new PhoneCreateDTO();

        phone.setModelName("model");
        phone.setPrice(250);
        phone.setTechnicalInfo("nejake informace");
        phone.setManufacturer(Manufacturer.HTC);
        phone.setStock(new StockDTO());

        when(phoneFacade.createPhone(phone)).thenReturn(5L);

        this.mockMvc.perform(post("/phone/create")
                .flashAttr("phoneCreate", phone)
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(flash().attributeExists("alert_success"))
                .andExpect(status().is3xxRedirection());
    }
}
