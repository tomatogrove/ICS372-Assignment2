import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import car.functionality.VehicleJSONParser;
import car.inventory.Vehicle;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;

public class ViewAllVehicleGui extends JFrame {

	private JPanel contentPane;
	static File path;
	static ViewAllVehicleGui frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ViewAllVehicleGui(path);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param filepath 
	 */
	public ViewAllVehicleGui(File filepath) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 446);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VehicleImportFile frameImport = new VehicleImportFile();
				frameImport.setVisible(true);
				frame.setVisible(false);
			}
		});
		backButton.setBounds(25, 10, 85, 21);
		contentPane.add(backButton);
		
		JLabel informationLabel = new JLabel("Informations");
		informationLabel.setBounds(214, 36, 130, 13);
		contentPane.add(informationLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(41, 59, 453, 304);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		
		
		
		path = filepath;
		String pathFile = String.valueOf(path);
		String detail ="";
		List<Vehicle> value = VehicleJSONParser.read(pathFile);
		for(Vehicle vehicle : value) {
			detail+= vehicle.toString();
		}
		textArea.setText(detail);
	}
}
