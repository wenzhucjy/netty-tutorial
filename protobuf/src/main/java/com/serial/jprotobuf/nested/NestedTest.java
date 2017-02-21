package com.serial.jprotobuf.nested;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufIDLProxy;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.google.protobuf.ByteString;
import com.serial.jprotobuf.EnumAttrPOJO;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description: 嵌套示例测试
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-12-06 16:03
 */
public class NestedTest {

    public static void main(String[] args) throws Exception {
        testDemo1();
        //testDemo2();
        testDemo3();
    }

    public static void testDemo1() throws Exception {
        Person person = new Person();
        person.id = 1;
        person.name = "name";
        person.bytesF = new byte[]{1, 2};
        person.doubleF = 1.1;
        person.floatF = 0.11f;
        person.email = "email";
        person.boolF = false;
        person.enumAttr = EnumAttrPOJO.STRING;

        Person person1 = new Person();
        person1.id = 2;
        person1.name = "name2";
        person1.bytesF = new byte[]{3, 4};
        person1.doubleF = 2.2;
        person1.floatF = 0.22f;
        person1.email = "email2";
        person1.boolF = true;
        person1.enumAttr = EnumAttrPOJO.STRING;

        List<Person> persons = new ArrayList<>();
        persons.add(person);
        persons.add(person1);

        AddressBookProtosPOJO pojo = new AddressBookProtosPOJO();
        pojo.person = person;
        pojo.persons = persons;

        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        pojo.stringList = stringList;

        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        pojo.intList = intList;

        //Codec<AddressBookProtosPOJO> simpleTypeCodec = ProtobufProxy.create(AddressBookProtosPOJO.class);
        // ProtobufProxy增加生成中间编译java字节码文件功能
        Codec<AddressBookProtosPOJO> simpleTypeCodec = ProtobufProxy.create(AddressBookProtosPOJO.class, false, new File("D:/"));
        byte[] encode = simpleTypeCodec.encode(pojo);

        System.out.println(Arrays.toString(encode));
        //返回的内容即为 Protobuf的IDL描述文件
        //String code = ProtobufIDLGenerator.getIDL(AddressBookProtosPOJO.class);
        //System.out.println(code);
        // 反序列化
        //AddressBookProtosPOJO pojoDecode = simpleTypeCodec.decode(encode);
        //System.out.println("pojoDecode : " + pojoDecode);

    }

    /**
     * ProtobufIDLProxy增加从proto文件到jprotobuf POJO源代码生成功能
     * @throws Exception 异常
     */
    public static void testDemo2() throws Exception {
        InputStream fis = new FileInputStream(new File(
                "O:\\Program\\netty-tutorial\\protobuf\\src\\main\\resources\\nested_demo.proto"));
        ProtobufIDLProxy.generateSource(fis, new File("D:\\test"));
    }

    public static void testDemo3() throws Exception {

        NestedDemo.Person.Builder person = NestedDemo.Person.newBuilder()
                .setName("name")
                .setId(1)
                .setAttrPojo(NestedDemo.Person.EnumAttrPOJO.STRING)
                .setBoolF(false)
                .setFloatF(0.11f)
                .setDoubleF(1.1)
                .setBytesF(ByteString.copyFrom(new byte[]{1, 2}))
                .setEmail("email");

        NestedDemo.Person.Builder person1 = NestedDemo.Person.newBuilder()
                .setName("name2")
                .setId(2)
                .setAttrPojo(NestedDemo.Person.EnumAttrPOJO.STRING)
                .setBoolF(true)
                .setFloatF(0.22f)
                .setDoubleF(2.2)
                .setBytesF(ByteString.copyFrom(new byte[]{3,4}))
                .setEmail("email2");

        NestedDemo.AddressBookProtosPOJO pojo = NestedDemo.AddressBookProtosPOJO.newBuilder()
                .setPerson(person)
                .addPersons(person)
                .addPersons(person1)
                .addIntList(1).addIntList(2).addStringList("a").addStringList("b").build();

        System.out.println(Arrays.toString(pojo.toByteArray()));

    }

}
