import javax.swing.OverlayLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PiSlide extends JPanel {

	private static final long serialVersionUID = -4571154780609704749L;
	private JLabel timerDisplay;
	private PiTimer timer;
	
	/**
	 * Creates a new PiSlide for an event.
	 * @param targetTime The PiTimer representing the target date for the event.
	 * @param slideImage The image with the details for the event.
	 */
	public PiSlide(PiTimer targetTime, BufferedImage slideImage) {
		setLayout(new OverlayLayout(this));
		this.add(new JLabel(new ImageIcon(slideImage)));
		timer = targetTime;
		timerDisplay = new JLabel(timer.getRemaining());
		timerDisplay.setAlignmentX(BOTTOM_ALIGNMENT);
		timerDisplay.setAlignmentY(CENTER_ALIGNMENT);
		this.add(timerDisplay);
	}
	
	/**
	 * Updates the remaining time displayed.
	 */
	public void update() {
		timer.update();
		timerDisplay.setText(timer.getRemaining());
	}
}
