package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInFrame extends JFrame implements ActionListener{
	
	private JTextField userText;
	private JPasswordField passwordTextField;
	JLabel wrongPass;
	
	LogInFrame(){
		JPanel panel = new JPanel();
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(panel);
		
		panel.setLayout(null);
		
		JLabel label = new JLabel("User");
		label.setBounds(10, 20, 80, 25);
		panel.add(label);
		
		userText = new JTextField(20);
		userText.setBounds(100, 20, 165, 25);
		panel.add(userText);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 50, 80, 25);
		panel.add(passwordLabel);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(100, 50, 165, 25);
		panel.add(passwordTextField);
		
		JButton button = new JButton("Login");
		button.setBounds(10,80, 80, 25);
		button.addActionListener(this);
		panel.add(button);
		
		wrongPass = new JLabel("");
		wrongPass.setBounds(10, 110, 300, 25);
		panel.add(wrongPass);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(userText.getText().equals("Cristian") && passwordTextField.getText().equals("1234")) {
			this.dispose();
			MainFrame frame = new MainFrame();
		}else {
			wrongPass.setText("Wrong password or user!");
		}
		
	}
}
