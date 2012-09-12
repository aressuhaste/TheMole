/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.stijn.mole.view;

import be.stijn.mole.controller.IGamesController;
import be.stijn.mole.model.GamesTableModel;

/**
 *
 * @author Stijn Bouchier
 */
public abstract class IGamesView extends IView{
    public abstract void addController(IGamesController c);
    public abstract void addModel(GamesTableModel m);
}
