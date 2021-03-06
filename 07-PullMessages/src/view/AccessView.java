package view;

import java.awt.Container;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import controller.MessagesPullController;
import controller.RegistryController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

/**
 * 
 * @author JuanAlberto
 *
 */
public class AccessView extends JFrame{
	private JLabel lblUsuario = new JLabel("Nick Name:");
	private JLabel lblPassword = new JLabel("Password:");

	private JButton btnAcceder = new JButton("Log in");
	private JButton btnRegistrarse = new JButton("New User");
	
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	
	private Container c = getContentPane();

	
	/**
	 * Constructor de la clase AccesoView
	 */
	public AccessView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Access");
		this.setBounds(100, 100, 270, 250);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		c.setLayout(null);
		
		btnAcceder.setBounds(76, 104, 120, 30);
		btnRegistrarse.setBounds(76, 145, 120, 30);
		
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setBounds(43, 31, 53, 14);
		
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setBounds(43, 59, 63, 14);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(122, 28, 132, 20);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(122, 56, 132, 20);
		
		c.add(btnAcceder);
		c.add(btnRegistrarse);
		c.add(lblUsuario);
		c.add(lblPassword);
		c.add(txtUsuario);
		c.add(txtPassword);
	}

	/**
	 * @return the btnAcceder
	 */
	public JButton getBtnAcceder() {
		return btnAcceder;
	}

	/**
	 * @return the btnRegistrarse
	 */
	public JButton getBtnRegistrarse() {
		return btnRegistrarse;
	}

	/**
	 * @return the txtUsuario
	 */
	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	/**
	 * @return the txtPassword
	 */
	public JPasswordField getTxtPassword() {
		return txtPassword;
	}

	/**
	 * M�todo para asignar el evento click a los botones
	 * @param al evento click
	 */
	public void onClickBotones(ActionListener al){
		btnAcceder.addActionListener(al);
		btnRegistrarse.addActionListener(al);
	}
	
	/**
	 * M�todo para mostrar la ventana de registro de usuarios
	 */
	public void mostrarVentanaRegistro(){
		RegistryView registroView = new RegistryView();
		RegistryController registroController = new RegistryController(registroView);
		this.setVisible(false);
		registroView.setVisible(true);
	}
	
	/**
	 * M�todo para mostrar mensaje en pantalla
	 * @param mensaje generado
	 */
	public void mostrarMensaje(String mensaje){
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	/**
	 * M�todo para mostrar alerta en pantalla
	 * @param alerta generada
	 */
	public void mostrarAlerta(String alerta){
		JOptionPane.showMessageDialog(this, alerta, "Alerta", JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * M�todo para mostrar error en pantalla
	 * @param error generado
	 */
	public void mostrarError(String error){
		JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * M�todo para limpiar la ventana
	 */
	public void limpiarVentana(){
		txtUsuario.setText(null);
		txtPassword.setText(null);
	}
	
	/**
	 * M�todo para mostrar la ventana de mensajes
	 */
	public void mostrarPullMensajes(){
		MessagesPullView pullMensajesView = new MessagesPullView();
		MessagesPullController pullMensajesController = new MessagesPullController(pullMensajesView);
		this.setVisible(false);
		pullMensajesView.setVisible(true);
	}
}
