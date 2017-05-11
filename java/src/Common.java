import java.util.*;

import java.text.SimpleDateFormat;

import java.io.*;

import java.nio.file.*;


class Common
{
	public static void log(String str)
	{
		logConsole(str);
		logFile(str);
	}

	public static void logConsole(String str)
	{
		// String consoleEncoding = System.getProperty("consoleEncoding");
		// if (consoleEncoding != null) {
		//     try {
		//         System.setOut(new PrintStream(System.out, true, consoleEncoding));
		//     } catch (java.io.UnsupportedEncodingException ex) {
		//         System.err.println("Unsupported encoding set for console: "+consoleEncoding);
		//     }
		// }

		// System.out.println(consoleEncoding);



		String strDateTime;
		
		Date dt= new Date();
		strDateTime = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss:SSS - ").format(dt);

		System.out.printf(strDateTime);
		System.out.println(str);
	}

	public static void logFile(String str)
	{
		String strDateTime;
		String strFileName;
		String strSlash="/";
		String strPath="Logs";

		try {Files.createDirectory(Paths.get(strPath));}
		catch(IOException e){}

		
		Date dt= new Date();
		strDateTime = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss:SSS - ").format(dt);
		strFileName = strPath+strSlash;
		strFileName += new SimpleDateFormat("yyyyMMdd").format(dt);;
		strFileName += ".log";

		File file = new File(strFileName);

		try 
		{
			if(!file.exists())
			{
				file.createNewFile();
			}

			FileWriter out = new FileWriter(file.getAbsoluteFile(), true);

			try 
			{
				out.write(strDateTime+str+'\n');
			} 
			finally 
			{
				out.close();
			}
		} 
		catch(IOException e) 
		{
			throw new RuntimeException(e);
	    }

	}

	public static String[] cutString(String bigString, int cutLength){

		int arrayLength = bigString.length()/cutLength;
		if (bigString.length()%cutLength>0) arrayLength+=1;

		String cuttedString[] = new String[arrayLength];
		int i=0;
		for(; i<arrayLength-1; i++){
			cuttedString[i]=bigString.substring(i*cutLength, (i+1)*cutLength);
		}
		cuttedString[i]=bigString.substring(i*cutLength, bigString.length());

		return cuttedString;
	}

	public static String rightJustified(String str, char ch, int length)
	{
		String out=str;
		while (out.length()<length)
		{
			out=ch+out;
		}
		return out;
	}

	public static String leftJustified(String str, char ch, int length)
	{
		String out=str;
		while (out.length()<length)
		{
			out+=ch;
		}
		return out;
	}

}