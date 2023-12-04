package ly.post.dinar.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.post.dinar.domain.ViewLog} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ViewLogDTO implements Serializable {

    private Long id;

    private String entityName;

    private Long entityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewLogDTO)) {
            return false;
        }

        ViewLogDTO viewLogDTO = (ViewLogDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, viewLogDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ViewLogDTO{" +
            "id=" + getId() +
            ", entityName='" + getEntityName() + "'" +
            ", entityId=" + getEntityId() +
            "}";
    }
}
