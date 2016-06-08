import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBase
{
	private Connection _conn=null;
	private Statement _statmt=null;
	private ResultSet _resSet=null;

	DataBase() //throws ClassNotFoundException, SQLException
	{
		boolean result=true;
		try
		{
			Class.forName("org.sqlite.JDBC");
			_conn = DriverManager.getConnection("jdbc:sqlite:FrTest.sqlite");
			_statmt = _conn.createStatement();
		}
		catch (ClassNotFoundException cnfEx)
		{
			System.out.println(cnfEx);
			result=false;
		}
		catch (SQLException sqlEx)
		{
			System.out.println(sqlEx);
			result=false;
		}
	}

	public boolean removeTableItems()
	{
		boolean result=true;
		try
		{
		    _statmt.execute("DROP TABLE IF EXISTS items");		
		}
		catch (SQLException sqlEx)
		{
			System.out.println(sqlEx);
			result=false;
		}
	    return result;
	}

	public boolean removeTableBar()
	{
		boolean result=true;
		try
		{
		    _statmt.execute("DROP TABLE IF EXISTS bar");		
		}
		catch (SQLException sqlEx)
		{
			System.out.println(sqlEx);
			result=false;
		}
	    return result;
	}


	public boolean createTableItems()
	{
		boolean result=true;

		try
		{
			_statmt = _conn.createStatement();
			_statmt.execute("CREATE TABLE if not exists items ("//'key' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'phone' INT);");
		    //_statmt.execute("CREATE TABLE IF NOT EXISTS 'items' ('key' INTEGER PRIMARY KEY AUTOINCREMENT);");
	            +"key INTEGER PRIMARY KEY AUTOINCREMENT, "// NOT NULL AUTO_INCREMENT"+
			    +"Article TEXT, "
	    		+"ItemName TEXT, "
	    		+"Cost TEXT, "
	    		+"Weight TEXT "
	            //"PRIMARY KEY (`id`)"+
	            +");");
		}
		catch (SQLException sqlEx)
		{
			System.out.println(sqlEx);
			result=false;
		}


	    return result;
		
	}

	public boolean createTableBar()
	{
		boolean result=true;

		try
		{
			_statmt = _conn.createStatement();
			_statmt.execute("CREATE TABLE if not exists bar ("
	            +"Barcode TEXT PRIMARY KEY, "
			    +"Article TEXT, "
	    		+"Quantity TEXT"
	            +");");
		}
		catch (SQLException sqlEx)
		{
			System.out.println(sqlEx);
			result=false;
		}


	    return result;
		
	}


	public boolean insertIntoTable(String query)
	{
		boolean result=true;
		try
		{
			_statmt.execute(query);
		}
		catch (SQLException sqlEx)
		{
			System.out.println(sqlEx);
			result=false;
		}
			// _statmt.execute("INSERT INTO 'testitems' ('Article', 'ItemName', 'Cost', 'Weight') VALUES ('колб6789', 'Колбаса вареная', '380.00', '0.452'); ");
			// _statmt.execute("INSERT INTO 'testitems' ('Article', 'ItemName', 'Cost', 'Weight') VALUES ('мол987654', 'Молоко', '39.49', '1.000'); ");

	    return result;	
	}


	public boolean fillTableTestitems()
	{
		boolean result=true;
		try
		{
			_statmt.execute("DROP TABLE IF EXISTS testitems");		

			_statmt = _conn.createStatement();
			_statmt.execute("CREATE TABLE if not exists testitems ("
	            +"key INTEGER PRIMARY KEY AUTOINCREMENT, "
			    +"Article TEXT, "
	    		+"ItemName TEXT, "
	    		+"Cost TEXT, "
	    		+"Weight TEXT "
	            +");");


			_statmt.execute("INSERT INTO 'testitems' ('Article', 'ItemName', 'Cost', 'Weight') VALUES ('сыр12345', 'Сыр Российский', '300.20', '0.123'); ");
			_statmt.execute("INSERT INTO 'testitems' ('Article', 'ItemName', 'Cost', 'Weight') VALUES ('колб6789', 'Колбаса вареная', '380.00', '0.452'); ");
			_statmt.execute("INSERT INTO 'testitems' ('Article', 'ItemName', 'Cost', 'Weight') VALUES ('мол987654', 'Молоко', '39.49', '1.000'); ");
		}
		catch (SQLException sqlEx)
		{
			System.out.println(sqlEx);
			result=false;
		}

	    return result;			
	}

	// 1	сыр12345	Сыр Российский	300.20	0.123
	// 2	колб6789	Колбаса вареная	380.00	0.452
	// 3	мол987654	Молоко	39.49	1.000



}
