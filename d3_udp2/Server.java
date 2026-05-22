package chapter1.test.d3_udp2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * UDP服务端
 */
public class Server {
    public static void main(String[] args) throws Exception{
        // 创建一个服务端对象（需要与客户端端口一样才能收到信息）
        DatagramSocket socket = new DatagramSocket(6666);

        // 创建一个数据包对象，用于接收数据
        /*
        参数1：接收数据的数组（空数组）
        参数2：接收数据的数组长度
         */
        byte[] buffer = new byte[1024 * 64];    // 64KB（一包数据不会大于64KB）
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        while (true) {
            // 接收数据
            socket.receive(packet);

            // 获取本次数据包接收数据长度
            int len = packet.getLength();
            // 从字节数组中获取数据并打印出来
            String data = new String(buffer, 0, len);
            System.out.println(data);

            // 获取数据包发送方信息
            System.out.println(packet.getAddress());
            System.out.println(packet.getAddress().getHostAddress());
            System.out.println(packet.getPort());
            System.out.println("--------------------------------");
        }
    }
}
