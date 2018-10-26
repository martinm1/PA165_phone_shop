package cz.muni.fi.pa165.smartphonEShop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Roman Nahalka
 */
@ContextConfiguration //TODO: Persistence Context
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PhoneDaoTest extends AbstractTestNGSpringContextTests
{
    @Autowired
    private PhoneDao phone;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void findAll()
    {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        phone1.setModelName("S6");
        phone2.setModelName("S7");

        phone.create(phone1);
        phone.create(phone2);

        List<Phone> phones = phone.findAll();
        Assert.assertEquals(phones.size(), 2);

        Phone assertPhone1 = new Phone();
        Phone assertPhone2 = new Phone();

        assertPhone1.setModelName("S6");
        assertPhone2.setModelName("S7");

        Assert.assertTrue(phones.contains(assertPhone1));
        Assert.assertTrue(phones.contains(assertPhone2));
    }

    @Test
    public void delete()
    {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        phone1.setModelName("S6");
        phone2.setModelName("S7");

        phone.create(phone1);
        phone.create(phone2);

        List<Phone> phones = phone.findAll();
        Assert.assertEquals(phones.size(), 2);

        phone.delete(phone1);
        Assert.assertEquals(phones.size(), 1);
        Assert.assertFalse(phones.contains(phone1));

        phone.delete(phone2);
        Assert.assertEquals(phones.size(), 0);
    }

    @Test
    public void findById()
    {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        phone1.setModelName("S6");
        phone2.setModelName("S7");

        phone.create(phone1);
        phone.create(phone2);

        Assert.assertEquals("S6", phone.findById(phone1.getId()).getModelName());
        Assert.assertEquals("S7", phone.findById(phone2.getId()).getModelName());
        Assert.assertNotEquals("S7", phone.findById(phone1.getId()).getModelName());
        Assert.assertNotEquals("S6", phone.findById(phone2.getId()).getModelName());

    }

    @Test
    public void update()
    {
        Phone phone1 = new Phone();

        phone1.setModelName("S7");

        phone.create(phone1);

        phone1.setModelName("S6");
        phone.update(phone1);

        Assert.assertNotEquals("S7", phone.findById(phone1.getId()).getModelName());
        Assert.assertEquals("S6", phone.findById(phone1.getId()).getModelName());
    }
}
