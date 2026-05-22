package chapter1.test.d4_tcp1;

import java.io.DataInputStream;
import java.io.InputStream;
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
        ServerSocket serverSocket = new ServerSocket(8888);

        // 使用serverSocket对象，调用一个accept方法，等待客户端连接请求
        Socket socket = serverSocket.accept();

        // 从socket通信管道中得到一个字节输入流
        InputStream inputStream = socket.getInputStream();
        // 将原始的字节输入流转换成字符输入流
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        // 使用数据输入流读取客户端发送过来的数据
        String data = dataInputStream.readUTF();
        System.out.println(data);
        // 获取客户端的地址和端口
        System.out.println(socket.getRemoteSocketAddress());

        // 释放资源
        dataInputStream.close();
        socket.close();
    }
}
