package vue;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import controler.controlerLocal.ChessGameControler;
import observateur.Observateur;
import tools.*;
import model.Coord;
import model.Couleur;
import model.PieceIHM;
import model.observable.ChessGame;


 
public class ChessGameDemo extends JFrame implements MouseListener, MouseMotionListener, Observateur {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    
    ChessImageProvider chessImageProvider;
    ChessGameControler chessGameControler;
    ChessGame chessGame;
    List<PieceIHM> chessPieceListRefresh;
    
    int xAdjustment;
    int yAdjustment;
    
    int TEMP;
    
     private Point positionInit;
 
 

	public ChessGameDemo(){
        Dimension boardSize = new Dimension(600, 600);
        
        chessGame= new ChessGame();
        chessGameControler= new ChessGameControler(chessGame);
        
        chessGame.addObservateur(this);
        
 
        //  Use a Layered Pane for this this application
 
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane 
 
         chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
 
        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );
 
            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.blue : Color.white );
            else
                square.setBackground( i % 2 == 0 ? Color.white : Color.blue );
        }
 
    	JLabel piece = new JLabel(new ImageIcon("/home/vinod/amarexamples/chess.jpg"));
 		JPanel panel = (JPanel) chessBoard.getComponent(0);
 		panel.add(piece);
 		int componentPosition;
        
     // Add a few pieces to the board
     		// Add white pieces
			for (int i = 0; i < ChessPiecePos.values().length; i++) {

				for (int j = 0; j <(ChessPiecePos.values()[i].coords).length; j++) {
					
					piece = new JLabel(new ImageIcon(chessImageProvider.getImageFile(ChessPiecePos.values()[i].nom, ChessPiecePos.values()[i].couleur)));
		     		
		     		componentPosition=ChessPiecePos.values()[i].coords[j].x + Math.abs(ChessPiecePos.values()[i].coords[j].y-7)*8;
		     		
		     		panel = (JPanel) chessBoard.getComponent(componentPosition);
		     		panel.add(piece);
					
				}

			}
			
			
			
			
    

    }
 
    public void mousePressed(MouseEvent e){
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
 
        if (c instanceof JPanel) 
	return;
 
        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
        
        Point positionInitiale = e.getPoint();
        
        positionInit = positionInitiale;
     

    }
   
    //Move the chess piece around
    
    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
         chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
     
     }
     
  //Drop the chess piece back onto the chess board
 
    public void mouseReleased(MouseEvent e) {
    	  	
        if(chessPiece == null) return;
 
        chessPiece.setVisible(false);
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
 
        if (c instanceof JLabel){
            Container parent = c.getParent();
            parent.remove(0);
            parent.add( chessPiece );
        }
        else {
            Container parent = (Container)c;
            parent.add( chessPiece );
        }
      
        Point position = e.getPoint();
        
        //Coordonnees initiales
        int posX = (int) Math.ceil((position.x)/75);
        int posY = (int) Math.ceil((position.y)/75);
        
        //Coordonnees Finales
        int posXf = (int) Math.ceil((positionInit.x)/75);
        int posYf = (int) Math.ceil((positionInit.y)/75);
   
        //L'origine du repere utilise par la methode move est en bas a droite
        posY=Math.abs(posY-7);
        posYf=Math.abs(posYf-7);
        
        System.out.print(posY);
        System.out.print(" \n");
        Coord coordFinale= new Coord(posX,posY);
        Coord coordInitiale= new Coord(posXf,posYf);
        
        chessPiece.setVisible(true);
    
        //System.out.print(coordInitiale.x+ " "+ coordInitiale.y+" "+coordFinale.x+ " "+ coordFinale.y);
       	if (chessGameControler.move(coordInitiale, coordFinale)) 
       	{
       		System.out.print("Deplacement is OK\n");
       	}
       	else
       	{
       		System.out.print("Deplacement not allowed \n");
       		refreshObservateur(chessPieceListRefresh);
       		
       	}
        return;
    }
 
    public void mouseClicked(MouseEvent e) {
   
    }
    public void mouseMoved(MouseEvent e) {
 
   }
    public void mouseEntered(MouseEvent e){
    	
    }
    public void mouseExited(MouseEvent e) {
    	
    }
 
    public static void main(String[] args) {
        JFrame frame = new ChessGameDemo();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
     }
    

	@Override
	public void updateObservateur(List<PieceIHM> chessPieceList) {
		Dimension boardSize = new Dimension(600, 600); 
		
		chessPieceListRefresh=chessPieceList;
		
		layeredPane.setVisible(false);
		chessBoard.setVisible(false);
		
        //  Use a Layered Pane for this this application	
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);
     

        //Add a chess board to the Layered Pane  
         chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
 
        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );
 
            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.blue : Color.white );
            else
                square.setBackground( i % 2 == 0 ? Color.white : Color.blue );
        }
 
    	JLabel piece = new JLabel(new ImageIcon("/home/vinod/amarexamples/chess.jpg"));
 		JPanel panel = (JPanel) chessBoard.getComponent(0);
 		panel.add(piece);
 		int componentPosition;
        
     // Add a few pieces to the board
     		// Add white pieces
 		
 		for (PieceIHM pieceIHM : chessPieceList)
 		{
 			for (int j = 0; j <pieceIHM.getList().size(); j++) {
				
				piece = new JLabel(new ImageIcon(chessImageProvider.getImageFile(pieceIHM.getTypePiece(), pieceIHM.getCouleur())));
	     		
				//Formule de correspondance entre les positions admise par le Jpanel et les coordonnees des pieces
	     		componentPosition=pieceIHM.getList().get(j).x + Math.abs(pieceIHM.getList().get(j).y-7)*8;
	     		
	     		panel = (JPanel) chessBoard.getComponent(componentPosition);
	     		panel.add(piece);
				
			}
 			
 		}			
    
	}

	
	public void refreshObservateur(List<PieceIHM> chessPieceList) {

		updateObservateur(chessPieceList);
	}
}


