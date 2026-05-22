package chapter1.test.d9_tcp6;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TCP服务端
 */
public class Server {
    public static void main(String[] args) throws  Exception{
        // 创建ServerSocket服务端对象，同时为服务端注册端口
        /*
        参数1：注册端口
         */
        ServerSocket serverSocket = new ServerSocket(9898);

        // 创建一个线程池，用于管理多个客户端的socket（线程池大小一般为电脑线程数*2）
        /*
        参数1：核心线程数
        参数2：最大线程数
        参数3：线程池中的线程空闲时间
        参数4：阻塞队列大小
        参数5：线程工厂（创建线程的工厂类）
        参数6：拒绝策略（AbortPolicy()是指线程池中的线程数已经达到最大值，且没有空闲线程，新的任务无法执行时，会直接拒绝执行该任务，并抛出异常）
         */
        ThreadPoolExecutor pool = new ThreadPoolExecutor(16 * 2, 16 * 2, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(8), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        while (true) {
            // 使用serverSocket对象，调用一个accept方法，等待客户端连接请求
            Socket socket = serverSocket.accept();

            // 追踪客户端连接
            System.out.println("客户端 " + socket.getRemoteSocketAddress() + " 连接通信");

            // 把客户端对应的socket通信管道，交给一个独立的线程负责处理
            pool.execute(new ServerReaderRunnable(socket));
        }
    }
}
