package be.stijn.mole.view;

import be.stijn.mole.controller.IPersonController;
import be.stijn.mole.model.Person;
import be.stijn.mole.model.PersonTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Stijn Bouchier
 * @since 20/08/2012 
 * @version 20120820.1 
 */
public class PersonOverviewPanel extends JPanel implements IPersonView {
    private IPersonController controller;
    private PersonTableModel model;
    
    private StatusPanel statusPanel;
    private JTable personTable;
    private JButton addButton;
    private JButton removeButton;

    public PersonOverviewPanel() {
        
    }
    
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
                setStatus();
            }
        });
        
        personTable = new JTable(model);
        personTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane sp = new JScrollPane(personTable);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(sp,BorderLayout.CENTER);
        
        statusPanel = new StatusPanel();
        statusPanel.initGui();
        setStatus();
        this.add(statusPanel,BorderLayout.PAGE_END);
    }
    
    public void setStatus(String status) {
        this.statusPanel.setStatus(status);
    }
    
    private void setStatus() {
        switch (model.getAction().getActionType()) {
            case FOUND:
                statusPanel.setStatus(model.getAction().getActionSize() + " people found.");
                break;
            case NEW:
                statusPanel.setStatus(model.getAction().getActionSize() + " people added.");
                break;
            case REMOVE:
                statusPanel.setStatus(model.getAction().getActionSize() + " people removed.");
                break;
        }

        personTable.repaint();
    }

    @Override
    public void addController(IPersonController c) {
        this.controller = c;
    }

    @Override
    public void addModel(PersonTableModel m) {
        this.model = m;
    }
}


