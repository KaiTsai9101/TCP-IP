package chapter1.test.d7_tcp4;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class ServerReaderThread extends Thread {
    private Socket socket;

    public ServerReaderThread(Socket socket) {
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
