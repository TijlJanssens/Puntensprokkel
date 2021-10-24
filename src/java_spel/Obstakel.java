package java_spel;

import java.util.Random;



public class Obstakel { //probeersel voor muren in spel
	Random rand = new Random();
	boolean raakbaar=false;
	double willekgetal = rand.nextDouble()*2;
	int breedteMuur = 100;
	int hoogteMuur = 100;
	double xpositie1, xpositie2, ypositie1, ypositie2;
	
	
	public Obstakel() {
		this.xpositie1= rand.nextDouble()*(MenuPanel.breedte-breedteMuur-200)+breedteMuur+100; //rand.nextDouble()*(MenuPanel.breedte-breedteMuur-100)+breedteMuur+100;
		this.ypositie1= rand.nextDouble()*(MenuPanel.hoogte-hoogteMuur-200)+hoogteMuur+100; //rand.nextDouble()*(MenuPanel.hoogte-hoogteMuur-100)+hoogteMuur+100
		this.xpositie2 = this.xpositie1 + breedteMuur;
		this.ypositie2 = this.ypositie1 + hoogteMuur;
	}
}
