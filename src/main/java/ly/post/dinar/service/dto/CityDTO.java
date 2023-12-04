package ly.post.dinar.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.post.dinar.domain.City} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CityDTO implements Serializable {

    private Long id;

    private String nameAr;

    private String nameEn;

    private CountryDTO country;

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

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CityDTO)) {
            return false;
        }

        CityDTO cityDTO = (CityDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cityDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CityDTO{" +
            "id=" + getId() +
            ", nameAr='" + getNameAr() + "'" +
            ", nameEn='" + getNameEn() + "'" +
            ", country=" + getCountry() +
            "}";
    }
}
