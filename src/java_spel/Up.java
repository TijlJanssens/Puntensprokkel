package java_spel;

import java.util.Random;

public class Up extends Bal {
	Random rand = new Random();
	boolean raakbaar=false;
	boolean bonusweg=false;
	double beginspawn = rand.nextDouble()*15; // in eerste 15 seconden spawnen de ups
	public Up() {
		super(15);
	}
}

