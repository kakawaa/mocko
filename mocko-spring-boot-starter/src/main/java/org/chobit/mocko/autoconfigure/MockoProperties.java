package org.chobit.mocko.autoconfigure;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置映射类
 *
 * @author rui.zhang
 */
@ConfigurationProperties(prefix = "mocko")
public class MockoProperties {

    private boolean enabled = true;

    private String mockUrl = "http://127.0.0.1:1024/api/mock";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getMockUrl() {
        return mockUrl;
    }

    public void setMockUrl(String mockUrl) {
        this.mockUrl = mockUrl;
    }
}
