package cz.muni.fi.pa165.smartphonEShop.exceptions;

import org.springframework.dao.DataAccessException;

import java.util.zip.DataFormatException;

/**
 * Created by Stefan Holecko
 * Class represents: Custom exception that extends DataAccessException
 */

public class DAOException extends DataAccessException {

    public DAOException(String msg) {
        super(msg);
    }

    public DAOException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
