package be.stijn.mole.model;

import be.stijn.mole.controller.IPersonController;
import be.stijn.mole.view.IPersonView;
import be.stijn.mole.view.IconManager;
import be.stijn.mole.view.Icons;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 * {@link PersonTableModel} is a {@link TableModel} that gives you one row for each object of 
 * {@link Person} in its list.
 * 
 * @author Stijn Bouchier
 * @since 20/08/2012
 * @version 20120820.1
 * @see IPersonController
 * @see IPersonView
 */
public class PersonTableModel extends AbstractTableModel {

    private List<Person> persons;
    private Icon personIcon;
    private Action action;

    /**
     * Creates a {@link PersonTableModel} object containing the given list of {@link Person}.
     * For each Person in the list, a row is created.
     * @param persons list of {@link Person} objects
     */
    public PersonTableModel(List<Person> persons) {
        this.persons = persons;
        action = new Action(ActionType.FOUND,persons.size());

        personIcon = IconManager.getInstance().getIcon(Icons.Person);
    }

    /**
     * Returns the number of rows ({@link Person} objects) in the model.
     * @return the number of rows.
     */
    @Override
    public int getRowCount() {
        return persons.size();
    }

    /**
     * Return the number of columns of the {@link TableModel}, 4.
     * @return 4
     */
    @Override
    public int getColumnCount() {
        return 4;
    }

    /**
     * Returns the value of the object representing the cell at the given 
     * location.
     * @param rowIndex index of the row
     * @param columnIndex index of the column
     * @return the value
     */
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

    /**
     * Returns the title of a column.
     * @param columnIndex index of the column
     * @return the title
     */
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

    /**
     * Returns the {@link Class} of the objects in a column.
     * @param columnIndex index of the column
     * @return the Class
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 1:
                return int.class;
            case 2:
            case 3:
                return String.class;
            default:
                return Icon.class;
        }
    }

    /**
     * Returns false for all cells. This {@link TableModel} doesn't allow editing in 
     * the {@link JTable}.
     * @return false
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //Nothing is editable in the table
        return false;
    }

    /**
     * A empty implementation. You're unable to setValueAt of this {@link TableModel}.
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Do nothing
    }

    /**
     * Returns the last action performed on the model as a {@link Action} object.
     * 
     * @return the {@link Action} object
     */
    public Action getAction() {
        return this.action;
    }

    /**
     * Returns the {@link Person} objects at given rows.
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
    
    /**
     * Adds the {@link Person} object to the model.
     * 
     * @param p the {@link Person} object to add
     */
    public void addPerson(Person p) {
        this.persons.add(p);
        
        fireTableDataChanged();
    }

    /**
     * Remove the {@link Person} object from the model.
     * 
     * @param p 
     */
    public void deletePerson(Person p) {
        this.persons.remove(p);
        
        fireTableDataChanged();
    }

    /**
     * Updates the table if the model contains the {@link Person} object.
     * @param p the {@link Person} object
     */
    public void updatePerson(Person p) {
        if(persons.contains(p))
            fireTableDataChanged();
    }

    /**
     * Returns the {@link Person} object at given row-index.
     * 
     * @param rowIndex row-index 
     * @return the Person object at given row
     */
    public Person getPerson(int rowIndex) {
        return persons.get(rowIndex);
    }
}