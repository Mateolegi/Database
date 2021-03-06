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
package io.github.mateolegi.database.util;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Filtro de búsqueda para consultas JPQL
 * @author <a href="https://mateolegi.github.io">Mateo Leal</a>
 */
public class Filter {

	private Set<Criterion> criteria = new HashSet<>();

	public Filter eq(String property, Object value) {
		criteria.add(Restrictions.eq(property, value));
		return this;
	}

	public Filter like(String property, Object value) {
		criteria.add(Restrictions.like(property, value));
		return this;
	}

	public Filter likeInsensitive(String property, Object value) {
		criteria.add(Restrictions.ilike(property, value));
		return this;
	}

	public Filter in(String property, Object... values) {
		criteria.add(Restrictions.in(property, values));
		return this;
	}

	public Filter between(String property, Object low, Object high) {
		criteria.add(Restrictions.between(property, low, high));
		return this;
	}
	
	public Filter disjunction(Filter filter) {
		criteria.add(Restrictions.disjunction(filter.getFilter()
				.toArray(new Criterion[filter.getFilter().size()])));
		return this;
	}

	public Filter conjunction(Filter filter) {
		criteria.add(Restrictions.conjunction(filter.getFilter()
				.toArray(new Criterion[filter.getFilter().size()])));
		return this;
	}

	public Filter greaterThanOrEqual(String property, Object value) {
		criteria.add(Restrictions.ge(property, value));
		return this;
	}

	public Filter greaterThan(String property, Object value) {
		criteria.add(Restrictions.gt(property, value));
		return this;
	}

	public Filter idEqual(Object value) {
		criteria.add(Restrictions.idEq(value));
		return this;
	}

	public Filter isEmpty(String property) {
		criteria.add(Restrictions.isEmpty(property));
		return this;
	}

	public Filter isNotEmpty(String property) {
		criteria.add(Restrictions.isNotEmpty(property));
		return this;
	}

	public Filter isNull(String property) {
		criteria.add(Restrictions.isNull(property));
		return this;
	}

	public Filter isNotNull(String property) {
		criteria.add(Restrictions.isNotNull(property));
		return this;
	}

	public Filter lessThanOrEqual(String property, Object value) {
		criteria.add(Restrictions.le(property, value));
		return this;
	}

	public Filter lessThan(String property, Object value) {
		criteria.add(Restrictions.lt(property, value));
		return this;
	}

	public Set<Criterion> getFilter() {
		return criteria;
	}
}
