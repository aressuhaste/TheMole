package be.stijn.mole.view;

import be.stijn.mole.controller.IGamesController;
import be.stijn.mole.controller.IPersonController;
import be.stijn.mole.model.GamesTableModel;
import be.stijn.mole.model.Person;
import be.stijn.mole.model.PersonTableModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Stijn Bouchier
 * @since 20/08/2012 
 * @version 20120820.1 
 */
public class GamesOverviewPanel extends IGamesView {
    private IGamesController controller;
    private GamesTableModel model;
    
    //private StatusPanel statusPanel;
    private JTable gamesTable;
    private JButton addButton;
    private JButton removeButton;

    public GamesOverviewPanel() {
        
    }
    
    /**
     * Implementation of initGui. Creates the GUI elements and places them 
     * as required.
     */
    @Override
    public void initGui() {
        this.setLayout(new BorderLayout());
        
        JPanel actionPanel = new JPanel();
        this.add(actionPanel,BorderLayout.PAGE_START);
        
        addButton =  new JButton(IconManager.getInstance().getIcon(Icons.Add));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JDialog dialog = new PersonDialog(newPerson, PersonDialog.PersonDialogAction.ADD, controller, getFrame());
            }
        });
        actionPanel.add(addButton);
        
        removeButton = new JButton(IconManager.getInstance().getIcon(Icons.Remove));
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.deleteGames(model.getGames(gamesTable.getSelectedRows()));
            }
        });
        actionPanel.add(removeButton);
        
        this.model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                gamesTable.repaint();
            }
        });
        
        gamesTable = new JTable(model);
        gamesTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        gamesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    //JDialog dialog = new PersonDialog(model.getGame(gamesTable.getSelectedRow()), PersonDialog.PersonDialogAction.EDIT, controller, getFrame());
                }
            }
        });
        
        JScrollPane sp = new JScrollPane(gamesTable);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(sp,BorderLayout.CENTER);
    }
    
    /**
     * Gets the Frame the panel belongs to.
     * @return the Frame
     */
    private Frame getFrame() {
        Component c = getParent();  
        while( c.getParent() != null )  
        {  
            c = c.getParent();  
        }  
    
        Frame topFrame = ( Frame )c;
        
        return topFrame;
    }

    @Override
    public void addController(IGamesController c) {
        this.controller = c;
    }

    @Override
    public void addModel(GamesTableModel m) {
        this.model = m;
    }
}