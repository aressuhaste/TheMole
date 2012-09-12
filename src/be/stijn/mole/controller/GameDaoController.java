package be.stijn.mole.controller;

import be.stijn.mole.dao.DataBaseManager;
import be.stijn.mole.model.Game;
import be.stijn.mole.model.GamesTableModel;
import be.stijn.mole.model.Person;
import be.stijn.mole.view.IGamesView;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Stijn Bouchier
 * @since 12/09/2012 
 * @version 20120912.1 
 */
public class GameDaoController implements IGamesController {
    private GamesTableModel model;
    private IGamesView view;

    @Override
    public void createGame(List<Person> persons) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteGame(Game g) {
        try {
            DataBaseManager.getInstance().deleteGame(g.getId());
            this.model.deleteGame(g);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Error while deleting", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void addView(IGamesView v) {
        this.view = v;
        
        this.view.addController(this);
        this.view.addModel(this.model);
        
        this.view.initGui();
    }

    @Override
    public void addModel(GamesTableModel m) {
        this.model = m;
    }

    @Override
    public GamesTableModel getModel() {
        return this.model;
    }

    @Override
    public void deleteGames(List<Game> games) {
        for(Game g: games)
            deleteGame(g);
    }

}
