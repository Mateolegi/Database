/**
 * 
 */
package io.github.mateolegi.database.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import io.github.mateolegi.database.dao.Crud;
import io.github.mateolegi.database.util.Filter;
import io.github.mateolegi.database.util.HibernateUtil;
import io.github.mateolegi.database.util.TransactionException;

/**
 * Implementación de la capa de acceso a datos genérica
 * @author Mateo Leal
 * @param <E> Clase de la entidad
 */
public class CrudImpl<E> implements Crud<E> {

	private final Class<E> clazz;

	public CrudImpl(Class<E> clazz) {
		this.clazz = clazz;
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.mateolegi.database.dao.Crud#findAll()
	 */
	@Override
	public List<E> findAll() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<E> query = builder.createQuery(clazz);
			Root<E> root = query.from(clazz);
			query.select(root);
			Query<E> q = session.createQuery(query);
			List<E> list = q.getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new TransactionException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.mateolegi.database.dao.Crud#findAll(io.github.mateolegi.database.util.Filter)
	 */
	@Override
	public List<E> findAll(Filter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.mateolegi.database.dao.Crud#find(int)
	 */
	@Override
	public E find(Object id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<E> query = builder.createQuery(clazz);
			Root<E> root = query.from(clazz);
			query.select(root).where(builder.equal(root.get("id"), id));
			Query<E> q = session.createQuery(query);
			E element = q.getSingleResult();
			transaction.commit();
			return element;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new TransactionException(e);
		} finally {
			session.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.mateolegi.database.dao.Crud#save(java.lang.Object)
	 */
	@Override
	public E save(E entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.mateolegi.database.dao.Crud#update(java.lang.Object)
	 */
	@Override
	public E update(E entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.mateolegi.database.dao.Crud#delete(java.lang.Object)
	 */
	@Override
	public void delete(E entity) {
		// TODO Auto-generated method stub

	}
}
