package ly.post.dinar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A VoucherType.
 */
@Entity
@Table(name = "voucher_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VoucherType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_ar")
    private String nameAr;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "slug")
    private String slug;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "menu_order")
    private Integer menuOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "user" }, allowSetters = true)
    private VoucherCompany voucherCompany;

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

    public VoucherCompany getVoucherCompany() {
        return this.voucherCompany;
    }

    public void setVoucherCompany(VoucherCompany voucherCompany) {
        this.voucherCompany = voucherCompany;
    }

    public VoucherType voucherCompany(VoucherCompany voucherCompany) {
        this.setVoucherCompany(voucherCompany);
        return this;
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
            "}";
    }
}
