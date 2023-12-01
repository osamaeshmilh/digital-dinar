package ly.post.dinar.repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.util.ArrayList;
import java.util.List;
import ly.post.dinar.domain.Attachment;
import ly.post.dinar.domain.criteria.AttachmentCriteria;
import ly.post.dinar.repository.rowmapper.AttachmentRowMapper;
import ly.post.dinar.repository.rowmapper.ColumnConverter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoin;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.data.relational.repository.support.MappingRelationalEntityInformation;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.service.ConditionBuilder;

/**
 * Spring Data R2DBC custom repository implementation for the Attachment entity.
 */
@SuppressWarnings("unused")
class AttachmentRepositoryInternalImpl extends SimpleR2dbcRepository<Attachment, Long> implements AttachmentRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final AttachmentRowMapper attachmentMapper;
    private final ColumnConverter columnConverter;

    private static final Table entityTable = Table.aliased("attachment", EntityManager.ENTITY_ALIAS);

    public AttachmentRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        AttachmentRowMapper attachmentMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter,
        ColumnConverter columnConverter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Attachment.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.attachmentMapper = attachmentMapper;
        this.columnConverter = columnConverter;
    }

    @Override
    public Flux<Attachment> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Attachment> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = AttachmentSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Attachment.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Attachment> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Attachment> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private Attachment process(Row row, RowMetadata metadata) {
        Attachment entity = attachmentMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends Attachment> Mono<S> save(S entity) {
        return super.save(entity);
    }

    @Override
    public Flux<Attachment> findByCriteria(AttachmentCriteria attachmentCriteria, Pageable page) {
        return createQuery(page, buildConditions(attachmentCriteria)).all();
    }

    @Override
    public Mono<Long> countByCriteria(AttachmentCriteria criteria) {
        return findByCriteria(criteria, null)
            .collectList()
            .map(collectedList -> collectedList != null ? (long) collectedList.size() : (long) 0);
    }

    private Condition buildConditions(AttachmentCriteria criteria) {
        ConditionBuilder builder = new ConditionBuilder(this.columnConverter);
        List<Condition> allConditions = new ArrayList<Condition>();
        if (criteria != null) {
            if (criteria.getId() != null) {
                builder.buildFilterConditionForField(criteria.getId(), entityTable.column("id"));
            }
            if (criteria.getName() != null) {
                builder.buildFilterConditionForField(criteria.getName(), entityTable.column("name"));
            }
            if (criteria.getDetails() != null) {
                builder.buildFilterConditionForField(criteria.getDetails(), entityTable.column("details"));
            }
            if (criteria.getNotes() != null) {
                builder.buildFilterConditionForField(criteria.getNotes(), entityTable.column("notes"));
            }
            if (criteria.getAttachmentType() != null) {
                builder.buildFilterConditionForField(criteria.getAttachmentType(), entityTable.column("attachment_type"));
            }
            if (criteria.getFileUrl() != null) {
                builder.buildFilterConditionForField(criteria.getFileUrl(), entityTable.column("file_url"));
            }
            if (criteria.getReferenceType() != null) {
                builder.buildFilterConditionForField(criteria.getReferenceType(), entityTable.column("reference_type"));
            }
            if (criteria.getReferenceId() != null) {
                builder.buildFilterConditionForField(criteria.getReferenceId(), entityTable.column("reference_id"));
            }
            if (criteria.getCreatedBy() != null) {
                builder.buildFilterConditionForField(criteria.getCreatedBy(), entityTable.column("created_by"));
            }
            if (criteria.getCreatedDate() != null) {
                builder.buildFilterConditionForField(criteria.getCreatedDate(), entityTable.column("created_date"));
            }
            if (criteria.getLastModifiedBy() != null) {
                builder.buildFilterConditionForField(criteria.getLastModifiedBy(), entityTable.column("last_modified_by"));
            }
            if (criteria.getLastModifiedDate() != null) {
                builder.buildFilterConditionForField(criteria.getLastModifiedDate(), entityTable.column("last_modified_date"));
            }
        }
        return builder.buildConditions();
    }
}
