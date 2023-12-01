package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.ViewLog;
import ly.post.dinar.service.dto.ViewLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ViewLog} and its DTO {@link ViewLogDTO}.
 */
@Mapper(componentModel = "spring")
public interface ViewLogMapper extends EntityMapper<ViewLogDTO, ViewLog> {}
