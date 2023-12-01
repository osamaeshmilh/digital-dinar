package ly.post.dinar.service.dto;

import jakarta.persistence.Lob;
import java.io.Serializable;
import java.util.Objects;
import ly.post.dinar.domain.enumeration.PaymentType;

/**
 * A DTO for the {@link ly.post.dinar.domain.PaymentMethod} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PaymentMethodDTO implements Serializable {

    private Long id;

    private String name;

    private String nameAr;

    private String nameEn;

    private Integer menuOrder;

    private String imageFileUrl;

    @Lob
    private byte[] imageFile;

    private String imageFileContentType;
    private String details;

    private Float feePercentage;

    private PaymentType paymentType;

    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Float getFeePercentage() {
        return feePercentage;
    }

    public void setFeePercentage(Float feePercentage) {
        this.feePercentage = feePercentage;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentMethodDTO)) {
            return false;
        }

        PaymentMethodDTO paymentMethodDTO = (PaymentMethodDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, paymentMethodDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentMethodDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", menuOrder=" + getMenuOrder() +
            ", imageFileUrl='" + getImageFileUrl() + "'" +
            ", imageFile='" + getImageFile() + "'" +
            ", details='" + getDetails() + "'" +
            ", feePercentage=" + getFeePercentage() +
            ", paymentType='" + getPaymentType() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
