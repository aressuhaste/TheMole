package be.stijn.mole;

import be.stijn.mole.controller.IPersonController;
import be.stijn.mole.controller.PersonController;
import be.stijn.mole.dao.DataBaseManager;
import be.stijn.mole.model.PersonTableModel;
import be.stijn.mole.view.IconManager;
import be.stijn.mole.view.Icons;
import be.stijn.mole.view.MoleFrame;
import be.stijn.mole.view.PersonOverviewPanel;
import java.awt.Dimension;
import java.sql.SQLException;
import javax.swing.*;

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
            
            MoleFrame window = new MoleFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.initGui();
            
            IPersonController personController = new PersonController();
            PersonOverviewPanel pop = new PersonOverviewPanel();
            
            personController.addModel(new PersonTableModel(DataBaseManager.getInstance().getAllPeople()));
            personController.addView(pop);
            
            JButton personButton = new JButton(IconManager.getInstance().getIcon(Icons.Persons));
            personButton.setToolTipText("People");
            
            window.add(personButton, pop);
            window.setContent(pop);
            
            window.setTitle("The Mole");
            window.setPreferredSize(new Dimension(1208, 720));
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
