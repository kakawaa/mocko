package org.chobit.mocko;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置映射类
 *
 * @author rui.zhang
 */
@ConfigurationProperties(prefix = "mocko")
public class MockoProperties {

    private String serverHost = "127.0.0.1";

    private int serverPort = 8190;

    private boolean useSsl = false;

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public boolean isUseSsl() {
        return useSsl;
    }

    public void setUseSsl(boolean useSsl) {
        this.useSsl = useSsl;
    }
}
