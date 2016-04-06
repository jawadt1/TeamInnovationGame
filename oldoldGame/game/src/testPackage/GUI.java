package testPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GUI {
	private JFrame frame;
	private JLabel titleLabel;
	private JButton playButton;
	private JButton helpButton;
	private JButton settingsButton;
	private JButton exitButton;
	private JButton singlePlayerButton;
	private JButton multiPlayerButton;
	private JButton backButton;

	private MainMenuPanel mainMenuPanel;
	private GameMenuPanel gameMenuPanel;
	private SettingsPanel settingsPanel;
	private HelpPanel helpPanel;
	private GamePanel gamePanel;
	private Clip clip;
	private JComboBox sizeList;
	private JButton soundButton;
	
	private boolean sound = true;
	
	public GUI(){
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(865, 520));
		frame.setTitle("Quoridor Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		mainMenuPanel = new MainMenuPanel();
		gameMenuPanel = new GameMenuPanel();
		settingsPanel = new SettingsPanel();
		helpPanel = new HelpPanel();
		gamePanel = new GamePanel();
		
		frame.add(mainMenuPanel);
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(GUI.class.getResource("background-music.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.loop(clip.LOOP_CONTINUOUSLY);
			clip.start();
		
		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	class MainMenuPanel extends JPanel{	
		public MainMenuPanel(){
			this.setVisible(true);
			this.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
				
			titleLabel = new JLabel();
			titleLabel.setText("Quoridor");
			titleLabel.setFont(new Font("Calibri", 0, 80));
			titleLabel.setForeground(Color.WHITE);
			c.gridx = 0;
			c.gridy = 0;
			c.insets = new Insets(0,0,30,0);
			this.add(titleLabel, c);
			
			playButton = new JButton();
			playButton.setText("Play");
			playButton.setPreferredSize(new Dimension(300,80));
			playButton.setFont(new Font("Calibri", 0, 30));
			playButton.setForeground(Color.BLACK);
			c.gridx = 0;
			c.gridy = 1;
			c.insets = new Insets(0,0,10,0);
			this.add(playButton, c);
			playButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					frame.remove(mainMenuPanel);				
					frame.add(gameMenuPanel);
					frame.validate();
					frame.repaint();
					
				}
			});
			
			helpButton = new JButton();
			helpButton.setText("Help");
			helpButton.setPreferredSize(new Dimension(300,80));
			helpButton.setFont(new Font("Calibri", 0, 30));
			playButton.setForeground(Color.BLACK);
			c.gridx = 0;
			c.gridy = 2;
			this.add(helpButton, c);
			helpButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					frame.remove(mainMenuPanel);				
					frame.add(helpPanel);
					frame.validate();	
					frame.repaint();
				}
			});
				
			settingsButton = new JButton();
			settingsButton.setText("Settings");
			settingsButton.setPreferredSize(new Dimension(300,80));
			settingsButton.setFont(new Font("Calibri", 0, 30));
			settingsButton.setForeground(Color.BLACK);
			c.gridx = 0;
			c.gridy = 3;
			this.add(settingsButton, c);
			settingsButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					frame.remove(mainMenuPanel);				
					frame.add(settingsPanel);
					frame.validate();	
					frame.repaint();
				}
			});
				
			exitButton = new JButton();
			exitButton.setText("Exit");
			exitButton.setPreferredSize(new Dimension(300,80));
			exitButton.setFont(new Font("Calibri", 0, 30));
			exitButton.setForeground(Color.BLACK);
			c.gridx = 0;
			c.gridy = 4;
			this.add(exitButton, c);
			exitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					System.exit(0);
				}
			});
				
			frame.add(this);
		}
			
		@Override
		public void paintComponent(Graphics g){
			super.paintComponents(g);
			ImageIcon i = new ImageIcon((getClass().getResource("background.png")));
			i.paintIcon(this, g, 0, 0);
			 	
		}
			 
	}
	
	class GameMenuPanel extends JPanel{	
		public GameMenuPanel(){
			this.setVisible(true);
			this.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			titleLabel = new JLabel();
			titleLabel.setText("Quoridor");
			titleLabel.setFont(new Font("Calibri", 0, 80));
			titleLabel.setForeground(Color.WHITE);
			c.gridx = 0;
		    c.gridy = 0;
		    c.insets = new Insets(0,0,50,0);
		    this.add(titleLabel, c);
			
			singlePlayerButton = new JButton();
			singlePlayerButton.setText("Single Player");
			singlePlayerButton.setPreferredSize(new Dimension(300,80));
			singlePlayerButton.setFont(new Font("Calibri", 0, 30));
			singlePlayerButton.setForeground(Color.BLACK);
			c.gridx = 0;
		    c.gridy = 1;
		    c.insets = new Insets(0,0,10,0);
		    this.add(singlePlayerButton, c);
			singlePlayerButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					frame.remove(gameMenuPanel);
					gamePanel = new GamePanel();
					frame.add(gamePanel);
					frame.validate();	
					frame.repaint();
				}
			});
			
			multiPlayerButton = new JButton();
			multiPlayerButton.setText("MultiPlayer");
			multiPlayerButton.setPreferredSize(new Dimension(300,80));
			multiPlayerButton.setFont(new Font("Calibri", 0, 30));
			multiPlayerButton.setForeground(Color.BLACK);
			c.gridx = 0;
		    c.gridy = 2;
		    this.add(multiPlayerButton, c);
			multiPlayerButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					frame.remove(gameMenuPanel);				
					frame.add(gamePanel);
					frame.validate();	
					frame.repaint();
				}
			});
			
			backButton = new JButton();
			backButton.setText("Back");
			backButton.setPreferredSize(new Dimension(300,80));
			backButton.setFont(new Font("Calibri", 0, 30));
			backButton.setForeground(Color.BLACK);
			c.gridx = 0;
		    c.gridy = 3;
		    c.insets = new Insets(0,0,80,0);
		    this.add(backButton, c);
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					frame.remove(gameMenuPanel);				
					frame.add(mainMenuPanel);
					frame.validate();	
					frame.repaint();
				}
			});
			
			frame.add(this);			
		}
		
		@Override
		public void paintComponent(Graphics g){
			super.paintComponents(g);
			ImageIcon i = new ImageIcon((getClass().getResource("background.png")));
			i.paintIcon(this, g, 0, 0);
			 	
		}
	}
	
	public class GamePanel extends JPanel implements ActionListener{
		private Game board;
		
		public GamePanel(){			
			this.setVisible(true);
			this.setLocation(50, 50);
			JButton b1 = new JButton("Move Player");
			JButton b2 = new JButton("Horizontal Wall");
			JButton b3 = new JButton("Vertical Wall");
			this.setLayout(new BorderLayout());
			board = new Game();

			this.add(board, BorderLayout.CENTER);
			Container box = new Container();
			Container buttonHolder = new Container();
			buttonHolder.setLayout(new BorderLayout());
			this.add(buttonHolder, BorderLayout.EAST);
			buttonHolder.add(box, BorderLayout.NORTH);
			box.setLayout(new GridLayout(4,1));

			b1.addActionListener(this);
			b1.setPreferredSize(new Dimension(300,80));
			b1.setFont(new Font("Calibri", 0, 30));
			box.add(b1);
			b2.addActionListener(this);
			b2.setPreferredSize(new Dimension(300,80));
			b2.setFont(new Font("Calibri", 0, 30));
			box.add(b2);
			b3.addActionListener(this);
			b3.setPreferredSize(new Dimension(300,80));
			b3.setFont(new Font("Calibri", 0, 30));
			box.add(b3);
			backButton = new JButton();
			backButton.setText("Back");
			backButton.setFont(new Font("Calibri", 0, 30));
		    box.add(backButton);
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					frame.remove(gamePanel);				
					frame.add(mainMenuPanel);
					frame.validate();	
					frame.repaint();
				}
			});

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			if (e.getActionCommand().equals("Move Player")){
				board.setMovePlayer();
			}if (e.getActionCommand().equals("Horizontal Wall")){
				board.setHorizontalWall();
			}
			if (e.getActionCommand().equals("Vertical Wall")){
				board.setVerticalWall();
			}
		}
		@Override
		public void paintComponent(Graphics g){
			super.paintComponents(g);
			ImageIcon i = new ImageIcon((getClass().getResource("background.png")));
			i.paintIcon(this, g, 0, 0);
			 	
		}
	}
	
	class HelpPanel extends JPanel{	
		public HelpPanel(){
			this.setVisible(true);
			this.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			titleLabel = new JLabel();
			titleLabel.setText("Help");
			titleLabel.setFont(new Font("Calibri", 0, 80));
			titleLabel.setForeground(Color.WHITE);
			c.gridx = 0;
		    c.gridy = 0;
		    c.insets = new Insets(0,0,50,0);
		    this.add(titleLabel, c);
			
			backButton = new JButton();
			backButton.setText("Back");
			backButton.setPreferredSize(new Dimension(300,80));
			backButton.setFont(new Font("Calibri", 0, 30));
			backButton.setForeground(Color.BLACK);
			c.gridx = 0;
		    c.gridy = 3;
		    c.insets = new Insets(0,0,80,0);
		    this.add(backButton, c);
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					frame.remove(helpPanel);				
					frame.add(mainMenuPanel);
					frame.validate();	
					frame.repaint();
				}
			});
			
			frame.add(this);
	}
		@Override
		public void paintComponent(Graphics g){
			super.paintComponents(g);
			ImageIcon i = new ImageIcon((getClass().getResource("background.png")));
			i.paintIcon(this, g, 0, 0);
			 	
		}
	}
	
	class SettingsPanel extends JPanel{	
		public SettingsPanel(){
			this.setVisible(true);
			this.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			titleLabel = new JLabel();
			titleLabel.setText("Settings");
			titleLabel.setFont(new Font("Calibri", 0, 80));
			titleLabel.setForeground(Color.WHITE);
			c.gridx = 0;
		    c.gridy = 0;
		    c.insets = new Insets(0,0,50,0);
		    this.add(titleLabel, c);
		    
		    String[] sizeStrings = { "Large", "Medium", "Small" };
		    sizeList = new JComboBox(sizeStrings);
		    sizeList.setPreferredSize(new Dimension(300,80));
		    sizeList.setFont(new Font("Calibri", 0, 40));
		  	sizeList.setSelectedIndex(2);
		  	c.gridx = 0;
		    c.gridy = 1;
		    c.insets = new Insets(0,0,10,0);
		  	sizeList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					if(sizeList.getSelectedItem()=="Small"){
						frame.setSize(new Dimension(865, 520));
					}
					if(sizeList.getSelectedItem()=="Medium"){
						frame.setSize(new Dimension(1280, 720));
					}
					if(sizeList.getSelectedItem()=="Large"){
						frame.setSize(new Dimension(1920, 1080));
					}
				}
			});
		  	this.add(sizeList, c);
		  	
		  	soundButton = new JButton();
			soundButton.setText("Sound On");
			soundButton.setPreferredSize(new Dimension(300,80));
			soundButton.setFont(new Font("Calibri", 0, 30));
			soundButton.setForeground(Color.BLACK);
			c.gridx = 0;
		    c.gridy = 3;
		    c.insets = new Insets(0,0,10,0);
		    this.add(soundButton, c);
			soundButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					if(sound){
						sound = false;
						soundButton.setText("Sound Off");
						clip.stop();
					}
					else if(sound==false){
						sound = true;
						soundButton.setText("Sound On");
						clip.start();
					}
				}
			});
		  	
			backButton = new JButton();
			backButton.setText("Back");
			backButton.setPreferredSize(new Dimension(300,80));
			backButton.setFont(new Font("Calibri", 0, 30));
			backButton.setForeground(Color.BLACK);
			c.gridx = 0;
		    c.gridy = 4;
		    c.insets = new Insets(0,0,50,0);
		    this.add(backButton, c);
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					frame.remove(settingsPanel);				
					frame.add(mainMenuPanel);
					frame.validate();
					frame.repaint();
				}
			});
			
			frame.add(this);
			
			
	}
		@Override
		public void paintComponent(Graphics g){
			super.paintComponents(g);
			ImageIcon i = new ImageIcon((getClass().getResource("background.png")));
			i.paintIcon(this, g, 0, 0);
			 	
		}
	}
	
	public static void main(String[] args) {
		try {
	    	
	        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	            if ("Nimbus".equals(info.getName())) {
	                javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                break;
	            }
	        }
	    }
	    	
	    catch (ClassNotFoundException ex) {
	        java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (InstantiationException ex) {
	        java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (IllegalAccessException ex) {
	        java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	        java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    }
	    java.awt.EventQueue.invokeLater(new Runnable() {
	        public void run() {
	        	new GUI();
	        }
	    });
	}	
}
