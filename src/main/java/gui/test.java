package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class test {
	public static void main(String[] args) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setVisible(true);
		//panel.setSize(600 / 2, 400 - 25);
		panel.setBounds(0, 25 , 600 / 2, 400 -25);
		panel.setLayout(null);
		panel.setBackground(Color.BLACK);
		
		JButton btn = new JButton("ADD");
		btn.setFont(new Font("Segoe UI Black", Font.PLAIN, 16));
		btn.setBounds(0, 0, 100, 25);
		btn.setBackground(Color.blue);
		btn.setBorder(null);
		btn.setOpaque(false);
		btn.setFocusable(true);
		//btn.setBorder(new RoundedBorder(10));
		btn.setForeground(Color.WHITE);
		//btn.setIcon(new ImageIcon("C:\\Users\\sc\\eclipse-workspace\\maven-test\\src\\test\\resources\\add2.png"));
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		frame.setSize(600,400);
		frame.getContentPane().add(btn);
		frame.getContentPane().add(panel);
		
		JButton testButton = new JButton("TEST");
		testButton.setBackground(SystemColor.textHighlight);
		testButton.setBounds(104, 0, 89, 25);
		frame.getContentPane().add(testButton);
		testButton.setFocusable(false);
		
		JButton btnNewButton_1 = new JButton("TEST2");
		btnNewButton_1.setBounds(203, 0, 89, 25);
		frame.getContentPane().add(btnNewButton_1);
	}
}
