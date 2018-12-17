package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dao.OrderDao;
import cz.muni.fi.pa165.smartphonEShop.dao.PersonDao;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.entity.Person;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.util.List;
import java.security.SecureRandom;
/**
 * Created by Jakub Ondrusek
 * Class represents: implementation of PersonService
 */

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDao personDao;
    
    @Autowired
    private OrderDao orderDao;

    @Override
    public Person findPersonById(Long id) {
        return personDao.findById(id);
    }

    @Override
    public Person findPersonByEmail(String email) {
        return personDao.findByEmail(email);
    }

    @Override
    public Person findPersonByPhoneNumber(String phoneNumber) {
        return personDao.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<Person> getAllPeople() {
        return personDao.findAll();
    }

    @Override
    public List<Person> getPeopleByPersonType(PersonType personType) {
        return personDao.findByPersonType(personType);
    }

    @Override
    public void addOrder(Long personId, Long orderId) {
        Person person = personDao.findById(personId);
        Order order = orderDao.findById(orderId);

        person.addOrder(order);
        personDao.update(person);
    }

    @Override
    public void registerPerson(Person person, String pass) {
        person.setPasswordHash(createHash(pass));
        personDao.create(person);
    }

    @Override
    public void removeOrder(Long personId, Long orderId) {
        //personDao.create(person);
        personDao.findById(personId).removeOrder(orderDao.findById(orderId));
    }

    @Override
    public boolean auth(String email, String pass) {
        return validatePassword(pass, personDao.findByEmail(email).getPasswordHash());
    }

    //see  https://crackstation.net/hashing-security.htm#javasourcecode
    private static String createHash(String password) {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validatePassword(String password, String correctHash) {
        if(password==null) return false;
        if(correctHash==null) throw new IllegalArgumentException("password hash is null");
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }

    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }
}
