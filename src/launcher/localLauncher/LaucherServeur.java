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


		ChessGame chessGame;
		ChessGameControler chessGameControler;	
		ChessGameDemo chessGameDemo;

		chessGame = new ChessGame();	
		chessGameControler = new ChessGameControler(chessGame,ChessGameControler.SERVER);


		Thread threadCient = new Thread(chessGameControler);
		threadCient.start();

		chessGameDemo = new ChessGameDemo(chessGameControler);	
		chessGameDemo.setVisible(true);

		chessGameDemo.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
		chessGameDemo.pack();
		chessGameDemo.setResizable(true);
		chessGameDemo.setLocationRelativeTo( null );
		chessGameDemo.setVisible(true);

		chessGame.addObservateur(chessGameDemo);

	}
}