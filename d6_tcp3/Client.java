package chapter1.test.d6_tcp3;

import chapter1.test.d7_tcp4.ClientReaderThread;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

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
        Socket socket = new Socket("127.0.0.1", 9898);

        // 创建一个独立的线程，负责从socket通信管道中接收服务端发送过来的消息
        new ClientReaderThread(socket).start();

        // 从socket通信管道中得到一个字节输出流，用来发数据给服务端程序
        OutputStream outputStream = socket.getOutputStream();

        // 把低级的字节输出流包装成数据输出流
        // 数据输出流允许应用程序以适当方式将基本 Java数据类型（这里就是outputStream）写入输出流中。然后，应用程序可以使用数据输入流将数据读入。
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        Scanner sc = new Scanner(System.in);
        while (true) {
            // 发数据给服务端程序
            String msg = sc.nextLine();

            // 如果msg是"exit"则退出socket管道
            if ("exit".equals(msg)) {
                System.out.println("退出成功");
                // 释放资源（关闭数据输出流也会顺带关闭字节输出流）
                dataOutputStream.close();
                socket.close();
                break;
            }

            // 否则发数据给服务端程序
            dataOutputStream.writeUTF(msg);

            // 刷新缓冲区（确保信息发送）
            dataOutputStream.flush();
        }
    }
}
