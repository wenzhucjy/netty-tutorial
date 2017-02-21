package com.serial.jprotobuf;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;

import java.io.IOException;
import java.util.Arrays;

/**
 * description: jprotobuf 测试
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-12-06 14:46
 */
public class JProtobufTest {

    public static void main(String[] args) throws Exception {
        testDemo1();
        testDemo2();
    }

    public static void testDemo2() throws Exception {
        SimpleType2.SimpleType build = SimpleType2.SimpleType.newBuilder().setName("abc").setValue(100)
                .setAttrPojo(SimpleType2.SimpleType.EnumAttrPOJO.STRING)
                .build();
        System.out.println(Arrays.toString(build.toByteArray()));
        SimpleType2.SimpleType nSimpleType = SimpleType2.SimpleType.parseFrom(build.toByteArray());
        System.out.println("name:" + nSimpleType.getName());
        System.out.println("value:" + nSimpleType.getValue());
        System.out.println("enum:"+nSimpleType.getAttrPojo());

    }

    public static void testDemo1() {
        Codec<SimpleType> simpleTypeCodec = ProtobufProxy.create(SimpleType.class);

        SimpleType stt = new SimpleType();
        stt.setName("abc");
        stt.setValue(100);
        stt.setEnumAttr(EnumAttrPOJO.STRING);
        try {
            // 序列化
            byte[] bb = simpleTypeCodec.encode(stt);
            System.out.println(Arrays.toString(bb));
            // 反序列化
            SimpleType newStt = simpleTypeCodec.decode(bb);
            System.out.println("newStt : " + newStt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
