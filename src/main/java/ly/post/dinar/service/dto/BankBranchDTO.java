package ly.post.dinar.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.post.dinar.domain.BankBranch} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BankBranchDTO implements Serializable {

    private Long id;

    private String nameAr;

    private String namEn;

    private String address;

    private BankDTO bank;

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

    public String getNamEn() {
        return namEn;
    }

    public void setNamEn(String namEn) {
        this.namEn = namEn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BankDTO getBank() {
        return bank;
    }

    public void setBank(BankDTO bank) {
        this.bank = bank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankBranchDTO)) {
            return false;
        }

        BankBranchDTO bankBranchDTO = (BankBranchDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, bankBranchDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BankBranchDTO{" +
            "id=" + getId() +
            ", nameAr='" + getNameAr() + "'" +
            ", namEn='" + getNamEn() + "'" +
            ", address='" + getAddress() + "'" +
            ", bank=" + getBank() +
            "}";
    }
}
