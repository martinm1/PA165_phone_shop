package cz.muni.fi.pa165.smartphonEShop.mvc.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AuthException extends AuthenticationException
{
    public AuthException(String msg)
    {
        super(msg);
    }

    public AuthException(String msg, Throwable throwable)
    {
        super(msg, throwable);
    }
}
