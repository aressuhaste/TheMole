package be.stijn.mole.model;

/**
 *
 * @author Stijn Bouchier
 * @since 20/08/2012 
 * @version 20120820.1 
 */
public class Action {
    private int actionSize;
    private ActionType actionType;
    
    Action(ActionType actionType, int size) {
        this.actionSize = size;
        this.actionType = actionType;
    }

    public int getActionSize() {
        return actionSize;
    }

    public ActionType getActionType() {
        return actionType;
    }
}
