import java.io.IOException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class FrTest {



    public static void main(String[] args)  throws ClassNotFoundException, SQLException
    {
		String param0="2",
			   param1="3",
			   param2="/dev/ttyS6",
			   param3="19200",
			   param4="SP";


	    switch (args.length-1)
	    {
	    	case 4: 
	    		param4=args[4];
	    	case 3: 
	    		param3=args[3];
	    	case 2: 
	    		param2=args[2];
	    	case 1: 
	    		param1=args[1];
	    	case 0: 
	    		param0=args[0];
	    }


	    FR.Log("привет");

		// conn.Conn();
		// conn.CreateDB();
		// conn.WriteDB();
		// conn.ReadDB();
		// conn.CloseDB();



		Connection conn=null;
		Statement statmt=null;
		ResultSet resSet=null;


		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:FrTest.sqlite");
		statmt = conn.createStatement();





		if (param0.contains("?"))
		{
			System.out.println("FrTest [cycle] [receipts in cycle] [COM] [BAUD] [FR]");
			System.out.println("Default [2] [3] [/dev/ttyS6] [19200] [SP]");
			System.out.println("FR may be SP or SHTRIH");

			return;
		}
	
		FR fr;

		if (param4.contains("SP")) fr=new SP();
		else if (param4.contains("SHTRIH")) fr=new SHTRIH();
		else return;

		try
		{
			fr.openPort(param2, param3);
			
			for(int cycle=0;cycle<Integer.parseInt(param0); cycle++)
			{
				try
				{
					fr.Init();
					for (int i=0; i<Integer.parseInt(param1); i++)
					{
						try
						{
							//fr.ReceiptSale();
							fr.OpenDocument(FR.ReceiptTypeSale, "0", "Иванова", "");

							resSet = statmt.executeQuery("SELECT * FROM Items");
		
							while(resSet.next())
							{
								String  article = resSet.getString("Article");
								String  itemname = resSet.getString("ItemName");
								String  cost = resSet.getString("Cost");
								String  weight = resSet.getString("Weight");

								fr.AddItem(itemname, article, weight, cost, "0", "1");
							}

							// fr.AddItem("Сыр", "сыр12345", "0.123", "100.11", "0", "1");
							// fr.AddItem("Молоко", "мол67890", "1.000", "40.05", "0", "1");
							// fr.AddItem("Хлеб", "хл3412", "1.000", "23.50", "0", "1");

							fr.Total();
							fr.Pay(FR.PayType0, "500.00", "");
							fr.CloseDocument("");

							if (((i%5)==0)&&(i!=0))
							{
								fr.OpenDocument(FR.ReceiptTypeSale, "0", "Иванова", "");
								
								resSet = statmt.executeQuery("SELECT * FROM Items");
								while(resSet.next())
								{
									String  article = resSet.getString("Article");
									String  itemname = resSet.getString("ItemName");
									String  cost = resSet.getString("Cost");
									String  weight = resSet.getString("Weight");

									fr.AddItem(itemname, article, weight, cost, "0", "1");
								}
								fr.CancelDocument();

								fr.OpenDocument(FR.ReceiptTypeReturnSale, "0", "Иванова", "");
	
								resSet = statmt.executeQuery("SELECT * FROM Items");
								while(resSet.next())
								{
									String  article = resSet.getString("Article");
									String  itemname = resSet.getString("ItemName");
									String  cost = resSet.getString("Cost");
									String  weight = resSet.getString("Weight");

									fr.AddItem(itemname, article, weight, cost, "0", "1");
								}
								fr.Total();
								fr.Pay(FR.PayType0, "500.00", "");
								fr.CloseDocument("");
					
								fr.Xreport("Иванова");
							}
						}
						catch (FrException frEx)
						{
							System.out.println(frEx);
							try {
								System.out.print("Press Enter ... ");
								System.in.read();
							}
							catch(IOException e){}
						}
					}
					//fr.Xreport("Иванова");
					fr.Zreport("Петрова");			
				}
				catch (FrException frEx)
				{
					System.out.println(frEx);
					try {
						System.out.print("Press Enter ... ");
						System.in.read();
					}
					catch(IOException e){}
				}
			}
		}
		catch (FrException frEx)
		{
			System.out.println(frEx);
		}
			
		
		conn.close();
		statmt.close();
		resSet.close();

		System.exit(0);
    }
}