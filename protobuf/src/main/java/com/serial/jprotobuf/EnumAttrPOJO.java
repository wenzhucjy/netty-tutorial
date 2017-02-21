package com.serial.jprotobuf;

import com.baidu.bjf.remoting.protobuf.EnumReadable;

/**
 * description: 使用枚举类型必须注意，如果枚举类型的值不是使用默认的ordinal的方式，则必须实现EnumReadable接口
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-12-06 15:09
 */
public enum EnumAttrPOJO implements EnumReadable {

    STRING(100), INT(50);

    private int value;

    EnumAttrPOJO(int value) { this.value = value; }

    public int toValue() { return this.value; }

    /* (non-Javadoc)
     * @see com.baidu.bjf.remoting.protobuf.Enumable#value()
     */
    public int value() {
        return toValue();
    }
}
