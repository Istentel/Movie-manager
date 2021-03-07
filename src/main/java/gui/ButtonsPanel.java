package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import movieShowPanels.MoviesShowPanel;

public class ButtonsPanel extends JPanel implements ActionListener{
	
	public JButton actorsButton;
	
	ButtonsPanel(){
		this.setBorder(null);
		this.setBounds(0, 25 , MainFrame.GetX() / 4, MainFrame.GetY() - 63);
		this.setVisible(true);
		this.setLayout(null);
		this.setBackground(Color.GRAY);
		
		
		

		
		actorsButton = new JButton("Actors");
		actorsButton.setBounds(26, 198, 89, 25);
		add(actorsButton);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(26, 290, 89, 25);
		add(btnNewButton_3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == actorsButton) {
			
		}
		
	}
}
