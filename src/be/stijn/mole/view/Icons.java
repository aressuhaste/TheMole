package be.stijn.mole.view;

/**
 *
 * @author Stijn Bouchier
 * @since 20/08/2012 
 * @version 20120820.1 
 */
public enum Icons {
    Person("person.png"),
    Add("add.png"),
    Remove("remove.png"),
    Save("save.png"),
    Persons("people.png"),
    Cancel("cancel.png"),
    Game("game.png"),
    Games("games.png"),
    Show("show.png");
    
    private String iconName;

    private Icons(String name) {
        this.iconName = name;
    }

    public String getName() {
        return iconName;
    }
}
