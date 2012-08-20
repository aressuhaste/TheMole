package be.stijn.mole.model;



/**
 *
 * @author Stijn Bouchier
 * @since 02/06/2012 
 * @version 20120602.1 
 */
public class Game {
    private int id;
    private Person mol;

    public Game(int id, Person mol) {
        this.id = id;
        this.mol = mol;
    }

    public int getId() {
        return id;
    }

    public Person getMol() {
        return mol;
    }

    public void setMol(Person mol) {
        this.mol = mol;
    }
}
