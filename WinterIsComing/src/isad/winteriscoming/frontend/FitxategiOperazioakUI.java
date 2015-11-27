package isad.winteriscoming.frontend;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import isad.winteriscoming.backend.FitxategiOperazioak;
import isad.winteriscoming.backend.Nagusia;
import isad.winteriscoming.salbuespenak.SentitzenNaizException;

public class FitxategiOperazioakUI {
	private FitxategiOperazioak nireFO;

	public FitxategiOperazioakUI() {
		nireFO = new FitxategiOperazioak();
	}

	public void kopiaEgin() {
		JOptionPane.showMessageDialog(null, "Hautatu non egin nahi duzun datu basearen kopia", Nagusia.TITULUA,
				JOptionPane.INFORMATION_MESSAGE);
		String path = Nagusia.getPath();
		JFileChooser gureFileChooser = new JFileChooser(new File(System.getProperty("user.home")));
		gureFileChooser.setDialogTitle(Nagusia.TITULUA);
		gureFileChooser.setAcceptAllFileFilterUsed(false);
		gureFileChooser.setFileFilter(new FileNameExtensionFilter("Access Datu Baseak", "accdb"));
		while (path == Nagusia.getPath()) {
			int gureZenbakia = gureFileChooser.showSaveDialog(null);
			if (gureZenbakia == JFileChooser.CANCEL_OPTION)
				return;
			try {
				path = gureFileChooser.getSelectedFile().getAbsolutePath();
				if (!path.contains(".accdb")) {
					path = path + ".accdb";
				}
			} catch (Exception salbuespena) {
				throw new SentitzenNaizException("Fitxategiak ez du balio!!!!!");
			}
			if (path == Nagusia.getPath())
				JOptionPane.showMessageDialog(gureFileChooser,
						"Hautatu duzun fitxategia kargatu duzunaren berdina da.\nMesedez, aukeratu beste bat",
						Nagusia.TITULUA, JOptionPane.WARNING_MESSAGE);
		}
		nireFO.kopiatu(path);
	}

	public String datuBaseaGordetzekoPath() {
		String path;
		JFileChooser gureFileChooser = new JFileChooser(new File(System.getProperty("user.home")));
		gureFileChooser.setAcceptAllFileFilterUsed(false);
		gureFileChooser.setDialogTitle(Nagusia.TITULUA);
		gureFileChooser.setFileFilter(new FileNameExtensionFilter("Access Datu Baseak", "accdb"));
		gureFileChooser.setSelectedFile(new File("WinterTwitter"));
		int gureZenbakia = gureFileChooser.showSaveDialog(null);
		if (gureZenbakia == JFileChooser.CANCEL_OPTION)
			System.exit(0);
		try {
			path = gureFileChooser.getSelectedFile().getAbsolutePath();
			if (!path.contains(".accdb")) {
				path = path + ".accdb";
			}
		} catch (Exception salbuespena) {
			throw new SentitzenNaizException("Fitxategiak ez du balio!!!!!");
		}
		return path;
	}

	public void datuBaseaEraiki(String path) {
		int aukera = JOptionPane.YES_OPTION;
		File fitxategia = new File(path);
		if (fitxategia.exists())
			aukera = JOptionPane.showConfirmDialog(null,
					"Datu basea existitzen da izen horrekin, jarraitzen baduzu ezabatu egingo da.", Nagusia.TITULUA,
					JOptionPane.YES_NO_OPTION);
		if (aukera == JOptionPane.NO_OPTION || aukera == JOptionPane.CLOSED_OPTION)
			System.exit(1);
		fitxategia.delete();
		FitxategiOperazioak nireFO = new FitxategiOperazioak();
		try {
			nireFO.dbEsportatu("/isad/winteriscoming/DatuBasea.accdb", path);
		} catch (Exception e) {
			new SentitzenNaizException("Ezin da fitxategia esportatu");
		}
		JOptionPane.showMessageDialog(null, "Datu basea " + path + " karpetan gorde da.", Nagusia.TITULUA,
				JOptionPane.INFORMATION_MESSAGE);
	}

	public String getPath() {
		String path;
		JFileChooser gureFileChooser = new JFileChooser(new File(System.getProperty("user.home")));
		gureFileChooser.setAcceptAllFileFilterUsed(false);
		gureFileChooser.setDialogTitle(Nagusia.TITULUA);
		gureFileChooser.setFileFilter(new FileNameExtensionFilter("Access Datu Baseak", "accdb"));
		int gureZenbakia = gureFileChooser.showOpenDialog(null);
		if (gureZenbakia == JFileChooser.CANCEL_OPTION)
			System.exit(0);
		try {
			path = gureFileChooser.getSelectedFile().getAbsolutePath();
		} catch (Exception salbuespena) {
			throw new SentitzenNaizException("Fitxategiak ez du balio!!!!!");
		}
		return path;
	}
}