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
	static class TestTypes
	{
		boolean testMain;
		boolean testTask;
		boolean testFiscalisation;
		boolean testJNIfunctions;
		boolean loadLogotype;
		boolean loadSettings;
	}
	static class EgaisParameters
	{
		String egaisHost;
		String egaisUserFsRarId;
		String egaisUserInn;
		String egaisUserKpp;
	}
	static class TestParameters
	{
		String cycles;
		String receipts;
		String devicePort;
		String deviceBaud;
		String device;
		String timeoutBetweenReceipts;

		EgaisParameters egaisParams;
		TestTypes testTypes;

		public TestParameters()
		{
			egaisParams = new EgaisParameters();
			testTypes   = new TestTypes();
			testTypes.testMain=false;
			testTypes.testTask=false;
			testTypes.testFiscalisation=false;
			testTypes.testJNIfunctions=false;
			testTypes.loadLogotype=false;
			testTypes.loadSettings=false;
		}
	}

	public static void TestingFRs(TestParameters testParams)  throws ClassNotFoundException, SQLException
	{
		EgaisExchange egaisEx = new EgaisExchange(testParams.egaisParams.egaisHost, testParams.egaisParams.egaisUserFsRarId, testParams.egaisParams.egaisUserInn, testParams.egaisParams.egaisUserKpp);

		FR _fr=null;
		if (testParams.device.equals("SP")) _fr=new SP();
		else if (testParams.device.equals("SHTRIH")) _fr=new SHTRIH();
		else if (testParams.device.equals("FPRINT")) _fr=new FPRINT();
		else if (testParams.device.equals("PYRITE")) _fr=new PYRITE();
		else if (testParams.device.equals("WINCOR")) _fr=new WINCOR();
		else if (testParams.device.equals("SHTRIHOLE")) _fr=new SHTRIHOLE();
		else if (testParams.device.equals("SPDRV")) _fr=new SPDRV();
		else if (testParams.device.equals("SPOLE")) _fr=new SPOLE();
		else if (testParams.device.equals("SPOLE1C")) _fr=new SPOLE1C();
		else return;

		Connection _conn=null;
		Statement _statmt=null;
		ResultSet _resSet=null;

		String databaseFileName="TestFR.sqlite";
		Class.forName("org.sqlite.JDBC");
		_conn = DriverManager.getConnection("jdbc:sqlite:"+databaseFileName);
		_statmt = _conn.createStatement();

		try {


			_fr.openPort(testParams.devicePort, testParams.deviceBaud);
			Common.log(" ");
			Common.log(" ");
			Common.log("!!!Start program!!!");

			if (testParams.testTypes.loadSettings) {

				for (int k = 4; k < 5; k++) {
					// Set parameters for SP
					if (testParams.device.equals("SP")) {

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

			if (testParams.testTypes.loadLogotype) {

				if (testParams.device.equals("SP")) {
					// Logo 1=print logo 0=do not print logo
					_fr.setKkmParameter(5, 0, "1");
				}

				String path = "";

				for (int i = 3; i < 3; i++) {
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
						path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP801_Logo_ServPlus_koordinats 576х200.bmp";
						break;
					case 1:
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP101_Logo_ServPlus_koordinats 282х107.bmp"; // AXIOHM
						path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP801_Logo_ServPlus_koordinats 288х200.bmp"; // CW1000
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/Chess-1_64X32.bmp";
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/Chess_64X08.bmp";
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP601_Logo_ServPlus_koordinats 551x079.bmp"; // IBM
						break;
					case 2:
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP101_Logo_ServPlus_koordinats 288х126.bmp"; // AXIOHM
						path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP601_Logo_ServPlus_koordinats 576x152.bmp"; // CW1000
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP601_Logo_ServPlus_koordinats 576x167.bmp"; // IBM
						break;
				}
				_fr.eraseLogotype();
				_fr.loadLogotype(path);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ie) {}
				_fr.xReport("");
			}

			if (testParams.testTypes.testTask) {

				try {
					Common.log("!!!Start TEST_TASK!!!");

//					try {
//						_fr.init();
//					} catch (FrException frEx) {}

					Common.log(_fr.getKkmType() + " " + _fr.getKkmVersion());
					Common.log("FR serial number - " + _fr.getSerialNumber());

					try {
						_fr.openDocument(FR.RECEIPT_TYPE_NON_FISCAL_DOCUMENT, "0", "Иванова", "");
						_fr.printText("NON_FISCAL_DOCUMENT");
						_fr.closeDocument("");

						_fr.openDocument(FR.RECEIPT_TYPE_SALE, "0", "Иванова", "1");
						_resSet = _statmt.executeQuery("SELECT * FROM testitems");
						while (_resSet.next()) {
							String article = _resSet.getString("Article");
							String itemname = _resSet.getString("ItemName");
							String cost = _resSet.getString("Cost");
							String weight = _resSet.getString("Weight");

							_fr.addItem(itemname, article, weight, cost, "0", "1");
						}
						_fr.printText("Текст");
						_fr.total();
						_fr.pay(FR.PAY_TYPE_0, "500.00", "");
						_fr.closeDocument("");


						_fr.openDocument(FR.RECEIPT_TYPE_SALE, "0", "Иванова", "1");
						_resSet = _statmt.executeQuery("SELECT * FROM testitems");
						while (_resSet.next()) {
							String article = _resSet.getString("Article");
							String itemname = _resSet.getString("ItemName");
							String cost = _resSet.getString("Cost");
							String weight = _resSet.getString("Weight");

							_fr.addItem(itemname, article, weight, cost, "0", "1");
						}
						_fr.cancelDocument();

						_fr.openDocument(FR.RECEIPT_TYPE_RETURN_SALE, "0", "Иванова", "1");
						_resSet = _statmt.executeQuery("SELECT * FROM testitems");
						_resSet.next();
							String article = _resSet.getString("Article");
							String itemname = _resSet.getString("ItemName");
							String cost = _resSet.getString("Cost");
							String weight = _resSet.getString("Weight");

							_fr.addItem(itemname, article, weight, cost, "0", "1");

						_fr.total();
						_fr.pay(FR.PAY_TYPE_0, "500.00", "");
						_fr.closeDocument("");


						_fr.openDocument(FR.RECEIPT_TYPE_CASHIN, "0", "Иванова", "1");
						_fr.addCashInCashOutSum("", "500.00");
						_fr.closeDocument("");

						_fr.openDocument(FR.RECEIPT_TYPE_CASHOUT, "0", "Иванова", "1");
						_fr.addCashInCashOutSum("", "500.00");
						_fr.closeDocument("");

						_fr.xReport("Иванова");

					} catch (FrException frEx) {
						Common.log(frEx.toString());
						try {
							Common.log("Press Enter ... ");
							System.in.read();
						} catch (IOException e) {
						}
					}
					_fr.zReport("Петрова");
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
			if (testParams.testTypes.testFiscalisation) {

				try {

					_fr.init();
					_fr.fiscal54Fz();

				} catch (FrException frEx) {
				}

			}

			if (testParams.testTypes.testJNIfunctions) {

				try {

					//_fr.init();
					_fr.testJNIfunctions("Текст");

				} catch (FrException frEx) {
				}

			}

			if (testParams.testTypes.testMain) {
				for (int cycle = 0; cycle < Integer.parseInt(testParams.cycles); cycle++) {
					try {
						Common.log(" ");
						Common.log("!!!Start cycle number - " + cycle);

						try {
							_fr.init();
						} catch (FrException frEx) {
						}
						Common.log(_fr.getKkmType() + " " + _fr.getKkmVersion());

//						_fr.openDocument(FR.RECEIPT_TYPE_NON_FISCAL_DOCUMENT, "0", "Иванова", "");
//						_fr.printQrCode("http://check.egais.ru?id=4c74ab86-f8e1-4a70-84b3-5230619a2bb7&amp;dt=1504161154&amp;cn=030000143587  http://check.egais.ru?id=4c74ab86-f8e1-4a70-84b3-5230619a2bb7&amp;");
//						_fr.closeDocument("");

						for (int i = 0; i < Integer.parseInt(testParams.receipts); i++)
						{
							try
							{
								try
								{
									Common.log("Pause " + Integer.parseInt(testParams.timeoutBetweenReceipts) + " ms ...");
									Thread.sleep(Integer.parseInt(testParams.timeoutBetweenReceipts));
								} catch (InterruptedException ie) {
								}


									_fr.openDocument(FR.RECEIPT_TYPE_SALE, "0", "Иванова", "");
									//_fr.openDocument(FR.RECEIPT_TYPE_NON_FISCAL_DOCUMENT, "0", "Иванова", "");

									for(int j=0; j<60; j++)
									{
										_resSet = _statmt.executeQuery("SELECT * FROM testitems");
										while (_resSet.next()) {
											String article = _resSet.getString("Article");
											String itemname = _resSet.getString("ItemName");
											String cost = _resSet.getString("Cost");
											String weight = _resSet.getString("Weight");

											_fr.addItem(itemname, article, weight, cost, "0", "1");
										}
									}

									_fr.printText("   ");
									_fr.printText("Текст");
									_fr.printText("42C1064C00591E14C1200E8B4684E7D5A4B51CE2");
									_fr.printText("   ");

									_fr.total();

									//String egaisUrl = egaisEx.executeChequeExchange();
									//String egaisUrl = "http://check.egais.ru?id=4c74ab86-f8e1-4a70-84b3-5230619a2bb7&amp;dt=1504161154&amp;cn=030000143587  http://check.egais.ru?id=4c74ab86-f8e1-4a70-84b3-5230619a2bb7&amp;";
									//_fr.printQrCode(egaisUrl);
									//Common.log(egaisUrl);

									_fr.pay(FR.PAY_TYPE_0, "50000.00", "");
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
										//_fr.xReport("Иванова");
									}
							} catch (FrException frEx) {
								Common.log(frEx.toString());
								try {
									Common.log("Press Enter ... ");
									System.in.read();
								} catch (IOException e) {
								}
								_fr.cancelDocument();
							}
						}
						//_fr.zReport("Петрова");

//						String lastShift = _fr.getLastShiftInFiscalMemory();
//						_fr.printEklzReportFullByDate(new Date(), new Date());// Report is very big sometimes
//						_fr.printEklzReportShortByDate(new Date(), new Date());
//						_fr.printEklzReportFullByShift(Integer.valueOf(lastShift), Integer.valueOf(lastShift));
//						_fr.printEklzReportShortByShift(Integer.valueOf(lastShift), Integer.valueOf(lastShift));
//						_fr.printEklzReportControlTape(Integer.valueOf(lastShift));
					} catch (FrException frEx) {
						Common.log(frEx.toString());
						try {
							Common.log("Press Enter ... ");
							System.in.read();
						} catch (IOException e) {
						}
						_fr.cancelDocument();
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
	}
	public static void TestingPrinters(TestParameters testParams)  throws ClassNotFoundException, SQLException
	{
		PRINTER _printer=null;
		if (testParams.device.equals("CW1000")) _printer=new CW1000();
		else if (testParams.device.equals("AXIOHM")) _printer=new AXIOHM();
		else return;

		try {


			_printer.openPort(testParams.devicePort, testParams.deviceBaud);
			Common.log(" ");
			Common.log(" ");
			Common.log("!!!Start program!!!");

			_printer.printText("CODE 39");
			_printer.printText(" ");
			_printer.printBarCode(2,40, "Code 39", "123456789012345678901");
			_printer.printText(" ");
			_printer.printText(" ");
			_printer.printText(" ");
			_printer.printBarCode(2,40, "Code 39", "ABCDIFGHIJKLMNOPQRSTU");
			_printer.printText(" ");
			_printer.printText(" ");
			_printer.printText(" ");
			_printer.printBarCode(2,40, "Code 39", "FGHIJKLMNOPQRSTUVWXYZ");
			_printer.printText(" ");
			_printer.printText(" ");
			_printer.printText(" ");

			_printer.printText("CODE 128");
			_printer.printText(" ");
			_printer.printBarCode(2,40, "Code 128", "123456789012345678901");
			_printer.printText(" ");
			_printer.printText(" ");
			_printer.printText(" ");
			_printer.printBarCode(2,40, "Code 128", "ABCDIFGHIJKLMNOPQRSTU");
			_printer.printText(" ");
			_printer.printText(" ");
			_printer.printText(" ");
			_printer.printBarCode(2,40, "Code 128", "FGHIJKLMNOPQRSTUVWXYZ");
			_printer.printText(" ");
			_printer.printText(" ");
			_printer.printText(" ");
			_printer.printText(" ");
			_printer.printText(" ");
			_printer.printText(" ");
			_printer.printText(" ");
			_printer.printText(" ");
			_printer.printText(" ");
			_printer.printText(" ");
			if (testParams.testTypes.loadLogotype)
			{

				String path = "";

				for (int i = 3; i < 3; i++) {
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

					_printer.eraseLogotype();
					_printer.loadLogotype(path);
				}

				// Choose logo for next test.
				switch(1){
					case 0:
						path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP801_Logo_ServPlus_koordinats 576х200.bmp";
						break;
					case 1:
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP101_Logo_ServPlus_koordinats 282х107.bmp"; // AXIOHM
						path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP801_Logo_ServPlus_koordinats 288х200.bmp"; // CW1000
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/Chess-1_64X32.bmp";
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/Chess_64X08.bmp";
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP601_Logo_ServPlus_koordinats 551x079.bmp"; // IBM
						break;
					case 2:
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP101_Logo_ServPlus_koordinats 288х126.bmp"; // AXIOHM
						path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP601_Logo_ServPlus_koordinats 576x152.bmp"; // CW1000
						//path = "/home/bork/VirtualBoxFolder/Projects/Fiscal/SP601_Logo_ServPlus_koordinats 576x167.bmp"; // IBM
						break;
				}
				_printer.eraseLogotype();
				_printer.loadLogotype(path);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ie) {}
				_printer.printLogotype();
				_printer.printText("");
				_printer.printText("");
				_printer.printText("");
				_printer.printText("");
				_printer.printText("");
				_printer.printText("");
				_printer.printText("");
				_printer.printText("");
			}
		}
		catch(FrException frEx)
		{
			Common.log(frEx.toString());
		}

	}
	public static void TestingSKNO(TestParameters testParams)  throws ClassNotFoundException, SQLException
	{
		SKNO _skno=null;
		if (testParams.device.equals("SKNO")) _skno=new SKNO();
		else return;

		try {
			_skno.openPort("COM10", "115200");
			_skno.init();
			_skno.openShift();
			_skno.getUI();
			_skno.closePort();
		}
		catch(FrException frEx)
		{
			Common.log(frEx.toString());
		}
	}


	public static void main(String[] args)  throws ClassNotFoundException, SQLException{
    	String configurationFileName="TestFR.xml";

		String _param0="",
			   _param1="",
			   _param2="",
			   _param3="",
			   _param4="";

		TestParameters testParam = new TestParameters();

		Properties _programProperties = new Properties();

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
					_programProperties.setProperty("TEST_MAIN", "YES");
					_programProperties.setProperty("TEST_TASK", "NO");
					_programProperties.setProperty("TEST_FISCAL", "NO");
					_programProperties.setProperty("TEST_JNI", "NO");
					_programProperties.setProperty("TEST_LOADLOGOTYPE", "NO");
					_programProperties.setProperty("TEST_LOADSETTINGS", "NO");

					FileOutputStream out = new FileOutputStream(configurationFileName);
					_programProperties.storeToXML(out, null);
					out.close();
				}

				FileInputStream in = new FileInputStream(configurationFileName);
            	_programProperties.loadFromXML(in);
		        in.close();

				testParam.cycles                 = _programProperties.getProperty("CYCLE", "1");
				testParam.receipts               = _programProperties.getProperty("RECEIPTS", "2");
				testParam.devicePort             = _programProperties.getProperty("PORT", "/dev/ttyS6");
				testParam.deviceBaud             = _programProperties.getProperty("BAUD", "19200");
				testParam.device                 = _programProperties.getProperty("FR", "SP");
				testParam.timeoutBetweenReceipts = _programProperties.getProperty("TIMEOUT", "1000");

				testParam.egaisParams.egaisHost        = _programProperties.getProperty("EGAIS_HOST", "192.168.25.109");
				testParam.egaisParams.egaisUserFsRarId = _programProperties.getProperty("EGAIS_USER_FSRARID", "030000112233");
				testParam.egaisParams.egaisUserInn     = _programProperties.getProperty("EGAIS_USER_INN", "7722334455");
				testParam.egaisParams.egaisUserKpp     = _programProperties.getProperty("EGAIS_USER_KPP", "773322110");

				if (_programProperties.getProperty("TEST_MAIN", "NO").equals("YES")) testParam.testTypes.testMain = true;
				if (_programProperties.getProperty("TEST_TASK", "NO").equals("YES")) testParam.testTypes.testTask = true;
				if (_programProperties.getProperty("TEST_FISCAL", "NO").equals("YES")) testParam.testTypes.testFiscalisation = true;
				if (_programProperties.getProperty("TEST_JNI", "NO").equals("YES")) testParam.testTypes.testJNIfunctions = true;
				if (_programProperties.getProperty("TEST_LOADLOGOTYPE", "NO").equals("YES")) testParam.testTypes.loadLogotype = true;
				if (_programProperties.getProperty("TEST_LOADSETTINGS", "NO").equals("YES")) testParam.testTypes.loadSettings = true;
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

		if (_programProperties.getProperty("TEST_DEVICE", "FR").equals("FR")) TestingFRs(testParam);
		else if (_programProperties.getProperty("TEST_DEVICE", "FR").equals("PRINTER")) TestingPrinters(testParam);
		else if (_programProperties.getProperty("TEST_DEVICE", "FR").equals("SKNO")) TestingSKNO(testParam);




		System.exit(0);
    }
}