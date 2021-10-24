package java_spel;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Music {			//gevonden via groep G18: 'Thiccballz'  zij hadden het gevonden via het project 'Ultimate Warrior' uit 2017 en gemodifieerd met info van de volgende sites:
	
	// https://stackoverflow.com/questions/10591852/how-to-cast-from-inputstream-to-audioinputstream
	// https://www.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html --> loop, werken met clip
	
	private String file;
	private boolean continu;
	Clip clip;

	public Music( String file, boolean continu ) {
		this.file=file;
		this.continu=continu;
	}
	public void start() {
		try {
			AudioInputStream muziek = AudioSystem.getAudioInputStream(getClass()
					.getResourceAsStream(file));
			clip = AudioSystem.getClip();
			clip.open(muziek);
			if (continu) {
				clip.loop(Clip.LOOP_CONTINUOUSLY); // repeat forever
			}
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		 // niet nodig door Exception
		/*}catch (IOException e) { 
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e){
            e.printStackTrace();
        } */
	}
	public void end() {
		clip.stop();
	}

	
}
