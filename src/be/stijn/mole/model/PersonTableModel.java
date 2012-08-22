package be.stijn.mole.model;

import be.stijn.mole.view.IconManager;
import be.stijn.mole.view.Icons;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Stijn Bouchier
 * @since 20/08/2012
 * @version 20120820.1
 */
public class PersonTableModel extends AbstractTableModel {

    private List<Person> persons;
    private Icon personIcon;
    private Action action;

    public PersonTableModel(List<Person> persons) {
        this.persons = persons;
        action = new Action(ActionType.FOUND,persons.size());

        personIcon = IconManager.getInstance().getIcon(Icons.Person);
    }

    @Override
    public int getRowCount() {
        return persons.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return personIcon;
            case 1:
                return persons.get(rowIndex).getId();
            case 2:
                return persons.get(rowIndex).getName();
            case 3:
                return persons.get(rowIndex).getEmail();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 1:
                return "Id";
            case 2:
                return "Name";
            case 3:
                return "Email address";
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(columnIndex, columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //Nothing is editable in the table
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Do nothing
    }

    public Action getAction() {
        return this.action;
    }

    /**
     * Returns the Person objects at given rows.
     * 
     * @param rows array of row-indexes
     * @return List of Person at given rows
     */
    public List<Person> getPersons(int[] rows) {
        List<Person> returnValue = new ArrayList<>();
        
        for(int i = 0; i < rows.length; i++) {
            returnValue.add(persons.get(rows[i]));
        }
        
        return returnValue;
    }
}