package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.Consumer;
import ly.post.dinar.domain.User;
import ly.post.dinar.domain.WalletProfile;
import ly.post.dinar.service.dto.ConsumerDTO;
import ly.post.dinar.service.dto.UserDTO;
import ly.post.dinar.service.dto.WalletProfileDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Consumer} and its DTO {@link ConsumerDTO}.
 */
@Mapper(componentModel = "spring")
public interface ConsumerMapper extends EntityMapper<ConsumerDTO, Consumer> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    @Mapping(target = "walletProfile", source = "walletProfile", qualifiedByName = "walletProfileNameAr")
    ConsumerDTO toDto(Consumer s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);

    @Named("walletProfileNameAr")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nameAr", source = "nameAr")
    WalletProfileDTO toDtoWalletProfileNameAr(WalletProfile walletProfile);
}
