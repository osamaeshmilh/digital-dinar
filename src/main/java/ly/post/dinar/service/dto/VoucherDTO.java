package ly.post.dinar.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.post.dinar.domain.Voucher} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VoucherDTO implements Serializable {

    private Long id;

    private String code;

    private Float amount;

    private String serialNumber;

    private Boolean isSold;

    private Long sellTransactionId;

    private VoucherTypeDTO voucherType;

    private VoucherCompanyDTO voucherCompany;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Boolean getIsSold() {
        return isSold;
    }

    public void setIsSold(Boolean isSold) {
        this.isSold = isSold;
    }

    public Long getSellTransactionId() {
        return sellTransactionId;
    }

    public void setSellTransactionId(Long sellTransactionId) {
        this.sellTransactionId = sellTransactionId;
    }

    public VoucherTypeDTO getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(VoucherTypeDTO voucherType) {
        this.voucherType = voucherType;
    }

    public VoucherCompanyDTO getVoucherCompany() {
        return voucherCompany;
    }

    public void setVoucherCompany(VoucherCompanyDTO voucherCompany) {
        this.voucherCompany = voucherCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VoucherDTO)) {
            return false;
        }

        VoucherDTO voucherDTO = (VoucherDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, voucherDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VoucherDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", amount=" + getAmount() +
            ", serialNumber='" + getSerialNumber() + "'" +
            ", isSold='" + getIsSold() + "'" +
            ", sellTransactionId=" + getSellTransactionId() +
            ", voucherType=" + getVoucherType() +
            ", voucherCompany=" + getVoucherCompany() +
            "}";
    }
}
