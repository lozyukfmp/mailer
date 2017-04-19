package by.samsolutions.imgcloud.controller.exception;

public class ControllerException extends Exception
{
	public ControllerException() {}

	public ControllerException(Exception e)
	{
		super(e);
	}

	public ControllerException(String message, Exception e)
	{
		super(message, e);
	}
}
