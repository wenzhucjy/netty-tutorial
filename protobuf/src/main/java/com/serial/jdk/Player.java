package com.serial.jdk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * description: 测试序列化对象
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 16:14
 */
public class Player implements Serializable {

    private static final long serialVersionUID = -5243055464631225344L;

    public Player(long playerId, int age, String name){
        this.playerId = playerId;
        this.age = age;
        this.name = name;
    }

    private long          playerId;                  // 玩家id

    private int           age;                       // 年龄

    private String        name;                      // 姓名

    private List<Integer> skills = new ArrayList<>();// 技能

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getSkills() {
        return skills;
    }

    public void setSkills(List<Integer> skills) {
        this.skills = skills;
    }
}
