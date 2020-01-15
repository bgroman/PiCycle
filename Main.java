import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
		/*try {
			BufferedImage screenshot = ImageIO.read(new File("screenshot.jpg"));
			window.add(new PiSlide(new PiTimer(0, 4, 16, 0), screenshot));
		}
		catch (IOException e) {System.out.println("screenshot.jpg failed to load.");}
		*/
		window.setVisible(true);
		
		//grab file - skipping
		//hand to parser - skipping
		//get back arraylist
		ArrayList<PiSlide> slides = new DeckBuilder().buildDeck();
		//while not interrupted
		while (!Thread.interrupted()) {
			//clean slides
			slides.removeIf(slide -> slide.targetReached());
			//in case we run out of slides that are current
			if (slides.size() == 0) {
				window.dispose();
				Thread.currentThread().interrupt();
			}
			//otherwise run the cycle
			else for (PiSlide s : slides) {
				//update
				s.update();
				//set display panel
				window.setContentPane(s);
				window.validate();
				//wait
				try {
					Thread.sleep(15000);
				}
				catch(InterruptedException e) {
					Thread.currentThread().interrupt();
					break;
				}
			}
		}
	}

}
