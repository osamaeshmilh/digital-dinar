package ly.post.dinar.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.post.dinar.domain.ViewLog} entity. This class is used
 * in {@link ly.post.dinar.web.rest.ViewLogResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /view-logs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ViewLogCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter entityName;

    private LongFilter entityId;

    private Boolean distinct;

    public ViewLogCriteria() {}

    public ViewLogCriteria(ViewLogCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.entityName = other.entityName == null ? null : other.entityName.copy();
        this.entityId = other.entityId == null ? null : other.entityId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ViewLogCriteria copy() {
        return new ViewLogCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getEntityName() {
        return entityName;
    }

    public StringFilter entityName() {
        if (entityName == null) {
            entityName = new StringFilter();
        }
        return entityName;
    }

    public void setEntityName(StringFilter entityName) {
        this.entityName = entityName;
    }

    public LongFilter getEntityId() {
        return entityId;
    }

    public LongFilter entityId() {
        if (entityId == null) {
            entityId = new LongFilter();
        }
        return entityId;
    }

    public void setEntityId(LongFilter entityId) {
        this.entityId = entityId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ViewLogCriteria that = (ViewLogCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(entityName, that.entityName) &&
            Objects.equals(entityId, that.entityId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, entityName, entityId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ViewLogCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (entityName != null ? "entityName=" + entityName + ", " : "") +
            (entityId != null ? "entityId=" + entityId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
