package java_spel;

public class Projectiel extends BewegendeBal{
	public double hoek;			//***
	
	public Projectiel(double snelheid, int straal) {
		super(snelheid, straal);
		this.hoek = rand.nextDouble()*2*Math.PI;	
		snelhx=Math.cos(hoek)*groottesnelh; 
		snelhy=Math.sin(hoek)*groottesnelh;
	}
}
