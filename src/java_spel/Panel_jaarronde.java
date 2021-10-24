package java_spel;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java_spel.Bal_jaarronde;


@SuppressWarnings("serial")
public class Panel_jaarronde extends JPanel implements ActionListener, KeyListener {        	// object moet luisteren naar acties, specifiek naar keys, uitbreiding jpanel
	
	Speler_jaar speler = new Speler_jaar();
	private Up framboos; 
	Speler_exam spelerexam= new Speler_exam();
	
	Music examenmuziek=new Music("/tte.wav",true);	
	
	public int aantalcoins = 10;
	public int aantalballen = 10;
	public int gevangenups=0;	
	public int uptijdsduur=5;
	//public int muurTijd = 7;
	public int collisioncounter=0;
	public int aantalUps = 3; 
	public int aantalFrambozen = 1; 
	//public int aantalMuren = 1;
	public int gevangenframboos = 0; 
	static double teller=0;
	static int timertik=4;
	final int diameter=40; 

	boolean collision = false;
	boolean verdergaan = true;
	boolean doen = true;
	boolean framboosgevangen = false; 
	static boolean makkelijk = false;
	private boolean pauze = false; 
	//private boolean wallStopCollision = false;
	
	Random rand =  new Random();
	
	Timer t= new Timer(timertik, this);                                                	 

	private final Set<Integer> pressedkeys = new TreeSet<Integer>();             	//set, wiskundige verzameling waar geen 2 mogen gelijk zijn --> hier is dit de bedoeling, ideale type 
	
	ArrayList<Bal_jaarronde> ballijst= new ArrayList<Bal_jaarronde>();
	ArrayList<Coin_jaarronde> coinlijst= new ArrayList<Coin_jaarronde>();
	ArrayList<Integer> checklijst= new ArrayList<Integer>();
	ArrayList<Up> uplijst = new ArrayList<Up>(); 
	ArrayList<Obstakel> muren = new ArrayList<Obstakel>();
	
	Image background=(new LoadImage("/jaarrondebackground.png")).getImage();	//vaste afb	
	Image backbackground=(new LoadImage("/black.jpg")).getImage();						
	Image punt=(new LoadImage("/muntje.png")).getImage();								
	Image vijand=(new LoadImage("/enemy3.png")).getImage();								
	Image oneup =(new LoadImage("/1up.png")).getImage();								
    Image flits=(new LoadImage("/ded.png")).getImage();										
    Image framboosImage = (new LoadImage("/image_framboos.png")).getImage(); 


	static Image player;																	//moet nog veranderen, hangt af vd gekozen optie in het menu
	
	
	public Panel_jaarronde() {
		addKeyListener(this);
		setFocusable(true);                                        	
		setFocusTraversalKeysEnabled(false);         				
		for (int i=0;i<=aantalballen;i++) {
			ballijst.add(new Bal_jaarronde());
		}
		for (int i=1;i<=aantalcoins;i++) {
			coinlijst.add(new Coin_jaarronde(i));
			checklijst.add(i);
		}
		for (int i=1;i<=aantalUps;i++) {
			uplijst.add(new Up()); 
		}
//		for (int i = 0;i<=aantalMuren;i++) {
//			muren.add(new Obstakel());
//		}
		framboos = new Up(); 
		uplijst.add(framboos); 
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backbackground, 0, 0, null);
		g.drawImage(background,0,0,null);
		g.drawLine(0, 0,  MenuPanel.breedte+diameter, 0);					//randen paneel + straal cirkel=kader
		g.drawLine(0, 0, 0, MenuPanel.hoogte+diameter);
		g.drawLine( MenuPanel.breedte+diameter, 0,  MenuPanel.breedte+diameter,MenuPanel.hoogte+diameter);
		g.drawLine(0,MenuPanel.hoogte+diameter, MenuPanel.breedte+diameter,MenuPanel.hoogte+diameter);
		for (Bal_jaarronde bal:ballijst) {
			g.drawImage(vijand, (int) bal.xpositie, (int) bal.ypositie, this); 
		}
		g.setColor(Color.red);
		for (Coin_jaarronde coin:coinlijst) {
			g.drawImage(punt, (int)coin.xpositie, (int)coin.ypositie,this);
			if (makkelijk) {
                  g.fillOval((int)coinlijst.get(0).xpositie-1, (int)coinlijst.get(0).ypositie-1, 21, 21);                  
             }		
		}
		g.setFont(new Font("Calibri", Font.PLAIN, 20));
		g.setColor(Color.WHITE);
		g.drawString("Score: " + (10-coinlijst.size()) + " aandachtspunten.", 850, 100);
		g.drawString(("Levens: " + (3-collisioncounter)), MenuPanel.breedte+50, 150);
		g.drawString("Ups: " + gevangenups, MenuPanel.breedte+50, 200);
		g.drawString("Framboos: " + gevangenframboos, MenuPanel.breedte+50, 250); 
		g.drawString("Spatiebalk = spel pauzeren", MenuPanel.breedte+50, 300); 
		g.drawImage(player, (int) speler.xpositie, (int) speler.ypositie, this);
		if (collision) {														
			g.drawImage(flits,(int)speler.xpositie,(int)speler.ypositie,this);	
		}
		for (int i=0;i<aantalUps-1;i++) { //min 1 want laatste in de lijst is de framboos en die krijgt een aparte if
			if(uplijst.get(i).raakbaar&&!uplijst.get(i).bonusweg){
				g.drawImage(oneup, (int)uplijst.get(i).xpositie, (int)uplijst.get(i).ypositie, this);
			}
			if(uplijst.get(3).raakbaar&&!uplijst.get(3).bonusweg){
				g.drawImage(framboosImage,(int)uplijst.get(3).xpositie, (int)uplijst.get(3).ypositie , this);			
			}
		}
		if (pauze) { 
			g.drawString("Spel op pauze", MenuPanel.breedte+50, 350);
        }
//		for (int i = 0; i<aantalMuren;i++) { 
//		
//		if(muren.get(i).raakbaar){
//				g.drawImage(wall, (int)muren.get(i).xpositie1, (int)muren.get(i).ypositie1,  this);
//		} 
//		}
		repaint();
	}
	
	public void actionPerformed(ActionEvent e) {			
		boolean previous=collision;
		if (!pauze) {
		//	for (Obstakel muur:muren) {
		collide();
		repaint();
		updatestep();
		beweeg();	
		speler.updateme();
		snelheidbepalen();
		for (Up up:uplijst) { 
			if (!up.bonusweg){ 
				bonuscheck(up);
			}
		}
		if (checklijst.size()!=0) {
			coincheck(checklijst.get(0));}
		if (collision && previous==false) {					//1 keer botsing detecteren, enkel bij binnenkomen andere cirkel		
			collisioncounter+=1;
		}
		if (collisioncounter==3 && verdergaan) {							
			jaarrondegedaan();
		}
		if (coinlijst.size()==0 && verdergaan) {
			jaarrondegedaan();
		}
		teller+=timertik;
		for (Up up:uplijst) { //**
			if (((teller/1000)>up.beginspawn)&&((teller/1000)<(up.beginspawn + uptijdsduur))&&!up.bonusweg) { 
				up.raakbaar=true;
			}
			else{
				up.raakbaar = false;
			}
		}
//		for (Obstakel muur1: muren) { 
//			if (((teller/1000)>muur1.willekgetal)&&((teller/1000)<(muur1.willekgetal + muurTijd))) {
//				muur1.raakbaar = true;
//			}
//			else {
//				muur1.raakbaar = false;
//			}
//		}
//		}
		}
	}
	
	
	
	public Boolean collide() {		
		for (Bal_jaarronde bal : ballijst) {
			if (bal.checkcirclecollide(speler)) {
				collision=true;
				break;
			}
			else {
				collision=false;
			}
		}	
		return collision;
	}
	
	public void bonuscheck(Up up) { 
		if (speler.checkcirclecollide(up)&&(up.raakbaar)) {
			spelerexam.levens+=1;
			gevangenups+=1;
			up.raakbaar=false;
			up.bonusweg=true;
			if (up == framboos){ 
				gevangenframboos += 1;
				framboosgevangen = true;
				up.raakbaar=false;
				up.bonusweg=true;
			}
		}
	}

	
	@Override
	public void keyPressed(KeyEvent e) {		
		int code=e.getKeyCode();
		pressedkeys.add(code);
		backSpace(e); 
	}

	public void keyReleased(KeyEvent e) {			 
		int code=e.getKeyCode();
		pressedkeys.remove(code);							
		if (code==KeyEvent.VK_LEFT||code==KeyEvent.VK_RIGHT) {
				speler.vxrest();
			}
		else if (code==KeyEvent.VK_UP||code==KeyEvent.VK_DOWN) {
				speler.vyrest();
			}
		}
		
	

	public void keyTyped(KeyEvent e) {}				
	
	public void beweeg() {
		Integer[] pressedk = pressedkeys.toArray(new Integer[] {});	//casten naar array, handig om mee te werken, indexen, set is mert
		if (pressedkeys.size()>0) {
			for (int i=pressedk.length-1;i>=0;i--){
				if (pressedk[i]==KeyEvent.VK_UP) {				
					speler.vymin();
				}
				else if (pressedk[i]==KeyEvent.VK_RIGHT) {				
					speler.vxplus();
				}
				else if (pressedk[i]==KeyEvent.VK_LEFT) {				
					speler.vxmin();
				}
				else if (pressedk[i]==KeyEvent.VK_DOWN) {				
					speler.vyplus();
				}
			}
		}
		
		else {
			speler.rest();
		}
	}
	
	public void snelheidbepalen() {								
		if (pressedkeys.size()>1) {
			speler.snelh=speler.groottesnelh/(Math.pow(2, 0.5));
		}
		else {
			speler.snelh=speler.groottesnelh;
		}
	}
	

	public void jaarrondegedaan() {
		if (coinlijst.size()==9) {
		JOptionPane.showMessageDialog(this, "Je werd 3 keer geraakt maar je behaalde 1 aandachtspunt.", "OP NAAR DE VOLGENDE RONDE", JOptionPane.INFORMATION_MESSAGE);
		overgang();
		}
		if (coinlijst.size()==0) {
		JOptionPane.showMessageDialog(this, "Gefeliciteerd, je hebt 10 aandachtspunten behaald! Je werd " + collisioncounter + " keer geraakt.", "GOED ZO! OP NAAR DE VOLGENDE RONDE", JOptionPane.INFORMATION_MESSAGE);
		overgang();
		}
		if (coinlijst.size()==10) {
		JOptionPane.showMessageDialog(this, "Je werd 3 keer geraakt en je behaalde 0 punten. De examenronde wordt extreem moeilijk, ben je zeker dat burgi een goede keuze was?", "Je bent niet goed bezig", JOptionPane.INFORMATION_MESSAGE);
		overgang();
		}
		if ((coinlijst.size()>0) && (coinlijst.size()<9)) {
		JOptionPane.showMessageDialog(this, "Je werd 3 keer geraakt maar je behaalde " + (10-coinlijst.size())+ " aandachtspunten.", "OP NAAR DE VOLGENDE RONDE", JOptionPane.INFORMATION_MESSAGE);
		overgang();
		}
	}

	public void overgang() {
		MenuPanel.Menuframe.remove(MenuPanel.p);
		MenuPanel.Menuframe.dispose();
		Panel_examenronde examenronde = new Panel_examenronde((int) 4000/(coinlijst.size()+1), spelerexam, this); //**
		MenuPanel.Menuframe.add(examenronde);
		MenuPanel.Menuframe.setVisible(true);
		MenuPanel.Menuframe.setTitle("Puntensprokkel: Examens");
		verdergaan=false;
		MenuPanel.jaarmuziek.end();									
		examenmuziek.start();										
	}
	
	
	public void updatestep() {
		if (!pauze) { 
			for (Bal_jaarronde balleke : ballijst) {
				balleke.updateme();
			}
		repaint();											
	}
}
	
	public void backSpace (KeyEvent e) { 
		if(e.getKeyCode() == KeyEvent.VK_SPACE) { 
			if (!pauze) {
			pauze = true;
			}
			else if (pauze) {
			pauze = false;
		}
	}
}
	
	public void coincheck(int waarde) {
		int volger=0;
		for (Coin_jaarronde coin : coinlijst) {
			if (coin.checkcirclecollide(speler)&&coin.waarde == waarde) {
				coinlijst.remove(volger);
				checklijst.remove(new Integer (waarde));
				break;
			}
			else {
				volger+=1;
			}
		}
	}
}
