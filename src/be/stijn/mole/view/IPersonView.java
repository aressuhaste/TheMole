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
public abstract class IPersonView extends IView{
    public abstract void addController(IPersonController c);
    public abstract void addModel(PersonTableModel m);
}
