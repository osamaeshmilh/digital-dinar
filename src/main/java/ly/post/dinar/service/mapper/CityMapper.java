package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.City;
import ly.post.dinar.domain.Country;
import ly.post.dinar.service.dto.CityDTO;
import ly.post.dinar.service.dto.CountryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link City} and its DTO {@link CityDTO}.
 */
@Mapper(componentModel = "spring")
public interface CityMapper extends EntityMapper<CityDTO, City> {
    @Mapping(target = "country", source = "country", qualifiedByName = "countryNameAr")
    CityDTO toDto(City s);

    @Named("countryNameAr")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nameAr", source = "nameAr")
    CountryDTO toDtoCountryNameAr(Country country);
}
