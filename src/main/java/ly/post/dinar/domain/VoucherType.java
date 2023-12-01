package ly.post.dinar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A VoucherType.
 */
@Table("voucher_type")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VoucherType extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("name_ar")
    private String nameAr;

    @Column("name_en")
    private String nameEn;

    @Column("slug")
    private String slug;

    @Column("amount")
    private Float amount;

    @Column("menu_order")
    private Integer menuOrder;

    // Inherited createdBy definition
    // Inherited createdDate definition
    // Inherited lastModifiedBy definition
    // Inherited lastModifiedDate definition

    @Transient
    @JsonIgnoreProperties(value = { "user" }, allowSetters = true)
    private VoucherCompany voucherCompany;

    @Column("voucher_company_id")
    private Long voucherCompanyId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public VoucherType id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAr() {
        return this.nameAr;
    }

    public VoucherType nameAr(String nameAr) {
        this.setNameAr(nameAr);
        return this;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public VoucherType nameEn(String nameEn) {
        this.setNameEn(nameEn);
        return this;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getSlug() {
        return this.slug;
    }

    public VoucherType slug(String slug) {
        this.setSlug(slug);
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Float getAmount() {
        return this.amount;
    }

    public VoucherType amount(Float amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getMenuOrder() {
        return this.menuOrder;
    }

    public VoucherType menuOrder(Integer menuOrder) {
        this.setMenuOrder(menuOrder);
        return this;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    // Inherited createdBy methods
    public VoucherType createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    // Inherited createdDate methods
    public VoucherType createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    // Inherited lastModifiedBy methods
    public VoucherType lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    // Inherited lastModifiedDate methods
    public VoucherType lastModifiedDate(Instant lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public VoucherCompany getVoucherCompany() {
        return this.voucherCompany;
    }

    public void setVoucherCompany(VoucherCompany voucherCompany) {
        this.voucherCompany = voucherCompany;
        this.voucherCompanyId = voucherCompany != null ? voucherCompany.getId() : null;
    }

    public VoucherType voucherCompany(VoucherCompany voucherCompany) {
        this.setVoucherCompany(voucherCompany);
        return this;
    }

    public Long getVoucherCompanyId() {
        return this.voucherCompanyId;
    }

    public void setVoucherCompanyId(Long voucherCompany) {
        this.voucherCompanyId = voucherCompany;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VoucherType)) {
            return false;
        }
        return getId() != null && getId().equals(((VoucherType) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VoucherType{" +
            "id=" + getId() +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", slug='" + getSlug() + "'" +
            ", amount=" + getAmount() +
            ", menuOrder=" + getMenuOrder() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
