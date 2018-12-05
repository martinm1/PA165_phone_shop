package cz.muni.fi.pa165.smartphonEShop.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Roman Nahalka
 * @author xnahalka
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "The enitity already exists.")
public class ResourceAlreadyExistingException extends RuntimeException
{

}
