package be.stijn.mole.view;

import be.stijn.mole.controller.IPersonController;
import be.stijn.mole.model.Person;
import be.stijn.mole.model.PersonTableModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Stijn Bouchier
 * @since 20/08/2012 
 * @version 20120820.1 
 */
public class PersonOverviewPanel extends IPersonView {
    private IPersonController controller;
    private PersonTableModel model;
    
    //private StatusPanel statusPanel;
    private JTable personTable;
    private JButton addButton;
    private JButton removeButton;

    public PersonOverviewPanel() {
        
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
                Person newPerson = controller.addNewPerson();
                
                JDialog dialog = new PersonDialog(newPerson, PersonDialog.PersonDialogAction.ADD, controller, getFrame());
            }
        });
        actionPanel.add(addButton);
        
        removeButton = new JButton(IconManager.getInstance().getIcon(Icons.Remove));
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.deletePersons(model.getPersons(personTable.getSelectedRows()));
            }
        });
        actionPanel.add(removeButton);
        
        this.model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                personTable.repaint();
            }
        });
        
        personTable = new JTable(model);
        personTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane sp = new JScrollPane(personTable);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(sp,BorderLayout.CENTER);
        
        //statusPanel = new StatusPanel();
        //statusPanel.initGui();
        //setStatus();
        //this.add(statusPanel,BorderLayout.PAGE_END);
    }
    
    //public void setStatus(String status) {
    //    this.statusPanel.setStatus(status);
    //}
    
    //public void setStatus() {
    //    switch (model.getAction().getActionType()) {
    //       case FOUND:
    //            statusPanel.setStatus(model.getAction().getActionSize() + " people found.");
    //            break;
    //        case NEW:
    //            statusPanel.setStatus(model.getAction().getActionSize() + " people added.");
    //            break;
    //       case REMOVE:
    //            statusPanel.setStatus(model.getAction().getActionSize() + " people removed.");
    //            break;
    //    }

    //    personTable.repaint();
    //}

    @Override
    public void addController(IPersonController c) {
        this.controller = c;
    }

    @Override
    public void addModel(PersonTableModel m) {
        this.model = m;
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
}


