import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.*;

public class DeckBuilder {
	private ArrayList<PiSlide> slides = null;
	
	public ArrayList<PiSlide> buildDeck() {
		if (slides == null) {
			slides = new ArrayList<PiSlide>();
			scanFiles();
		}
		return slides;
	}

	private void scanFiles() {
		slides.addAll(Stream.of(new File("./").listFiles())
				.filter(file -> !file.isDirectory())
				.sequential().sorted()
				.map(this::buildSlide)
				.filter(slide -> slide!=null)
				.collect(Collectors.toList()));
	}
	
	private PiSlide buildSlide(File file) {
		try {
			String[] numbers = file.getName().split("[_\\.]");
			int week = Integer.parseInt(numbers[0]);
			int day = Integer.parseInt(numbers[1]);
			int hour = Integer.parseInt(numbers[2]);
			int minute = Integer.parseInt(numbers[3]);
			PiTimer time = new PiTimer(week, day, hour, minute);
			BufferedImage image = ImageIO.read(file);
			return new PiSlide(time, image);
		}
		catch(Exception e) {
			System.out.println("Skipping " + file.getName());
			return null;
		}
	}
}
