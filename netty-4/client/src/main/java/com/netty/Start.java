package com.netty;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * description: 多客户端测试启动类
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 15:59
 */
public class Start {

    public static void main(String[] args) {

        MultClient client = new MultClient();
        client.init(5);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println("请输入:");
                String msg = bufferedReader.readLine();
                client.nextChannel().writeAndFlush(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
