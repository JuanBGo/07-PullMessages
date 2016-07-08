package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import database.GestionBD;
import model.UserModel;
import view.RegistryView;

/**
 * 
 * @author JuanAlberto
 *
 */
public class RegistryController implements ActionListener{
	RegistryView rV;
	UserModel uM;
	GestionBD gBD;
	
	/**
	 * Constructor de la clase RegistroController
	 * @param rV vista
	 */
	public RegistryController(RegistryView rV) {
		this.rV=rV;
		this.uM=UserModel.getUsuarioModel();
		this.gBD=gBD.getGestionBD();
		
		rV.onClickBotones(this);
	}

	/**
	 * Método que controla la acción de los botones
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==rV.getBtnRegistrarse()){
			if(rV.getTxtUsuario().getText().isEmpty()){
				rV.mostrarAlerta("Ingrese su nombre de usuario");
				return;
			}else if(rV.getTxtPassword().getText().isEmpty()){
				rV.mostrarAlerta("Ingrese su contraseña");
				return;
			}else if(rV.getTxtPassword2().getText().isEmpty()){
				rV.mostrarAlerta("Ingrese de nuevo su contraseña");
				return;
			}else if(!rV.getTxtPassword().getText().equals(rV.getTxtPassword2().getText())){
				rV.mostrarAlerta("Las contraseñas con coinciden");
				return;
			}
			
			uM.setNombreUsuario(rV.getTxtUsuario().getText());
			uM.setPasswordUsuario(rV.getTxtPassword().getText());
			
			try {
				gBD.registrarUsuario(uM);
				rV.mostrarMensaje("Usuario registrado correctamente");
				rV.limpiarVentana();
			} catch (Exception ex) {
				rV.mostrarError(ex.getMessage());
			}
		
		}else if(e.getSource()==rV.getBtnRegresar()){
			rV.mostrarVentanaAcceso();
		}
	}
}
