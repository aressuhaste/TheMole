package be.stijn.mole.controller;

import be.stijn.mole.model.Person;
import be.stijn.mole.model.PersonTableModel;
import be.stijn.mole.view.IPersonView;
import java.util.List;

/**
 *
 * @author Stijn Bouchier
 * @since 22/08/2012 
 * @version 20120822.1 
 */
public class PersonController implements IPersonController{
    private PersonTableModel model;
    private IPersonView view;

    @Override
    public Person addNewPerson() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void savePerson(Person p) {
       // model.save
    }

    @Override
    public void deletePersons(List<Person> lP) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addView(IPersonView v) {
        this.view = v;
    }

    @Override
    public void addModel(PersonTableModel m) {
        this.model = m;
    }

}
