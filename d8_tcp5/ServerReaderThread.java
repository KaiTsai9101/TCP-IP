package chapter1.test.d8_tcp5;

import java.io.*;
import java.net.Socket;

public class ServerReaderThread extends Thread {
    private Socket socket;

    public ServerReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 服务端响应数据给浏览器
            OutputStream outputStream = socket.getOutputStream();
            // 因为浏览器接收的数据有规范，所以需要将字符流转换成打印流
            PrintStream printStream = new PrintStream(outputStream);
            printStream.println("HTTP/1.1 200 OK");
            printStream.println("Content-Type:text/html;charset=UTF-8");
            printStream.println();  // 必须空一行，以区分响应头和响应体
            printStream.println("<div style='color:red;font-size:120px;text-align:center'>Hello TCP/IP<div>");
            printStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
