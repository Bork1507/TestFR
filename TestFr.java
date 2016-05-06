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

public class TestFr{
    public static void main(String[] args)  throws ClassNotFoundException, SQLException{
    	String configurationFileName="TestFr.xml";
    	String databaseFileName="TestFr.sqlite";

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
			System.out.println("TestFr [cycle] [receipts in cycle] [COM] [BAUD] [FR]");
			System.out.println("Default [1] [2] [/dev/ttyS6] [19200] [SP]");
			System.out.println("FR may be SP or SHTRIH or FPRINT");
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
		if (_param4.contains("SP")) _fr=new SP();
		else if (_param4.contains("SHTRIH")) _fr=new SHTRIH();
		else if (_param4.contains("FPRINT")) _fr=new FPRINT();
		else return;

		try{
			_fr.openPort(_param2, _param3);
			
			for(int cycle=0;cycle<Integer.parseInt(_param0); cycle++){
				try{
					_fr.init();
					Common.log(_fr.getKkmType()+" "+_fr.getKkmVersion());
					for (int i=0; i<Integer.parseInt(_param1); i++){
						try{
							try{
								Common.log("Pause "+Integer.parseInt(_timeoutBetweenReceipts)+" ms ...");
								Thread.sleep(Integer.parseInt(_timeoutBetweenReceipts));
							} catch (InterruptedException ie) {}	


							_fr.openDocument(FR.RECEIPT_TYPE_SALE, "0", "Иванова", "");

							_resSet = _statmt.executeQuery("SELECT * FROM testitems");
		
							while(_resSet.next()){
								String  article = _resSet.getString("Article");
								String  itemname = _resSet.getString("ItemName");
								String  cost = _resSet.getString("Cost");
								String  weight = _resSet.getString("Weight");

								_fr.addItem(itemname, article, weight, cost, "0", "1");
							}

							// _fr.AddItem("Сыр", "сыр12345", "0.123", "100.11", "0", "1");
							// _fr.AddItem("Молоко", "мол67890", "1.000", "40.05", "0", "1");
							// _fr.AddItem("Хлеб", "хл3412", "1.000", "23.50", "0", "1");

							_fr.total();

							String egaisUrl=egaisEx.executeChequeExchange();
							_fr.printQrCode(egaisUrl);
							Common.log(egaisUrl);
							
							_fr.pay(FR.PAY_TYPE_0, "500.00", "");
							_fr.closeDocument("");


							if (((i%5)==0)&&(i!=0)){
								_fr.openDocument(FR.RECEIPT_TYPE_SALE, "0", "Иванова", "");
								
								_resSet = _statmt.executeQuery("SELECT * FROM testitems");
								while(_resSet.next()){
									String  article = _resSet.getString("Article");
									String  itemname = _resSet.getString("ItemName");
									String  cost = _resSet.getString("Cost");
									String  weight = _resSet.getString("Weight");

									_fr.addItem(itemname, article, weight, cost, "0", "1");
								}
								_fr.cancelDocument();

								_fr.openDocument(FR.RECEIPT_TYPE_RETURN_SALE, "0", "Иванова", "");
	
								_resSet = _statmt.executeQuery("SELECT * FROM testitems");
								while(_resSet.next()){
									String  article = _resSet.getString("Article");
									String  itemname = _resSet.getString("ItemName");
									String  cost = _resSet.getString("Cost");
									String  weight = _resSet.getString("Weight");

									_fr.addItem(itemname, article, weight, cost, "0", "1");
								}
								_fr.total();
								_fr.pay(FR.PAY_TYPE_0, "500.00", "");
								_fr.closeDocument("");
					
								_fr.xReport("Иванова");
							}
						}
						catch (FrException frEx){
							Common.log(frEx.toString());
							try {
								Common.log("Press Enter ... ");
								System.in.read();
							}
							catch(IOException e){}
						}
					}
					_fr.zReport("Петрова");			

					String lastShift = _fr.getLastShiftInFiscalMemory();
					_fr.printEklzReportFullByDate(new Date(), new Date());
					_fr.printEklzReportShortByDate(new Date(), new Date());
					_fr.printEklzReportFullByShift(Integer.valueOf(lastShift), Integer.valueOf(lastShift));
					_fr.printEklzReportShortByShift(Integer.valueOf(lastShift), Integer.valueOf(lastShift)); 
					_fr.printEklzReportControlTape(Integer.valueOf(lastShift));
				}
				catch (FrException frEx){
					Common.log(frEx.toString());
					try {
						Common.log("Press Enter ... ");
						System.in.read();
					}
					catch(IOException e){}
				}
			}
		}
		catch (FrException frEx){
			Common.log(frEx.toString());
		}
			
		
		_conn.close();
		_statmt.close();
		_resSet.close();

		System.exit(0);
    }
}