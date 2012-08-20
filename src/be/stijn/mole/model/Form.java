package be.stijn.mole.model;



import java.util.List;

/**
 *
 * @author Stijn Bouchier
 * @since 02/06/2012 
 * @version 20120602.1 
 */
public class Form {
    private int id;
    private Game game;
    private List<Question> formQuestions;

    public Form(int id, Game game, List<Question> formQuestions) {
        this.id = id;
        this.game = game;
        this.formQuestions = formQuestions;
    }

    public int getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
