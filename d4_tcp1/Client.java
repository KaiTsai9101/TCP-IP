package chapter1.test.d4_tcp1;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * TCP客户端
 */
public class Client {
    public static void main(String[] args) throws  Exception{
        // 创建Socket对象，并同时请求与服务端程序的连接
        /*
        参数1：指定服务端的IP地址
        参数2：指定服务端的端口号
         */
        Socket socket = new Socket("127.0.0.1", 8888);

        // 从socket通信管道中得到一个字节输出流，用来发数据给服务端程序
        OutputStream outputStream = socket.getOutputStream();

        // 把低级的字节输出流包装成数据输出流
        // 数据输出流允许应用程序以适当方式将基本 Java数据类型（这里就是outputStream）写入输出流中。然后，应用程序可以使用数据输入流将数据读入。
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        // 发数据给服务端程序
        dataOutputStream.writeUTF("hello, i am client");

        // 释放资源（关闭数据输出流也会顺带关闭字节输出流）
        dataOutputStream.close();
        socket.close();
    }
}
