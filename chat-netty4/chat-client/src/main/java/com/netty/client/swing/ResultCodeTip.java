package com.netty.client.swing;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Properties;

/**
 * description: 错误提示
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-28 20:15
 */
@Component
public class ResultCodeTip {

    private Properties properties = new Properties();

    @PostConstruct
    public void init() throws IOException {
        InputStream in = getClass().getResourceAsStream("/code.properties");
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));
        properties.load(bf);
    }

    /**
     * 获取内容提示
     * 
     * @param code
     * @return
     */
    public String getTipContent(int code) {
        Object object = properties.get(code + "");
        return Optional.ofNullable(object).orElse("错误码：" + code).toString();
    }
}
