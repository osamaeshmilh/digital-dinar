package ly.post.dinar.domain;

import java.io.Serializable;
import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A City.
 */
@Table("city")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class City extends AbstractAuditingEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("name_ar")
    private String nameAr;

    @Column("name_en")
    private String nameEn;

    // Inherited createdBy definition
    // Inherited createdDate definition
    // Inherited lastModifiedBy definition
    // Inherited lastModifiedDate definition

    @Transient
    private Country country;

    @Column("country_id")
    private Long countryId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public City id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAr() {
        return this.nameAr;
    }

    public City nameAr(String nameAr) {
        this.setNameAr(nameAr);
        return this;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public City nameEn(String nameEn) {
        this.setNameEn(nameEn);
        return this;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    // Inherited createdBy methods
    public City createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    // Inherited createdDate methods
    public City createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    // Inherited lastModifiedBy methods
    public City lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    // Inherited lastModifiedDate methods
    public City lastModifiedDate(Instant lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
        this.countryId = country != null ? country.getId() : null;
    }

    public City country(Country country) {
        this.setCountry(country);
        return this;
    }

    public Long getCountryId() {
        return this.countryId;
    }

    public void setCountryId(Long country) {
        this.countryId = country;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof City)) {
            return false;
        }
        return getId() != null && getId().equals(((City) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "City{" +
            "id=" + getId() +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
