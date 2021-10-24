package java_spel;
import java.awt.Color;
import java.awt.Font;
// bij sommige ballen heel veel foutmeldingen, maar stopt programma niet, dus in principe geen probleem: Vraag aan David.
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel_examenronde extends JPanel implements ActionListener, KeyListener {
	public static final int breedte=MenuPanel.breedte, hoogte=MenuPanel.breedte;
	private final Set<Integer> pressedkeys = new TreeSet<Integer>();
	private List<Kogel_exam> kogellijst = new ArrayList<Kogel_exam>();
	private int spawnsnelheid;
	private int aantalballetjes=0;
	private int tijd;
	Speler_exam speler;
	Panel_jaarronde paneljaar; 
	private boolean intersected = false;
	boolean space = false; 
	private int framboospower = 10; 
	private boolean framboosNietGebruikt = true; 
    Image examenbackground=(new LoadImage("/examenbackground.jpg")).getImage();			
    Image flits=(new LoadImage("/ded.png")).getImage();									
    static Random rand = new Random(); 
    
    private boolean pauze = false; 
    boolean frambooseffect = false; 
    
	public Panel_examenronde(int spawnv, Speler_exam speler,Panel_jaarronde paneljaar) {  //constructor
		this.spawnsnelheid = spawnv;
		this.speler = speler;
		this.paneljaar = paneljaar;
		Timer timer = new Timer();
		timer.schedule(new TimerTaak1(this),1000,spawnsnelheid); // 1 seconde wachten voor 1ste kogel, daarna om de 5.16 seconden 
		timer.schedule(new TimerTaak2(this),0,5); // deze timer doet de repaint van de aanwezige kogels
		timer.schedule(new TimerTaak3(this),0,5); // deze timer trekt punten af 
		timer.schedule(new TimerTaak4(),1000,1000);
		timer.schedule(new TimerTaak5(),1000,1000); 
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        g.drawImage(examenbackground,0,0,null);
        g.setColor(Color.WHITE);
        for (int i=kogellijst.size()-1;i>=0;i--) {	// oplossing tegen Concurrent modification https://stackoverflow.com/questions/8104692/how-to-avoid-java-util-concurrentmodificationexception-when-iterating-through-an
            kogellijst.get(i).drawme(g);
        }  
        g.drawLine(breedte, 0, breedte, hoogte);                 
        g.drawLine(breedte, hoogte, 0, hoogte);  
        g.drawImage(Panel_jaarronde.player, (int) speler.xpositie, (int) speler.ypositie, this);
        g.setFont(new Font("Calibri", Font.PLAIN, 20));
        g.drawString("Levens: " + speler.levens, breedte+40, 100);
        g.drawString("Behaalde punten: " + speler.punten, breedte+40, 150);
        g.drawString("Kogels in spel: " + aantalballetjes, breedte +40, 250);
        g.drawString("Tijd bezig: "+tijd + " s", breedte+40, 200); 
        g.drawString("Duw op de spatiebalk om het spel te pauzeren! Druk F om framboos te gebruiken!", 50, hoogte+20); 
        if (intersected) {																	
    		g.drawImage(flits,(int)speler.xpositie,(int)speler.ypositie,this);				
        }  
        if (paneljaar.uplijst.get(3).bonusweg) { 
        	g.drawString("Framboospower: " + framboospower, MenuPanel.breedte+40, 300);
        }
   
        
	}
	public class TimerTaak1 extends TimerTask {
		private Panel_examenronde panel1;
		public TimerTaak1(Panel_examenronde paneel){
			panel1 = paneel;
			
		}
		@Override
		public void run() {
			if (!pauze) { 
			panel1.maakKogels();	
			aantalballetjes+=1;
			}
		}
	}
	public class TimerTaak2 extends TimerTask {
		private Panel_examenronde panel2;
		public TimerTaak2(Panel_examenronde paneel){
			panel2 = paneel;
		}
		@Override 
		public void run() {
			if (!pauze) { 
			for( Kogel_exam kogel : panel2.kogellijst) {
				kogel.updateme();
			}
			speler.updateme();
			repaint();
		}
		}
	}
	public class TimerTaak3 extends TimerTask {
		private Panel_examenronde panel3;
		public TimerTaak3(Panel_examenronde paneel){
			panel3 = paneel;
		}
		@Override
		public void run() {
			boolean previous = panel3.intersected;
			intersectupdate();
			
			if (panel3.intersected&&previous==false) { 
				speler.levens-=1;
			}
			if ((speler.levens<=0)&&(speler.punten <=7)){
				panel3.dikkeBuis();
			}
			else if ((speler.levens<=0)&&(speler.punten <10)){
				panel3.deliberatie();
			}
			else if ((speler.levens<=0)&&(speler.punten >=10)){
				panel3.geslaagd();
			}
			else if (speler.punten >=20) {
				panel3.geslaagd();
			}
		}
	}
	public class TimerTaak4 extends TimerTask {
		@Override
		public void run() {
			if (!pauze) { 
			tijd+=1;
			speler.punten+=0.25;
			}
		}
	}
	public class TimerTaak5 extends TimerTask { 
		@Override
		public void run() {
			
			if (paneljaar.uplijst.get(3).bonusweg) {
				if (frambooseffect) {
					framboosNietGebruikt = false;
					if (framboospower != 0)
						if (!pauze) { 
						framboospower -= 1;
					if (framboospower == 0) {
						paneljaar.uplijst.get(3).bonusweg = false;
					}
				}
				}
		}
		}
	}

	public void intersectupdate(){ 
		for (Kogel_exam kogel : kogellijst){
			if (kogel.checkcirclecollide(speler)){
				if (!paneljaar.uplijst.get(3).bonusweg|| framboosNietGebruikt) {
					this.intersected=true;
					break;  
				}
				else if (paneljaar.uplijst.get(3).bonusweg) {
					if (frambooseffect) { 
						kogel.hoek = rand.nextDouble()*Math.PI;
						kogel.snelhx= - Math.cos(kogel.hoek)*kogel.groottesnelh; 
						kogel.snelhy= - Math.sin(kogel.hoek)*kogel.groottesnelh;
					}
				}
			}
			else {
				this.intersected=false;
			}
		}
	}

	public void maakKogels() {
		kogellijst.add(new Kogel_exam(2));
	}
	public void dikkeBuis() {
		JOptionPane.showMessageDialog(this, "Je hebt een " + speler.punten + " behaald. Je bent niet geslaagd.", "Jaar opnieuw doen!", JOptionPane.YES_NO_OPTION);
		System.exit(0);
	}
	public void deliberatie() {
		JOptionPane.showMessageDialog(this, "Je hebt een " + speler.punten + " behaald. Je kan er nog doorgeraken in tweede zit.", "Je hebt nog een kans.", JOptionPane.YES_NO_OPTION);
		System.exit(0);
	}
	public void geslaagd() {
		JOptionPane.showMessageDialog(this, "Je hebt een " + speler.punten + " behaald. Proficiat!", "Je bent geslaagd", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	
	public void check(Set<Integer> set) {
		Integer[] pressedk = set.toArray(new Integer[] {});
		if (pressedk[0]==KeyEvent.VK_RIGHT) {
			speler.vxplus();
		}
		else if (pressedk[0]==KeyEvent.VK_LEFT) {
			speler.vxmin();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int code=e.getKeyCode();
		pressedkeys.add(code);
		check(pressedkeys);
		FKey(e);
		backSpace(e); 
		
	}
	
	public void FKey (KeyEvent e) { 
		if(e.getKeyCode()==KeyEvent.VK_F){
			 frambooseffect = true; 
			 
		}
		}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int code=e.getKeyCode();
		pressedkeys.remove(code);
		if (pressedkeys.size()>0) {
			check(pressedkeys);
		}
		else {
			speler.rest();
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

	
	@Override
	public void keyTyped(KeyEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent e) {
		speler.xpositie+=speler.snelhx;
		repaint();
	}
	
	
}
