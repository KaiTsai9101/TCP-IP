package chapter1.test.d7_tcp4;

import chapter1.test.d6_tcp3.Server;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class ClientReaderThread extends Thread{
    private Socket socket;
    public ClientReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            while (true) {
                // 追踪客户端的信息（是否离线）
                try {
                    String data = dataInputStream.readUTF();
                    System.out.println(data);
                } catch (Exception e) {
                    System.out.println("客户端: " + socket.getRemoteSocketAddress() + " 退出通信");
                    dataInputStream.close();
                    socket.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
