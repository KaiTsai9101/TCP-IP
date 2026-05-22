package chapter1.test.d6_tcp3;

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
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            while (true) {
                // 追踪客户端的信息（是否离线）
                try {
                    String data = dataInputStream.readUTF();
                    System.out.println(data);
                    sendMsgToAll(data);
                } catch (Exception e) {
                    System.out.println("客户端: " + socket.getRemoteSocketAddress() + " 退出通信");
                    Server.onLineSockets.remove(socket);
                    dataInputStream.close();
                    socket.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMsgToAll(String data) throws IOException {
        // 发送给全部在线的socket管道接收
        for (Socket onLineSocket : Server.onLineSockets) {
            OutputStream outputStream = onLineSocket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(data);
            dataOutputStream.flush();
        }
    }
}
