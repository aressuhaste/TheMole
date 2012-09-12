package be.stijn.mole.view;

import be.stijn.mole.controller.IPersonController;
import be.stijn.mole.model.Person;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 * {@link PersonDialog} is a {@link JDialog} that displays the information of a 
 * given {@link Person} object.
 * <p>
 * Depending on the given {@link PersonDialogAction} the text of the save button 
 * will vary.
 * @author Stijn Bouchier
 * @since 08/09/2012 
 * @version 20120908.1 
 * @see JDialog
 */
public class PersonDialog extends JDialog {
    private Person person;
    private PersonDialogAction action;
    private final IPersonController controller;
    private JTextField nameField;
    private JTextField emailField;

    /**
     * Creates a {@link PersonDialog} for the given {@link Person} object and 
     * the given {@link PersonDialogAction}.
     * <p>
     * When the action is {@link PersonDialogAction#ADD}, the dialog save button 
     * will say: "Add". When it is {@link PersonDialogAction#EDIT}, it will say: 
     * "Save".
     * @param person the {@link Person} object
     * @param action the type of action
     */
    public PersonDialog(Person person, PersonDialogAction action, IPersonController controller, Frame parent) {
        super(parent,true);
        
        this.setLocationRelativeTo(parent);
        
        this.person = person;
        this.action = action;
        this.controller = controller;
        
        this.initGui();
    }
    
    /**
     * Creates the GUI components of the dialog.
     */
    private void initGui() {
        setLayout(new BorderLayout());
        
        JPanel informationPanel = new JPanel(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        
        informationPanel.add(new JLabel("Name"), gbc);
        
        gbc.gridy = 1;
        informationPanel.add(new JLabel("Email address"), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        nameField = new JTextField(person.getName(),25);
        informationPanel.add(nameField, gbc);
        
        gbc.gridy = 1;
        emailField = new JTextField(person.getEmail());
        informationPanel.add(emailField, gbc);
        
        this.add(informationPanel,BorderLayout.CENTER);
        
        
        JPanel saveCancelPanel = new JPanel();
        
        JButton saveButton;
        if(action == PersonDialogAction.ADD) {
            saveButton = new JButton("Add",IconManager.getInstance().getIcon(Icons.Save));
            saveButton.setMnemonic('A');
            saveButton.setToolTipText("Add");
        } else {
            saveButton = new JButton("Save",IconManager.getInstance().getIcon(Icons.Save));
            saveButton.setMnemonic('S');
            saveButton.setToolTipText("Save");
        }
        
        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        
        saveCancelPanel.add(saveButton);
        
        JButton cancelButton = new JButton("Cancel",IconManager.getInstance().getIcon(Icons.Cancel));
        cancelButton.setMnemonic('C');
        cancelButton.setToolTipText("Cancel");
        
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        
        saveCancelPanel.add(cancelButton);
        
        this.add(saveCancelPanel,BorderLayout.PAGE_END);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });
        
        this.setTitle(person.getName());
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    
    /**
     * Closes the window.
     */
    private void close() {
        this.dispose();
    }
    
    /**
     * Saves the {@link Person} object and disposes of the dialog.
     * model.
     */
    private void save() {
        person.setName(nameField.getText());
        person.setEmail(emailField.getText());
        
        controller.savePerson(person);
        this.dispose();
    }
    
    
    public enum PersonDialogAction {
        ADD,
        EDIT
    }
}