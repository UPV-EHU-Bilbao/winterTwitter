package isad.winteriscoming.backend;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import isad.winteriscoming.frontend.AukerakUI;
import isad.winteriscoming.frontend.WinterTwitter;

public class Nagusia {
	public static final float BERTSIOA = 0.56F;
	public static final String TITULUA = "WinterTwitter " + Nagusia.BERTSIOA;
	private static String path;
	private static WinterTwitter wtFrame;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
			}
		}
		//CAMBIAR ESTILO DE MAC A GETCROSSPLATFORMLOOKANDFEEL()
		wtFrame = new WinterTwitter();
		wtFrame.dekoratuGabeHasieratu();
		AukerakUI nireAUI = new AukerakUI();
		path = nireAUI.hasi();
		DBKS.getDBKS().konektatu(path);
		wtFrame.dekoratu();
	}

	public static String getPath() {
		return path;
	}
}
