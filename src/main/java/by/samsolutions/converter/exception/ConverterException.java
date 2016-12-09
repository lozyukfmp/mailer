package by.samsolutions.converter.exception;

public class ConverterException extends Exception
{
	public ConverterException(Exception exception)
	{
		super(exception);
	}

	public ConverterException(String message, Exception exception)
	{
		super(message, exception);
	}
}
