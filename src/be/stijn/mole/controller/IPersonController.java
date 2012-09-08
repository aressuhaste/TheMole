package be.stijn.mole.controller;

import be.stijn.mole.model.Person;
import be.stijn.mole.model.PersonTableModel;
import be.stijn.mole.view.IPersonView;
import java.util.List;

/**
 *
 * @author Stijn Bouchier
 */
public interface IPersonController {
    public Person addNewPerson();
    public void savePerson(Person p);
    public void deletePersons(List<Person> lP);
    
    public void addView(IPersonView v);
    public void addModel(PersonTableModel m);

    public PersonTableModel getModel();
}
