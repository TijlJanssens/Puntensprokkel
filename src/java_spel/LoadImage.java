package java_spel;

import java.awt.Image;
//import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadImage {

	//gevonden via groep G18: 'Thiccballz' op https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
	
	private Image image;
	
	public LoadImage(String neededimage) {
			try {
				image=ImageIO.read(getClass().getResourceAsStream(neededimage));
			}catch(IOException e) {
				e.printStackTrace();
			}		
		
		
	}
	public Image getImage() {
		return image;
	}
}
