package gui;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class RoundButton extends JButton {
     public static void main(String[] args)
     {
         //Create a button with the label "Jackpot".
         JButton button = new RoundButton("Jackpot");
         button.setBackground(Color.green);

         //Create a frame in which to show the button.
         JFrame frame = new JFrame();
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.getContentPane().setLayout(new FlowLayout());
         frame.getContentPane().setBackground(Color.yellow);
         frame.getContentPane().add(button);
         frame.setSize(150, 120);
         frame.setVisible(true);
     }

     public RoundButton(String label)
     {
         super(label);

         //Enlarge button to make a circle rather than an oval.
         Dimension size = getPreferredSize();
         size.width = size.height = Math.max(size.width, size.height);
         setPreferredSize(size);

         //This call causes the JButton *not* to paint the background
         //and allows us to paint a round background instead.
         setContentAreaFilled(false);
     }

     //Paint the round background and label.
    protected void paintComponent(Graphics g)
     {
         if (getModel().isArmed())
             //You might want to make the highlight color
             //a property of the RoundButton class.
            g.setColor(Color.lightGray);
         else
             g.setColor(getBackground());
         g.fillOval(0, 0, getSize().width-1, getSize().height-1);
         //This call will paint label and focus rectangle.
         super.paintComponent(g);
     }

     //Paint the border of the button using a simple stroke.
     protected void paintBorder(Graphics g)
     {
         g.setColor(getForeground());
         g.drawOval(0, 0, getSize().width-1, getSize().height-1);
     }
}
