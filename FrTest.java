import java.io.IOException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class FrTest {



    public static void main(String[] args)  throws ClassNotFoundException, SQLException
    {
		String _param0="2",
			   _param1="3",
			   _param2="/dev/ttyS6",
			   _param3="19200",
			   _param4="SP";


	    switch (args.length-1)
	    {
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
		_conn = DriverManager.getConnection("jdbc:sqlite:FrTest.sqlite");
		_statmt = _conn.createStatement();

		if (_param0.contains("?"))
		{
			System.out.println("FrTest [cycle] [receipts in cycle] [COM] [BAUD] [FR]");
			System.out.println("Default [2] [3] [/dev/ttyS6] [19200] [SP]");
			System.out.println("FR may be SP or SHTRIH or FPRINT");
			System.out.println("For Windows example: \\>java FrTest 2 4 COM1 19200 SP");
			System.out.println("For Linux example: \\>java FrTest 2 4 /dev/ttyS0 19200 SP");

			return;
		}

		if (_param0.contains("LOAD"))
		{
			SeparatedText separatedText = new SeparatedText();
			
			return;
		}

	
		FR _fr;

		if (_param4.contains("SP")) _fr=new SP();
		else if (_param4.contains("SHTRIH")) _fr=new SHTRIH();
		else if (_param4.contains("FPRINT")) _fr=new FPRINT();
		else return;

		try
		{
			_fr.openPort(_param2, _param3);
			
			for(int cycle=0;cycle<Integer.parseInt(_param0); cycle++)
			{
				try
				{
					_fr.init();
					for (int i=0; i<Integer.parseInt(_param1); i++)
					{
						try
						{
							//_fr.ReceiptSale();
							_fr.openDocument(FR.RECEIPT_TYPE_SALE, "0", "Иванова", "");

							_resSet = _statmt.executeQuery("SELECT * FROM testitems");
		
							while(_resSet.next())
							{
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
							_fr.pay(FR.PAY_TYPE_0, "500.00", "");
							_fr.closeDocument("");

							if (((i%5)==0)&&(i!=0))
							{
								_fr.openDocument(FR.RECEIPT_TYPE_SALE, "0", "Иванова", "");
								
								_resSet = _statmt.executeQuery("SELECT * FROM testitems");
								while(_resSet.next())
								{
									String  article = _resSet.getString("Article");
									String  itemname = _resSet.getString("ItemName");
									String  cost = _resSet.getString("Cost");
									String  weight = _resSet.getString("Weight");

									_fr.addItem(itemname, article, weight, cost, "0", "1");
								}
								_fr.cancelDocument();

								_fr.openDocument(FR.RECEIPT_TYPE_RETURN_SALE, "0", "Иванова", "");
	
								_resSet = _statmt.executeQuery("SELECT * FROM testitems");
								while(_resSet.next())
								{
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
						catch (FrException frEx)
						{
							_fr.log(frEx.toString());
							try {
								_fr.log("Press Enter ... ");
								System.in.read();
							}
							catch(IOException e){}
						}
					}
					_fr.zReport("Петрова");			
				}
				catch (FrException frEx)
				{
					_fr.log(frEx.toString());
					try {
						_fr.log("Press Enter ... ");
						System.in.read();
					}
					catch(IOException e){}
				}
			}
		}
		catch (FrException frEx)
		{
			_fr.log(frEx.toString());
		}
			
		
		_conn.close();
		_statmt.close();
		_resSet.close();

		System.exit(0);
    }
}