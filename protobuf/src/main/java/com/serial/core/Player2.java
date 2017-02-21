package com.serial.core;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 玩家
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 16:58
 */
public class Player2 extends Serializer {

    public Player2(){

    }

    public Player2(long playerId, int age, String name){
        this.playerId = playerId;
        this.age = age;
        this.name = name;
    }

    private long          playerId;                    // 玩家id

    private int           age;                         // 年龄

    private String        name;                        // 姓名

    private List<Integer> skills   = new ArrayList<>();// 技能

    private Resource      resource = new Resource();   // 金币

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

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

    @Override
    protected void read() {
        this.playerId = readLong();
        this.age = readInt();
        this.name = readString();
        this.skills = readList(Integer.class);
        this.resource = read(Resource.class);
    }

    @Override
    protected void write() {
        writeLong(playerId);
        writeInt(age);
        writeString(name);
        writeList(skills);
        writeObject(resource);
    }

}
