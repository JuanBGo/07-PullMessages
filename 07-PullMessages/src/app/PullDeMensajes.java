package app;

import controller.AccessController;
import database.GestionBD;
import view.AccessView;

/**
 * 
 * @author JuanAlberto
 *
 */
public class PullDeMensajes {

	/**
	 * Met�do para inicializar la aplicaci�n
	 * @param args Metodo main
	 */
	public static void main(String[] args) {
		GestionBD.getGestionBD();
		AccessView accesoView=new AccessView();
		AccessController accesoController=new AccessController(accesoView);
		accesoView.setVisible(true);
	}
}
