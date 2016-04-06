package testPackage;


import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;

@SuppressWarnings("serial")

// By extending JFrame we have our applet

public class test extends JFrame implements ActionListener{

	
	private Game game;
	public static void main(String[] args) {
		new test();
	}
	
	public test(){

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//this.add(new DrawStuff(), BorderLayout.CENTER);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setFocusable(true);
		this.setSize(585, 600);
		this.setResizable(false);
		this.setLocation(50, 50);
		JButton b1 = new JButton("Move Player");
		JButton b2 = new JButton("Horizontal Wall");
		JButton b3 = new JButton("Vertical Wall");
		//this.setLayout(new BorderLayout());
		game =new Game();
		// applet.setLocation(new Point(0,0));
		// applet.setBounds(new Rectangle(new Point(0,0)));
		//applet.setPreferredSize(new Dimension(370, 500));
		this.add(game, BorderLayout.CENTER);
		Container box = new Container();
		Container buttonHolder = new Container();
		buttonHolder.setLayout(new BorderLayout());
		this.add(buttonHolder, BorderLayout.EAST);
		buttonHolder.add(box, BorderLayout.NORTH);
		box.setLayout(new GridLayout(3,1));
		//box.add(applet);
		b1.addActionListener(this);
		box.add(b1);
		b2.addActionListener(this);
		box.add(b2);
		b3.addActionListener(this);
		box.add(b3);
		// frame.pack();
		this.setVisible(true);
		//this.setVisible(true);

	}

	// Creating my own component by extending JComponent
	// JComponent is the base class for all swing components. Even custom ones
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getActionCommand().equals("Move Player")){
			game.setMovePlayer();
		}if (e.getActionCommand().equals("Horizontal Wall")){
			game.setHorizontalWall();
		}
		if (e.getActionCommand().equals("Vertical Wall")){
			game.setVerticalWall();
		}
	}
	
	

}


