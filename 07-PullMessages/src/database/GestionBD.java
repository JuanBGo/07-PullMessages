package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.MesssageModel;
import model.UserModel;

/**
 * 
 * @author JuanAlberto
 *
 */
public final class GestionBD implements DatabaseOperations{
	
	private static final GestionBD gestionBD = new GestionBD(); 
	
	private Connection lineConnection;
	private Statement statemet;
	
	/**
	 * Constructor de la clase GestionBD
	 */
	private GestionBD(){
		lineConnection=SingleDBConnection.getConexionBD().conectarBD();
	}
	
	/**
	 * Método para recuperar estancia de la clase GestionBD
	 * @return GestionBD instancia de la clase
	 */
	public static GestionBD getGestionBD(){
		return gestionBD;
	}
	
	/**
	 * Metodo para ejecutar una statemet SQL
	 * @param statemetSQL statemet que será ejecutada
	 */
	public void ejecutar(String statemetSQL){
		try {
			statemet=lineConnection.createStatement();
			statemet.execute(statemetSQL);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se pudo ejecutar la statemet"+e, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Método para ejecutar una consulta SQL
	 * @param statemetSQL statemet de la consulta que será ejecutada
	 * @return rs ResultSet resultado de la consulta
	 */
	public ResultSet consultar(String statemetSQL) {
		ResultSet rs=null;
		try {
			statemet=lineConnection.createStatement();
			rs=statemet.executeQuery(statemetSQL);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta"+e,"Error",JOptionPane.ERROR_MESSAGE);
		}
		return rs;
	}
	
	/**
	 * Método para testear conexión a BD
	 * @param args metodo main
	 */
	public static void main(String[] args) {
		GestionBD.getGestionBD();
	}

	/**
	 * Método para registrar usuario
	 */
	@Override
	public void registrarUsuario(UserModel usuarioModel) throws SQLException {
		String sql="INSERT INTO t_usuario (nombreUsuario,passwordUsuario) VALUES"
				+ "('"+usuarioModel.getNombreUsuario()+"',"
				+ "'"+usuarioModel.getPasswordUsuario()+"')";
		try {
			statemet=lineConnection.createStatement();
			statemet.execute(sql);
		} catch (SQLException e) {
			System.out.println(e);
			throw new SQLException("No se pudo registrar el usuario");
		}
		usuarioModel.limpiarModelo();
	}

	/**
	 * Método para validar el acceso al usuario
	 */
	@Override
	public boolean verificarUsuario(UserModel usuarioModel) throws SQLException {
		String sql = "SELECT * FROM t_usuario WHERE "
				+ "nombreUsuario = '"+usuarioModel.getNombreUsuario()+"' AND "
				+ "passwordUsuario = '"+usuarioModel.getPasswordUsuario()+"'";
		
		ResultSet rs=null;
		
		try {
			statemet=lineConnection.createStatement();
			rs=statemet.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println(e);
			throw new SQLException("No se pudo realizar la consulta");
		}
		
		if(rs.next()){
			usuarioModel.setIdUsuario(rs.getInt("idUsuario"));
			return true;
		}else{
			usuarioModel.limpiarModelo();
			return false;
		}
	}

	/**
	 * Método para consultar los mensajes almacenados en el pull
	 */
	@Override
	public List<MesssageModel> consultarMensajes() throws SQLException {
		
		List<MesssageModel> lista=new ArrayList<MesssageModel>();
				
		String sql = "SELECT * FROM t_mensaje";
		
		ResultSet rs=null;
		
		try {
			statemet=lineConnection.createStatement();
			rs=statemet.executeQuery(sql);
			
			int c=0;
			
			while (rs.next()) {
				lista.add(new MesssageModel());
				lista.get(c).setIdMensaje(rs.getInt("idMensaje"));
				lista.get(c).setNombreUsuario(rs.getString("nombreUsuario"));
				lista.get(c).setMensaje(rs.getString("mensaje"));
				lista.get(c).setFecha(rs.getString("fecha"));
				lista.get(c).setHora(rs.getString("hora"));
				c++;
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new SQLException("No se pudo realizar la consulta");
		}
		
		return lista;
	}

	/**
	 * Método para agregar mensajes al pull
	 */
	@Override
	public void agregarMensaje(MesssageModel mensajeModel) throws SQLException {
		
		String sql="INSERT INTO t_mensaje VALUES(null,"
				+ "'"+mensajeModel.getNombreUsuario()+"',"
				+ "'"+mensajeModel.getMensaje()+"',"
				+ "'"+mensajeModel.getFecha()+"',"
				+ "'"+mensajeModel.getHora()+"')";
		
		try {
			statemet=lineConnection.createStatement();
			statemet.execute(sql);
		} catch (SQLException e) {
			System.out.println(e);
			throw new SQLException("No se pudo insertar el mensaje");
		}
	}
}