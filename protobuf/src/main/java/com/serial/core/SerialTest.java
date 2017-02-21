package com.serial.core;

import java.io.IOException;
import java.util.Arrays;

/**
 * description: 自定义序列化测试
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 17:00
 */
public class SerialTest {

    public static void main(String[] args) throws Exception {
        byte[] bytes = toBytes();
        toPlayer(bytes);
    }


    /**
     * 序列化
     * @throws IOException
     */
    public static byte[] toBytes() throws IOException {

        Player2 player = new Player2(320, 20, "peter");
        player.getSkills().add(1001);
        player.getResource().setGold(99999);
        //获取 字节数组
        byte[] byteArray = player.getBytes();
        System.out.println(Arrays.toString(byteArray));
        return byteArray;
    }


    /**
     * 反序列化
     * @param bs
     * @throws Exception
     */
    public static void toPlayer(byte[] bs) throws Exception{

        Player2 player = new Player2();
        player.readFromBytes(bs);

        //打印
        System.out.println("playerId:" + player.getPlayerId());
        System.out.println("age:" + player.getAge());
        System.out.println("name:" + player.getName());
        System.out.println("skills:" + (Arrays.toString(player.getSkills().toArray())));
        System.out.println("gold:" + player.getResource().getGold());
    }
}
