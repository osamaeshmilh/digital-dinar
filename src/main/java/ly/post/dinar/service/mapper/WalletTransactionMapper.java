package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.Transaction;
import ly.post.dinar.domain.WalletTransaction;
import ly.post.dinar.domain.WalletUser;
import ly.post.dinar.service.dto.TransactionDTO;
import ly.post.dinar.service.dto.WalletTransactionDTO;
import ly.post.dinar.service.dto.WalletUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WalletTransaction} and its DTO {@link WalletTransactionDTO}.
 */
@Mapper(componentModel = "spring")
public interface WalletTransactionMapper extends EntityMapper<WalletTransactionDTO, WalletTransaction> {
    @Mapping(target = "transaction", source = "transaction", qualifiedByName = "transactionId")
    @Mapping(target = "walletUser", source = "walletUser", qualifiedByName = "walletUserId")
    WalletTransactionDTO toDto(WalletTransaction s);

    @Named("transactionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TransactionDTO toDtoTransactionId(Transaction transaction);

    @Named("walletUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    WalletUserDTO toDtoWalletUserId(WalletUser walletUser);
}
