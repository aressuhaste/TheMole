package be.stijn.mole.controller;

import be.stijn.mole.model.Game;
import be.stijn.mole.model.GamesTableModel;
import be.stijn.mole.model.Person;
import be.stijn.mole.view.IGamesView;
import java.util.List;

/**
 * {@link IGamesController} is the definition of a controller with a {@link IGamesView} as 
 * view and a {@link GamesTableModel} as model.
 * <p>
 * For more information about Model-View-Controller see <a href="http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller">http://en.wikipedia.org/wiki/Model-view-controller</a>.
 * 
 * @author Stijn Bouchier
 */
public interface IGamesController {
    public void createGame(List<Person> persons);
    public void deleteGame(Game g);
    public void deleteGames(List<Game> games);
    
    public void addView(IGamesView v);
    public void addModel(GamesTableModel m);

    public GamesTableModel getModel();
}
