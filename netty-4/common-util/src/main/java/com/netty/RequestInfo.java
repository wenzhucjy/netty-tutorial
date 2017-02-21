package com.netty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * description: 序列化对象 RequestInfo
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-12-02 16:59
 */
public class RequestInfo implements Serializable {

    private String ip;
    private Map<String, Object> cpuPercMap = new HashMap<>();
    private Map<String, Object> memoryMap = new HashMap<>();

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Map<String, Object> getCpuPercMap() {
        return cpuPercMap;
    }

    public void setCpuPercMap(Map<String, Object> cpuPercMap) {
        this.cpuPercMap = cpuPercMap;
    }

    public Map<String, Object> getMemoryMap() {
        return memoryMap;
    }

    public void setMemoryMap(Map<String, Object> memoryMap) {
        this.memoryMap = memoryMap;
    }
}
