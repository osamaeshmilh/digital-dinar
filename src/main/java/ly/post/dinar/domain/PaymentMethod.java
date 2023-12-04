package ly.post.dinar.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import ly.post.dinar.domain.enumeration.PaymentType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PaymentMethod.
 */
@Entity
@Table(name = "payment_method")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PaymentMethod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_ar")
    private String nameAr;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "menu_order")
    private Integer menuOrder;

    @Column(name = "image_file_url")
    private String imageFileUrl;

    @Lob
    @Column(name = "image_file")
    private byte[] imageFile;

    @Column(name = "image_file_content_type")
    private String imageFileContentType;

    @Column(name = "details")
    private String details;

    @Column(name = "fee_percentage")
    private Float feePercentage;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "notes")
    private String notes;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PaymentMethod id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public PaymentMethod name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAr() {
        return this.nameAr;
    }

    public PaymentMethod nameAr(String nameAr) {
        this.setNameAr(nameAr);
        return this;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public PaymentMethod nameEn(String nameEn) {
        this.setNameEn(nameEn);
        return this;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Integer getMenuOrder() {
        return this.menuOrder;
    }

    public PaymentMethod menuOrder(Integer menuOrder) {
        this.setMenuOrder(menuOrder);
        return this;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getImageFileUrl() {
        return this.imageFileUrl;
    }

    public PaymentMethod imageFileUrl(String imageFileUrl) {
        this.setImageFileUrl(imageFileUrl);
        return this;
    }

    public void setImageFileUrl(String imageFileUrl) {
        this.imageFileUrl = imageFileUrl;
    }

    public byte[] getImageFile() {
        return this.imageFile;
    }

    public PaymentMethod imageFile(byte[] imageFile) {
        this.setImageFile(imageFile);
        return this;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageFileContentType() {
        return this.imageFileContentType;
    }

    public PaymentMethod imageFileContentType(String imageFileContentType) {
        this.imageFileContentType = imageFileContentType;
        return this;
    }

    public void setImageFileContentType(String imageFileContentType) {
        this.imageFileContentType = imageFileContentType;
    }

    public String getDetails() {
        return this.details;
    }

    public PaymentMethod details(String details) {
        this.setDetails(details);
        return this;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Float getFeePercentage() {
        return this.feePercentage;
    }

    public PaymentMethod feePercentage(Float feePercentage) {
        this.setFeePercentage(feePercentage);
        return this;
    }

    public void setFeePercentage(Float feePercentage) {
        this.feePercentage = feePercentage;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public PaymentMethod paymentType(PaymentType paymentType) {
        this.setPaymentType(paymentType);
        return this;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getNotes() {
        return this.notes;
    }

    public PaymentMethod notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentMethod)) {
            return false;
        }
        return getId() != null && getId().equals(((PaymentMethod) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentMethod{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", menuOrder=" + getMenuOrder() +
            ", imageFileUrl='" + getImageFileUrl() + "'" +
            ", imageFile='" + getImageFile() + "'" +
            ", imageFileContentType='" + getImageFileContentType() + "'" +
            ", details='" + getDetails() + "'" +
            ", feePercentage=" + getFeePercentage() +
            ", paymentType='" + getPaymentType() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
