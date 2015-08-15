import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Sergey Lavinov on 02.08.2015.
 */
public class ReceiverThread implements Runnable {

    private ServerSocket serverSocket;
    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;


    @Override
    public void run() {

    }

}
