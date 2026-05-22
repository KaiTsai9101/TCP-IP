package chapter1.test.d2_udp1;

import java.net.*;

/**
 * UDP客户端
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // 创建客户端对象（套接字）
        DatagramSocket socket = new DatagramSocket();

        // 创建数据包对象封装要发出去的数据（信息包）
        byte[] data = "客户端信息".getBytes();
        /*
         * 参数1：数据
         * 参数2：数据长度
         * 参数3：目标地址
         * 参数4：目标端口
         */
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 6666);

        // 开始正式发送数据
        socket.send(packet);

        // 关闭连接（释放资源）
        socket.close();
    }
}
