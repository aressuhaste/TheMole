package be.stijn.mole;

import be.stijn.mole.controller.IPersonController;
import be.stijn.mole.controller.PersonController;
import be.stijn.mole.dao.DataBaseManager;
import be.stijn.mole.model.PersonTableModel;
import be.stijn.mole.view.PersonOverviewPanel;
import java.sql.SQLException;
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
            
            IPersonController personController = new PersonController();
            PersonOverviewPanel pop = new PersonOverviewPanel();
            
            personController.addModel(new PersonTableModel(DataBaseManager.getInstance().getAllPeople()));
            personController.addView(pop);
            
            window.getContentPane().add(pop);
            
            window.pack();
            window.setVisible(true);
            
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}
