package com.serial.jprotobuf.nested;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.google.common.base.MoreObjects;
import com.serial.jprotobuf.EnumAttrPOJO;

/**
 * description: 嵌套对象的开发示例
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-12-06 15:45
 */
public class Person {

    @Protobuf(fieldType = FieldType.STRING, order = 1, required = true, description = "名称")
    public String       name;
    @Protobuf(fieldType = FieldType.INT32, order = 2, required = true, description = "id")
    public int          id;
    @Protobuf(fieldType = FieldType.STRING, order = 3, description = "邮件地址")
    public String       email;

    @Protobuf(fieldType = FieldType.DOUBLE, order = 4, description = "测试")
    public Double       doubleF;

    @Protobuf(fieldType = FieldType.FLOAT, order = 5)
    public Float        floatF;

    @Protobuf(fieldType = FieldType.BYTES, order = 6)
    public byte[]       bytesF;

    @Protobuf(fieldType = FieldType.BOOL, order = 7)
    public Boolean      boolF;

    @Protobuf(fieldType = FieldType.ENUM, order = 8)
    public EnumAttrPOJO enumAttr;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("name", name)
                .add("id", id)
                .add("email", email)
                .add("doubleF", doubleF)
                .add("floatF", floatF)
                .add("bytesF", bytesF)
                .add("boolF", boolF)
                .add("enumAttr", enumAttr)
                .toString();
    }
}
