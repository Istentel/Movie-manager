package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.AbstractListModel;
import javax.swing.border.BevelBorder;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

import org.xml.sax.SAXException;

import gui.Movie.MovieNode;
import javax.swing.JComboBox;

public class MovieAddPanel extends JPanel implements ActionListener {
	Movie data = new Movie();

	private JTextField nameTextField;
	JComboBox dayComboBox;
	JComboBox monthComboBox;
	JComboBox yearComboBox;
	private JTextField imdbTextField;
	JButton saveButton;
	JButton backButton;
	private JComboBox ratingComboBox;
	private JComboBox categoryComboBox;
	
	JLabel nameInfoText;
	JLabel imdbInfoLabel;
	
	MovieAddPanel(){
		this.setSize(MainFrame.GetX(), MainFrame.GetY());
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(15, 15, 15, 15);
		JLabel nameLabel = new JLabel("Nume:");
		nameLabel.setFont(new Font("Serif", Font.PLAIN, 24));
		nameLabel.setBounds(38, 10, 105, 25);
		add(nameLabel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		JLabel categoryLabel = new JLabel("Category:");
		categoryLabel.setFont(new Font("Serif", Font.PLAIN, 24));
		categoryLabel.setBounds(38, 72, 105, 39);
		add(categoryLabel, c);
		
		c.gridx = 0;
		c.gridy = 2;
		JLabel releaseDateLabel = new JLabel("Release date:");
		releaseDateLabel.setFont(new Font("Serif", Font.PLAIN, 24));
		releaseDateLabel.setBounds(38, 149, 143, 25);
		add(releaseDateLabel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		JLabel ratingLable = new JLabel("Rating:");
		ratingLable.setFont(new Font("Serif", Font.PLAIN, 24));
		ratingLable.setBounds(38, 217, 112, 32);
		add(ratingLable, c);
		
		c.gridx = 0;
		c.gridy = 4;
		JLabel imdbLabel = new JLabel("IMDB:");
		imdbLabel.setFont(new Font("Serif", Font.PLAIN, 24));
		imdbLabel.setBounds(38, 286, 95, 25);
		add(imdbLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 4;
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Serif", Font.PLAIN, 22));
		nameTextField.setBounds(176, 10, 203, 27);
		add(nameTextField, c);
		nameTextField.setColumns(10);
		
		c.gridx = 4;
		c.gridy = 0;
		nameInfoText = new JLabel("");
		add(nameInfoText, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 4;
		String categoryString[] = {"Action", "Comedy", "Drama", "Fantasy", "Horror", "Mystery", "Romance", "Thriller"};		
		categoryComboBox = new JComboBox(categoryString);
		categoryComboBox.setBounds(176, 85, 203, 27);
		add(categoryComboBox, c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.insets = new Insets(15, 100, 15, 5);
		String dayString[] = new String[31];
		for(int i = 1; i < 32; i++) {
			dayString[i - 1] = String.valueOf(i);
		}
		dayComboBox = new JComboBox(dayString);
		dayComboBox.setPreferredSize(new Dimension(40, 25));
		add(dayComboBox, c);
		
		c.gridx = 2;
		c.gridy = 2;
		c.insets = new Insets(15, 1, 15, 5);
		String monthString[] = new String[12];
		for(int i = 1; i < 13; i++) {
			monthString[i - 1] = String.valueOf(i);
		}
		monthComboBox = new JComboBox(monthString);
		monthComboBox.setPreferredSize(new Dimension(40, 25));
		add(monthComboBox, c);
		
		c.gridx = 3;
		c.gridy = 2;
		String yearString[] = new String[134];
		for(int i = 1888, j = 0; i < 2022; i++, j++) {
			yearString[j] = String.valueOf(i);
		}
		yearComboBox = new JComboBox(yearString);
		yearComboBox.setPreferredSize(new Dimension(60, 25));
		add(yearComboBox, c);
		
		c.gridx = 4;
		c.gridy = 2;
		JLabel dateInfoLabel = new JLabel("dd/mm/yyyy");
		add(dateInfoLabel, c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 4;
		c.insets = new Insets(15, 15, 15, 15);
		String ratingString[] = {"10", "9", "8", "7", "6", "5", "4", "3", "2", "1"};		
		ratingComboBox = new JComboBox(ratingString);
		add(ratingComboBox, c);
		
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 4;
		imdbTextField = new JTextField();
		imdbTextField.setFont(new Font("Serif", Font.PLAIN, 22));
		imdbTextField.setBounds(176, 286, 203, 27);
		add(imdbTextField, c);
		imdbTextField.setColumns(10);
		
		c.gridx = 4;
		c.gridy = 4;
		imdbInfoLabel = new JLabel("0.0 - 10");
		add(imdbInfoLabel, c);
		
		c.gridx = 4;
		c.gridy = 5;
		c.gridwidth = 1;
		saveButton = new JButton("Save");
		saveButton.setFont(new Font("Serif", Font.PLAIN, 24));
		saveButton.setBounds(492, 341, 85, 25);
		saveButton.addActionListener(this);
		add(saveButton, c);
		
		c.gridx = 0;
		c.gridy = 5;
		backButton = new JButton("Back");
		backButton.setFont(new Font("Serif", Font.PLAIN, 24));
		backButton.setBounds(38, 341, 85, 25);
		backButton.addActionListener(this);
		add(backButton, c);
		
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == saveButton) {
			boolean mistake = false;
			boolean isFloat = imdbTextField.getText().matches("[0-9]*\\.[0-9]");
			
			if(nameTextField.getText().isEmpty()) {
				nameInfoText.setText("*Name required");
				nameInfoText.setForeground(Color.RED);
				mistake = true;
			}
			if(!isFloat || imdbTextField.getText().isEmpty() || Float.parseFloat(imdbTextField.getText()) < 0  || Float.parseFloat(imdbTextField.getText()) > 10) {
				imdbInfoLabel.setText("*0.0 - 10");
				imdbInfoLabel.setForeground(Color.RED);
				mistake = true;
			}
			
			if(mistake) { return; }
		}
		this.removeAll();
		if(e.getSource() == saveButton) {
			String relDate = (String) dayComboBox.getSelectedItem() + "/" + (String) monthComboBox.getSelectedItem() + "/" + (String) yearComboBox.getSelectedItem();
			MovieNode newData = new MovieNode(nameTextField.getText(), (String) categoryComboBox.getSelectedItem(), relDate, (String) ratingComboBox.getSelectedItem(), imdbTextField.getText());
			try {
				data.addMovie(newData);
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			MainPanel panel = new MainPanel();
			this.add(panel);
			this.updateUI();
		}
		
		if(e.getSource() == backButton) {			
			MainPanel panel = new MainPanel();
			this.add(panel);			
			this.updateUI();
		}
		
	}
}