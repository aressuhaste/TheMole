package be.stijn.mole.view;

import be.stijn.mole.model.Person;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Stijn Bouchier
 * @since 20/08/2012
 * @version 20120820.1
 */
public class IconManager {

    private IconManager() {
    }
    private static IconManager instance;

    public static IconManager getInstance() {
        if (instance == null) {
            instance = new IconManager();
        }

        return instance;
    }

    public Icon getIcon(Icons i) {
        Icon ico = new ImageIcon(IconManager.class.getResource("/data/icons/"+i.getName()));

        return ico;
    }
}
