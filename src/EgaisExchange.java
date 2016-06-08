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
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N0000154KNI68RDB8380V50918001236357TQEGTBEPPHZIZ5VJK5IAC44G94S3AN4\"";
		myRequestBody+=" ean=\"5010677925105\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N00000ABU7Y3NH5320T5050814013006827OQC9SKWCXIPVJVTSRP1DB3UPS0U1C88\"";
		myRequestBody+=" ean=\"7791728000160\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N0000154H3W8JGL3H380L50520022012000L3WHKUF16IZPN0A17EW6TR2FSZG2NTF\"";
		myRequestBody+=" ean=\"5000387905771\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N00001CJRWZVC40B23SYO50703018047128ORV6LJXHDRUHPBVUMQUZBD05T0LJGGF\"";
		myRequestBody+=" ean=\"4850001001904\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N000000HCXJ0PYNIT01CZ50903002147876SG6ZQFFP3ZXYZ1DI97YMZ7O4IPSS0CK\"";
		myRequestBody+=" ean=\"4601033066016\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"24996430040085750070040708150021392711169983832111603920923143634743\"";
		myRequestBody+=" ean=\"4600346000045\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"19N00001CJRWZVB8O8Y1NSW312060120048954416588194167987108014168421211\"";
		myRequestBody+=" ean=\"5000329002155\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N000009MVZQKL725E0R6M50917003001677LAVCYFDSWYD85OIHW957KXS1G6LPVG9\"";
		myRequestBody+=" ean=\"3272591460199\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"19N00001CG05K3K89V01NI9310250010007442251451311001596380240124156859\"";
		myRequestBody+=" ean=\"4820001290417\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N00001CFTRV0IUSOL3SNJ505150020001767S66TH7VKL0RZZAASLA37B4AY5OYJGK\"";
		myRequestBody+=" ean=\"8437001172030\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N000000VP1G6OPPEP02HF41024001009641O31PODRDIQH868LEXFHZ8JZ4KHAJ3MN\"";
		myRequestBody+=" ean=\"4650061050573\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N00002V5ORQU6SY3V830Q509160040088430NHVAZWO49YFUUIPI5AZJ2DSBROAUHV\"";
		myRequestBody+=" ean=\"3251091508730\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N0000154NUCPRVOA03814509170010008349Y2Y4INY533H7QAJB6AX3PHLG1V1890\"";
		myRequestBody+=" ean=\"3254560048607\" price=\"123.45\" volume=\"0.7500\"/>";
		
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"19N00001CJFID3C6ORW1NRX307240150038851551072471075711478202199245694\"";
		myRequestBody+=" ean=\"4630017480142\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"20N00002V5DFE8OPGHX82ZU40116024004736IUJ9649XS10TXEUKJOESKU6YTUX471B\"";
		myRequestBody+=" ean=\"8437007139785\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N000004ZE3H2070NX0E2351105002000067HP1SN7IBN2UE3XRVVMBBWC1ISS3MTYY\"";
		myRequestBody+=" ean=\"4627099040326\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N000009MVZQKL65W10R6M50429015000253B27K0T9JTGCA4MNN17LKKKI4NX6XTZ6\"";
		myRequestBody+=" ean=\"3372370109728\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N000004928QEX3SNV0BZT51118002023658SYQUMD9PPQ7D336QL61TW4H3QNUA1IZ\"";
		myRequestBody+=" ean=\"4601728011994\" price=\"123.45\" volume=\"0.7500\"/>";
// старый
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"17N0000154KNI67TJGW12V3209030090034123116196200166182811518111925315\"";
		myRequestBody+=" ean=\"5010677925105\" price=\"123.45\" volume=\"0.7500\"/>";
		//21
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"19N00001CGTXH617FG31NKL307230420010251691814523245711231241112131844\"";
		myRequestBody+=" ean=\"9319002010230\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N000004ZE3H206RCG0E23411110050017580J0Y9596AZPS8LA4KEF73U99582MZPL\"";
		myRequestBody+=" ean=\"4627099040142\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N00001CJ9HFEEV57Y3SX851019003013811L4KEPDM8XWSJ2R7GKX2SCSRT70ZH1H4\"";
		myRequestBody+=" ean=\"3351650000214\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N00001CFTRV0IKXR63SNJ40620015004373AWUNEH7R80H2UHUYTTP61ZVC2JVTCJ9\"";
		myRequestBody+=" ean=\"9317189023944\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N00001CJRWZVC6OJ83SYO508180070021877F09FZ69T9HGA7S5KKGDEMN5S57X6S5\"";
		myRequestBody+=" ean=\"8410302121006\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N000004UL44Y8JZOX0DOJ50512001005643S2FA4V7VXNNK4PL8KXT989XC6NUNQ5L\"";
		myRequestBody+=" ean=\"4690363025220\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N00001CFTRV0IMML73SNJ41111080010301MF46F4REWBAZUF5T40Y0OJ7OY21E022\"";
		myRequestBody+=" ean=\"3500610075926\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N00001CHYOZFQT9RV3STK50903009002304AB47O82ZE364SSNHWARRTK9UDXHU32N\"";
		myRequestBody+=" ean=\"8436014671226\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N000004ZB9DWMQL2U0E1V51201002001966IMX3HEGBVD4I12J6YJ5VBHL2I5R37RL\"";
		myRequestBody+=" ean=\"4627114840153\" price=\"123.45\" volume=\"0.7500\"/>";
		// myRequestBody+="<Bottle";
		// myRequestBody+=" barcode=\"2105300080725300300JY2100300200080654750000000000000000000000000000\"";
		// myRequestBody+=" ean=\"4603928000976\" price=\"123.45\" volume=\"0.7500\"/>";
		// myRequestBody+="<Bottle";
		// myRequestBody+=" barcode=\"123456784435425\"";
		// myRequestBody+=" ean=\"5000329002292\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N0000154QOFV53RMZ381C40907002044533SRXKCCZ1RF3NII637HOIJCAPZS2CUSM\"";
		myRequestBody+=" ean=\"4650001040374\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"19N0000154L09KFNYU6380W312170220029951201621020575120105237215512861\"";
		myRequestBody+=" ean=\"3049197110168\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N0000033EH7UHVUPO08QA4100100303090145XIHD1SDDE6DA1O03T7YZY4JGRSBMT\"";
		myRequestBody+=" ean=\"4601823001050\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N00001CJRWZVBWGCB3SYO50402014019489CDLF47BWRLEN6HB8SWDGCH951CPBO8W\"";
		myRequestBody+=" ean=\"7312040017072\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"15N00001CL691RP22ML1NWU111260061030592498232236451202208291851611812\"";
		myRequestBody+=" ean=\"4620008910765\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N00001525T2Q5SLYG37U250522002003095T3PC5JUGUYM53CJDNHWAYW9ORAMSMGW\"";
		myRequestBody+=" ean=\"8006063006807\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N000000HCXJ0PUEL701CZ50417004064619T0IPP272NZXCNMWO240LTAV495LGVKB\"";
		myRequestBody+=" ean=\"4650061050535\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"15N000000AEBJPBNHRN001K112050080112241316642391131610297117714152185\"";
		myRequestBody+=" ean=\"4607122942018\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"10N000004UPQ12G7BZ505Z4009290310006991292401161291342679235461183195\"";
		myRequestBody+=" ean=\"4850092000176\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"12N00000XOCIVJ9QVD40HUE104040010057719921441291351814096912361331810\"";
		myRequestBody+=" ean=\"4620002630539\" price=\"123.45\" volume=\"0.7500\"/>";
		//40
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N000000X2NZAQ23XQ02LB50921001029179AFCRLP7VWTJXIZ9OUZMYU1EHKB8XMGW\"";
		myRequestBody+=" ean=\"4620008990972\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N0000040WDX2KYKJV0BCS50421001017782SWMP65SUO2I6C6F62EN4LPV6L6R9NVW\"";
		myRequestBody+=" ean=\"4601249003850\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N00002NS4CKHM0BF37I7J41020016003908K3ZROTPC2FBXTRR0GEYRSHLPW2ZB06B\"";
		myRequestBody+=" ean=\"3114080016053\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"21N00001CIRRDPSUIDT3SVU40422023000774ENZNCC7I1QGCZMPLOYZFK09RDS3T63V\"";
		myRequestBody+=" ean=\"8032644312029\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N000008UZF8Y0T10P0OZW510210030010787DJKJKIYK1DHNKZSDLM4TQVOTQX2HMZ\"";
		myRequestBody+=" ean=\"4630017480142\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"22N000003DZJ812CHCH09K5504270010520669XFY75J2DD4U9LHJI85L2HEAICZZSZ5\"";
		myRequestBody+=" ean=\"4607026340118\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"19N0000154PYX2UHR5T12VI311120180003329641165113331632398824211942206\"";
		myRequestBody+=" ean=\"5602014100599\" price=\"123.45\" volume=\"0.7500\"/>";
		myRequestBody+="<Bottle";
		myRequestBody+=" barcode=\"15N00001CJ0Z5Y9LGZX1NQS109220020029322411471043747752402222217175193\"";
		myRequestBody+=" ean=\"7790470005904\" price=\"123.45\" volume=\"0.7500\"/>";



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