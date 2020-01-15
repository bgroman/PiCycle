import javax.swing.OverlayLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class PiSlide extends JPanel {

	private static final long serialVersionUID = -4571154780609704749L;
	private JLabel imageLabel;
	private JLabel timerDisplay;
	private PiTimer timer;
	
	/**
	 * Creates a new PiSlide for an event.
	 * @param targetTime The PiTimer representing the target date for the event.
	 * @param slideImage The image with the details for the event.
	 */
	public PiSlide(PiTimer targetTime, BufferedImage slideImage) {
		setLayout(new BorderLayout());
		imageLabel = new JLabel(new ImageIcon(slideImage));
		imageLabel.setBounds(0, 0, slideImage.getWidth(), slideImage.getHeight());
		this.add(imageLabel, BorderLayout.CENTER);
		timer = targetTime;
		timer.update();
		timerDisplay = new JLabel(timer.getRemaining());
		timerDisplay.setBackground(new Color(0, true));//.setOpaque(false);
		timerDisplay.setFont(Font.decode("Calibri 72"));
		timerDisplay.setAlignmentY(BOTTOM_ALIGNMENT);
		timerDisplay.setAlignmentX(CENTER_ALIGNMENT);
		this.add(timerDisplay, BorderLayout.SOUTH);
	}
	
	/**
	 * Updates the remaining time displayed.
	 */
	public void update() {
		timer.update();
		timerDisplay.setText(timer.getRemaining());
	}
	public boolean targetReached() {
		return timer.getRemaining().equals("ALREADY STARTED");
	}
}
