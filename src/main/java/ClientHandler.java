import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private Socket client;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public ClientHandler(Socket socket) throws IOException {
        this.client=socket;
        bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        printWriter = new PrintWriter(client.getOutputStream(),true);
    }

    @Override
    public void run() {
        System.out.println("(SERVER) Connected to client!");

        try {
            while (true){
                String request = bufferedReader.readLine();
                if (request.contains("name")){
                    printWriter.println(Server.getRandomName());
                }else if (request.contains("quit") || request.contains("exit")){
                    break;
                }else{
                    printWriter.println("Type 'tell me a name' to get random name");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("(SERVER) Received exit code  and closed the socket.");
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printWriter.close();
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
