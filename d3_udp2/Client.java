package chapter1.test.d3_udp2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * UDP客户端
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // 创建客户端对象（套接字）
        DatagramSocket socket = new DatagramSocket();

        Scanner sc = new Scanner(System.in);
        while (true) {
            // 创建数据包对象封装要发出去的数据（信息包）
            String msg = sc.nextLine();
            byte[] data = msg.getBytes();

            // 一旦发现用户输入exit命令，就退出客户端
            if ("exit".equals(msg)) {
                System.out.println("退出成功");
                socket.close();
                break;
            }

            /*
             * 参数1：数据
             * 参数2：数据长度
             * 参数3：目标地址
             * 参数4：目标端口
             */
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 6666);

            // 开始正式发送数据
            socket.send(packet);
        }

        // 关闭连接（释放资源）
//        socket.close();
    }
}
