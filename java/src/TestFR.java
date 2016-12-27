import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

/* Стив Макконнелл (Steven C. McConnell) убедил меня, что мой стиль расположения скобочек:
if (...)
{
	...	
}
не очень правильный.
По этому, я решил попробовать располагать скобочки в более "правильном" стиле:
if (...){
	...	
}
В связи с этим, я прошу с пониманием отнестись к тому, что в коде проекта будет несколько стилей расположения.
Со временем я надеюсь прийти к какому-то однообразию.
*/

public class TestFR {
    public static void main(String[] args)  throws ClassNotFoundException, SQLException{
    	String configurationFileName="TestFR.xml";
    	String databaseFileName="TestFR.sqlite";

    	String egaisHost="";
    	String egaisUserFsRarId="";
    	String egaisUserInn="";
    	String egaisUserKpp="";

		String _param0="",
			   _param1="",
			   _param2="",
			   _param3="",
			   _param4="";

		Properties _programProperties = new Properties();

		String _timeoutBetweenReceipts="0";


	    switch (args.length-1){
	    	case 4: 
	    		_param4=args[4];
	    	case 3: 
	    		_param3=args[3];
	    	case 2: 
	    		_param2=args[2];
	    	case 1: 
	    		_param1=args[1];
	    	case 0: 
	    		_param0=args[0];
	    }

		Connection _conn=null;
		Statement _statmt=null;
		ResultSet _resSet=null;


		Class.forName("org.sqlite.JDBC");
		_conn = DriverManager.getConnection("jdbc:sqlite:"+databaseFileName);
		_statmt = _conn.createStatement();

		if (_param0.contains("?")){
			System.out.println("TestFR [cycle] [receipts in cycle] [COM] [BAUD] [FR]");
			System.out.println("Default [1] [2] [/dev/ttyS6] [19200] [SP]");
			System.out.println("FR may be SP or SHTRIH or FPRINT or PYRITE or WINCOR");
			System.out.println("For Windows example: \\>java FrTest 2 4 COM1 19200 SP");
			System.out.println("For Linux example: $>java FrTest 2 4 /dev/ttyS0 19200 SP");

			return;
		}
		else if (_param0.contains("LOAD")){
			SeparatedText separatedText = new SeparatedText();
			
			return;
		}
		else if (_param0.isEmpty()){
			try{
				File file = new File(configurationFileName);
				if(!file.exists()){
					file.createNewFile();

					_programProperties.setProperty("CYCLE", "1");
					_programProperties.setProperty("RECEIPTS", "2");
					_programProperties.setProperty("PORT", "/dev/ttyS6");
					_programProperties.setProperty("BAUD", "19200");
					_programProperties.setProperty("FR", "SP");
					_programProperties.setProperty("TIMEOUT", "1000");
					_programProperties.setProperty("TEST_LOADLOGOTYPE", "YES");
					_programProperties.setProperty("TEST_LOADSETTINGS", "YES");
					_programProperties.setProperty("TEST_TASK", "NO");
					_programProperties.setProperty("TEST_MAIN", "YES");

					FileOutputStream out = new FileOutputStream(configurationFileName);
					_programProperties.storeToXML(out, null);
					out.close();
				}

				FileInputStream in = new FileInputStream(configurationFileName);
            	_programProperties.loadFromXML(in);
		        in.close();

		        _param0 = _programProperties.getProperty("CYCLE", "1");
		        _param1 = _programProperties.getProperty("RECEIPTS", "2");
		        _param2 = _programProperties.getProperty("PORT", "/dev/ttyS6");
		        _param3 = _programProperties.getProperty("BAUD", "19200");
		        _param4 = _programProperties.getProperty("FR", "SP");
		        _timeoutBetweenReceipts = _programProperties.getProperty("TIMEOUT", "1000");

		    	egaisHost = _programProperties.getProperty("EGAIS_HOST", "192.168.25.109");
		    	egaisUserFsRarId= _programProperties.getProperty("EGAIS_USER_FSRARID", "030000112233");
		    	egaisUserInn= _programProperties.getProperty("EGAIS_USER_INN", "7722334455");
		    	egaisUserKpp= _programProperties.getProperty("EGAIS_USER_KPP", "773322110");

		    }
            catch(InvalidPropertiesFormatException ex){
				System.out.printf(ex.toString());
	        }
		    catch (FileNotFoundException ex){
				System.out.printf(ex.toString());
		    }
		    catch (IOException ex){
				System.out.printf(ex.toString());
		    }

		}
		else{
			try{
				File file = new File(configurationFileName);
				if(!file.exists()){
					file.createNewFile();
				}

				_programProperties.setProperty("CYCLE", _param0);
				_programProperties.setProperty("RECEIPTS", _param1);
				_programProperties.setProperty("PORT", _param2);
				_programProperties.setProperty("BAUD", _param3);
				_programProperties.setProperty("FR", _param4);
				_programProperties.setProperty("TIMEOUT", "1000");

				FileOutputStream out = new FileOutputStream(configurationFileName);
				_programProperties.storeToXML(out, null);
				out.close();   
			}
            catch(InvalidPropertiesFormatException ex){
				System.out.printf(ex.toString());
	        }
		    catch (FileNotFoundException ex){
				System.out.printf(ex.toString());
		    }
		    catch (IOException ex){
				System.out.printf(ex.toString());
		    }

		}

		EgaisExchange egaisEx = new EgaisExchange(egaisHost, egaisUserFsRarId, egaisUserInn, egaisUserKpp);

		FR _fr=null;
		if (_param4.equals("SP")) _fr=new SP();
		else if (_param4.equals("SHTRIH")) _fr=new SHTRIH();
		else if (_param4.equals("FPRINT")) _fr=new FPRINT();
		else if (_param4.equals("PYRITE")) _fr=new PYRITE();
		else if (_param4.equals("WINCOR")) _fr=new WINCOR();
		else if (_param4.equals("SHTRIHOLE")) _fr=new SHTRIHOLE();
		else if (_param4.equals("CW1000")) _fr=new CW1000();
		else if (_param4.equals("SPDRV")) _fr=new SPDRV();
		else if (_param4.equals("SPOLE")) _fr=new SPOLE();
		else if (_param4.equals("SPOLE1C")) _fr=new SPOLE1C();
		else if (_param4.equals("SKNO")) _fr=new SKNO();
		else return;

		try {


			_fr.openPort(_param2, _param3);
			Common.log(" ");
			Common.log(" ");
			Common.log("!!!Start program!!!");

			if (_programProperties.getProperty("TEST_LOADSETTINGS", "NO").equals("YES")) {

				for (int k = 4; k < 5; k++) {
					// Set parameters for SP
					if (_param4.equals("SP")) {

						// Compact mode. Normal = 0, Compact = 1
						String tmp = _fr.getKkmParameter(2, 0);
						tmp = Integer.toBinaryString(Integer.valueOf(tmp));
						tmp = tmp.substring(0, tmp.length() - 1);
						tmp = tmp + "0";
						_fr.setKkmParameter(2, 0, String.valueOf(Integer.valueOf(tmp, 2)));

						// Set 1-st row of header
						_fr.setKkmParameter(20, 0, "");
						if (k > 0) _fr.setKkmParameter(20, 0, "1234567890");
						// Set 2-nd row of header
						_fr.setKkmParameter(20, 1, "");
						if (k > 1) _fr.setKkmParameter(20, 1, "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ");
						// Set 3-rd row of header
						_fr.setKkmParameter(20, 2, "");
						if (k > 2) _fr.setKkmParameter(20, 2, "абвгдеёжзийклмнопрстуфхцчшщъыьэюя");
						// Set 4-th row of header
						_fr.setKkmParameter(20, 3, "");
						if (k > 3) _fr.setKkmParameter(20, 3, "!\"№;%:?*()_+@#$%^&*()-=");
					}

					_fr.init();

					_fr.openDocument(FR.RECEIPT_TYPE_SALE, "0", "Иванова", "0");
					String article = "1234567";
					String itemname = "ItemName";
					String cost = "111";
					String weight = "1";
					_fr.addItem(itemname, article, weight, cost, "0", "1");
					_fr.total();
					_fr.pay(FR.PAY_TYPE_0, "500.00", "");
					_fr.closeDocument("");

					_fr.zReport("Петрова");

					_fr.openDocument(FR.RECEIPT_TYPE_NON_FISCAL_DOCUMENT, "0", "Иванова", "0");

					_fr.printTextEx("Шрифт А межсимв.расст.ум. 41 симв в стр", 0);
					_fr.printTextEx("Шрифт А надчеркнутый", 0x04);
					_fr.printTextEx("Шрифт А выделенный", 0x08);
					_fr.printTextEx("Шрифт А двойная высота", 0x10);
					_fr.printTextEx("Шрифт А двойная ширина", 0x20);
					_fr.printTextEx("Шрифт А инверсия", 0x40);
					_fr.printTextEx("Шрифт А подчеркнутый", 0x80);
					_fr.printText("");

					_fr.printTextEx("Шрифт C межсимв.расст.2. 57 симв в стр", 1);
					_fr.printTextEx("Шрифт C надчеркнутый", 0x05);
					_fr.printTextEx("Шрифт C выделенный", 0x09);
					_fr.printTextEx("Шрифт C двойная высота", 0x11);
					_fr.printTextEx("Шрифт C двойная ширина", 0x21);
					_fr.printTextEx("Шрифт C инверсия", 0x41);
					_fr.printTextEx("Шрифт C подчеркнутый", 0x81);
					_fr.printText("");

					_fr.printTextEx("Шрифт C2 межсимв.расст.0. 72 симв в стр", 2);
					_fr.printTextEx("Шрифт C2 надчеркнутый", 0x06);
					_fr.printTextEx("Шрифт C2 выделенный", 0x0A);
					_fr.printTextEx("Шрифт C2 двойная высота", 0x12);
					_fr.printTextEx("Шрифт C2 двойная ширина", 0x22);
					_fr.printTextEx("Шрифт C2 инверсия", 0x42);
					_fr.printTextEx("Шрифт C2 подчеркнутый", 0x82);
					_fr.printText("");

					_fr.printTextEx("Шрифт B межсимв.расст.ум. 36 симв в стр", 3);
					_fr.printTextEx("Шрифт B надчеркнутый", 0x07);
					_fr.printTextEx("Шрифт B выделенный", 0x0B);
					_fr.printTextEx("Шрифт B двойная высота", 0x13);
					_fr.printTextEx("Шрифт B двойная ширина", 0x23);
					_fr.printTextEx("Шрифт B инверсия", 0x43);
					_fr.printTextEx("Шрифт B подчеркнутый", 0x83);
					_fr.printText("");

					_fr.closeDocument("0");

					_fr.init();
				}
			}

			if (_programProperties.getProperty("TEST_LOADLOGOTYPE", "NO").equals("YES")) {

				if (_param4.equals("SP")) {
					// Logo 1=print logo 0=do not print logo
					_fr.setKkmParameter(5, 0, "1");
				}

				String path = "";

				for (int i = 2; i < 3; i++) {
					if (i == 0) {
						path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP101_Logo_ServPlus_koordinats 269х050_.bmp";
					} else if (i == 1) {
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP101_Logo_ServPlus_koordinats 282х107.bmp"; // AXIOHM
						path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP101_Logo_ServPlus_koordinats 282х080.bmp"; // CW1000
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP601_Logo_ServPlus_koordinats 551x079.bmp"; // IBM
					} else if (i == 2) {
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP101_Logo_ServPlus_koordinats 288х126.bmp"; // AXIOHM
						path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP101_Logo_ServPlus_koordinats 282х100.bmp"; // CW1000
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP601_Logo_ServPlus_koordinats 576x167.bmp"; // IBM
					}

					try {
						Thread.sleep(1000);
					} catch (InterruptedException ie) {}

					_fr.eraseLogotype();
					_fr.loadLogotype(path);
					_fr.xReport("");
				}

				// Choose logo for next test.
				switch(1){
					case 0:
						path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP101_Logo_ServPlus_koordinats 269х050_.bmp";
						break;
					case 1:
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP101_Logo_ServPlus_koordinats 282х107.bmp"; // AXIOHM
						path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP101_Logo_ServPlus_koordinats 282х080.bmp"; // CW1000
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP601_Logo_ServPlus_koordinats 551x079.bmp"; // IBM
						break;
					case 2:
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP101_Logo_ServPlus_koordinats 288х126.bmp"; // AXIOHM
						path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP101_Logo_ServPlus_koordinats 282х100.bmp"; // CW1000
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP601_Logo_ServPlus_koordinats 576x167.bmp"; // IBM
						break;
				}
				_fr.eraseLogotype();
				_fr.loadLogotype(path);
			}

			if (_programProperties.getProperty("TEST_TASK", "NO").equals("YES")) {

				try {
					Common.log("!!!Start TEST_TASK!!!");

//					try {
//						_fr.init();
//					} catch (FrException frEx) {}

					Common.log(_fr.getKkmType() + " " + _fr.getKkmVersion());
					Common.log("FR serial number - " + _fr.getSerialNumber());

					try {

//						_fr.openDocument(FR.RECEIPT_TYPE_SALE, "0", "Иванова", "1");
//						_fr.openDocument(FR.RECEIPT_TYPE_SALE, "0", "Иванова", "1");
//						//_fr.openDocument(FR.RECEIPT_TYPE_NON_FISCAL_DOCUMENT, "0", "Иванова", "");
//
//						_resSet = _statmt.executeQuery("SELECT * FROM testitems");
//
//						while (_resSet.next()) {
//							String article = _resSet.getString("Article");
//							String itemname = _resSet.getString("ItemName");
//							String cost = _resSet.getString("Cost");
//							String weight = _resSet.getString("Weight");
//
//							_fr.addItem(itemname, article, weight, cost, "0", "1");
//						}
						_fr.testJNIfunctions("Текст");
//						_fr.printText("Текст");
//						_fr.total();
////						String egaisUrl = egaisEx.executeChequeExchange();
////						_fr.printQrCode(egaisUrl);
////						Common.log(egaisUrl);
//						_fr.pay(FR.PAY_TYPE_0, "500.00", "");
//						_fr.closeDocument("");
//
//
////						_fr.openDocument(FR.RECEIPT_TYPE_SALE, "0", "Иванова", "1");
////						_resSet = _statmt.executeQuery("SELECT * FROM testitems");
////						while (_resSet.next()) {
////							String article = _resSet.getString("Article");
////							String itemname = _resSet.getString("ItemName");
////							String cost = _resSet.getString("Cost");
////							String weight = _resSet.getString("Weight");
////
////							_fr.addItem(itemname, article, weight, cost, "0", "1");
////						}
//						_fr.cancelDocument();
//
//						_fr.openDocument(FR.RECEIPT_TYPE_RETURN_SALE, "0", "Иванова", "1");
//						_resSet = _statmt.executeQuery("SELECT * FROM testitems");
//						_resSet.next();
//							String article = _resSet.getString("Article");
//							String itemname = _resSet.getString("ItemName");
//							String cost = _resSet.getString("Cost");
//							String weight = _resSet.getString("Weight");
//
//							_fr.addItem(itemname, article, weight, cost, "0", "1");
//
//						_fr.total();
//						_fr.pay(FR.PAY_TYPE_0, "500.00", "");
//						_fr.closeDocument("");
//
//
//						_fr.openDocument(FR.RECEIPT_TYPE_CASHIN, "0", "Иванова", "1");
//						_fr.addCashInCashOutSum("", "500.00");
////						_fr.total();
////						_fr.pay(FR.PAY_TYPE_0, "500.00", "");
//						_fr.closeDocument("");
//
//						_fr.openDocument(FR.RECEIPT_TYPE_CASHOUT, "0", "Иванова", "1");
//						_fr.addCashInCashOutSum("", "500.00");
////						_fr.total();
////						_fr.pay(FR.PAY_TYPE_0, "500.00", "");
//						_fr.closeDocument("");

						_fr.xReport("Иванова");

					} catch (FrException frEx) {
						Common.log(frEx.toString());
						try {
							Common.log("Press Enter ... ");
							System.in.read();
						} catch (IOException e) {
						}
					}
//					_fr.zReport("Петрова");
				} catch (FrException frEx) {
					Common.log(frEx.toString());
					try {
						Common.log("Press Enter ... ");
						System.in.read();
					} catch (IOException e) {
					}
				}
				if (_resSet!=null) _resSet.close();
			}
			if (_programProperties.getProperty("TEST_FISCAL", "NO").equals("YES")) {

				try {

					_fr.init();
					_fr.fiscal54Fz();

				} catch (FrException frEx) {
				}

			}

			if (_programProperties.getProperty("TEST_MAIN", "NO").equals("YES")) {
				for (int cycle = 0; cycle < Integer.parseInt(_param0); cycle++) {
					try {
						Common.log(" ");
						Common.log("!!!Start cycle number - " + cycle);

						try {
							_fr.init();
						} catch (FrException frEx) {
						}

						Common.log(_fr.getKkmType() + " " + _fr.getKkmVersion());
						for (int i = 0; i < Integer.parseInt(_param1); i++) {
							try {
								try {
									Common.log("Pause " + Integer.parseInt(_timeoutBetweenReceipts) + " ms ...");
									Thread.sleep(Integer.parseInt(_timeoutBetweenReceipts));
								} catch (InterruptedException ie) {
								}


								_fr.openDocument(FR.RECEIPT_TYPE_SALE, "0", "Иванова", "");
								//_fr.openDocument(FR.RECEIPT_TYPE_NON_FISCAL_DOCUMENT, "0", "Иванова", "");


								_resSet = _statmt.executeQuery("SELECT * FROM testitems");

								while (_resSet.next()) {
									String article = _resSet.getString("Article");
									String itemname = _resSet.getString("ItemName");
									String cost = _resSet.getString("Cost");
									String weight = _resSet.getString("Weight");

									_fr.addItem(itemname, article, weight, cost, "0", "1");
								}

								_fr.printText("   ");
								_fr.printText("Текст");
								_fr.printText("42C1064C00591E14C1200E8B4684E7D5A4B51CE2");
								_fr.printText("   ");

								_fr.total();

								String egaisUrl = egaisEx.executeChequeExchange();
								_fr.printQrCode(egaisUrl);
								Common.log(egaisUrl);

								_fr.pay(FR.PAY_TYPE_0, "500.00", "");
								_fr.closeDocument("");


								if (((i % 5) == 0) && (i != 0)) {
									_fr.openDocument(FR.RECEIPT_TYPE_SALE, "0", "Иванова", "");

									_resSet = _statmt.executeQuery("SELECT * FROM testitems");
									while (_resSet.next()) {
										String article = _resSet.getString("Article");
										String itemname = _resSet.getString("ItemName");
										String cost = _resSet.getString("Cost");
										String weight = _resSet.getString("Weight");

										_fr.addItem(itemname, article, weight, cost, "0", "1");
									}
									_fr.cancelDocument();

									_fr.openDocument(FR.RECEIPT_TYPE_RETURN_SALE, "0", "Иванова", "");

									_resSet = _statmt.executeQuery("SELECT * FROM testitems");
									while (_resSet.next()) {
										String article = _resSet.getString("Article");
										String itemname = _resSet.getString("ItemName");
										String cost = _resSet.getString("Cost");
										String weight = _resSet.getString("Weight");

										_fr.addItem(itemname, article, weight, cost, "0", "1");
									}

									_fr.total();
									_fr.pay(FR.PAY_TYPE_0, "500.00", "");
									_fr.closeDocument("");

									_fr.xReport("Иванова");
								}
							} catch (FrException frEx) {
								Common.log(frEx.toString());
								try {
									Common.log("Press Enter ... ");
									System.in.read();
								} catch (IOException e) {
								}
							}
						}
						_fr.zReport("Петрова");

						String lastShift = _fr.getLastShiftInFiscalMemory();
						_fr.printEklzReportFullByDate(new Date(), new Date());// Report is very big sometimes
						_fr.printEklzReportShortByDate(new Date(), new Date());
						_fr.printEklzReportFullByShift(Integer.valueOf(lastShift), Integer.valueOf(lastShift));
						_fr.printEklzReportShortByShift(Integer.valueOf(lastShift), Integer.valueOf(lastShift));
						_fr.printEklzReportControlTape(Integer.valueOf(lastShift));
					} catch (FrException frEx) {
						Common.log(frEx.toString());
						try {
							Common.log("Press Enter ... ");
							System.in.read();
						} catch (IOException e) {
						}
					}
				}
				_resSet.close();
			}
		}
		catch(FrException frEx){
			Common.log(frEx.toString());
		}

		_fr.closePort();

		_conn.close();
		_statmt.close();


		System.exit(0);
    }
}