package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.VoucherCompany;
import ly.post.dinar.domain.VoucherType;
import ly.post.dinar.service.dto.VoucherCompanyDTO;
import ly.post.dinar.service.dto.VoucherTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link VoucherType} and its DTO {@link VoucherTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface VoucherTypeMapper extends EntityMapper<VoucherTypeDTO, VoucherType> {
    @Mapping(target = "voucherCompany", source = "voucherCompany", qualifiedByName = "voucherCompanyNameAr")
    VoucherTypeDTO toDto(VoucherType s);

    @Named("voucherCompanyNameAr")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nameAr", source = "nameAr")
    VoucherCompanyDTO toDtoVoucherCompanyNameAr(VoucherCompany voucherCompany);
}
