package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import gui.Movie.MovieNode;

public class EditPanel extends JPanel implements ActionListener{
	Movie data = new Movie();
	int ind;
	
	private JTextField nameTextField;

	private JTextField releaseDateTextField;

	private JTextField imdbTextField;
	JButton saveButton;
	JButton backButton;
	
	JComboBox<String> categoryComboBox;
	JComboBox<String> ratingComboBox;
	
	public EditPanel(int index){
		ind = index;
		data.getXmlData();
		
		this.setSize(MainFrame.GetX(), MainFrame.GetY());
		this.setLayout(null);
		
		JLabel nameLabel = new JLabel("Nume:");
		nameLabel.setFont(new Font("Serif", Font.PLAIN, 24));
		nameLabel.setBounds(38, 10, 105, 25);
		add(nameLabel);
		
		JLabel categoryLabel = new JLabel("Category:");
		categoryLabel.setFont(new Font("Serif", Font.PLAIN, 24));
		categoryLabel.setBounds(38, 72, 105, 39);
		add(categoryLabel);
		
		JLabel releaseDateLabel = new JLabel("Release date:");
		releaseDateLabel.setFont(new Font("Serif", Font.PLAIN, 24));
		releaseDateLabel.setBounds(38, 149, 143, 25);
		add(releaseDateLabel);
		
		JLabel ratingLable = new JLabel("Rating:");
		ratingLable.setFont(new Font("Serif", Font.PLAIN, 24));
		ratingLable.setBounds(38, 215, 112, 32);
		add(ratingLable);
		
		JLabel imdbLabel = new JLabel("IMDB:");
		imdbLabel.setFont(new Font("Serif", Font.PLAIN, 24));
		imdbLabel.setBounds(38, 286, 95, 25);
		add(imdbLabel);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Serif", Font.PLAIN, 22));
		nameTextField.setBounds(176, 10, 203, 27);
		nameTextField.setText(data.getNameNode(index));
		add(nameTextField);
		nameTextField.setColumns(10);
		
		
		releaseDateTextField = new JTextField();
		releaseDateTextField.setFont(new Font("Serif", Font.PLAIN, 22));
		releaseDateTextField.setBounds(176, 148, 203, 27);
		releaseDateTextField.setText(data.getReleaseDateNode(index));
		add(releaseDateTextField);
		releaseDateTextField.setColumns(10);
		
		
		imdbTextField = new JTextField();
		imdbTextField.setFont(new Font("Serif", Font.PLAIN, 22));
		imdbTextField.setBounds(176, 286, 203, 27);
		imdbTextField.setText(String.valueOf(data.getImdbNode(index)));
		add(imdbTextField);
		imdbTextField.setColumns(10);
		
		saveButton = new JButton("Save");
		saveButton.setFont(new Font("Serif", Font.PLAIN, 24));
		saveButton.setBounds(492, 341, 85, 25);
		saveButton.addActionListener(this);
		add(saveButton);
		
		backButton = new JButton("Back");
		backButton.setFont(new Font("Serif", Font.PLAIN, 24));
		backButton.setBounds(38, 341, 85, 25);
		backButton.addActionListener(this);
		add(backButton);
		
		String categoryString[] = {"Action", "Codemy", "Drama", "Fantasy", "Horror", "Mystery", "Romance", "Thriller"};
		
		categoryComboBox = new JComboBox<String>(categoryString);
		categoryComboBox.setBounds(176, 85, 203, 27);
		add(categoryComboBox);
		
		String ratingString[] = {"10", "9", "8", "7", "6", "5", "4", "3", "2", "1"};
		
		ratingComboBox = new JComboBox<String>(ratingString);
		ratingComboBox.setBounds(176, 220, 203, 27);
		add(ratingComboBox);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.removeAll();
		if(e.getSource() == saveButton) {
			MovieNode newData = new MovieNode(nameTextField.getText(), (String) categoryComboBox.getSelectedItem(), releaseDateTextField.getText(), (String) ratingComboBox.getSelectedItem(), imdbTextField.getText());
			try {
				data.updateMovie(newData, ind);
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParserConfigurationException e1) {
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
