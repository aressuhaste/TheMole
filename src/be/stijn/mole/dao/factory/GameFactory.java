package be.stijn.mole.dao.factory;

import be.stijn.mole.model.Game;
import be.stijn.mole.model.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stijn Bouchier
 * @since 13/09/2012 
 * @version 20120913.1 
 */
public class GameFactory {
    public static List<Game> getList(ResultSet rs) throws SQLException {
        List<Game> list = new ArrayList<>();
        
        while(rs.next()) {
            list.add(new Game(rs.getInt("id"), new Person(rs.getInt("personid"), rs.getString("name"), rs.getString("email"))));
        }
        
        return list;
    }
}
