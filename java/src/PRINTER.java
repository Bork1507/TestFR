import java.util.Date;

/**
 * Created by s.baryagin on 11.05.2017.
 */
abstract class PRINTER extends PosDevice
{
    private boolean _wrileLog=false;

    public static String getErrorDetails(int error)
    {
        String str="";

        switch (error)
        {
            default:
                str=PosDevice.getErrorDetails(error);
                break;
        }

        return str;
    }

    abstract public String deviceType();

    abstract public void openPort(String portName, String baud) throws FrException;

    abstract public void closePort();

    abstract public String getKkmType() throws FrException;

    abstract public String getKkmVersion() throws FrException;

    abstract public String getSerialNumber() throws FrException;

    abstract public int init() throws FrException;

    abstract public int printText(String text) throws FrException;

    abstract public int printTextEx(String text, int mask) throws FrException;

    abstract public int printQrCode(String url) throws FrException;

    abstract public int eraseLogotype() throws FrException;
    abstract public int loadLogotype(String filePath) throws FrException;
    abstract public int printLogotype() throws FrException;
}



