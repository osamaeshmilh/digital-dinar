package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.Voucher;
import ly.post.dinar.domain.VoucherCompany;
import ly.post.dinar.domain.VoucherType;
import ly.post.dinar.service.dto.VoucherCompanyDTO;
import ly.post.dinar.service.dto.VoucherDTO;
import ly.post.dinar.service.dto.VoucherTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Voucher} and its DTO {@link VoucherDTO}.
 */
@Mapper(componentModel = "spring")
public interface VoucherMapper extends EntityMapper<VoucherDTO, Voucher> {
    @Mapping(target = "voucherType", source = "voucherType", qualifiedByName = "voucherTypeNameAr")
    @Mapping(target = "voucherCompany", source = "voucherCompany", qualifiedByName = "voucherCompanyNameAr")
    VoucherDTO toDto(Voucher s);

    @Named("voucherTypeNameAr")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nameAr", source = "nameAr")
    VoucherTypeDTO toDtoVoucherTypeNameAr(VoucherType voucherType);

    @Named("voucherCompanyNameAr")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nameAr", source = "nameAr")
    VoucherCompanyDTO toDtoVoucherCompanyNameAr(VoucherCompany voucherCompany);
}
