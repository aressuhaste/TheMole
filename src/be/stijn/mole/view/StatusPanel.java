package be.stijn.mole.view;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Stijn Bouchier
 * @since 20/08/2012 
 * @version 20120820.1 
 */
public class StatusPanel extends JPanel {
    private JLabel statusLabel;

    public String getStatus() {
        return this.statusLabel.getText();
    }

    public void setStatus(String status) {
        this.statusLabel.setText(status);
        this.repaint();
    }
    
    public void initGui() {
        this.setLayout(new FlowLayout());
        
        this.statusLabel = new JLabel();
        this.add(statusLabel);
    }
}
