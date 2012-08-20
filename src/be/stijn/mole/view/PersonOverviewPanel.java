package be.stijn.mole.view;

import be.stijn.mole.model.Person;
import be.stijn.mole.model.PersonTableModel;
import java.awt.BorderLayout;
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
public class PersonOverviewPanel extends JPanel {
    private List<Person> persons;
    private PersonTableModel ptModel;
    private StatusPanel statusPanel;
    private JTable personTable;

    public PersonOverviewPanel(List<Person> persons) {
        this.persons = persons;
    }
    
    public void initGUI() {
        this.setLayout(new BorderLayout());
        
        JPanel actionPanel = new JPanel();
        this.add(actionPanel,BorderLayout.PAGE_START);
        
        actionPanel.add(new JButton(IconManager.getInstance().getIcon(Icons.Add)));
        actionPanel.add(new JButton(IconManager.getInstance().getIcon(Icons.Remove)));
        //actionPanel.add(new JButton(IconManager.getInstance().getIcon(Icons.Remove)));
        
        this.ptModel = new PersonTableModel(persons);
        this.ptModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                setStatus();
            }
        });
        
        personTable = new JTable(ptModel);
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

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
    
    private void setStatus() {
        switch (ptModel.getAction().getActionType()) {
            case FOUND:
                statusPanel.setStatus(ptModel.getAction().getActionSize() + " people found.");
                break;
            case NEW:
                statusPanel.setStatus(ptModel.getAction().getActionSize() + " people added.");
                break;
            case REMOVE:
                statusPanel.setStatus(ptModel.getAction().getActionSize() + " people removed.");
                break;
        }

        personTable.repaint();
    }
}


