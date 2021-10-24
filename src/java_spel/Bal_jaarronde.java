package java_spel;

public class Bal_jaarronde extends Projectiel {
	
	private final double versnelling = 0.06;
	
	
	public Bal_jaarronde() {
		super(rand.nextDouble()*0.2+0.4,20); // willekeurige beginsnelheid
		
	}
	public void updateme() {			//checkt botsingen met randen, maakt beweging mogelijk, versnelt bij elke botsing --> inspiratie van balpaneel uit oefeningen klas

		xpositie+=snelhx;
		ypositie+=snelhy;			
		
		if (xpositie<0) {
			xpositie=0;snelhx=-snelhx+versnelling;
		} 
		else if (xpositie>MenuPanel.breedte) {
			xpositie=MenuPanel.breedte; snelhx=-snelhx-versnelling;	
		}
		if (ypositie<0) {
			ypositie=0;snelhy=-snelhy+versnelling;
		}
		else if (ypositie>MenuPanel.hoogte) {
			ypositie=MenuPanel.hoogte; snelhy=-snelhy-versnelling;
		}
//		if (((this.xpositie+2*this.straal) > muur.xpositie1) && this.ypositie>muur.ypositie1 && this.ypositie<muur.ypositie2) { //collide langs links
//			this.xpositie = muur.xpositie1-2*this.straal;
//		}
//		if ((this.xpositie < muur.xpositie2) && this.ypositie>muur.ypositie1 && this.ypositie<muur.ypositie2) { //collide langs rechts
//			this.xpositie = muur.xpositie2;
//		}
//		if ((this.ypositie+2*this.straal > muur.ypositie1) && this.xpositie>muur.xpositie1 && this.xpositie<muur.xpositie2) { // collide langs boven
//			this.ypositie = muur.ypositie1-2*this.straal;
//		}	
//		if ((this.ypositie < muur.ypositie2) && this.xpositie>muur.xpositie1 && this.xpositie<muur.xpositie2 ) { //collide langs vanonder
//			this.ypositie = muur.ypositie2;
//		}
	}		
}

