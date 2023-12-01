package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.Slider;
import ly.post.dinar.service.dto.SliderDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Slider} and its DTO {@link SliderDTO}.
 */
@Mapper(componentModel = "spring")
public interface SliderMapper extends EntityMapper<SliderDTO, Slider> {}
