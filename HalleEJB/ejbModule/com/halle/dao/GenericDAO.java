package com.halle.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class GenericDAO<T> {

	/** The Constant UNIT_NAME. */
	private static final String UNIT_NAME = "HallePU";
	
    /** The em. */
    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;
     
    /** The entity class. */
    private Class<T> entityClass;
 
    /**
     * Instantiates a new generic dao.
     *
     * @param entityClass the entity class
     */
    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
 
    /**
     * Save.
     *
     * @param entity the entity
     */
    public void save(T entity) {
        em.persist(entity);     
        em.flush();
    }
 
    /**
     * Delete.
     *
     * @param id the id
     * @param classe the classe
     */
    protected void delete(Object id, Class<T> classe) {
        T entityToBeRemoved = em.getReference(classe, id);
        em.remove(entityToBeRemoved);
        em.flush();
    }
 
    /**
     * Delete.
     *
     * @param entity the entity
     */
    protected void delete(T entity) {
        em.remove(entity);
        em.flush();
    }
    
    /**
     * Update.
     *
     * @param entity the entity
     * @return the t
     */
    public T update(T entity) {
    	T e = em.merge(entity);
    	em.flush();
    	return e;
        
    }
 
    /**
     * Find.
     *
     * @param entityID the entity id
     * @return the t
     */
    public T find(int entityID) {
        return em.find(entityClass, entityID);
    }
 
    // Using the unchecked because JPA does not have a
    // em.getCriteriaBuilder().createQuery()<T> method
    /**
     * Find all.
     *
     * @return the list
     */
    @SuppressWarnings("unchecked")
	public List<T> findAll() {
        CriteriaQuery<T> cq = (CriteriaQuery<T>) em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }
 
    // Using the unchecked because JPA does not have a
    // ery.getSingleResult()<T> method
    /**
     * Find one result.
     *
     * @param namedQuery the named query
     * @param parameters the parameters
     * @return the t
     */
    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
        T result = null;
 
        try {
            Query query = em.createNamedQuery(namedQuery);
 
            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
 
            result = (T) query.getSingleResult();
 
        } catch (NoResultException e) {
        	return null;
        }
        
 
        return result;
    }
 
    /**
     * Find one result.
     *
     * @param namedQuery the named query
     * @param parameters the parameters
     * @return the t
     */
    @SuppressWarnings("unchecked")
    protected List<T> findAllResult(String namedQuery, Map<String, Object> parameters) {
    	List<T> result = null;
 
        try {
            Query query = em.createNamedQuery(namedQuery);
 
            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
 
            result = (List<T>) query.getResultList();
 
        } catch (NoResultException e) {
        	return null;
        }
        
 
        return result;
    }
 
    
    /**
     * Populate query parameters.
     *
     * @param query the query
     * @param parameters the parameters
     */
    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
 
        for (Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * Gets the entity manager.
     *
     * @return the entity manager
     */
    public EntityManager getEntityManager() {
    	return this.em;
    }
}
