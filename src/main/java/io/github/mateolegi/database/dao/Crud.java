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
package io.github.mateolegi.database.dao;

import java.util.List;

/**
 * Capa de acceso a datos genérica
 * @author <a href="https://mateolegi.github.io">Mateo Leal</a>
 * @param <E> Clase de la entidad
 */
public interface Crud<E> {

	/**
	 * Obtiene todos los registros de una tabla
	 * @return lista con todos los registros de la tabla
	 */
	List<E> findAll();

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

	/**
	 * Ejecuta una consulta en SQL nativo
	 * @param sql consulta SQL
	 * @param isList <code>true</code> si la consulta debe traer varios resultados
	 * @param values parámetros de la consulta
	 * @return resultado de la consulta
	 */
	Object nativeQuery(String sql, boolean isList, String... values);
}
