package be.stijn.mole.dao.factory;

import be.stijn.mole.model.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stijn Bouchier
 * @since 20/08/2012 
 * @version 20120820.1 
 */
public class PersonFactory {
    public static List<Person> getList(ResultSet rs) throws SQLException {
        List<Person> list = new ArrayList<>();
        
        while(rs.next()) {
            list.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
        }
        
        return list;
    }
}