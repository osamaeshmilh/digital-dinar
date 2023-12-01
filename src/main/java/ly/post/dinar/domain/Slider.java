package ly.post.dinar.domain;

import java.io.Serializable;
import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Slider.
 */
@Table("slider")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Slider extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("details")
    private String details;

    @Column("menu_order")
    private Integer menuOrder;

    @Column("image_file_url")
    private String imageFileUrl;

    @Column("image_file")
    private byte[] imageFile;

    @Column("image_file_content_type")
    private String imageFileContentType;

    @Column("url")
    private String url;

    // Inherited createdBy definition
    // Inherited createdDate definition
    // Inherited lastModifiedBy definition
    // Inherited lastModifiedDate definition

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Slider id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return this.details;
    }

    public Slider details(String details) {
        this.setDetails(details);
        return this;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getMenuOrder() {
        return this.menuOrder;
    }

    public Slider menuOrder(Integer menuOrder) {
        this.setMenuOrder(menuOrder);
        return this;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getImageFileUrl() {
        return this.imageFileUrl;
    }

    public Slider imageFileUrl(String imageFileUrl) {
        this.setImageFileUrl(imageFileUrl);
        return this;
    }

    public void setImageFileUrl(String imageFileUrl) {
        this.imageFileUrl = imageFileUrl;
    }

    public byte[] getImageFile() {
        return this.imageFile;
    }

    public Slider imageFile(byte[] imageFile) {
        this.setImageFile(imageFile);
        return this;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageFileContentType() {
        return this.imageFileContentType;
    }

    public Slider imageFileContentType(String imageFileContentType) {
        this.imageFileContentType = imageFileContentType;
        return this;
    }

    public void setImageFileContentType(String imageFileContentType) {
        this.imageFileContentType = imageFileContentType;
    }

    public String getUrl() {
        return this.url;
    }

    public Slider url(String url) {
        this.setUrl(url);
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // Inherited createdBy methods
    public Slider createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    // Inherited createdDate methods
    public Slider createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    // Inherited lastModifiedBy methods
    public Slider lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    // Inherited lastModifiedDate methods
    public Slider lastModifiedDate(Instant lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Slider)) {
            return false;
        }
        return getId() != null && getId().equals(((Slider) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Slider{" +
            "id=" + getId() +
            ", details='" + getDetails() + "'" +
            ", menuOrder=" + getMenuOrder() +
            ", imageFileUrl='" + getImageFileUrl() + "'" +
            ", imageFile='" + getImageFile() + "'" +
            ", imageFileContentType='" + getImageFileContentType() + "'" +
            ", url='" + getUrl() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
