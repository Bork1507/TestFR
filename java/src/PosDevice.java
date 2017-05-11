/**
 * Created by s.baryagin on 11.05.2017.
 */

class FrException extends Exception
{

    private String _errorCode;
    private String _errorDetail;


    FrException(String errorCode, String errorDetail)
    {
        _errorCode=errorCode;
        _errorDetail=errorDetail;
    }

    public int getErrorCodeAsInt()
    {
        return Integer.parseInt(_errorCode);
    }

    public String getErrorCodeAsString()
    {
        return _errorCode;
    }

    public String toString()
    {
        return "FrException : Error - "+_errorCode+" - "+_errorDetail;
    }
}

abstract class PosDevice
{
    public static final int ERROR_PORT          =1001;
    public static final int ERROR_CONNECT       =1002;
    public static final int END_OF_FILE         =1003;
    public static final int NO_RESPONSE_FR      =1004;
    public static final int FILE_NOT_FOUND      =1005;
    public static final int FR_IS_NOT_SUPPORTED =1006;
    public static final int FR_NOT_FISCAL       =1007;
    public static final int ERROR_PAY_TYPE      =1008;
    public static final int ERROR_SET_BAUD      =1010;
    public static final int ANY_LOGICAL_ERROR   =1011;
    public static final int ERROR_SEND          =1013;

    public static String getErrorDetails(int error)
    {
        String str="Error ";
        str+=error;
        str+="!";
        switch (error)
        {
            case ERROR_PORT:
                str="Error 1001! Error opening port!";
                break;
            case ERROR_CONNECT:
                str="Error 1002! Error connect!";
                break;
            case END_OF_FILE:
                str="Error 1003! End of file!";
                break;
            case NO_RESPONSE_FR:
                str="Error 1004! Error connection!";
                break;
            case FILE_NOT_FOUND:
                str="Error 1005! File not found!";
                break;
            case FR_IS_NOT_SUPPORTED:
                str="Error 1006! POS type not supported!!";
                break;
            case FR_NOT_FISCAL:
                str="Error 1007! POS not fiskal!";
                break;
            case ERROR_PAY_TYPE:
                str="Error 1008! Error pay type!";
                break;
            case ERROR_SET_BAUD:
                str="Error 1010! Error set baud!";
                break;
            case ANY_LOGICAL_ERROR:
                str="Error 1011! Any logical error!";
                break;
            case ERROR_SEND:
                str="Error 1013! Error sending command!";
                break;
            default:
                break;
        }

        return str;
    }
}
