package com.serial.protobuf;

import com.baidu.bjf.remoting.protobuf.IDLProxyObject;
import com.baidu.bjf.remoting.protobuf.ProtobufIDLProxy;

/**
 * description: jprotobuf测试
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-12-06 13:42
 */
public class JProtobufTest {

    public static void main(String[] args) throws Exception {
        // 通过 .proto描述文件生成动态解析对象
        String protoCotent = "package mypackage.test; " +
                "option java_package = \"com.baidu.bjf.remoting.protobuf.simplestring\";" +
                "option java_outer_classname = \"StringTypeClass\";  " +
                "message StringMessage { " +
                "  required string message = 1; }" ;

        IDLProxyObject object = ProtobufIDLProxy.createSingle(protoCotent);
        //if .proto IDL defines multiple messages use as follow
        //Map<String, IDLProxyObject> objects = ProtobufIDLProxy.create(protoCotent);
        // 动态设置字段值
        object.put("message", "hello你好");
        // protobuf 序列化
        byte[] bb = object.encode();

        // protobuf 反序列化
        IDLProxyObject result = object.decode(bb);
        System.out.println(result.get("message"));
    }
}
