package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.Activation;
import ly.post.dinar.service.dto.ActivationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Activation} and its DTO {@link ActivationDTO}.
 */
@Mapper(componentModel = "spring")
public interface ActivationMapper extends EntityMapper<ActivationDTO, Activation> {}
