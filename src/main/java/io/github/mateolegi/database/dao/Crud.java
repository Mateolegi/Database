/**
 * 
 */
package io.github.mateolegi.database.dao;

import java.util.List;

import io.github.mateolegi.database.util.Filter;

/**
 * Capa de acceso a datos genérica
 * @author Mateo Leal
 * @param <E> Clase de la entidad
 */
public interface Crud<E> {

	/**
	 * Obtiene todos los registros de una tabla
	 * @return lista con todos los registros de la tabla
	 */
	List<E> findAll();

	/**
	 * Obtiene todos los registros de una tabla aplicando un filtro
	 * @param filter filtro de búsqueda
	 * @return lista con todos los registros filtrados
	 */
	List<E> findAll(Filter filter);

	/**
	 * Obtiene una entidad basada en su identificador
	 * @param id id de la entidad
	 * @return entidad
	 */
	E find(Object id);

	/**
	 * Almacena una entidad en el contexto de persistencia
	 * @param entity entidad que se va a almacenar
	 */
	E save(E entity);

	/**
	 * Actualiza una entidad
	 * @param entity entidad que se va a actualizar
	 * @return entidad actualizada
	 */
	E update(E entity);

	/**
	 * Elimina una entidad del contexto de persistencia
	 * @param entity entidad que se va a eliminar
	 */
	void delete(E entity);
}
