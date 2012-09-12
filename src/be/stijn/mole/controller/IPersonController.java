package be.stijn.mole.controller;

import be.stijn.mole.model.Person;
import be.stijn.mole.model.PersonTableModel;
import be.stijn.mole.view.IPersonView;
import java.util.List;

/**
 * {@link IPersonController} is the definition of a controller with a {@link IPersonView} as 
 * view and a {@link PersonTableModel} as model.
 * <p>
 * For more information about Model-View-Controller see <a href="http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller">http://en.wikipedia.org/wiki/Model-view-controller</a>.
 * 
 * @author Stijn Bouchier
 */
public interface IPersonController {
    public Person addNewPerson();
    public void savePerson(Person p);
    public void deletePerson(Person p);
    public void deletePersons(List<Person> lP);
    
    public void addView(IPersonView v);
    public void addModel(PersonTableModel m);

    public PersonTableModel getModel();
}
