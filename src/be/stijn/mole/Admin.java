package be.stijn.mole;

import be.stijn.mole.dao.DataBaseManager;
import be.stijn.mole.view.PersonOverviewPanel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Stijn Bouchier
 * @since 20/08/2012 
 * @version 20120820.1 
 */
public class Admin {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            JFrame window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            PersonOverviewPanel pop = new PersonOverviewPanel(DataBaseManager.getInstance().getAllPeople());
            window.getContentPane().add(pop);
            
            pop.initGUI();
            
            window.pack();
            window.setVisible(true);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}
