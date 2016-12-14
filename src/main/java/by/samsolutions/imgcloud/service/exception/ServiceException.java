package by.samsolutions.imgcloud.service.exception;

public class ServiceException extends Exception
{
	public ServiceException()
	{

	}

	public ServiceException(Exception e)
	{
		super(e);
	}

	public ServiceException(String message, Exception e)
	{
		super(message, e);
	}
}
