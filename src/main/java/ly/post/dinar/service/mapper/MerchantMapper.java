package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.Category;
import ly.post.dinar.domain.City;
import ly.post.dinar.domain.Merchant;
import ly.post.dinar.domain.User;
import ly.post.dinar.domain.WalletProfile;
import ly.post.dinar.service.dto.CategoryDTO;
import ly.post.dinar.service.dto.CityDTO;
import ly.post.dinar.service.dto.MerchantDTO;
import ly.post.dinar.service.dto.UserDTO;
import ly.post.dinar.service.dto.WalletProfileDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Merchant} and its DTO {@link MerchantDTO}.
 */
@Mapper(componentModel = "spring")
public interface MerchantMapper extends EntityMapper<MerchantDTO, Merchant> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    @Mapping(target = "category", source = "category", qualifiedByName = "categoryNameAr")
    @Mapping(target = "city", source = "city", qualifiedByName = "cityNameAr")
    @Mapping(target = "walletProfile", source = "walletProfile", qualifiedByName = "walletProfileNameAr")
    MerchantDTO toDto(Merchant s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);

    @Named("categoryNameAr")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nameAr", source = "nameAr")
    CategoryDTO toDtoCategoryNameAr(Category category);

    @Named("cityNameAr")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nameAr", source = "nameAr")
    CityDTO toDtoCityNameAr(City city);

    @Named("walletProfileNameAr")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nameAr", source = "nameAr")
    WalletProfileDTO toDtoWalletProfileNameAr(WalletProfile walletProfile);
}
