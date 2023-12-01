package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.PaymentMethod;
import ly.post.dinar.service.dto.PaymentMethodDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PaymentMethod} and its DTO {@link PaymentMethodDTO}.
 */
@Mapper(componentModel = "spring")
public interface PaymentMethodMapper extends EntityMapper<PaymentMethodDTO, PaymentMethod> {}
