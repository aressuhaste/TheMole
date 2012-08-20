package be.stijn.mole.dao;

import be.stijn.mole.dao.factory.PersonFactory;
import be.stijn.mole.model.Person;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stijn Bouchier
 * @since 02/06/2012
 * @version 20120602.1
 */
public class DataBaseManager {
    private static DataBaseManager instance;
    
    private DataBaseManager() {
        getConnection();
    }
    
    public static DataBaseManager getInstance() {
        if(instance == null)
            instance = new DataBaseManager();
        
        return instance;
    }

    private Connection connection = null;

    private Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1;instanceName=MOL;databaseName=Mol","sa","5lavmx");
                if (connection != null) {
                    System.out.println("Connection Successful!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error Trace in getConnection() : " + e.getMessage());
            }
        }

        return connection;
    }

    private void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Person> getAllPeople() {
        List<Person> returnValue = new ArrayList<>();
        
        try {
            getConnection();
            
            String SQL = "SELECT * FROM Person ORDER BY Id";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            
            returnValue = PersonFactory.getList(rs);
            while (rs.next()) {
                System.out.println(rs.getString("LastName") + ", " + rs.getString("FirstName"));
            }
            rs.close();
            stmt.close();
            
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return returnValue;
    }
}