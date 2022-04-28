import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class DateServer {

    public static String[] names={"Ahmed","Murad","Nijat","Omer"};
    public static String[] adjs={"the gentle","the un-gentle","the braveheart","the urbane"};
    public static final int PORT = 9090;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("(SERVER) Waiting for client connection...");
        Socket client = serverSocket.accept();
        System.out.println("(SERVER) Connected to client!");
        PrintWriter out = new PrintWriter(client.getOutputStream(),true);

        System.out.println("SocketServer info:" + serverSocket.toString());
        System.out.println("Socket info:" + client.toString());

        //Print current time
        String currentDateTime = LocalDateTime.now().toString();
        out.println(getRandomName());
        client.close();
        System.out.println("(SERVER) Sent date (" + currentDateTime + ") and closed the socket.");
        serverSocket.close();
        //reopen
        main(new String[0]);
    }

    public static String getRandomName(){
        String name = names[(int) (Math.random()*names.length)];
        String adj = adjs[(int)(Math.random()*adjs.length)];
        return name + " " + adj;
    }

}
