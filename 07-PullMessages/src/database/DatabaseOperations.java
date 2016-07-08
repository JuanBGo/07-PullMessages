package database;

import java.sql.SQLException;
import java.util.List;

import model.MesssageModel;
import model.UserModel;;

/**
 * 
 * @author JuanAlberto
 *
 */
public interface DatabaseOperations {
	
	/**
	 * M�todo para registrar un usuario en la BD 
	 * @param usuarioModel modelo del usuario
	 * @throws SQLException Excepci�n de transacci�n SQL
	 */
	public void registrarUsuario(UserModel usuarioModel) throws SQLException;
	
	/**
	 * M�todo para verificar el acceso del usuario
	 * @param usuarioModel modelo del usuario
	 * @return boolean estado del acceso
	 * @throws SQLException Excepci�n de la transacci�n SQL
	 */
	public boolean verificarUsuario(UserModel usuarioModel) throws SQLException;
	
	/**
	 * M�todo para consultar los mensajes del pull
	 * @return Lista con los modelos de los mensajes
	 * @throws SQLException Excepci�n de la transacci�n SQL
	 */
	public List<MesssageModel> consultarMensajes() throws SQLException;
	
	
	
	/**
	 * M�todo para agregar un nuevo mensaje al pull
	 * @param mensajeModel modelo del mensaje
	 * @throws SQLException Excepci�n de la transacci�n SQL
	 */
	public void agregarMensaje(MesssageModel mensajeModel) throws SQLException;
}
