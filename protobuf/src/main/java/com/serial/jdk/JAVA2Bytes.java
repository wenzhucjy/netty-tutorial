package com.serial.jdk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

/**
 * description: 原始的序列化
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 16:15
 */
public class JAVA2Bytes {

    public static void main(String[] args) throws Exception {
        byte[] bytes = toBytes();
        // [-84, -19, 0, 5, 115, 114, 0, 22, 99, 111, 109, 46, 115, 101, 114, 105, 97, 108, 46, 106, 97, 118, 97, 46, 80, 108, 97, 121, 101, 114, -73, 60, -20, -43, 116, -4, 52, 0, 2, 0, 4, 73, 0, 3, 97, 103, 101, 74, 0, 8, 112, 108, 97, 121, 101, 114, 73, 100, 76, 0, 4, 110, 97, 109, 101, 116, 0, 18, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 76, 0, 6, 115, 107, 105, 108, 108, 115, 116, 0, 16, 76, 106, 97, 118, 97, 47, 117, 116, 105, 108, 47, 76, 105, 115, 116, 59, 120, 112, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 101, 116, 0, 5, 112, 101, 116, 101, 114, 115, 114, 0, 19, 106, 97, 118, 97, 46, 117, 116, 105, 108, 46, 65, 114, 114, 97, 121, 76, 105, 115, 116, 120, -127, -46, 29, -103, -57, 97, -99, 3, 0, 1, 73, 0, 4, 115, 105, 122, 101, 120, 112, 0, 0, 0, 1, 119, 4, 0, 0, 0, 1, 115, 114, 0, 17, 106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 73, 110, 116, 101, 103, 101, 114, 18, -30, -96, -92, -9, -127, -121, 56, 2, 0, 1, 73, 0, 5, 118, 97, 108, 117, 101, 120, 114, 0, 16, 106, 97, 118, 97, 46, 108, 97, 110, 103, 46, 78, 117, 109, 98, 101, 114, -122, -84, -107, 29, 11, -108, -32, -117, 2, 0, 0, 120, 112, 0, 0, 3, -23, 120]
        toPlayer(bytes);
    }

    /**
     * 序列化
     * 
     * @throws IOException
     */
    public static byte[] toBytes() throws IOException {

        Player player = new Player(101, 20, "peter");
        player.getSkills().add(1001);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        // 写入对象
        objectOutputStream.writeObject(player);

        // 获取字节数组
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        System.out.println(Arrays.toString(byteArray));
        return byteArray;
    }

    /**
     * 反序列化
     * 
     * @param bs
     * @throws Exception
     */
    public static void toPlayer(byte[] bs) throws Exception {

        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(bs));
        Player player = (Player) inputStream.readObject();

        // 打印
        System.out.println("playerId:" + player.getPlayerId());
        System.out.println("age:" + player.getAge());
        System.out.println("name:" + player.getName());
        System.out.println("skills:" + (Arrays.toString(player.getSkills().toArray())));
    }
}
