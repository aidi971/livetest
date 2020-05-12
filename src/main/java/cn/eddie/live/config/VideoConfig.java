package cn.eddie.live.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "video")
public class VideoConfig {
    private String filepath;
    private String materialFilePath;

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getMaterialFilePath() {
        return materialFilePath;
    }

    public void setMaterialFilePath(String materialFilePath) {
        this.materialFilePath = materialFilePath;
    }
}
