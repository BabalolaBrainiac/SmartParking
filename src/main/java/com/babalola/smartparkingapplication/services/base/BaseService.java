package com.babalola.smartparkingapplication.services.base;


import com.babalola.smartparkingapplication.domain.entities.BaseEntity;
import com.babalola.smartparkingapplication.events.listeners.EntityTransactionLogListener;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public abstract class BaseService<E extends BaseEntity<Long>, Long extends Serializable> {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public abstract JpaRepository<E,Long> getRepository();

    @Transactional(readOnly = true)
    public E findById(final Long id) {
        log.debug("[retrieving] {} {}", this.getEntityName(), id);
        return this.getRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not retried item"));
    }

    @Transactional(readOnly = true)
    public Optional<E> findByIdOptional(final Long id) {
        log.debug("[retrieving] {} {}", this.getEntityName(), id);
        return this.getRepository().findById(id);
    }

    @Transactional(readOnly = true)
    public Page<E> findAll(final Pageable pageable) {
        log.debug("[retrieving] all {}", this.getEntityName());
        return this.getRepository().findAll(pageable);
    }

    @Transactional
    public E create(final E entity) {
        log.info("[creating] {} {}", this.getEntityName(), entity);
        if (entity.getId() != null) {
            throw new IllegalStateException("You are trying to create an entity that already exists.");
        }
        this.getRepository().save(entity);
        this.publishTxCreateLog((Long) entity.getId());
        return entity;
    }

    @Transactional
    public E update(final E entity) {
        log.info("[updating] {} {}", this.getEntityName(), entity);
        if (entity.getId() == null) {
            throw new IllegalStateException("You are trying to update an entity that does not exist.");
        }
        this.getRepository().save(entity);
        this.publishTxUpdateLog((Long) entity.getId());
        return entity;
    }

    @Transactional
    public void delete(final Long id) {
        log.info("[deleting] {} {}", this.getEntityName(), id);
        E entity = this.getRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not delete item"));
        this.getRepository().delete(entity);
        this.publishTxDeleteLog(id);
    }

    protected void publishTxLogEvent(EntityTransactionLogListener.TransactionEvent.TransactionType type, Long entityId) {
        this.applicationEventPublisher.publishEvent(
                new EntityTransactionLogListener.TransactionEvent(type, this.getEntityName(), entityId.toString()));
    }

    protected void publishTxCreateLog(Long entityId) {
        this.publishTxLogEvent(EntityTransactionLogListener.TransactionEvent.TransactionType.CREATE, entityId);
    }

    protected void publishTxUpdateLog(Long entityId) {
        this.publishTxLogEvent(EntityTransactionLogListener.TransactionEvent.TransactionType.UPDATE, entityId);
    }

    protected void publishTxDeleteLog(Long entityId) {
        this.publishTxLogEvent(EntityTransactionLogListener.TransactionEvent.TransactionType.DELETE, entityId);
    }

    protected String getEntityName() {
        final Class<E> entityModelClass = (Class<E>)
                ((ParameterizedType) this.getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
        final Table annotation = entityModelClass.getAnnotation(Table.class);
        return annotation.name();
    }
}
