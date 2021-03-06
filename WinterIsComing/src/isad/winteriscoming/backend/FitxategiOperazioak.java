package isad.winteriscoming.backend;

import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import isad.winteriscoming.salbuespenak.WinterTwitterSalbuespena;

public class FitxategiOperazioak {

	public FitxategiOperazioak() {

	}
	
/**
 * Datu basea kopiatzen du esandako lekura
 * @param path Nora kopiatu datu basea
 */
	public void kopiatu(String path) {
		File oraingoa = new File(Nagusia.getPath());
		File berria = new File(path);
		try {
			Files.copy(oraingoa.toPath(), berria.toPath(), COPY_ATTRIBUTES);
		} catch (IOException e) {
			throw new WinterTwitterSalbuespena("Ezin da fitxategia kopiatu");
		}
	}

	/**
	 * Datu basea ateratzen du Jar fitxategitik, eta esandako lekura idazten du
	 * @param resourceName Zein fitxategi kopiatu
	 * @param path Nora kopiatu
	 * @return Kopiatutako fitxategiaren lekua
	 */
	public String dbEsportatu(String resourceName, String path) {
		InputStream stream = null;
		OutputStream resStreamOut = null;
		String jarFolder;
		try {
			stream = DBKS.class.getResourceAsStream(resourceName);
			if (stream == null) {
				throw new WinterTwitterSalbuespena("Ez da datu basea aurkitu .jar fitxategian");
			}
			int readBytes;
			byte[] buffer = new byte[4096];
			File f = new File(ClassLoader.getSystemClassLoader().getResource(".").toURI());
			jarFolder = f.toString();
			resStreamOut = new FileOutputStream(path);
			while ((readBytes = stream.read(buffer)) > 0) {
				resStreamOut.write(buffer, 0, readBytes);
			}
		} catch (Exception ex) {
			throw new WinterTwitterSalbuespena("Ezin da fitxategia kopiatu");
		} finally {
			try {
				stream.close();
				resStreamOut.close();
			} catch (IOException e) {

			}
		}
		return jarFolder + resourceName;
	}
}
