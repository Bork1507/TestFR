public class FrTest {



    public static void main(String[] args) 
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


		if (param0.contains("?"))
		{
			System.out.println("FrTest [cycle] [receipts in cycle] [COM] [BAUD] [FR]");
			System.out.println("Default [2] [3] [/dev/ttyS6] [19200] [SP]");
			System.out.println("FR may be SP or SHTRIH");

			return;
		}

		// for (int i=0;i<args.length;i++)
		// {
		// 	System.out.println(args[i]);

		// }
		// 	System.out.println(param0);
		// 	System.out.println(param1);
		// 	System.out.println(param2);
		// 	System.out.println(param3);
		// 	System.out.println(param4);
	
		FR fr;

		if (param4.contains("SP")) fr=new SP();
		else if (param4.contains("SHTRIH")) fr=new SHTRIH();
		else return;

		try
		{
			fr.openPort(param2, param3);
			
			for(int cycle=0;cycle<Integer.parseInt(param0); cycle++)
			{
				fr.Init();
				for (int i=0; i<Integer.parseInt(param1); i++)
				{
					//fr.ReceiptSale();
					fr.OpenDocument(FR.ReceiptTypeSale, "0", "Иванова", "");
					fr.AddItem("Сыр", "сыр12345", "0.123", "100.11", "0", "1");
					fr.AddItem("Молоко", "мол67890", "1.000", "40.05", "0", "1");
					fr.AddItem("Хлеб", "хл3412", "1.000", "23.50", "0", "1");
					fr.Total();
					fr.Pay(FR.PayType0, "500.00", "");
					fr.CloseDocument("");

					if (((i%5)==0)&&(i!=0))
					{
						fr.OpenDocument(FR.ReceiptTypeSale, "0", "Иванова", "");
						fr.AddItem("Сыр", "сыр12345", "0.123", "100.11", "0", "1");
						fr.AddItem("Молоко", "мол67890", "1.000", "40.05", "0", "1");
						fr.AddItem("Хлеб", "хл3412", "1.000", "23.50", "0", "1");
						fr.CancelDocument();

						fr.OpenDocument(FR.ReceiptTypeReturnSale, "0", "Иванова", "");
						fr.AddItem("Сыр", "сыр12345", "0.123", "100.11", "0", "1");
						fr.AddItem("Молоко", "мол67890", "1.000", "40.05", "0", "1");
						fr.AddItem("Хлеб", "хл3412", "1.000", "23.50", "0", "1");
						fr.Total();
						fr.Pay(FR.PayType0, "500.00", "");
						fr.CloseDocument("");
			
						fr.Xreport("Иванова");
					}
				}
				//fr.Xreport("Иванова");
				fr.Zreport("Петрова");			
			}				
		}
		catch (FrException frEx)
		{
			System.out.println(frEx);
		}
		System.exit(0);
    }
}
