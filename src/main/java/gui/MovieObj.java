package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import gui.Movie.MovieNode;

public class MovieObj extends JPanel{
	Movie data = new Movie();
	//private final int index;
	
	static void setData(final JPanel mainPanel, final JPanel p, final MovieNode data) {
		MovieNode temp = new MovieNode();
		temp = data;
		GridBagConstraints c = new GridBagConstraints();
		int index = 0;
		while(temp != null) {
			c.gridx = 0;
			c.gridy = index;
			p.add(new MovieObj(mainPanel, p, temp.getName(), temp.getCategory(), temp.getReleaseDate(), temp.getImdb(), temp.getRating(), Movie.getIndex(temp), index), c);
			temp = temp.next;
			index++;
		}
	}

	MovieObj(final JPanel mainPanel, final JPanel panel, String name, String gender, String relDate, String imdb, String rating, final int index, final int indexToShow){
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.setLayout(null);
		this.setPreferredSize(new Dimension(600, 70));
		
		this.addMouseListener( new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        if ( SwingUtilities.isRightMouseButton(e) ) {      
		        	//this.setSelectedIndex(e.getPoint());
		        	
		            JPopupMenu menu = new JPopupMenu();
		            JMenuItem itemRemove = new JMenuItem("Remove");
		            JMenuItem itemEdit = new JMenuItem("Edit");
		            itemRemove.addActionListener(new ActionListener() {
		                public void actionPerformed(ActionEvent e) {

		                    try {
								data.removeMovie(index);
								data.resetHeadNode();
								panel.removeAll();
								data.getXmlData();
								setData(mainPanel, panel, data.head);
								panel.repaint();
								panel.revalidate();
								panel.updateUI();
							} catch (ParserConfigurationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SAXException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

		                }
		            });
		            
		            itemEdit.addActionListener(new ActionListener() {
		            	public void actionPerformed(ActionEvent e) {		           
		            		EditPanel editPanel = new EditPanel(index);
		            		mainPanel.removeAll();
		            		mainPanel.add(editPanel);
		            		mainPanel.repaint();
		            		mainPanel.revalidate();
		            		mainPanel.updateUI();
		            	}
		            });
		            
		            menu.add(itemRemove);
		            menu.add(itemEdit);
		            menu.show(e.getComponent(), e.getPoint().x, e.getPoint().y);            
		        }
		    }
		});
		
		JLabel nameLabel = new JLabel("New label");
		nameLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 20));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(179, 8, 229, 26);
		nameLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		nameLabel.setText(name);
		add(nameLabel);
		
		JLabel imdbLabel = new JLabel("New label");
		imdbLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		imdbLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imdbLabel.setBounds(173, 45, 42, 14);
		imdbLabel.setText(imdb);
		add(imdbLabel);
		
		JLabel genderLabel = new JLabel("New label");
		genderLabel.setFont(new Font("Verdana", Font.ITALIC, 16));
		genderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		genderLabel.setBounds(254, 38, 80, 26);
		genderLabel.setText(gender);
		add(genderLabel);
		
		JLabel ratingLabel = new JLabel("New label");
		ratingLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		ratingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ratingLabel.setBounds(374, 45, 34, 14);
		ratingLabel.setText(rating);
		add(ratingLabel);
		
		JLabel numberLabel = new JLabel("New label");
		numberLabel.setBounds(10, 45, 27, 14);
		numberLabel.setText(Integer.toString(indexToShow + 1));
		add(numberLabel);
		
		JLabel releaseDateLabel = new JLabel("New label");
		releaseDateLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		releaseDateLabel.setBounds(454, 20, 91, 20);
		releaseDateLabel.setText(relDate);
		add(releaseDateLabel);
			
		this.setVisible(true);
	}
}
