import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		//build frame
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setTitle("Computer Science Association Events");
		window.setUndecorated(true);
		//temporary display
		try {
			BufferedImage screenshot = ImageIO.read(new File("screenshot.jpg"));
			window.add(new PiSlide(new PiTimer(0, 4, 16, 0), screenshot));
		}
		catch (IOException e) {System.out.println("screenshot.jpg failed to load.");}
		window.setVisible(true);
		
		//grab file
		//hand to parser
		//get back arraylist
		//while not interrupted
		 //foreach
		  //update
		  //set display panel
		  //wait

	}

}
