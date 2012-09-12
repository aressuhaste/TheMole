package be.stijn.mole.dao;

import be.stijn.mole.dao.factory.GameFactory;
import be.stijn.mole.dao.factory.PersonFactory;
import be.stijn.mole.model.Game;
import be.stijn.mole.model.Person;
import java.sql.*;
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
    }

    public static DataBaseManager getInstance() {
        if (instance == null) {
            instance = new DataBaseManager();
        }

        return instance;
    }
    private Connection connection = null;

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1;instanceName=MOL;databaseName=Mol", "sa", "5lavmx");
        }

        return connection;
    }

    private void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = null;
    }

    /**
     * Gets a list of all {@link Person} stored in the database.
     *
     * @return the list of {@link Person} object
     */
    public List<Person> getAllPeople() throws SQLException, ClassNotFoundException {
        List<Person> returnValue = new ArrayList<>();
        try {
            getConnection();

            String SQL = "SELECT * FROM Person ORDER BY Id";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            returnValue = PersonFactory.getList(rs);
            rs.close();
            stmt.close();
        } finally {
            closeConnection();
        }

        return returnValue;
    }

    /**
     * Deletes the person with the given id from the database.
     *
     * @param id the id
     */
    public void deletePerson(int id) throws SQLException, ClassNotFoundException {
        try {
            getConnection();

            String SQL = "DELETE Person WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } finally {
            closeConnection();
        }
    }

    /**
     * Insert a person into the database with given name and email.
     *
     * @param name person's name
     * @param email person's email address
     * @return the id of the person
     */
    public int insertPerson(String name, String email) throws SQLException, ClassNotFoundException {
        int id = -1;

        try {
            getConnection();

            String SQL = "INSERT INTO Person VALUES (?,?)";
            PreparedStatement pstmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();

            ResultSetMetaData rsmd = rs.getMetaData();
            if (rs.next()) {
                String key = rs.getString(1);
                id = Integer.parseInt(key);
            }
        } finally {
            closeConnection();
        }

        return id;
    }

    /**
     * Update the person with given id.
     *
     * @param id the person's id
     * @param name the new person's name
     * @param email the new person's email
     */
    public void updatePerson(int id, String name, String email) throws ClassNotFoundException, SQLException {
        try {
            getConnection();

            String SQL = "UPDATE Person SET name = ?, email = ? WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } finally {
            closeConnection();
        }
    }

    /**
     * Deletes the game with the given id from the database.
     *
     * @param id the id
     */
    public void deleteGame(int id) throws ClassNotFoundException, SQLException {
        try {
            getConnection();

            String SQL = "DELETE Game WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } finally {
            closeConnection();
        }
    }
    
    /**
     * Gets a list of all {@link Game} stored in the database.
     *
     * @return the list of {@link Game} object
     */
    public List<Game> getAllGames() throws SQLException, ClassNotFoundException {
        List<Game> returnValue = new ArrayList<>();
        
        try {
            getConnection();

            String SQL = "SELECT g.id AS id, p.id AS personid, p.name AS name, p.email AS email FROM Game g INNER JOIN Person p ON p.id = g.mol ORDER BY id DESC";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            returnValue = GameFactory.getList(rs);
            rs.close();
            stmt.close();
        } finally {
            closeConnection();
        }

        return returnValue;
    }
}