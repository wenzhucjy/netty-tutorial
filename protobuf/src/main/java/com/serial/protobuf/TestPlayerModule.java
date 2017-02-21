package com.serial.protobuf;

import com.google.protobuf.ExtensionRegistry;

import java.util.Arrays;

/**
 * description: 测试 protobuf
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-12-05 15:25
 */
public class TestPlayerModule {

    public static void main(String[] args) throws Exception {

        PlayerModule.PBPlayer player = PlayerModule.PBPlayer.newBuilder().setPlayerId(101).setAge(20).setName("peter").addSkills(1001).build();

        byte[] byteArray = player.toByteArray();

        System.out.println(Arrays.toString(byteArray));

        PlayerModule.PBPlayer builder = PlayerModule.PBPlayer.parseFrom(byteArray);

        System.out.println("playerId:" + builder.getPlayerId());
        System.out.println("age:" + builder.getAge());
        System.out.println("name:" + builder.getName());
        System.out.println("skill:" + builder.getSkillsList());

        PlayerModule.Foo.Builder builder2 = PlayerModule.Foo.newBuilder();
        PlayerModule.Foo foo = builder2.setExtension(PlayerModule.bar, 123).build();

        // -------------- 分割线：下面是接收方，将数据接收后反序列化 ---------------
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(PlayerModule.bar);
        PlayerModule.Foo newFoo = PlayerModule.Foo.parseFrom(foo.toByteArray(), registry);
        Integer result = newFoo.getExtension(PlayerModule.bar);
        System.out.println(result);

        // 先构造一个 message PBResource 类型对象
        PlayerModule.PBResource.Builder builder3 = PlayerModule.PBResource.newBuilder();
        PlayerModule.PBResource resource = builder3.setGold(123).setEnergy(321).build();

        // 再构造 message BaseData 对象，将 PBResource 对象通过 setExtension 设置到 BaseData 中
        PlayerModule.BaseData.Builder builder4 = PlayerModule.BaseData.newBuilder();
        builder4.setCode(456);
        PlayerModule.BaseData baseData = builder4.setExtension(PlayerModule.PBResource.extendData, resource).build();
        // -------------- 分割线：下面是接收方，将数据接收后反序列化 ---------------

        ExtensionRegistry registry2 = ExtensionRegistry.newInstance();

        registry.add(PlayerModule.PBResource.extendData); // 或者 PlayerModule.registerAllExtensions(registry2);

        PlayerModule.BaseData receiveBaseData = PlayerModule.BaseData.parseFrom(baseData.toByteArray(), registry2);
        System.out.println(receiveBaseData.getCode());
        System.out.println(receiveBaseData.getExtension(PlayerModule.PBResource.extendData).getEnergy());
    }
}
