/*
 * The MIT License
 * 
 * Copyright (c) 2010-2018 Google, Inc. http://angularjs.org
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS I
 * THE SOFTWARE.
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
import io.github.mateolegi.database.util.HibernateUtil;
import io.github.mateolegi.database.util.TransactionException;

/**
 * Implementación de la capa de acceso a datos genérica
 * @author <a href="https://mateolegi.github.io">Mateo Leal</a>
 * @param <E> Clase de la entidad
 */
public class CrudImpl<E> implements Crud<E> {

	/** Clase que representa la entidad que se mapea en las consultas */
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
		}
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.mateolegi.database.dao.Crud#save(java.lang.Object)
	 */
	@Override
	public E save(E entity) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Object id = session.save(entity);
			session.evict(entity);
			transaction.commit();
			return find(id);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new TransactionException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.mateolegi.database.dao.Crud#update(java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E update(E entity) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			E updatedEntity = (E) session.merge(entity);
			session.evict(entity);
			transaction.commit();
			return updatedEntity;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new TransactionException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.mateolegi.database.dao.Crud#delete(java.lang.Object)
	 */
	@Override
	public void delete(E entity) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.remove(entity);
			session.evict(entity);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new TransactionException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see io.github.mateolegi.database.dao.Crud
	 * #nativeQuery(java.lang.String, boolean, java.lang.String[])
	 */
	@Override
	public Object nativeQuery(String sql, boolean isList, String... values) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			javax.persistence.Query query = session.createNativeQuery(sql);
			if (values != null) {
				int index = 1;
				for (String value: values) {
					query.setParameter(index++, value);
				}
			}
			transaction.commit();
			return isList ? query.getResultList() : query.getSingleResult();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new TransactionException(e);
		}
	}
}
