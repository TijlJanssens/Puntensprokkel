package java_spel;

public class Speler_exam extends Speler {

	public double punten=0;
	public int levens;

	public Speler_exam() {
		super(MenuPanel.breedte/2, 100, 5);
		levens = 5;

	}
	@Override
	public void randcheck() {
		if (xpositie>MenuPanel.breedte-straal) {
			xpositie=-straal;
		}
		else if(xpositie<-straal) {
			xpositie=MenuPanel.breedte-straal;
		}
	}
}

