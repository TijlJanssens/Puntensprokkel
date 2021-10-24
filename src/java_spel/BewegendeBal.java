package java_spel;
public class BewegendeBal extends Bal {
	
	public double groottesnelh;
	public double snelhx;
	public double snelhy;
	
	public BewegendeBal (double snelheid, int straal) { // Constructor voor willekeurige startplaatsen
		super(straal);								
		this.groottesnelh=snelheid;											
	}	
	

	
	public BewegendeBal (double xspawn,double yspawn, double snelheid) { // Constructor voor bepaalde startplaatsen
		super(20, xspawn, yspawn);								
		this.groottesnelh = snelheid;
		}
}