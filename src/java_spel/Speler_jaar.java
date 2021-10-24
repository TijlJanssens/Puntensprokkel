package java_spel;

public class Speler_jaar extends Speler {

	public Speler_jaar() {
		super(0,0,1.8);
	}
	
	public void updateme() {
		this.xpositie+=this.snelhx;
		this.ypositie+=this.snelhy;
		if (this.ypositie>MenuPanel.hoogte) {
			this.ypositie=MenuPanel.hoogte;
		}
		if (this.ypositie<0) {
			this.ypositie=0;
		}
		if (this.xpositie>MenuPanel.breedte) {
			this.xpositie=MenuPanel.breedte;
		}
		else if(this.xpositie<0) {
			this.xpositie=0;
		}
	}
//	public void wallUpdate(Obstakel muur) {
//	this.xpositie+=this.snelhx;
//	this.ypositie+=this.snelhy;
//		 if ((this.xpositie+2*this.straal>muur.xpositie1) && (this.ypositie+2*this.straal>muur.ypositie1) && (this.ypositie<muur.ypositie2)) { //collide langs links
//			this.xpositie = muur.xpositie1-2*this.straal;
//			this.snelhx = 0;
//			System.out.println("hello");
//			
//		}
//		 if ((this.xpositie < muur.xpositie2) && (this.ypositie+2*this.straal>muur.ypositie1) && (this.ypositie<muur.ypositie2)) { //collide langs rechts
//			this.xpositie = muur.xpositie2;
//			this.snelhx = 0;
//			
//		}
//		 if ((this.ypositie+2*this.straal > muur.ypositie1) && (this.xpositie+2*this.straal>muur.xpositie1) && (this.xpositie<muur.xpositie2)) { // collide langs boven
//			this.ypositie = muur.ypositie1-2*this.straal;
//			this.snelhy = 0;
//			
//		}	
//		 if ((this.ypositie < muur.ypositie2) && (this.xpositie+2*this.straal>muur.xpositie1) && (this.xpositie<muur.xpositie2) ) { //collide langs vanonder
//			this.ypositie = muur.ypositie2;
//			this.snelhy = 0;
//			
//		} //prototype obstakel
//	}
}
