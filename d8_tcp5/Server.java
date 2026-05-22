package chapter1.test.d8_tcp5;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP服务端
 */
public class Server {
    public static void main(String[] args) throws  Exception{
        // 创建ServerSocket服务端对象，同时为服务端注册端口
        /*
        参数1：注册端口
         */
        ServerSocket serverSocket = new ServerSocket(9898);

        while (true) {
            // 使用serverSocket对象，调用一个accept方法，等待客户端连接请求
            Socket socket = serverSocket.accept();

            // 追踪客户端连接
            System.out.println("客户端 " + socket.getRemoteSocketAddress() + " 连接通信");

            // 把客户端对应的socket通信管道，交给一个独立的线程负责处理
            new ServerReaderThread(socket).start();
        }
    }
}
