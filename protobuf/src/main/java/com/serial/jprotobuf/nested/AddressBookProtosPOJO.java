package com.serial.jprotobuf.nested;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * description: 嵌套对象的开发示例
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-12-06 15:47
 */
public class AddressBookProtosPOJO {

    @Protobuf(fieldType = FieldType.OBJECT, order=1)
    public Person person;

    @Protobuf(fieldType = FieldType.OBJECT, order=2)
    public List<Person> persons;

    @Protobuf(fieldType = FieldType.STRING, order=3)
    public List<String> stringList;

    @Protobuf(fieldType = FieldType.INT32, order=4)
    public List<Integer> intList;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("person", person)
                .add("persons", persons)
                .add("stringList", stringList)
                .add("intList", intList)
                .toString();
    }
}
