package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import movieShowPanels.CategoryShowPanel;
import movieShowPanels.MoviesShowPanel;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ListSelectionModel;

public class MainPanel extends JPanel implements ActionListener{
	Movie data = new Movie();
	DefaultListModel info = new DefaultListModel();
	MoviesShowPanel moviesShowPanel;
	CategoryShowPanel categoryShowPanel;
	
	JPanel showingPanel;
	
	ButtonsPanel buttonsPanel;
	JButton categoryButton;
	JButton moviesButton;
	
	JButton addButton;
	JButton editButton;
	
	MainPanel(){	
		addButton = new JButton();
		addButton.setText("Add");
		addButton.setBounds(0, 0, 100, 25);
		addButton.setSize(100, 25);
		addButton.addActionListener(this);
		addButton.setForeground(Color.BLUE);
		//addButton.setLayout(BorderLayout.NORTH);
		
		
		this.setSize(585, 383);
		this.setLayout(new BorderLayout());
		this.add(addButton);
		
		buttonsPanel = new ButtonsPanel();
		
		categoryButton = new JButton("Category");
		categoryButton.setBounds(26, 30, 89, 25);
		categoryButton.addActionListener(this);
		buttonsPanel.add(categoryButton);
		
		moviesButton = new JButton("Movies");
		moviesButton.setBounds(26, 115, 89, 25);
		moviesButton.addActionListener(this);
		buttonsPanel.add(moviesButton);
		
		
		this.add(buttonsPanel);
		data.getXmlData();
		info = data.GetMoviesList();
		
		moviesShowPanel = new MoviesShowPanel(info);
		moviesShowPanel.data = info;
		this.add(moviesShowPanel);
		moviesShowPanel.list.setLocation(150, 26);
		
		
		
		editButton = new JButton("Edit");
		editButton.setBounds(100, 0, 100, 25);
		editButton.addActionListener(this);
		moviesShowPanel.add(editButton);
		
		
		this.validate();
		this.repaint();
		
		//buttonsPanel.validate();
		moviesShowPanel.validate();
		//this.repaint();		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == addButton) {
			this.removeAll();
			MovieAddPanel addPanel = new MovieAddPanel();
			this.add(addPanel);
			this.updateUI();
		}
		
		if(e.getSource() == editButton) {
			this.removeAll();
			EditPanel editPanel = new EditPanel(moviesShowPanel.list.getSelectedIndex());
			this.add(editPanel);
			this.updateUI();
		}
		
		if(e.getSource() == categoryButton) {
			//MoviesShowPanel updated = new MoviesShowPanel(data.getCategorySortedData());
			//info = data.getCategorySortedData();
			//data.getXmlData();
			//moviesShowPanel.data.removeAllElements();
			//moviesShowPanel.data = data.getCategorySortedData();
			
			//info = data.getCategorySortedData();
			//moviesShowPanel.updateListCategory();		
			
			moviesShowPanel.setVisible(false);
			categoryShowPanel = new CategoryShowPanel(data.getCategorySortedData());
			this.add(categoryShowPanel);
			this.updateUI();
		}
		
		if(e.getSource() == moviesButton) {
			categoryShowPanel.setVisible(false);
			//MoviesShowPanel n = new MoviesShowPanel(data.getCategorySortedData());
			this.add(moviesShowPanel);
			this.updateUI();
		}
	}
}
