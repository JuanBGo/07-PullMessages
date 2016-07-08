package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import database.GestionBD;
import model.UserModel;
import view.AccessView;

/**
 * 
 * @author JuanAlberto
 *
 */
public class AccessController implements ActionListener{
	private AccessView accessV;
	private GestionBD gBD;
	private UserModel usuarioModel;
	
	/**
	 * Constructor de la clase AccesoController
	 * @param accessV vista
	 */
	public AccessController(AccessView accessV) {
		this.accessV=accessV;
		this.gBD=gBD.getGestionBD();
		this.usuarioModel=UserModel.getUsuarioModel();
		
		accessV.onClickBotones(this);
	}

	/**
	 * Método que controla la acción de los botones
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==accessV.getBtnAcceder()){
			if(accessV.getTxtUsuario().getText().isEmpty()){
				accessV.mostrarAlerta("Ingrese su nombre de usuario, por favor");
				return;
			}else if(accessV.getTxtPassword().getText().isEmpty()){
				accessV.mostrarAlerta("Ingrese su contraseña, por favor");
				return;
			}
			
			usuarioModel.setNombreUsuario(accessV.getTxtUsuario().getText());
			usuarioModel.setPasswordUsuario(accessV.getTxtPassword().getText());
			
			try {
				if(gBD.verificarUsuario(usuarioModel)){
					//accessV.mostrarMensaje("Acceso concedido");
					accessV.mostrarPullMensajes();
					
				}else{
					accessV.mostrarAlerta("Usuario o contraseña incorrectos");
				}
			} catch (SQLException e) {
				accessV.mostrarError(e.getMessage());
			}
		}else if(ae.getSource()==accessV.getBtnRegistrarse()){
			accessV.mostrarVentanaRegistro();
		}
	}
}
