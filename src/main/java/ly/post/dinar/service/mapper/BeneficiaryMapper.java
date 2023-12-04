package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.BankBranch;
import ly.post.dinar.domain.Beneficiary;
import ly.post.dinar.domain.WalletUser;
import ly.post.dinar.service.dto.BankBranchDTO;
import ly.post.dinar.service.dto.BeneficiaryDTO;
import ly.post.dinar.service.dto.WalletUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Beneficiary} and its DTO {@link BeneficiaryDTO}.
 */
@Mapper(componentModel = "spring")
public interface BeneficiaryMapper extends EntityMapper<BeneficiaryDTO, Beneficiary> {
    @Mapping(target = "bankBranch", source = "bankBranch", qualifiedByName = "bankBranchNameAr")
    @Mapping(target = "walletUser", source = "walletUser", qualifiedByName = "walletUserId")
    BeneficiaryDTO toDto(Beneficiary s);

    @Named("bankBranchNameAr")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nameAr", source = "nameAr")
    BankBranchDTO toDtoBankBranchNameAr(BankBranch bankBranch);

    @Named("walletUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    WalletUserDTO toDtoWalletUserId(WalletUser walletUser);
}
