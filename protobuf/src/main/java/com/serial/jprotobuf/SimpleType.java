package com.serial.jprotobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.google.common.base.MoreObjects;

/**
 * description: jprotobuf 测试
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-12-06 14:44
 */
public class SimpleType {

    @Protobuf(fieldType = FieldType.STRING, order = 1, required = true)
    private String name;

    @Protobuf(fieldType = FieldType.INT32, order = 2,required = true)
    private int    value;

    @Protobuf(fieldType = FieldType.ENUM, order = 3)
    private EnumAttrPOJO enumAttr;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnumAttrPOJO getEnumAttr() {
        return enumAttr;
    }

    public void setEnumAttr(EnumAttrPOJO enumAttr) {
        this.enumAttr = enumAttr;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("name", name)
                .add("value", value)
                .add("enumAttr", enumAttr)
                .toString();
    }
}
