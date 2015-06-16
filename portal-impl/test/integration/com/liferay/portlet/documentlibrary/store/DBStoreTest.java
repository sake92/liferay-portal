/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.documentlibrary.store;

import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.MainServletTestRule;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.documentlibrary.store.test.BaseStoreTestCase;

import org.junit.ClassRule;
import org.junit.Rule;

/**
 * @author Shuyang Zhou
 * @author Tina Tian
 */
public class DBStoreTest extends BaseStoreTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), MainServletTestRule.INSTANCE);

	@Override
	protected Store getStore() {
		String originalDLStoreImpl = PropsValues.DL_STORE_IMPL;

		PropsValues.DL_STORE_IMPL = DBStore.class.getName();

		StoreFactory.setInstance(null);

		try {
			return StoreFactory.getInstance();
		}
		finally {
			PropsValues.DL_STORE_IMPL = originalDLStoreImpl;

			StoreFactory.setInstance(null);
		}
	}

}