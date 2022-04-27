import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class DateServer {

    public static final int PORT = 9090;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket client = serverSocket.accept();
        PrintWriter out = new PrintWriter(client.getOutputStream(),true);

        System.out.println("SocketServer info:" + serverSocket.toString());
        System.out.println("Socket info:" + client.toString());

        //Print current time
        out.println("Current Time is:" + LocalDateTime.now());
        client.close();
        serverSocket.close();
        //reopen
        main(new String[0]);
    }
}
