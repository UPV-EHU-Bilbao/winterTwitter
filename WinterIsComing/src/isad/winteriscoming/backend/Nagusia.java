package isad.winteriscoming.backend;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import twitter4j.TwitterException;

public class Nagusia {

	public static void main(String[] args) throws TwitterException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
		}
		Konexioa.getKonexioa().logeatu();
	}

}
