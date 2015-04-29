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

package com.liferay.workflow.definition.web.portlet.action;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.ActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.WebKeys;

/**
 * @author Leonardo Barros
 */
@Component(
	immediate = true,
	property = {
		"action.command.name=deleteWorkflowDefinition",
		"javax.portlet.name=" + PortletKeys.WORKFLOW_DEFINITIONS
	},
	service = ActionCommand.class
)
public class DeleteWorkflowDefinitionActionCommand extends 
	BaseWorkflowDefinitionActionCommand {

	@Override
	protected void doProcessWorkflowDefinitionCommand(
		PortletRequest portletRequest, PortletResponse portletResponse) 
		throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		String name = ParamUtil.getString(portletRequest, _NAME);
		int version = ParamUtil.getInteger(portletRequest, _VERSION);
		
		WorkflowDefinitionManagerUtil.undeployWorkflowDefinition(
			themeDisplay.getCompanyId(), themeDisplay.getUserId(), name,
			version);
	}

	private static final String _VERSION = "version";
	private static final String _NAME = "name";

}
