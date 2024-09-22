package org.chobit.mocko.autoconfigure;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;

/**
 * 配置映射类
 *
 * @author robin
 */
@ConfigurationProperties(prefix = "mocko")
public class MockoProperties {

    /**
     * 是否启用Mocko能力
     */
    private boolean enabled = true;

    /**
     * 应用ID
     */
    @Nullable
    private String appId;

    /**
     * 获取mock信息的url
     */
    private String mockUrl = "http://127.0.0.1:1024/api/mock";



    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Nullable
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMockUrl() {
        return mockUrl;
    }

    public void setMockUrl(String mockUrl) {
        this.mockUrl = mockUrl;
    }
}
