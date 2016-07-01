import java.io.*;

public class SeparatedText
{
	enum TypeOfLoad{change, update, unknown};

	private String _cashNumber;
	private TypeOfLoad _typeOfLoad;
	private DataBase _localDbOfCash;

	SeparatedText()
	{
		_cashNumber="";
		_typeOfLoad=setTypeOfLoad();
		_localDbOfCash = new DataBase();

        System.out.println(_typeOfLoad);
        if (_typeOfLoad==TypeOfLoad.change) 
        {
        	rebuildItems();
        	loadTableItems();
        	rebuildBar();
        	loadTableBar();
        }

	}

	private TypeOfLoad setTypeOfLoad()
	{
		TypeOfLoad localEnum=TypeOfLoad.unknown;

        String list[] = new File(".").list();
        for(int i = 0; i < list.length; i++)
        {
        	String currentFile=list[i];           	
            // System.out.println(currentFile);
            if (currentFile.indexOf(".CNG")>-1) localEnum=TypeOfLoad.change;
            else if (currentFile.indexOf(".cng")>-1) localEnum=TypeOfLoad.change;
            else if (currentFile.indexOf(".UPD")>-1) localEnum=TypeOfLoad.update;
            else if (currentFile.indexOf(".upd")>-1) localEnum=TypeOfLoad.update;
        }
        
        return localEnum;
	}

	private boolean rebuildItems()
	{
		boolean result=true;
		if (result) result = _localDbOfCash.removeTableItems();
		if (result) result = _localDbOfCash.createTableItems();
		//if (result) result = _localDbOfCash.fillTableTestitems();
		return result;
	}

	private boolean rebuildBar()
	{
		boolean result=true;
		if (result) result = _localDbOfCash.removeTableBar();
		if (result) result = _localDbOfCash.createTableBar();
		return result;
	}


	private boolean loadTableItems()
	{
		String itemsInputFileName="PLUCASH.DAT";

		File file = new File(itemsInputFileName);
		String line;

		try 
		{
			if(file.exists())
			{
				//FileReader out = new FileReader(file.getAbsoluteFile());

				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsoluteFile()), "CP866"));
				while (( line = br.readLine()) != null)
				{
					System.out.println(line);
					String fieldsOfRow[]=line.split(",");

					_localDbOfCash.insertIntoTable("INSERT INTO 'items' ('Article', 'ItemName', 'Cost', 'Weight') VALUES ('"+fieldsOfRow[0]+"', '"+fieldsOfRow[1]+"', '"+fieldsOfRow[16]+"', '"+fieldsOfRow[3]+"');");
				}
				br.close();

			}
		} 
		catch(IOException e) 
		{
			throw new RuntimeException(e);
	    }
	    return true;
	}

	private boolean loadTableBar()
	{
		String barInputFileName="BAR.DAT";

		File file = new File(barInputFileName);
		String line;

		try 
		{
			if(file.exists())
			{
				//FileReader out = new FileReader(file.getAbsoluteFile());

				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsoluteFile()), "CP866"));
				while (( line = br.readLine()) != null)
				{
					System.out.println(line);
					String fieldsOfRow[]=line.split(",");

					_localDbOfCash.insertIntoTable("INSERT INTO 'bar' ('Barcode', 'Article', 'Quantity') VALUES ('"+fieldsOfRow[0]+"', '"+fieldsOfRow[1]+"', '"+fieldsOfRow[3]+"');");
				}
				br.close();

			}
		} 
		catch(IOException e) 
		{
			throw new RuntimeException(e);
	    }
	    return true;
	}


}