package ly.post.dinar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Voucher.
 */
@Table("voucher")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Voucher extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("code")
    private String code;

    @Column("amount")
    private Float amount;

    @Column("serial_number")
    private String serialNumber;

    @Column("is_sold")
    private Boolean isSold;

    @Column("sell_transaction_id")
    private Long sellTransactionId;

    // Inherited createdBy definition
    // Inherited createdDate definition
    // Inherited lastModifiedBy definition
    // Inherited lastModifiedDate definition

    @Transient
    @JsonIgnoreProperties(value = { "voucherCompany" }, allowSetters = true)
    private VoucherType voucherType;

    @Transient
    @JsonIgnoreProperties(value = { "user" }, allowSetters = true)
    private VoucherCompany voucherCompany;

    @Column("voucher_type_id")
    private Long voucherTypeId;

    @Column("voucher_company_id")
    private Long voucherCompanyId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Voucher id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Voucher code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getAmount() {
        return this.amount;
    }

    public Voucher amount(Float amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public Voucher serialNumber(String serialNumber) {
        this.setSerialNumber(serialNumber);
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Boolean getIsSold() {
        return this.isSold;
    }

    public Voucher isSold(Boolean isSold) {
        this.setIsSold(isSold);
        return this;
    }

    public void setIsSold(Boolean isSold) {
        this.isSold = isSold;
    }

    public Long getSellTransactionId() {
        return this.sellTransactionId;
    }

    public Voucher sellTransactionId(Long sellTransactionId) {
        this.setSellTransactionId(sellTransactionId);
        return this;
    }

    public void setSellTransactionId(Long sellTransactionId) {
        this.sellTransactionId = sellTransactionId;
    }

    // Inherited createdBy methods
    public Voucher createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    // Inherited createdDate methods
    public Voucher createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    // Inherited lastModifiedBy methods
    public Voucher lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    // Inherited lastModifiedDate methods
    public Voucher lastModifiedDate(Instant lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public VoucherType getVoucherType() {
        return this.voucherType;
    }

    public void setVoucherType(VoucherType voucherType) {
        this.voucherType = voucherType;
        this.voucherTypeId = voucherType != null ? voucherType.getId() : null;
    }

    public Voucher voucherType(VoucherType voucherType) {
        this.setVoucherType(voucherType);
        return this;
    }

    public VoucherCompany getVoucherCompany() {
        return this.voucherCompany;
    }

    public void setVoucherCompany(VoucherCompany voucherCompany) {
        this.voucherCompany = voucherCompany;
        this.voucherCompanyId = voucherCompany != null ? voucherCompany.getId() : null;
    }

    public Voucher voucherCompany(VoucherCompany voucherCompany) {
        this.setVoucherCompany(voucherCompany);
        return this;
    }

    public Long getVoucherTypeId() {
        return this.voucherTypeId;
    }

    public void setVoucherTypeId(Long voucherType) {
        this.voucherTypeId = voucherType;
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
        if (!(o instanceof Voucher)) {
            return false;
        }
        return getId() != null && getId().equals(((Voucher) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Voucher{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", amount=" + getAmount() +
            ", serialNumber='" + getSerialNumber() + "'" +
            ", isSold='" + getIsSold() + "'" +
            ", sellTransactionId=" + getSellTransactionId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
