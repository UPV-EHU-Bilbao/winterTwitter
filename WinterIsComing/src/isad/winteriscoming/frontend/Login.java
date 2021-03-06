package isad.winteriscoming.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import isad.winteriscoming.backend.Konexioa;
import isad.winteriscoming.externals.SpringUtilities;

public class Login extends JPanel implements KeyListener {

	private static final long serialVersionUID = -5514346274613273871L;
	private JLabel pin;
	private JTextField pinField;
	private JPanel pinPanela;
	private JButton ok;
	private JCheckBox gorde;
	private static Login gureLogin;
	private boolean tokenakGorde;
	private String pinString;

	public Login() {
		tokenakGorde = true;
		this.setLayout(new BorderLayout());
		this.pinPanela = new JPanel(new SpringLayout());
		pinPanela.setBackground(new Color(217, 251, 243));
		pinString = "PIN: ";
		this.pin = new JLabel(pinString, SwingConstants.TRAILING);
		this.pinPanela.add(pin);
		this.pinField = new JTextField(7);
		this.pin.setLabelFor(pinField);
		this.pinPanela.add(pinField);
		this.gorde = new JCheckBox("PINa gorde");
		this.gorde.setSelected(true);
		this.gorde.addActionListener(gureAE -> this.aldatu());
		gorde.setBackground(new Color(217, 251, 243));
		SpringUtilities.makeCompactGrid(this.pinPanela, 1, 2, 6, 6, 6, 6);
		JPanel berria = new JPanel();	
		berria.setLayout(new BorderLayout());
		berria.add(this.pinPanela, BorderLayout.PAGE_START);
		berria.add(this.gorde, BorderLayout.CENTER);
		berria.setBackground(new Color(217, 251, 243));
		this.add(berria, BorderLayout.PAGE_START);
		this.ok = new JButton("Sartu");
		this.ok.addActionListener(gureAE -> this.datuakGorde());
		this.ok.addKeyListener(this);
		this.pinField.addKeyListener(this);
		this.add(this.ok, BorderLayout.PAGE_END);
		this.setMinimumSize(new Dimension(210, 110));
		this.setVisible(true);
		this.setOpaque(true);
		this.setBackground(new Color(217, 251, 243));
		Login.gureLogin = this;
	}

	public static Login getLogin() {
		return Login.gureLogin;
	}

	public String getPIN() {
		return this.pinString;
	}

	private void datuakGorde() {
		this.pinString = new String(this.pinField.getText());
		this.setVisible(false);
		Konexioa.getKonexioa().tokenaLortu();
	}

	private void aldatu() {
		tokenakGorde = this.gorde.isSelected();
	}

	@Override
	public void keyPressed(KeyEvent teklaSakatuta) {
		if (teklaSakatuta.getKeyCode() == KeyEvent.VK_ENTER)
			this.datuakGorde();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public boolean getGordetzeko() {
		return tokenakGorde;
	}
}