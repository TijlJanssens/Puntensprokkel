package java_spel;

public class Speler extends BewegendeBal{
	public double snelh;
	public Speler(double spawnx, double spawny, double speed) { //constructor
		super (spawnx, spawny, speed);
		this.snelh=groottesnelh;
	}
	public void updateme() {

		this.xpositie+=this.snelhx;
		this.ypositie+=this.snelhy;
		randcheck();}
	public void randcheck() {
		// ingevuld voor de subklasses apart
	}
	public void vxplus() {
		this.snelhx=this.snelh;
	}
	public void vxmin() {
		this.snelhx=-this.snelh;
	}
	public void vyrest() {
		this.snelhy=0;
	}
	public void vxrest() {
		this.snelhx=0;
	}
	public void rest() {
		this.vxrest();
		this.vyrest();
	}
	public void vyplus() {
		this.snelhy=this.snelh;
	}
	public void vymin() {
		this.snelhy=-this.snelh;
	} 
}
