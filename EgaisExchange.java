import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
import java.text.SimpleDateFormat;



//throws IOException, MalformedURLException

public class EgaisExchange
{
	private static String _boundary="7d33188e01e4";
	private static String _host="192.168.25.109";

	private static String _userFsRarId="030000112233";
	private static String _userInn="7722334455";
	private static String _userKpp="773322110";

	EgaisExchange(String host, String userFsRarId, String userInn, String userKpp)
	{
		_host=host;
		_userFsRarId=userFsRarId;
		_userInn=userInn;
		_userKpp=userKpp;
	}

	private static String getCurrentDateTime()
	{
		String strDate;
		
		Date dt= new Date();
		strDate = new SimpleDateFormat("ddMMyyHHmm").format(dt);
		
		return strDate;
	}

	public static boolean sendPartnerRequest()
	{
		boolean result=true;

		String myRequestStart="";
		String myRequestBody="";
		String myRequestEnd="";


		byte byteOrderMark[] = {(byte)0xEF, (byte)0xBB, (byte)0xBF};

		myRequestStart+="--"+_boundary+"\r\n";
		myRequestStart+="Content-Disposition: form-data; name=\"xml_file\"; filename=\"ClientSP.xml\"\r\n";
		myRequestStart+="\r\n";

		myRequestBody+="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		myRequestBody+="<ns:Documents Version=\"1.0\"";
		myRequestBody+="           xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
		myRequestBody+="           xmlns:ns=\"http://fsrar.ru/WEGAIS/WB_DOC_SINGLE_01\"";
		myRequestBody+="           xmlns:oref=\"http://fsrar.ru/WEGAIS/ClientRef\"";
		myRequestBody+="           xmlns:qp=\"http://fsrar.ru/WEGAIS/QueryParameters\"";
		myRequestBody+=">";
		myRequestBody+="  <!--Кто запрашивает документы-->";
		myRequestBody+="  <ns:Owner>";
		myRequestBody+="    <!--Идентификатор организации в ФС РАР-->";
		myRequestBody+="    <ns:FSRAR_ID>"+_userFsRarId+"</ns:FSRAR_ID>";
		myRequestBody+="  </ns:Owner>";
		myRequestBody+="  <!--Запрос на организацию -->";
		myRequestBody+="  <ns:Document>";
		myRequestBody+="    <ns:QueryClients>";
		myRequestBody+="      <qp:Parameters>";
		myRequestBody+="        <qp:Parameter>";
		myRequestBody+="          <qp:Name>ИНН</qp:Name>";
		myRequestBody+="          <qp:Value>"+_userInn+"</qp:Value>";
		myRequestBody+="        </qp:Parameter>";
		myRequestBody+="      </qp:Parameters>";
		myRequestBody+="    </ns:QueryClients>";
		myRequestBody+="  </ns:Document>";
		myRequestBody+="</ns:Documents>";

		myRequestEnd+="\r\n";
		myRequestEnd+="--"+_boundary+"--";

		URL url=null;
		HttpURLConnection con=null;
		try
		{
			url = new URL("http", _host, 8080, "/opt/in/QueryPartner");
			con = (HttpURLConnection) url.openConnection();	
		}
		catch(IOException ex)
		{
			System.out.printf("openConnection");
			result=false;
		}

		try
		{
			con.setRequestMethod("POST");
			con.setRequestProperty("Host", _host);
			//con.setRequestProperty("Accept", "*/*");
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+_boundary);
			//con.setRequestProperty("Content-Length", Integer.toString( myRequest.length() ));

			con.setDoOutput(true);

		}
		catch(IOException ex)
		{
			System.out.printf("setRequestMethod");
			result=false;

		}

		OutputStream os=null;
		try
		{
			os = con.getOutputStream();
			os.write(myRequestStart.getBytes("UTF-8"));
			os.write(byteOrderMark);
			os.write(myRequestBody.getBytes("UTF-8"));
			os.write(myRequestEnd.getBytes("UTF-8"));
			os.flush();
			os.close();

			System.out.println("Sent a package of " + (myRequestStart.length()+byteOrderMark.length+myRequestBody.length()+myRequestEnd.length())+ " bytes.");

		}
		catch(IOException ex)
		{
			System.out.printf("write");
			result=false;

		}



		try
		{
			BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
			for (String line = bf.readLine(); line != null; line = bf.readLine())
			{
				//body.append(line);
				System.out.printf(line);
			}
		}
		catch(IOException ex)
		{
			System.out.println(ex.toString());
			result=false;
		}


		return result;
	}



	public static String executeChequeExchange()
	{
		String result="";

		String myRequestStart="";
		String myRequestBody="";
		String myRequestEnd="";


		byte byteOrderMark[] = {(byte)0xEF, (byte)0xBB, (byte)0xBF};

		myRequestStart+="--"+_boundary+"\r\n";
		myRequestStart+="Content-Disposition: form-data; name=\"xml_file\"; filename=\"Cheque.xml\"\r\n";
		myRequestStart+="\r\n";

		myRequestBody+="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		myRequestBody+="<Cheque";
		myRequestBody+=" inn=\""+_userInn+"\"";
		myRequestBody+=" datetime=\""+getCurrentDateTime()+"\"";
		myRequestBody+=" kpp=\""+_userKpp+"\"";
		myRequestBody+=" kassa=\"45664\"";
		myRequestBody+=" address=\"г. Москва, ул. Никопольская,4\"";
		myRequestBody+=" name=\"Гармония\"";
		myRequestBody+=" number=\"45\"";
		myRequestBody+=" shift=\"1\"";
		myRequestBody+=">";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N0000154KNI68VP9Z380V50907004002565F9THBEKKU58NAF3EVCM832M84VW9KDS\"";
		myRequestBody+=" ean=\"8000570552505\" price=\"150.00\" volume=\"1.0000\"/>";
		// myRequestBody+="<Bottle";
		// myRequestBody+=" barcode=\"19N00000XOPN13MM66T0HVF311220130003476539219152175585956302712947109\"";
		// myRequestBody+=" ean=\"177736216338\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="</Cheque>";

		myRequestEnd+="\r\n";
		myRequestEnd+="--"+_boundary+"--";

		URL url=null;
		HttpURLConnection con=null;
		try
		{
			url = new URL("http", _host, 8080, "/xml");
			con = (HttpURLConnection) url.openConnection();	
		}
		catch(IOException ex)
		{
			System.out.printf("openConnection");
		}

		try
		{
			con.setRequestMethod("POST");
			con.setRequestProperty("Host", _host);
			//con.setRequestProperty("Accept", "*/*");
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+_boundary);
			//con.setRequestProperty("Content-Length", Integer.toString( myRequest.length() ));

			con.setDoOutput(true);

		}
		catch(IOException ex)
		{
			System.out.printf("setRequestMethod");
		}

		OutputStream os=null;
		try
		{
			os = con.getOutputStream();
			os.write(myRequestStart.getBytes("UTF-8"));
			os.write(byteOrderMark);
			os.write(myRequestBody.getBytes("UTF-8"));
			os.write(myRequestEnd.getBytes("UTF-8"));
			os.flush();
			os.close();

			System.out.println(myRequestStart);
			System.out.println(byteOrderMark);
			System.out.println(myRequestBody);
			System.out.println(myRequestEnd);
			System.out.println("Sent a package of " + (myRequestStart.length()+byteOrderMark.length+myRequestBody.length()+myRequestEnd.length())+ " bytes.");

		}
		catch(IOException ex)
		{
			System.out.printf("write");
		}


		String response="";

		try
		{
			BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
			//for (response = bf.readLine(); response != null; response += bf.readLine())
			String line="";
			while (( line = bf.readLine()) != null)
			{
				response+=line;
			}
			System.out.println(response);
		}
		catch(IOException ex)
		{
			System.out.println(ex.toString());
		}

		result=response.substring(response.indexOf("<url>")+5, response.indexOf("</url>"));



		return result;
	}


	
}