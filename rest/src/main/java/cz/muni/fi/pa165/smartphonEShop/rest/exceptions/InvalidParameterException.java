package cz.muni.fi.pa165.smartphonEShop.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Roman Nahalka
 * @author xnahalka
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class InvalidParameterException extends RuntimeException
{

}
