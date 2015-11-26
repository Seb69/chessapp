package launcher.localLauncher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import model.observable.ChessGame;
import controler.controlerLocal.ChessGameControler;
import vue.ChessGameDemo;

public  class LaucherClient {

	

	    private static final int DISPOSE_ON_CLOSE = 2;
		

		public static void main(String[] args) {
			
	        
	    	ChessGame chessGameClient;
	    	ChessGameControler chessGameControlerClient;	
			ChessGameDemo chessGameDemoClient;
	
			
			
			chessGameClient = new ChessGame();	
			chessGameControlerClient = new ChessGameControler(chessGameClient,ChessGameControler.CLIENT);
			
			
			Thread threadCient = new Thread(chessGameControlerClient);
			threadCient.start();
			
			chessGameDemoClient = new ChessGameDemo(chessGameControlerClient);	
			chessGameDemoClient.setVisible(true);

			chessGameDemoClient.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
			chessGameDemoClient.pack();
			chessGameDemoClient.setResizable(true);
			chessGameDemoClient.setLocationRelativeTo( null );
			chessGameDemoClient.setVisible(true);
			
			chessGameClient.addObservateur(chessGameDemoClient);
		}
	    

}
