import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * Created by s.baryagin on 11.05.2017.
 */
public class ArrayOfBytes
{
    private boolean _wrileLog=false;

    private byte bytesArray[]=new byte [0];

    ArrayOfBytes()
    {

    }

    ArrayOfBytes(int ... addBytes)
    {
        for(int i=0;i<addBytes.length;i++) this.append(addBytes[i]);
        //for(byte i:addBytes) this.append(i);
    }

    ArrayOfBytes(byte addByte)
    {
        this.append(addByte);
    }

    ArrayOfBytes(byte addBytes[])
    {
        this.append(addBytes);
    }

    public byte at(int i)
    {
        return bytesArray[i];
    }

    public int atInt(int i)
    {
        return bytesArray[i];
    }

    public int atUnsignedInt(int i)
    {
        return bytesArray[i]&0xFF;
    }

    public String atHex(int i)
    {
        int localInt=0;

        if(bytesArray[i]>0) localInt=bytesArray[i];
        else localInt=bytesArray[i]&0xFF;

        String result=Integer.toHexString(localInt);
        if (result.length()==1) result="0"+result;

        return result;
    }

    public void append(byte addBytes[])
    {
        byte tmp[]=new byte[bytesArray.length+addBytes.length];
        int i=0;
        for(;i<bytesArray.length;i++)
        {
            tmp[i]=bytesArray[i];
        }
        for(int j=0;j<addBytes.length;i++,j++)
        {
            tmp[i]=addBytes[j];
        }
        bytesArray=tmp;
    }

    public void append(byte addByte)
    {
        byte tmp[]=new byte[bytesArray.length+1];
        int i=0;
        for(;i<bytesArray.length;i++)
        {
            tmp[i]=bytesArray[i];
        }

        tmp[i]=addByte;
        bytesArray=tmp;
    }

    public void append(ArrayOfBytes addByte)
    {
        this.append(addByte.getBytes());
    }

    public void append(int addByte)
    {
        this.append((byte)(addByte));
    }

    public void append(String addString)
    {
        this.append(addString.getBytes());
    }

    public void append(String addString, String charsetName)
    {
        try
        {
            this.append(addString.getBytes(charsetName));
        }
        catch (UnsupportedEncodingException ie) {
            System.out.println("Unsupported character set");
        }
    }

    public void appendIntAsByteArray(int addInt)
    {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(addInt);
        this.append(bb.array());
    }

    public void appendCharAsByteArray(char addChar)
    {
        ByteBuffer bb = ByteBuffer.allocate(2);
        bb.putChar(addChar);
        this.append(bb.array());
    }


    public byte[] getBytes()
    {
        return bytesArray;
    }

    public int length()
    {
        return bytesArray.length;
    }

    public void clear()
    {
        bytesArray=new byte [0];
    }

    public boolean equals(ArrayOfBytes anotherBytesArray)
    {
        boolean result=true;

        if (this.length()==anotherBytesArray.length())
        {
            for(int i=0;i<this.length();i++)
            {
                if(this.at(i)!=anotherBytesArray.at(i))
                {
                    result=false;
                    break;
                }

                //log(String.format("%02x", this.at(i))+" == "+String.format("%02x", anotherBytesArray.at(i)));

            }
        }
        else result=false;

        return result;
    }

    public void fill(int size, int value)
    {
        this.clear();
        for(int i= 0;i<size;i++) this.append(value);
    }
    public void set(int i, int setByte)
    {
        if (i<this.length())
        {
            bytesArray[i]=(byte)setByte;
        }
    }
//		public void set(int i, byte setByte)
//		{
//			if (i<this.length())
//			{
//				bytesArray[i]=(byte)setByte;
//			}
//		}

    public ArrayOfBytes mid(int startByte, int length)
    {
        if ((this.length()-1)<startByte) return new ArrayOfBytes();
        if (length<1) return new ArrayOfBytes();

        int localStart=startByte;
        int localLength=length;
        if (startByte<0) localStart=0;
        if ((this.length()-localStart)<length) localLength=this.length()-localStart;


        return new ArrayOfBytes(java.util.Arrays.copyOfRange(bytesArray, localStart, localStart+localLength));
    }

    public ArrayOfBytes mid(int startByte)
    {
        if ((this.length()-1)<startByte) return new ArrayOfBytes();

        int localStart=startByte;
        if (startByte<0) localStart=0;

        return new ArrayOfBytes(java.util.Arrays.copyOfRange(bytesArray, localStart, this.length()-1));
    }

    public String byteToHex(byte currentByte)
    {
        int localInt=0;
        String result="";
        localInt=currentByte&0xFF;
        result=Integer.toHexString(localInt);
        return result;
    }

    public String toHex(int byteHexLength)
    {
        String result="";
        for (int i=0;i<length();i++)
        {
            result+=Common.rightJustified(byteToHex(bytesArray[i]), '0', byteHexLength);
        }
        return result;
    }

    public String toHex()
    {
        int localInt=0;
        String result="";
        for (int i=0;i<length();i++)
        {
            if(bytesArray[i]>0) localInt=bytesArray[i];
            else localInt=bytesArray[i]&0xFF;
            result+=Integer.toHexString(localInt);
        }
        return result;
    }

    public String toString()
    {
        return new String(bytesArray);
    }
    public String toString(String charsetName)
    {
        String result="";
        try
        {
            result = new String(bytesArray, charsetName);
        }
        catch(UnsupportedEncodingException ex)
        {
            result = new String(bytesArray);
        }
        return result;
    }

    public int indexOf(byte key)
    {
        for (int i=0; i<this.length();i++)
        {
            if (this.at(i)==key) return i;
        }
        return -1; //java.util.Arrays.binarySearch(bytesArray, key);
    }

    public int indexOf(int key)
    {
        return this.indexOf((byte) key);
    }

    public ArrayOfBytes rightJustified(char ch, int length)
    {
        if (_wrileLog) Common.log("rightJustifiedArrayOfBytes");

        ArrayOfBytes out = new ArrayOfBytes();
        while (out.length()<length-this.length())
        {
            out.append(ch);
        }
        out.append(this);
        return out;
    }

    public ArrayOfBytes leftJustified(char ch, int length)
    {
        if (_wrileLog) Common.log("leftJustifiedArrayOfBytes");

        ArrayOfBytes out = this;
        while (out.length()<length)
        {
            out.append(ch);
        }
        return out;
    }

}
