package FFT;
/***********************************************************
 /      filename:  WavFile.java
 /
 /   description:  Exceptions for WavFile
 /
 /       author:  A. Greensted
 / Copyright (c) A. Greensted, http://www.labbookpages.co.uk/audio/javaWavFiles.html
 **********************************************************/
public class WavFileException extends Exception
{
    public WavFileException()
    {
        super();
    }

    public WavFileException(String message)
    {
        super(message);
    }

    public WavFileException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public WavFileException(Throwable cause)
    {
        super(cause);
    }
}
