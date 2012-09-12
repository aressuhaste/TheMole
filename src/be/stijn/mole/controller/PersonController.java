package be.stijn.mole.controller;

import be.stijn.mole.dao.DataBaseManager;
import be.stijn.mole.model.Person;
import be.stijn.mole.model.PersonTableModel;
import be.stijn.mole.view.IPersonView;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * {@link PersonController is a {@link IPersonController} that uses {@link DatabaseManager} to 
 * persist the data of the {@link Person} objects in its {@link PersonTableModel}.
 * 
 * @author Stijn Bouchier
 * @since 22/08/2012 
 * @version 20120822.1 
 */
public class PersonController implements IPersonController{
    private PersonTableModel model;
    private IPersonView view;

    /**
     * Creates a new {@link Person} object and adds its to the model.
     * 
     * @return the new {@link Person} object
     */
    @Override
    public Person addNewPerson() {
        return new Person();
    }

    /**
     * Persist the {@link Person} object in the database.
     * @param p the {@link Person} object
     */
    @Override
    public void savePerson(Person p) {
        // if the person is new insert
        if(p.getId() == -1) {
            try {
                p.setId(DataBaseManager.getInstance().insertPerson(p.getName(), p.getEmail()));
                
                this.model.addPerson(p);
            } //else update
            catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error while inserting", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            try {
                DataBaseManager.getInstance().updatePerson(p.getId(), p.getName(), p.getEmail());
                this.model.updatePerson(p);
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error while updating", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    @Override
    public void deletePerson(Person p) {
        if(p.getId() == -1) {
            model.deletePerson(p);
        } else {
            try {
                DataBaseManager.getInstance().deletePerson(p.getId());
                model.deletePerson(p);
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error while deleting", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void deletePersons(List<Person> lP) {
        for(Person p: lP) {
            deletePerson(p);
        }
    }

    /**
     * Sets the view for this controller. Gives the view the model and initializes 
     * it.
     * @param v the view
     */
    @Override
    public void addView(IPersonView v) {
        this.view = v;
        
        this.view.addController(this);
        this.view.addModel(this.model);
        
        this.view.initGui();
    }

    /**
     * Set the model of the controller.
     * @param m the model
     */
    @Override
    public void addModel(PersonTableModel m) {
        this.model = m;
    }

    /**
     * Gets the model of the controller.
     * @return the model
     */
    @Override
    public PersonTableModel getModel() {
        return this.model;
    }
}
