package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.Bank;
import ly.post.dinar.service.dto.BankDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Bank} and its DTO {@link BankDTO}.
 */
@Mapper(componentModel = "spring")
public interface BankMapper extends EntityMapper<BankDTO, Bank> {}
