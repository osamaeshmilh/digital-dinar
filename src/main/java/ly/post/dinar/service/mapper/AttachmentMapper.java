package ly.post.dinar.service.mapper;

import ly.post.dinar.domain.Attachment;
import ly.post.dinar.service.dto.AttachmentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Attachment} and its DTO {@link AttachmentDTO}.
 */
@Mapper(componentModel = "spring")
public interface AttachmentMapper extends EntityMapper<AttachmentDTO, Attachment> {}
