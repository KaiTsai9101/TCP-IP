package chapter1.test.d1_ip;

import java.io.IOException;
import java.net.InetAddress;

public class InetAddressTest {
    public static void main(String[] args) throws IOException {
        // 获取本机地址
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostName());
        System.out.println(localHost.getHostAddress());

        // 获取指定IP或者域名的地址
        InetAddress ip2 = InetAddress.getByName("www.baidu.com");
        System.out.println(ip2.getHostName());
        System.out.println(ip2.getHostAddress());

        // 等价于 ping www.baidu.com 可判断服务端或客户端是否宕机/断网
        System.out.println(ip2.isReachable(6000));
    }
}
