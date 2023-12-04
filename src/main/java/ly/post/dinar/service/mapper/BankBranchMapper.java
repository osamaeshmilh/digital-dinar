package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.Bank;
import ly.post.dinar.domain.BankBranch;
import ly.post.dinar.service.dto.BankBranchDTO;
import ly.post.dinar.service.dto.BankDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BankBranch} and its DTO {@link BankBranchDTO}.
 */
@Mapper(componentModel = "spring")
public interface BankBranchMapper extends EntityMapper<BankBranchDTO, BankBranch> {
    @Mapping(target = "bank", source = "bank", qualifiedByName = "bankNameAr")
    BankBranchDTO toDto(BankBranch s);

    @Named("bankNameAr")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nameAr", source = "nameAr")
    BankDTO toDtoBankNameAr(Bank bank);
}
