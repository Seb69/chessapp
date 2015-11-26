package launcher.localLauncher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

import model.observable.ChessGame;
import controler.controlerLocal.ChessGameControler;
import vue.ChessGameDemo;

public class LaucherServeur {

	private static final int DISPOSE_ON_CLOSE = 2;
	public static Thread t;

	public static void main(String[] args) {


		ChessGame chessGameServeur;
		ChessGameControler chessGameControlerServeur;	
		ChessGameDemo chessGameDemoServeur;

		chessGameServeur = new ChessGame();	
		chessGameControlerServeur = new ChessGameControler(chessGameServeur,ChessGameControler.SERVER);


		Thread threadCient = new Thread(chessGameControlerServeur);
		threadCient.start();

		chessGameDemoServeur = new ChessGameDemo(chessGameControlerServeur);	
		chessGameDemoServeur.setVisible(true);

		chessGameDemoServeur.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
		chessGameDemoServeur.pack();
		chessGameDemoServeur.setResizable(true);
		chessGameDemoServeur.setLocationRelativeTo( null );
		chessGameDemoServeur.setVisible(true);

		chessGameServeur.addObservateur(chessGameDemoServeur);

	}
}