/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;



public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {// You fill this in, along with any helper methods //
		initInteractors();
		
		database = new NameSurferDataBase(NAMES_DATA_FILE);
		
		graph = new NameSurferGraph();
		add(graph);				
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == nameField || e.getActionCommand().equals("Graph")) {
			
			NameSurferEntry entry = database.findEntry(nameField.getText());
			nameField.setText("");
			
			if (entry != null) {
		        graph.addEntry(entry);
		        graph.update();
			}
	      }
		if (e.getActionCommand().equals("Clear")) {
			graph.clear();
			graph.update();
	      }		
	}

	public void initInteractors() {
		
		nameField = new JTextField(20);
	    add(new JLabel("Name"), SOUTH);
	    add(nameField, SOUTH);
	    nameField.addActionListener(this);

		add(new JButton("Graph"), SOUTH);
		add(new JButton("Clear"), SOUTH);
	    addActionListeners(); 		
	}
			
	
/* Private instance variables */
	private JTextField nameField;
	private NameSurferDataBase database;
	private NameSurferGraph graph;
}
