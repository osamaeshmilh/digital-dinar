package ly.post.dinar.domain;

import java.io.Serializable;
import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Country.
 */
@Table("country")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Country extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("name_ar")
    private String nameAr;

    @Column("name_en")
    private String nameEn;

    @Column("iso_2")
    private String iso2;

    @Column("iso_3")
    private String iso3;

    @Column("iso_no")
    private Integer isoNo;

    // Inherited createdBy definition
    // Inherited createdDate definition
    // Inherited lastModifiedBy definition
    // Inherited lastModifiedDate definition

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Country id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAr() {
        return this.nameAr;
    }

    public Country nameAr(String nameAr) {
        this.setNameAr(nameAr);
        return this;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public Country nameEn(String nameEn) {
        this.setNameEn(nameEn);
        return this;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getIso2() {
        return this.iso2;
    }

    public Country iso2(String iso2) {
        this.setIso2(iso2);
        return this;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return this.iso3;
    }

    public Country iso3(String iso3) {
        this.setIso3(iso3);
        return this;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public Integer getIsoNo() {
        return this.isoNo;
    }

    public Country isoNo(Integer isoNo) {
        this.setIsoNo(isoNo);
        return this;
    }

    public void setIsoNo(Integer isoNo) {
        this.isoNo = isoNo;
    }

    // Inherited createdBy methods
    public Country createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    // Inherited createdDate methods
    public Country createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    // Inherited lastModifiedBy methods
    public Country lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    // Inherited lastModifiedDate methods
    public Country lastModifiedDate(Instant lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Country)) {
            return false;
        }
        return getId() != null && getId().equals(((Country) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Country{" +
            "id=" + getId() +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", iso2='" + getIso2() + "'" +
            ", iso3='" + getIso3() + "'" +
            ", isoNo=" + getIsoNo() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
