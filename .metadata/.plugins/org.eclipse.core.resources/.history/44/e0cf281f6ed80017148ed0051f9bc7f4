package conPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionPool.
 */
public class ConnectionPool 
{

	/** The instance. */
	private static ConnectionPool INSTANCE = null;

	/** The Constant MAX_CONNECTIONS. */
	private static final int MAX_CONNECTIONS = 20;
	
	/** The driver. */
	private final String driver = "com.mysql.jdbc.Driver";
	
	/** The url. */
	private final String url = "jdbc:mysql://localhost:3306/jbproject?useSSL=false";
	
	/** The username. */
	private final String username = "root";
	
	/** The password. */
	private final String password = "Teto17@end";
	
	/** The all connections. */
	private Set<Connection> allConnections;
	
	/** The system is not working. */
	private boolean systemIsNotWorking = false;

	/** The obj. */
	private Object obj = new Object();


	/**
	 * Gets the single instance of ConnectionPool.
	 *
	 * @return single instance of ConnectionPool
	 */
	public static ConnectionPool getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = 	new ConnectionPool();
		}
		return INSTANCE;
	}

	/**
	 * Instantiates a new connection pool.
	 */
	private ConnectionPool() 
	{
		//System.out.println("ConnectionPool constructor");
		try 
		{
			Class.forName(driver);
			//System.out.println("Class for name driver");

		} catch (Exception e) 
		{
			// TODO: handle exception
		}
		allConnections = new HashSet<>();
		//System.out.println("allconnections size : "+allConnections.size());
		while(allConnections.size() < MAX_CONNECTIONS)
		{
			try 
			{
				allConnections.add(DriverManager.getConnection(url, username, password));

			} catch (Exception e) 
			{
				e.printStackTrace();
			}

		}
		//System.out.println("allconnections size : "+allConnections.size());

	}	
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public synchronized Connection getConnection()
	{
		Connection con = null;
		synchronized (obj) 
		{
			while(allConnections.isEmpty())
			{
				try 
				{
					obj.wait();

				} catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			if(systemIsNotWorking == false)
			{
				con = allConnections.iterator().next();
				allConnections.remove(con);		
			}
		}
		if(con == null)
		{
			throw new NullPointerException();

		}
			return con;

	}
	
	/**
	 * Return connection.
	 *
	 * @param conn the conn
	 */
	public void returnConnection(Connection conn)
	{
		synchronized (obj)
		{
			allConnections.add(conn);
			obj.notifyAll();
		}

	} 
	
	/**
	 * Close all connection.
	 */
	public void closeAllConnection()
	{
		systemIsNotWorking = true;
		Iterator<Connection> conIt = allConnections.iterator();
		
		while(conIt.hasNext())
		{
			try 
			{
				conIt.next().close();
				
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	
	/**
	 * Shutting down.
	 */
	public void shuttingDown()
	{
		this.systemIsNotWorking = true;
	}


}
