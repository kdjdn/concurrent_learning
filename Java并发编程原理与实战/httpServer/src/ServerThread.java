import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable{
    private Socket client;
    private InputStream ins;
    private OutputStream ous;

    public ServerThread(Socket client){
        this.client = client;
    }

    private void init(){
        InputStream ins = null;
        try {
            ins = client.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
             ous = client.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(line);
    }
    @Override
    public void run() {
        init();
    }
}
