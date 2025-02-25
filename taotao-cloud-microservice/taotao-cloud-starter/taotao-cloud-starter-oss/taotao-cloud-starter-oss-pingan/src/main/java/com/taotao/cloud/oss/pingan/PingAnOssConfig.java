package com.taotao.cloud.oss.pingan;


import com.taotao.cloud.oss.common.model.SliceConfig;
import com.taotao.cloud.oss.common.util.OssPathUtil;

/**
 * 平安oss配置
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:42:01
 */
public class PingAnOssConfig {

    private String userAgent;
    private String obsUrl;
    private String obsAccessKey;
    private String obsSecret;

    private String basePath;
    private String bucketName;

    private Boolean representPathInKey = false;
    private String domainName;

    /**
     * 断点续传参数
     */
    private SliceConfig sliceConfig = new SliceConfig();

    public void init() {
        this.sliceConfig.init();
        basePath = OssPathUtil.valid(basePath);
    }

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getObsUrl() {
		return obsUrl;
	}

	public void setObsUrl(String obsUrl) {
		this.obsUrl = obsUrl;
	}

	public String getObsAccessKey() {
		return obsAccessKey;
	}

	public void setObsAccessKey(String obsAccessKey) {
		this.obsAccessKey = obsAccessKey;
	}

	public String getObsSecret() {
		return obsSecret;
	}

	public void setObsSecret(String obsSecret) {
		this.obsSecret = obsSecret;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public Boolean getRepresentPathInKey() {
		return representPathInKey;
	}

	public void setRepresentPathInKey(Boolean representPathInKey) {
		this.representPathInKey = representPathInKey;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public SliceConfig getSliceConfig() {
		return sliceConfig;
	}

	public void setSliceConfig(SliceConfig sliceConfig) {
		this.sliceConfig = sliceConfig;
	}
}
