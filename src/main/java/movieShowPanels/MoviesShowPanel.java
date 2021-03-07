package movieShowPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import gui.EditPanel;
import gui.MainFrame;
import gui.Movie;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JMenuItem;
import java.awt.Font;

public class MoviesShowPanel extends JPanel{
	public static JList list;
	public static DefaultListModel data = new DefaultListModel();
	
	public static void updateListCategory() {
		data = Movie.getCategorySortedData();
		
	}
	
	public MoviesShowPanel(DefaultListModel info){

		
		this.setBounds(MainFrame.GetX() / 4 - 1, 25, 435, MainFrame.GetY() - 63);
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(null);
		
		
		//data.getMovies();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(571, 25, 14, 358);
		scrollPane.setViewportView(list);
		
		list = new JList(info);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//add(list);
		list.setFont(new Font("Palatino Linotype", Font.PLAIN, 20));
		//list.setLayout(null);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setBounds(151, 25, 434, 358);
		//add(scrollPane);
		list.addMouseListener( new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        if ( SwingUtilities.isRightMouseButton(e) ) {      
		           list.setSelectedIndex(list.locationToIndex(e.getPoint()));

		            JPopupMenu menu = new JPopupMenu();
		            JMenuItem itemRemove = new JMenuItem("Remove");
		            itemRemove.addActionListener(new ActionListener() {
		                public void actionPerformed(ActionEvent e) {

		                    try {
								Movie.removeMovie(list.getSelectedIndex());
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
		                    
		                    System.out.println("Remove the element in position " + list.getSelectedValue());

		                }
		            });
		            
		            
		            
		            menu.add(itemRemove);
		            menu.show(list, e.getPoint().x, e.getPoint().y);            
		        }
		    }
		});		
		
		this.add(list);
		this.setVisible(true);
	}
}
