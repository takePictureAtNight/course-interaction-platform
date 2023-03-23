package dto;
public class UploadDTO {
    private String resourceUrl;
    private String originalFilename;

    @Override
    public String toString() {
        return "UploadDTO{" +
                "resourceUrl='" + resourceUrl + '\'' +
                ", originalFilename='" + originalFilename + '\'' +
                '}';
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }
}
