/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.stijn.mole.view;

import be.stijn.mole.controller.IPersonController;
import be.stijn.mole.model.PersonTableModel;

/**
 *
 * @author Stijn Bouchier
 */
public interface IPersonView extends IView{
    public void addController(IPersonController c);
    public void addModel(PersonTableModel m);
}
