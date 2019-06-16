import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 简易web服务器
 */
public class HttpServer {
    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8888);
        System.out.println("服务器启动，监听+"+8888+"端口。");
        while (!Thread.interrupted()) {
            Socket client = server.accept();
            pool.execute(new ServerThread(client));
        }

        server.close();
    }
}
