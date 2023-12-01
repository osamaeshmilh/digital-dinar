package ly.post.dinar.domain.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.post.dinar.domain.Slider} entity. This class is used
 * in {@link ly.post.dinar.web.rest.SliderResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sliders?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SliderCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter details;

    private IntegerFilter menuOrder;

    private StringFilter imageFileUrl;

    private StringFilter url;

    private StringFilter createdBy;

    private InstantFilter createdDate;

    private StringFilter lastModifiedBy;

    private InstantFilter lastModifiedDate;

    private Boolean distinct;

    public SliderCriteria() {}

    public SliderCriteria(SliderCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.details = other.details == null ? null : other.details.copy();
        this.menuOrder = other.menuOrder == null ? null : other.menuOrder.copy();
        this.imageFileUrl = other.imageFileUrl == null ? null : other.imageFileUrl.copy();
        this.url = other.url == null ? null : other.url.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
        this.lastModifiedBy = other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
        this.lastModifiedDate = other.lastModifiedDate == null ? null : other.lastModifiedDate.copy();
        this.distinct = other.distinct;
    }

    @Override
    public SliderCriteria copy() {
        return new SliderCriteria(this);
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

    public StringFilter getDetails() {
        return details;
    }

    public StringFilter details() {
        if (details == null) {
            details = new StringFilter();
        }
        return details;
    }

    public void setDetails(StringFilter details) {
        this.details = details;
    }

    public IntegerFilter getMenuOrder() {
        return menuOrder;
    }

    public IntegerFilter menuOrder() {
        if (menuOrder == null) {
            menuOrder = new IntegerFilter();
        }
        return menuOrder;
    }

    public void setMenuOrder(IntegerFilter menuOrder) {
        this.menuOrder = menuOrder;
    }

    public StringFilter getImageFileUrl() {
        return imageFileUrl;
    }

    public StringFilter imageFileUrl() {
        if (imageFileUrl == null) {
            imageFileUrl = new StringFilter();
        }
        return imageFileUrl;
    }

    public void setImageFileUrl(StringFilter imageFileUrl) {
        this.imageFileUrl = imageFileUrl;
    }

    public StringFilter getUrl() {
        return url;
    }

    public StringFilter url() {
        if (url == null) {
            url = new StringFilter();
        }
        return url;
    }

    public void setUrl(StringFilter url) {
        this.url = url;
    }

    public StringFilter getCreatedBy() {
        return createdBy;
    }

    public StringFilter createdBy() {
        if (createdBy == null) {
            createdBy = new StringFilter();
        }
        return createdBy;
    }

    public void setCreatedBy(StringFilter createdBy) {
        this.createdBy = createdBy;
    }

    public InstantFilter getCreatedDate() {
        return createdDate;
    }

    public InstantFilter createdDate() {
        if (createdDate == null) {
            createdDate = new InstantFilter();
        }
        return createdDate;
    }

    public void setCreatedDate(InstantFilter createdDate) {
        this.createdDate = createdDate;
    }

    public StringFilter getLastModifiedBy() {
        return lastModifiedBy;
    }

    public StringFilter lastModifiedBy() {
        if (lastModifiedBy == null) {
            lastModifiedBy = new StringFilter();
        }
        return lastModifiedBy;
    }

    public void setLastModifiedBy(StringFilter lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public InstantFilter getLastModifiedDate() {
        return lastModifiedDate;
    }

    public InstantFilter lastModifiedDate() {
        if (lastModifiedDate == null) {
            lastModifiedDate = new InstantFilter();
        }
        return lastModifiedDate;
    }

    public void setLastModifiedDate(InstantFilter lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
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
        final SliderCriteria that = (SliderCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(details, that.details) &&
            Objects.equals(menuOrder, that.menuOrder) &&
            Objects.equals(imageFileUrl, that.imageFileUrl) &&
            Objects.equals(url, that.url) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
            Objects.equals(lastModifiedDate, that.lastModifiedDate) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, details, menuOrder, imageFileUrl, url, createdBy, createdDate, lastModifiedBy, lastModifiedDate, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SliderCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (details != null ? "details=" + details + ", " : "") +
            (menuOrder != null ? "menuOrder=" + menuOrder + ", " : "") +
            (imageFileUrl != null ? "imageFileUrl=" + imageFileUrl + ", " : "") +
            (url != null ? "url=" + url + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (lastModifiedDate != null ? "lastModifiedDate=" + lastModifiedDate + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
