package java_spel;

import java.awt.BorderLayout;

import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

@SuppressWarnings({ "serial" })
public class MenuPanel extends JPanel implements ActionListener, FocusListener  {
	private static JButton start;
	@SuppressWarnings("rawtypes")
	private static JComboBox spelers;
	@SuppressWarnings("rawtypes")
	private static JComboBox moeilijkheid;
	private static JButton instructies;
	private static JButton terug;
	private static JButton spelVerlaten;
	//private static JTextField playerNameField; 
	
	String[] spelerlijst = new String[] {"Kies speler","Rahier","Caenepeel","Isar","Berghmans"};
	String[] moeilijkheidslijst= new String [] {"Kies moeilijkheid","Archi", "Burgi"};
	
	public static final int breedte=800, hoogte=700;
	static JFrame Menuframe=new JFrame();
	static MenuPanel menupaneel = new MenuPanel();
	public static Panel_jaarronde p = new Panel_jaarronde();    
	Music menumuziek=new Music("/nyancat.wav",true);	
	static Music jaarmuziek=new Music("/tom.wav",true);		
	JLabel text = new JLabel(
			/*"<html>Hoe spelen?\r\n" +
            "<br/>\r\n" +
            "<br/>\r\n" +
            "<br/>\r\n" +
            "<br/>Je begint steeds met de jaarronde. Hier is het de bedoeling dat je zoveel mogelijk <br/>aandachtspunten verzamelt. Hoe meer aandachtspunten je behaalt tijdens de jaarronde, hoe <br/>makkelijker je examenronde zal zijn! \r\n" +
            "<br/>\r\n" +
            "<br/>\r\n" +
            "<br/>\r\n" +
            "<br/>DE JAARRONDE:\r\n" +
            "<br/>\r\n" +
            "<br/>De player start linksboven op het scherm. Je kan deze controleren met de pijltjestoetsen: alle <br/>bewegingen (ook schuin) zijn mogelijk. Het is de bedoeling dat je zoveel mogelijk <br/>aandachtspunten behaalt, deze verzamel je door met je player op de gele, stilstaande ï¿½coinsï¿½ te <br/>gaan staan. Deze moet je verzamelen in volgorde, maar de moeilijkheid is dat je nooit weet <br/>welke volgorde: dit zal niet getoond worden op het scherm. Ga zo snel mogelijk alle coins af, <br/>want de vijandige ballen versnellen met iedere bots! LET OP: je hebt maar 3 levens, zorg <br/>dat je zeker niet geraakt wordt door de bewegende emojiï¿½s!" +
            "<br/>\r\n" +
            "<br/>\r\n" +
            "<br/>\r\n" +
            "<br/>DE EXAMENRONDE:\r\n" +
            "<br/>\r\n" +
            "<br/>De player start in het midden bovenaan het scherm. Deze keer is je beweging gelimiteerd tot <br/>links en rechts. Je kan ook door de muren teleporten. Het is in deze ronde de bedoeling om zo <br/>lang mogelijk in leven te blijven, let op dat je niet geraakt wordt door de kogels! Per <br/>seconde dat je in leven bent, krijg je 0.25 punten op 20 bij. LET OP: je hebt maar 5 levens! Vanaf 10 <br/>op 20 ben je geslaagd, minder dan 8 betekent jaar opnieuw!\r\n" +
            "<br/>\r\n" +
            "<br/>\r\n" +
            "<br/>\r\n" +
            "<br/>Succes en veel speelplezier! \r\n" +
            ""
            + "<html>"*/
			"<html>Hoe spelen?\r\n" +
			"<br/>\r\n" +
			"<br/>\r\n" +
			"<br/>\r\n" +
			"<br/>Kies in het Menu eerst je speler en je moeilijkheid. Je begint steeds met de <br/> jaarronde: hier is het de bedoeling dat je zoveel mogelijk ‘aandachtspunten’ <br/> verzamelt. Hoe meer aandachtspunten je behaalt tijdens de jaarronde, hoe <br/> makkelijker de examenronde zal zijn! \r\n" +
			"<br/>\r\n" +
			"<br/>\r\n" +
			"<br/>\r\n" +
			"<br/>DE JAARRONDE:\r\n" +
			"<br/>\r\n" +
			"<br/>De player start linksboven op het scherm. Je kan deze controleren met de <br/>pijltjestoetsen: alle bewegingen (ook schuin) zijn mogelijk. Het is de bedoeling <br/>dat je zoveel mogelijk ‘aandachtspunten’ behaalt, deze verzamel je door met je <br/>player op de gele, stilstaande 'coins' te gaan staan. Als je als <br/>moeilijkheid ‘burgi’ hebt gekozen, weet je niet in welke volgorde je de punten <br/>moet verzamelen. Als je als moeilijkheid ‘archi’ hebt gekozen, licht het punt <br/>dat je moet nemen steeds rood op. Ga zo snel mogelijk alle coins af, want de <br/>vijandige ballen versnellen met iedere bots! Je kan ook Ups verzamelen en <br/>frambozen, die je extra levens en immuniteit zullen opleveren in de <br/>examenronde! LET OP: je hebt maar 3 levens, zorg dat je zeker niet geraakt <br/>wordt door de bewegende emoji's!" +
			"<br/>\r\n" +
			"<br/>\r\n" +
			"<br/>\r\n" +
			"<br/>DE EXAMENRONDE:\r\n" +
			"<br/>\r\n" +
			"<br/>De player start in het midden bovenaan het scherm. Deze keer is je beweging <br/> gelimiteerd tot links en rechts. Je kan ook door de muren teleporten. Het is <br/> in deze ronde de bedoeling om zo lang mogelijk in leven te blijven, let op <br/> dat je niet geraakt wordt door de kogels! Per seconde dat je in leven bent, <br/> krijg je 0.25 punten op 20 bij. Druk F om de framboospower te activeren (lees: <br/> immuniteit voor 10 seconden). LET OP: je hebt maar 5 levens, extra levens zijn <br/> mogelijk door Ups te verzamelen in de jaarronde! Vanaf 10 op 20 ben je <br/> geslaagd, minder dan 8 betekent jaar opnieuw!\r\n" +
			            "<br/>\r\n" +
			            "<br/>\r\n" +
			            "<br/>\r\n" +
			            "<br/>Succes en veel speelplezier! \r\n" +
			            ""
			            + "<html>"

            ,JLabel.CENTER);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MenuPanel() {
		start = new JButton("Start");
		spelers = new JComboBox (spelerlijst);
		moeilijkheid= new JComboBox(moeilijkheidslijst);
		instructies = new JButton("Instructies");
		spelVerlaten = new JButton("Spel verlaten");
		this.add(start);
		this.add(spelers);
		this.add(moeilijkheid);
		this.add(instructies);
		this.add(spelVerlaten);
		//this.add(playerNameField); 
		spelVerlaten.addActionListener (new QuitListener());
		//playerNameField = new JTextField(5); 
		start.addActionListener(new StartListener());
		instructies.addActionListener(new InstructieListener());
		setLayout(new BorderLayout());                                                                                                                                                                                                                                                                                                                             //https://java-demos.blogspot.be/2012/09/setting-background-image-in-jframe.html
		JLabel background=new JLabel(new ImageIcon((new LoadImage("/menubackground.png")).getImage()));
		this.add(background);
		background.setLayout(new FlowLayout());
		//this.add(new JLabel("Enter Player Name")); //afw
		menumuziek.start();
	}

	public class QuitListener implements ActionListener {
		@Override
		public void actionPerformed (ActionEvent e) {
			System.exit(0);
		}
	}
	

	public void startjaarronde() {  
		p.t.start();
		Menuframe.remove(menupaneel);
		Menuframe.dispose();
		Menuframe.add(p);
		Menuframe.setVisible(true);
		Menuframe.setTitle("Puntensprokkel: Jaarronde");   
		menumuziek.end();						
		jaarmuziek.start();						
	}

	public void error() {
		JOptionPane.showMessageDialog(this, "Je moet een speler en een moeilijkheid kiezen om met het spel te kunnen beginnen.", "Kies een speler/moeilijkheid", JOptionPane.ERROR_MESSAGE);
	}

	public class StartListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {                                                                                                          
			String speler= (String) spelers.getSelectedItem();
			String moeilijkheidsgraad= (String) moeilijkheid.getSelectedItem();
			if (moeilijkheidsgraad.equals("Archi")) {
				Panel_jaarronde.makkelijk=true;
			}
			if (speler.equals("Rahier")&&(moeilijkheidsgraad.equals("Kies een moeilijkheid")==false)){
				Panel_jaarronde.player=(new LoadImage("/huub.png")).getImage();
				startjaarronde();
			}
			else if (speler.equals("Caenepeel")&&(moeilijkheidsgraad.equals("Kies een moeilijkheid")==false)){
				Panel_jaarronde.player=(new LoadImage("/stef.png")).getImage();
				startjaarronde();
			}
			else if (speler.equals("Isar")&&(moeilijkheidsgraad.equals("Kies een moeilijkheid")==false)) {
				Panel_jaarronde.player=(new LoadImage("/goy.png")).getImage();
				startjaarronde();
			}
			else if (speler.equals("Berghmans")&&(moeilijkheidsgraad.equals("Kies een moeilijkheid")==false)) {
				Panel_jaarronde.player=(new LoadImage("/francis.png")).getImage();
				startjaarronde();
			}
			else {
				error();
			}
		}
	}

	public class InstructieListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Menuframe.remove(menupaneel);
			Menuframe.dispose();
			text.setAlignmentX(15);
			text.setAlignmentY(5);
			text.setOpaque(true);
			text.setForeground(Color.white);
			text.setBackground(Color.BLACK);
			terug=new JButton("Terug");
			Menuframe.add(terug);
			terug.setBounds(40,40,80,40);
			Menuframe.setTitle("Puntensprokkel: Instructies");
			terug.addActionListener(new GoBack());
			Menuframe.add(text);
			Menuframe.setVisible(true);
		}
	}

	public class GoBack implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Menuframe.remove(text);
			Menuframe.remove(terug);
			Menuframe.dispose();
			Menuframe.add(menupaneel);
			Menuframe.setVisible(true);
		}
	}   

	public static void main(String[] args)	{
		Menuframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Menuframe.setLocation(100, 100); //standaard in de hoek van het scherm
		Menuframe.add(menupaneel);
		Menuframe.setSize(breedte+300, hoogte+75);
		Menuframe.setVisible(true);
		spelVerlaten.setBounds(750,250,150,60);
		instructies.setBounds(150,250,150,60);
		start.setBounds(425,600,200,60);
		spelers.setBounds(550,250,150,60);
		moeilijkheid.setBounds(350,250,150,60);
		//playerNameField.setBounds(425,450,200,60); 
		Menuframe.setResizable(false);
		Menuframe.setTitle("Puntensprokkel: Menu");
	} 

	@Override
	public void actionPerformed(ActionEvent e) {}


	@Override
	public void focusGained(FocusEvent arg0) {	//probeersel voor highscore
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

}

