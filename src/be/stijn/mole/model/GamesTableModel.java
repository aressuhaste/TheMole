package be.stijn.mole.model;

import be.stijn.mole.view.IconManager;
import be.stijn.mole.view.Icons;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 * {@link GamesTableModel} is a {@link TableModel} that gives you one row for each object of 
 * {@link Game} in its list.
 * 
 * @author Stijn Bouchier
 * @since 20/08/2012
 * @version 20120820.1
 */
public class GamesTableModel extends AbstractTableModel {

    private List<Game> games;
    private Icon gameIcon;

    /**
     * Creates a {@link GamesTableModel} object containing the given list of {@link Game}.
     * For each Game in the list, a row is created.
     * @param games list of {@link Game} objects
     */
    public GamesTableModel(List<Game> games) {
        this.games = games;

        gameIcon = IconManager.getInstance().getIcon(Icons.Game);
    }

    /**
     * Returns the number of rows ({@link Game} objects) in the model.
     * @return the number of rows.
     */
    @Override
    public int getRowCount() {
        return games.size();
    }

    /**
     * Return the number of columns of the {@link TableModel}, 2.
     * @return 4
     */
    @Override
    public int getColumnCount() {
        return 2;
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
                return gameIcon;
            case 1:
                return games.get(rowIndex).getId();
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
     * Returns the {@link Game} objects at given rows.
     * 
     * @param rows array of row-indexes
     * @return List of Games at given rows
     */
    public List<Game> getGames(int[] rows) {
        List<Game> returnValue = new ArrayList<>();
        
        for(int i = 0; i < rows.length; i++) {
            returnValue.add(games.get(rows[i]));
        }
        
        return returnValue;
    }
    
    /**
     * Adds the {@link Game} object to the model.
     * 
     * @param p the {@link Game} object to add
     */
    public void addGame(Game g) {
        this.games.add(g);
        
        fireTableDataChanged();
    }

    /**
     * Remove the {@link Game} object from the model.
     * 
     * @param p 
     */
    public void deleteGame(Game g) {
        this.games.remove(g);
        
        fireTableDataChanged();
    }

    /**
     * Returns the {@link Game} object at given row-index.
     * 
     * @param rowIndex row-index 
     * @return the Game object at given row
     */
    public Game getGame(int rowIndex) {
        return games.get(rowIndex);
    }
}