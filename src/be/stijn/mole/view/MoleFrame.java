package be.stijn.mole.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Stijn Bouchier
 * @since 12/09/2012 
 * @version 20120912.1 
 */
public class MoleFrame extends JFrame {
    private JPanel buttonPanel;
    private Container contentPane;
    private IView currentView;
    
    public MoleFrame() {
        super();
    }
    
    public void initGui() {
        contentPane = this.getContentPane();
        
        contentPane.setLayout(new BorderLayout());
        
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.gray);
        contentPane.add(buttonPanel,BorderLayout.NORTH);
    }
    
    public void add(JButton viewButton, IView view) {
        buttonPanel.add(viewButton);
        viewButton.addActionListener(new ViewButtonListener(view));
    }
    
    public void setContent(IView view) {
        if(currentView != null)
            contentPane.remove(currentView);
        
        contentPane.add(view,BorderLayout.CENTER);
        currentView = view;
    }
    
    private class ViewButtonListener implements ActionListener {
        private IView view;

        private ViewButtonListener(IView view) {
            this.view = view;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            setContent(view);
            pack();
        }
    }
}
