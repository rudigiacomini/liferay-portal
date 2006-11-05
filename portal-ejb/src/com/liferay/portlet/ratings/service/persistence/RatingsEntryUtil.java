/**
 * Copyright (c) 2000-2006 Liferay, Inc. All rights reserved.
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.ratings.service.persistence;

import com.liferay.portal.model.ModelListener;
import com.liferay.portal.spring.util.SpringUtil;
import com.liferay.portal.util.PropsUtil;

import com.liferay.util.GetterUtil;
import com.liferay.util.Validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.context.ApplicationContext;

/**
 * <a href="RatingsEntryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class RatingsEntryUtil {
	public static final String CLASS_NAME = RatingsEntryUtil.class.getName();
	public static final String LISTENER = GetterUtil.getString(PropsUtil.get(
				"value.object.listener.com.liferay.portlet.ratings.model.RatingsEntry"));

	public static com.liferay.portlet.ratings.model.RatingsEntry create(
		long entryId) {
		return getPersistence().create(entryId);
	}

	public static com.liferay.portlet.ratings.model.RatingsEntry remove(
		long entryId)
		throws com.liferay.portlet.ratings.NoSuchEntryException, 
			com.liferay.portal.SystemException {
		ModelListener listener = null;

		if (Validator.isNotNull(LISTENER)) {
			try {
				listener = (ModelListener)Class.forName(LISTENER).newInstance();
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		if (listener != null) {
			listener.onBeforeRemove(findByPrimaryKey(entryId));
		}

		com.liferay.portlet.ratings.model.RatingsEntry ratingsEntry = getPersistence()
																		  .remove(entryId);

		if (listener != null) {
			listener.onAfterRemove(ratingsEntry);
		}

		return ratingsEntry;
	}

	public static com.liferay.portlet.ratings.model.RatingsEntry remove(
		com.liferay.portlet.ratings.model.RatingsEntry ratingsEntry)
		throws com.liferay.portal.SystemException {
		ModelListener listener = null;

		if (Validator.isNotNull(LISTENER)) {
			try {
				listener = (ModelListener)Class.forName(LISTENER).newInstance();
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		if (listener != null) {
			listener.onBeforeRemove(ratingsEntry);
		}

		ratingsEntry = getPersistence().remove(ratingsEntry);

		if (listener != null) {
			listener.onAfterRemove(ratingsEntry);
		}

		return ratingsEntry;
	}

	public static com.liferay.portlet.ratings.model.RatingsEntry update(
		com.liferay.portlet.ratings.model.RatingsEntry ratingsEntry)
		throws com.liferay.portal.SystemException {
		ModelListener listener = null;

		if (Validator.isNotNull(LISTENER)) {
			try {
				listener = (ModelListener)Class.forName(LISTENER).newInstance();
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		boolean isNew = ratingsEntry.isNew();

		if (listener != null) {
			if (isNew) {
				listener.onBeforeCreate(ratingsEntry);
			}
			else {
				listener.onBeforeUpdate(ratingsEntry);
			}
		}

		ratingsEntry = getPersistence().update(ratingsEntry);

		if (listener != null) {
			if (isNew) {
				listener.onAfterCreate(ratingsEntry);
			}
			else {
				listener.onAfterUpdate(ratingsEntry);
			}
		}

		return ratingsEntry;
	}

	public static com.liferay.portlet.ratings.model.RatingsEntry update(
		com.liferay.portlet.ratings.model.RatingsEntry ratingsEntry,
		boolean saveOrUpdate) throws com.liferay.portal.SystemException {
		ModelListener listener = null;

		if (Validator.isNotNull(LISTENER)) {
			try {
				listener = (ModelListener)Class.forName(LISTENER).newInstance();
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		boolean isNew = ratingsEntry.isNew();

		if (listener != null) {
			if (isNew) {
				listener.onBeforeCreate(ratingsEntry);
			}
			else {
				listener.onBeforeUpdate(ratingsEntry);
			}
		}

		ratingsEntry = getPersistence().update(ratingsEntry, saveOrUpdate);

		if (listener != null) {
			if (isNew) {
				listener.onAfterCreate(ratingsEntry);
			}
			else {
				listener.onAfterUpdate(ratingsEntry);
			}
		}

		return ratingsEntry;
	}

	public static com.liferay.portlet.ratings.model.RatingsEntry findByPrimaryKey(
		long entryId)
		throws com.liferay.portlet.ratings.NoSuchEntryException, 
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(entryId);
	}

	public static com.liferay.portlet.ratings.model.RatingsEntry fetchByPrimaryKey(
		long entryId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(entryId);
	}

	public static java.util.List findByC_C(java.lang.String className,
		java.lang.String classPK) throws com.liferay.portal.SystemException {
		return getPersistence().findByC_C(className, classPK);
	}

	public static java.util.List findByC_C(java.lang.String className,
		java.lang.String classPK, int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_C(className, classPK, begin, end);
	}

	public static java.util.List findByC_C(java.lang.String className,
		java.lang.String classPK, int begin, int end,
		com.liferay.util.dao.hibernate.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_C(className, classPK, begin, end, obc);
	}

	public static com.liferay.portlet.ratings.model.RatingsEntry findByC_C_First(
		java.lang.String className, java.lang.String classPK,
		com.liferay.util.dao.hibernate.OrderByComparator obc)
		throws com.liferay.portlet.ratings.NoSuchEntryException, 
			com.liferay.portal.SystemException {
		return getPersistence().findByC_C_First(className, classPK, obc);
	}

	public static com.liferay.portlet.ratings.model.RatingsEntry findByC_C_Last(
		java.lang.String className, java.lang.String classPK,
		com.liferay.util.dao.hibernate.OrderByComparator obc)
		throws com.liferay.portlet.ratings.NoSuchEntryException, 
			com.liferay.portal.SystemException {
		return getPersistence().findByC_C_Last(className, classPK, obc);
	}

	public static com.liferay.portlet.ratings.model.RatingsEntry[] findByC_C_PrevAndNext(
		long entryId, java.lang.String className, java.lang.String classPK,
		com.liferay.util.dao.hibernate.OrderByComparator obc)
		throws com.liferay.portlet.ratings.NoSuchEntryException, 
			com.liferay.portal.SystemException {
		return getPersistence().findByC_C_PrevAndNext(entryId, className,
			classPK, obc);
	}

	public static com.liferay.portlet.ratings.model.RatingsEntry findByU_C_C(
		java.lang.String userId, java.lang.String className,
		java.lang.String classPK)
		throws com.liferay.portlet.ratings.NoSuchEntryException, 
			com.liferay.portal.SystemException {
		return getPersistence().findByU_C_C(userId, className, classPK);
	}

	public static com.liferay.portlet.ratings.model.RatingsEntry fetchByU_C_C(
		java.lang.String userId, java.lang.String className,
		java.lang.String classPK) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByU_C_C(userId, className, classPK);
	}

	public static java.util.List findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List findAll(int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(begin, end);
	}

	public static java.util.List findAll(int begin, int end,
		com.liferay.util.dao.hibernate.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(begin, end, obc);
	}

	public static void removeByC_C(java.lang.String className,
		java.lang.String classPK) throws com.liferay.portal.SystemException {
		getPersistence().removeByC_C(className, classPK);
	}

	public static void removeByU_C_C(java.lang.String userId,
		java.lang.String className, java.lang.String classPK)
		throws com.liferay.portlet.ratings.NoSuchEntryException, 
			com.liferay.portal.SystemException {
		getPersistence().removeByU_C_C(userId, className, classPK);
	}

	public static int countByC_C(java.lang.String className,
		java.lang.String classPK) throws com.liferay.portal.SystemException {
		return getPersistence().countByC_C(className, classPK);
	}

	public static int countByU_C_C(java.lang.String userId,
		java.lang.String className, java.lang.String classPK)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByU_C_C(userId, className, classPK);
	}

	public static void initDao() {
		getPersistence().initDao();
	}

	public static RatingsEntryPersistence getPersistence() {
		ApplicationContext ctx = SpringUtil.getContext();
		RatingsEntryUtil util = (RatingsEntryUtil)ctx.getBean(CLASS_NAME);

		return util._persistence;
	}

	public void setPersistence(RatingsEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static Log _log = LogFactory.getLog(RatingsEntryUtil.class);
	private RatingsEntryPersistence _persistence;
}