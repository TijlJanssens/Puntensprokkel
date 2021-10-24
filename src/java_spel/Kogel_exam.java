package java_spel;

public class Kogel_exam extends Projectiel {
	public static int straallengte = 5;
	
	public Kogel_exam(int snelheid) { 
		super(2,straallengte);
		this.xpositie = Panel_examenronde.breedte/2; // begin positie is midden onderaan het scherm
		this.ypositie = Panel_examenronde.hoogte;
	}

	void updateme(){			//inspiratie van balpaneel (oefening in de klas)
		if (xpositie<0){
			xpositie=0;snelhx=-snelhx;
		}
		else if (xpositie>Panel_examenronde.breedte-2*straallengte){
			xpositie=Panel_examenronde.breedte-2*straallengte; snelhx=-snelhx;
		}
		if(ypositie<straallengte) {
			ypositie=straallengte;snelhy=-snelhy;
		}
		else if(ypositie>Panel_examenronde.hoogte-2*straallengte){
			ypositie= Panel_examenronde.hoogte-2*straallengte;snelhy=-snelhy;
		}
		xpositie=xpositie+snelhx;
		ypositie=ypositie+snelhy;
	}	
}

