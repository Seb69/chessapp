package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import observable.ObservableReception;




public class Reception extends ObservableReception implements Runnable {


	Communication communication;
	private Object object;
	private Socket socket;


	public Reception(Socket socket, Communication communication){

		this.socket = socket;
		this.communication=communication;
	}

	public void run() {


		while(true){


			try {
				ObjectInputStream in;
				in = new ObjectInputStream(socket.getInputStream());

				try {
					object = in.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			if (object!=null)
			{
				System.out.println("Reception : " + object.toString());
				communication.object = object;
				notifyObservableReception();
			}



		}
	}

}
