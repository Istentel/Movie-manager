package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import gui.Movie.MovieNode;

public class MainPanel extends JPanel implements ActionListener{
	Movie data = new Movie();
	JButton addButton;
	JPanel panel;
	
	JMenuItem sortByCategory;
	JMenuItem sortByRating;
	JMenuItem sortByImdb;
	
	JMenuItem raportByCategory;
	JMenuItem raportByRating;
	JMenuItem raportByImdb;
	
	MainPanel(){
		this.setLayout(new GridBagLayout());

		GridBagConstraints frameConstraints = new GridBagConstraints();
		
		addButton = new JButton();
		addButton.setText("Add");
		addButton.addActionListener(this);
		//addButton.setPreferredSize(new Dimension(60, 21));
		frameConstraints.gridx = 0;
		frameConstraints.gridy = 0;
		this.add(addButton, frameConstraints);
		
		JMenuBar sortMenuBar = new JMenuBar();
		JMenu sortMenu = new JMenu("Sort");
		sortByCategory = new JMenuItem("Category");
		sortByRating = new JMenuItem("Rating");
		sortByImdb = new JMenuItem("IMDB");
		
		sortByCategory.setMnemonic(KeyEvent.VK_C);
		sortByCategory.addActionListener(this);
		sortByRating.setMnemonic(KeyEvent.VK_R);
		sortByRating.addActionListener(this);
		sortByImdb.setMnemonic(KeyEvent.VK_I);
		sortByImdb.addActionListener(this);
		
		sortMenu.add(sortByCategory);
		sortMenu.add(sortByRating);
		sortMenu.add(sortByImdb);
		sortMenu.setMnemonic(KeyEvent.VK_S);
		sortMenu.setPreferredSize(new Dimension(40, 25));
		
		sortMenuBar.add(sortMenu);
		
		frameConstraints.gridx = 1;
		frameConstraints.gridy = 0;
		this.add(sortMenuBar, frameConstraints);
		
		JMenuBar raportMenuBar = new JMenuBar();
		JMenu  raportMenu = new JMenu("Raport");
		raportByCategory = new JMenuItem("Category");
		raportByRating = new JMenuItem("Rating");
		raportByImdb = new JMenuItem("Imdb");
		
		raportByCategory.setMnemonic(KeyEvent.VK_C);
		raportByCategory.addActionListener(this);
		raportByRating.setMnemonic(KeyEvent.VK_R);
		raportByRating.addActionListener(this);
		raportByImdb.setMnemonic(KeyEvent.VK_I);
		raportByImdb.addActionListener(this);
		
		raportMenu.add(raportByCategory);
		raportMenu.add(raportByRating);
		raportMenu.add(raportByImdb);
		raportMenu.setMnemonic(KeyEvent.VK_R);
		raportMenu.setPreferredSize(new Dimension(50, 25));
		
		raportMenuBar.add(raportMenu);
		
		frameConstraints.gridx = 2;
		frameConstraints.gridy = 0;
		this.add(raportMenuBar, frameConstraints);
		
		////panel
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(Color.black);
		data.getXmlData();
		setData(data.head);
		
		
		JScrollPane scrollPane = new JScrollPane(panel,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(600, 420));
        
        frameConstraints.gridx = 0;
		frameConstraints.gridy = 1;
		frameConstraints.gridwidth = 4;
        
		this.add(scrollPane, frameConstraints);
		
		
		//this.validate();
		//this.repaint();
		this.setVisible(true);
	}
	
	void setData(final MovieNode data) {
		MovieNode temp = new MovieNode();
		temp = data;
		GridBagConstraints c = new GridBagConstraints();
		int index = 0;
		
		while(temp != null) {
			c.gridx = 0;
			c.gridy = index;
			panel.add(new MovieObj(this, panel, temp.getName(), temp.getCategory(), temp.getReleaseDate(), temp.getImdb(), temp.getRating(), Movie.getIndex(temp), index), c);
			temp = temp.next;
			index++;
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == addButton) {			
			this.removeAll();
			//this.setLayout(null);
			MovieAddPanel addPanel = new MovieAddPanel();
			this.add(addPanel);
			this.updateUI();
		}
		
		if(e.getSource() == sortByCategory) {
			panel.removeAll();
			data.resetHeadNode();
			data.getXmlData();
			setData(data.getCategorySortedData());
			panel.repaint();
			panel.revalidate();
			panel.updateUI();
		}
		
		if(e.getSource() == sortByRating) {
			panel.removeAll();
			data.resetHeadNode();
			data.getXmlData();
			setData(data.getRatingSortedData());
			panel.repaint();
			panel.revalidate();
			panel.updateUI();
		}
		
		if(e.getSource() == sortByImdb) {
			panel.removeAll();
			data.resetHeadNode();
			data.getXmlData();
			setData(data.getImdbSortedData());
			panel.repaint();
			panel.revalidate();
			panel.updateUI();
		}
		
		if(e.getSource() == raportByCategory) {
			Movie newData = new Movie();
			newData.getXmlData();
			data.writeRaport("Movies Raport by Category.txt", newData.getCategorySortedData());
		}
		
		if(e.getSource() == raportByRating) {
			Movie newData = new Movie();
			newData.getXmlData();
			data.writeRaport("Movies Raport by Rating.txt", newData.getRatingSortedData());
		}
		
		if(e.getSource() == raportByImdb) {
			Movie newData = new Movie();
			newData.getXmlData();
			data.writeRaport("Movies Raport by Imdb.txt", newData.getImdbSortedData());
		}
		
	}
}
