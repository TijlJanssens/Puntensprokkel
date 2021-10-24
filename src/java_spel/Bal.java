package java_spel;

import java.awt.Graphics;
import java.util.Random;

//moederklasse voor kogels, spelers, coins,...

public class Bal {
	public double xpositie, ypositie;
	int straal; 
	static Random rand = new Random();
	
	public Bal (int straal) { //willekeurige spawnplaatsen
		this.straal = straal;
		this.xpositie=rand.nextDouble()*(MenuPanel.breedte-2*straal-100)+straal+100;
		this.ypositie=rand.nextDouble()*(MenuPanel.hoogte-2*straal-100)+straal+100;
	}
	
	public Bal (int straal, double xpositie, double ypositie) { // bepaalde spawnplaatsen
		this.straal = straal;
		this.xpositie = xpositie;
		this.ypositie = ypositie;
	
	}

	public boolean checkcirclecollide(Bal bal){ //voor collision detection tussen 2 cirkels, van internet: https://stackoverflow.com/questions/12083775/java-returning-a-boolean-error 
	     return Math.abs(((this.xpositie+this.straal) - (bal.xpositie+bal.straal)) * ((this.xpositie+this.straal) - (bal.xpositie+bal.straal)) + ((this.ypositie+this.straal) - (bal.ypositie+bal.straal)) * ((this.ypositie+this.straal) - (bal.ypositie+bal.straal))) < (this.straal + bal.straal) * (this.straal + bal.straal);
	}
	
	public void spawnMuur(Obstakel muur) {
//		if ((this.xpositie>muur.xpositie1) && (this.xpositie<muur.xpositie1+breedteMuur/2) && (this.ypositie>muur.ypositie1) && (this.ypositie<muur.ypostitie2-muurHoogte/2)) {
//			this.xpositie = muur.xpositie1-2*this.straal;
//			this.ypositie = muur.ypositie		
//		}
//		if ((this.xpositie>muur.xpositie1+breedteMuur/2) && (this.xpositie<muur.xpositie2) && (this.ypositie>muur.ypositie1) && (this.ypositie<muur.ypostitie2-muurHoogte/2)) { 
//			this.xpositie = muur.xpositie2;
//			this.ypositie = muur.ypositie1;
//		}
//		if ((this.xpositie>muur.xpositie1) && (this.xpositie<muur.xpositie1+breedteMuur/2) && (this.ypositie>muur.ypositie1+hoogteMuur/2) && (this.ypositie<muur.ypostitie2)) {
//			this.xpositie = muur.xpositie1-2*this.straal;
//			this.ypositie =	muur.ypositie1+hoogteMuur/2;
//		}
//		if ((this.xpositie>muur.xpositie1+breedteMuur/2) && (this.xpositie<muur.xpositie2) && (this.ypositie>muur.ypositie1+hoogteMuur/2) && (this.ypositie<muur.ypostitie2) {
//			this.xpositie = muur.xpositie2;
//		this.ypositie = muur.ypositie1+hoogteMuur/2;
//		}
		
	}
	public void drawme(Graphics g) { // geen updateme() nodig, tenzij we ze willen laten blinken ofzo
		g.drawOval((int)xpositie,(int)ypositie, 2*straal, 2*straal);
	}
}

