package launcher.localLauncher;

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


			chessGameDemoClient.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
			chessGameDemoClient.pack();
			chessGameDemoClient.setResizable(true);
			chessGameDemoClient.setLocationRelativeTo( null );
			chessGameDemoClient.setVisible(true);
			
			chessGameClient.addObservateur(chessGameDemoClient);
		}
	     

}
