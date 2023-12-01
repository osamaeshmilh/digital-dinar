package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.Beneficiary;
import ly.post.dinar.service.dto.BeneficiaryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Beneficiary} and its DTO {@link BeneficiaryDTO}.
 */
@Mapper(componentModel = "spring")
public interface BeneficiaryMapper extends EntityMapper<BeneficiaryDTO, Beneficiary> {}
