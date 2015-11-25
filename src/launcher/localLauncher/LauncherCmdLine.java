package launcher.localLauncher;

import javax.swing.JFrame;

import model.observable.ChessGame;
import vue.ChessGameCmdLine;
import vue.ChessGameDemo;
import controler.controlerLocal.ChessGameControler;


/**
 * @author francoise.perrin
 * Lance l'exécution d'un jeu d'échec en mode console.
 */
public class LauncherCmdLine {
	
    private static final int DISPOSE_ON_CLOSE = 2;

	public static void main(String[] args) {
        JFrame frame = new ChessGameDemo();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
     }
    

}
