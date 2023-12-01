package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.Setting;
import ly.post.dinar.service.dto.SettingDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Setting} and its DTO {@link SettingDTO}.
 */
@Mapper(componentModel = "spring")
public interface SettingMapper extends EntityMapper<SettingDTO, Setting> {}
