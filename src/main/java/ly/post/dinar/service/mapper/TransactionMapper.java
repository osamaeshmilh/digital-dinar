package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.Transaction;
import ly.post.dinar.domain.WalletUser;
import ly.post.dinar.service.dto.TransactionDTO;
import ly.post.dinar.service.dto.WalletUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Transaction} and its DTO {@link TransactionDTO}.
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper extends EntityMapper<TransactionDTO, Transaction> {
    @Mapping(target = "sender", source = "sender", qualifiedByName = "walletUserId")
    @Mapping(target = "receiver", source = "receiver", qualifiedByName = "walletUserId")
    TransactionDTO toDto(Transaction s);

    @Named("walletUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    WalletUserDTO toDtoWalletUserId(WalletUser walletUser);
}
