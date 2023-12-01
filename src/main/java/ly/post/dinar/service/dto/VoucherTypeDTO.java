package ly.post.dinar.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.post.dinar.domain.VoucherType} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VoucherTypeDTO implements Serializable {

    private Long id;

    private String nameAr;

    private String nameEn;

    private String slug;

    private Float amount;

    private Integer menuOrder;

    private VoucherCompanyDTO voucherCompany;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
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
        if (!(o instanceof VoucherTypeDTO)) {
            return false;
        }

        VoucherTypeDTO voucherTypeDTO = (VoucherTypeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, voucherTypeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VoucherTypeDTO{" +
            "id=" + getId() +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", slug='" + getSlug() + "'" +
            ", amount=" + getAmount() +
            ", menuOrder=" + getMenuOrder() +
            ", voucherCompany=" + getVoucherCompany() +
            "}";
    }
}
