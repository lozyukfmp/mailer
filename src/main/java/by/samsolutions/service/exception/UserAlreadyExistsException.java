package by.samsolutions.service.exception;

public class UserAlreadyExistsException extends ServiceException
{

	public UserAlreadyExistsException()
	{

	}

	public UserAlreadyExistsException(Exception e)
	{
		super(e);
	}

	public UserAlreadyExistsException(String message, Exception e)
	{
		super(message, e);
	}
}
