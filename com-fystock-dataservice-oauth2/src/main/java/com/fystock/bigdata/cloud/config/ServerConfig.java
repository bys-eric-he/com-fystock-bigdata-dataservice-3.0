package com.fystock.bigdata.cloud.config;


import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取项目的IP和端口
 *
 * @author He.Yong
 * @since 2021-04-21 14:35:08
 */
@Component
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {
    public int getServerPort() {
        return serverPort;
    }

    private int serverPort;

    /**
     * 获取当前应用Url
     *
     * @return
     */
    public String getUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
            return "http://" + address.getHostAddress() + ":" + this.serverPort;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前应用主机地址
     *
     * @return
     */
    public String getHost() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前应用端口号
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        serverPort = event.getWebServer().getPort();
    }
}