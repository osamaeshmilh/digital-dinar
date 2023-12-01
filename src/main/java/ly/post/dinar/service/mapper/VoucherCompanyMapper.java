package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.User;
import ly.post.dinar.domain.VoucherCompany;
import ly.post.dinar.service.dto.UserDTO;
import ly.post.dinar.service.dto.VoucherCompanyDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link VoucherCompany} and its DTO {@link VoucherCompanyDTO}.
 */
@Mapper(componentModel = "spring")
public interface VoucherCompanyMapper extends EntityMapper<VoucherCompanyDTO, VoucherCompany> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    VoucherCompanyDTO toDto(VoucherCompany s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);
}
