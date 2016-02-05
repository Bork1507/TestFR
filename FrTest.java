public class FrTest {



    public static void main(String[] args) 
    {
		String param0="2",
			   param1="3",
			   param2="/dev/ttyS6",
			   param3="19200";
			   //param4=args[4];


	    switch (args.length-1)
	    {
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
			System.out.println("FrTest [cycle] [receipts in cycle] [COM] [BAUD]");
			System.out.println("Default [2] [3] [/dev/ttyS6] [19200]");

			return;
		}
	
		SP fr=new SP();
		fr.openPort(param2, param3);
		
		for(int cycle=0;cycle<Integer.parseInt(param0); cycle++)
		{
			fr.Init();
			for (int i=0; i<Integer.parseInt(param1); i++)
			{
				//fr.ReceiptSale();
				fr.OpenDocument("2", "0", "Иванова", "");
				fr.AddItem("Сыр", "сыр12345", "0.123", "100.11", "0", "1");
				fr.AddItem("Молоко", "мол67890", "1.000", "40.05", "0", "1");
				fr.AddItem("Хлеб", "хл3412", "1.000", "23.50", "0", "1");
				fr.Total();
				fr.Pay("0", "500.00", "");
				fr.CloseDocument("");

				if (((i%5)==0)&&(i!=0))
				{
					fr.OpenDocument("2", "0", "Иванова", "");
					fr.AddItem("Сыр", "сыр12345", "0.123", "100.11", "0", "1");
					fr.AddItem("Молоко", "мол67890", "1.000", "40.05", "0", "1");
					fr.AddItem("Хлеб", "хл3412", "1.000", "23.50", "0", "1");
					fr.CancelDocument();

					fr.OpenDocument("3", "0", "Иванова", "");
					fr.AddItem("Сыр", "сыр12345", "0.123", "100.11", "0", "1");
					fr.AddItem("Молоко", "мол67890", "1.000", "40.05", "0", "1");
					fr.AddItem("Хлеб", "хл3412", "1.000", "23.50", "0", "1");
					fr.Total();
					fr.Pay("0", "500.00", "");
					fr.CloseDocument("");
		
					fr.Xreport("Иванова");
				}
			}
			fr.Zreport("Петрова");			
		}

		System.exit(0);
    }
}
