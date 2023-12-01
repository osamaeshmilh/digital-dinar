package ly.post.dinar.service.dto;

import jakarta.persistence.Lob;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.post.dinar.domain.Slider} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SliderDTO implements Serializable {

    private Long id;

    private String details;

    private Integer menuOrder;

    private String imageFileUrl;

    @Lob
    private byte[] imageFile;

    private String imageFileContentType;
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getImageFileUrl() {
        return imageFileUrl;
    }

    public void setImageFileUrl(String imageFileUrl) {
        this.imageFileUrl = imageFileUrl;
    }

    public byte[] getImageFile() {
        return imageFile;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageFileContentType() {
        return imageFileContentType;
    }

    public void setImageFileContentType(String imageFileContentType) {
        this.imageFileContentType = imageFileContentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SliderDTO)) {
            return false;
        }

        SliderDTO sliderDTO = (SliderDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sliderDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SliderDTO{" +
            "id=" + getId() +
            ", details='" + getDetails() + "'" +
            ", menuOrder=" + getMenuOrder() +
            ", imageFileUrl='" + getImageFileUrl() + "'" +
            ", imageFile='" + getImageFile() + "'" +
            ", url='" + getUrl() + "'" +
            "}";
    }
}
