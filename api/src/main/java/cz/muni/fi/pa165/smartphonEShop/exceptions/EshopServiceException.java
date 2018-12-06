package cz.muni.fi.pa165.smartphonEShop.exceptions;

/**
 * Created by Roman Nahalka
 * @author xnahalka
 */
public class EshopServiceException extends RuntimeException
{
    public EshopServiceException()
    {
        super();
    }

    public EshopServiceException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(msg, cause, enableSuppression, writableStackTrace);
    }

    public EshopServiceException(String msg, Throwable cause)
    {
        super(msg, cause);
    }

    public EshopServiceException(String msg)
    {
        super(msg);
    }

    public EshopServiceException(Throwable cause)
    {
        super(cause);
    }
}
