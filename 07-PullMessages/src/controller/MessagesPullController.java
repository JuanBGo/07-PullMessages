package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import database.GestionBD;
import model.MesssageModel;
import model.UserModel;
import view.MessagesPullView;

/**
 * 
 * @author JuanAlberto
 *
 */
public class MessagesPullController implements ActionListener{
	private MessagesPullView pmV;
	private UserModel uM;
	private List<MesssageModel> listMessages;
	private GestionBD gBD;
	private List<UserModel> listUers;	
	/**
	 * Constructor de la clase PullMensajesController
	 * @param pmV Vista
	 */
	public MessagesPullController(MessagesPullView pmV) {
		this.pmV=pmV;
		this.uM=UserModel.getUsuarioModel();
		this.gBD=gBD.getGestionBD();
		
		pmV.onClickBotones(this);
		
		cargarPull();
	}

	/**
	 * Método para cargar los mensajes del pull a la ventana
	 */
	private void cargarPull() {
		try {
			listMessages=gBD.consultarMensajes();
		} catch (SQLException e) {
			e.getMessage();
		}
		
		DefaultTableModel modelo = (DefaultTableModel) pmV.getTable().getModel();
		Object a[]={null};
		int count = modelo.getRowCount();
		
		for (int i = 0; i < count; i++) {
			modelo.removeRow(0);
		}
		
		for (int i = 0; i < listMessages.size(); i++) {
			modelo.addRow(a);
			
			pmV.getTable().setValueAt(listMessages.get(i).getNombreUsuario(), i, 0);
			pmV.getTable().setValueAt(listMessages.get(i).getMensaje(), i, 1);
			pmV.getTable().setValueAt(listMessages.get(i).getFecha(), i, 2);
			pmV.getTable().setValueAt(listMessages.get(i).getHora(), i, 3);
		}
		pmV.getScrollPane().getVerticalScrollBar().setValue(pmV.getScrollPane().getVerticalScrollBar().getMaximum());
	}

	/**
	 * Método que controla el evento del boton
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==pmV.getBtnAgregar()){
			if (pmV.getTxtMensaje().getText().isEmpty()){
				pmV.mostrarAlerta("Ingresar mensaje");
				return;
			}
			
			MesssageModel mensajeModel = new MesssageModel();
			
			String[] meses = new String[]{
					"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}; 
			
			Calendar now = Calendar.getInstance();
			
			int dia = now.get(Calendar.DAY_OF_MONTH);
			String mes = meses[now.get(Calendar.MONTH)];
			int año = now.get(Calendar.YEAR);
			String hora,minutos,segundos,ampm;
			
			ampm = now.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";

			if(ampm.equals("PM")){
			 int h = now.get(Calendar.HOUR_OF_DAY)-12;

			 hora = h>9?""+h:"0"+h;

			}else{
				hora = now.get(Calendar.HOUR_OF_DAY)>9?""+now.get(Calendar.HOUR_OF_DAY):"0"+now.get(Calendar.HOUR_OF_DAY);            
			}
			
			minutos = now.get(Calendar.MINUTE)>9?""+now.get(Calendar.MINUTE):"0"+now.get(Calendar.MINUTE);
			segundos = now.get(Calendar.SECOND)>9?""+now.get(Calendar.SECOND):"0"+now.get(Calendar.SECOND);
		
			
			mensajeModel.setNombreUsuario(uM.getNombreUsuario());
			mensajeModel.setMensaje(pmV.getTxtMensaje().getText());
			mensajeModel.setFecha(mes+" "+ dia+", "+año);
			mensajeModel.setHora(hora+":"+minutos+":"+segundos+" "+ampm);
			
			try {
				gBD.agregarMensaje(mensajeModel);
			} catch (SQLException e) {
				e.getMessage();
			}
			
			pmV.limpiarVentana();
			
			cargarPull();
		}else if(ae.getSource()==pmV.getBtnCambiarUsuario()){
			pmV.mostrarInicio();
		}
	}
}
