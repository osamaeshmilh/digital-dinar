package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.WalletProfile;
import ly.post.dinar.service.dto.WalletProfileDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WalletProfile} and its DTO {@link WalletProfileDTO}.
 */
@Mapper(componentModel = "spring")
public interface WalletProfileMapper extends EntityMapper<WalletProfileDTO, WalletProfile> {}
