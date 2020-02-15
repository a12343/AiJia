package com.aijia.util.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 文件管理配置
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "imms.upload")
public class FileManageProperties implements Serializable {
    private static final long serialVersionUID = -3780139867548492779L;

    /**
     * 上传路径
     */
    private String fileUploadPath = "/upload";

    /**
     * 默认头像
     */
    private String defaultHeadPicture = "/resource/img/default.jpg";

    /**
     * 二维码logo
     */
    private String qrCodeLogoPath;
}
