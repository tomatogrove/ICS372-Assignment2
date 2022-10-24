package car.ui;

import car.storage.StateManager;
import car.inventory.DealerGroup;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NavigationManager extends JFrame {
    private static JPanel currentPanel;
    private static JFrame frame;


    //ensures that everything is set up on start so that the public methods don't access null values.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(NavigationManager::setUpFrame);
    }

    private static void setUpFrame() {
        StateManager.load();

        frame = new JFrame("The Container");
        frame.setSize(500, 500);

        // code found https://www.codejava.net/java-se/swing/preventing-jframe-window-from-closing
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                StateManager.save();
                frame.dispose();
            }
        });

        //placeholder for Pao's panel. Will change when Pao decides on a name
        currentPanel = new WelcomePanel();
        frame.add(currentPanel);

        frame.setVisible(true);
    }

    public static void changePanel(JPanel newPanel) {
        newPanel.setVisible(true);
        currentPanel.setVisible(false);

        frame.remove(currentPanel);
        currentPanel = newPanel;

        frame.add(currentPanel);
    }
}
