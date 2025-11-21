/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.internal.related.models;

import com.liferay.object.constants.ObjectRelationshipConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntryTable;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.petra.sql.dsl.DynamicObjectDefinitionTable;
import com.liferay.object.petra.sql.dsl.DynamicObjectRelationshipMappingTable;
import com.liferay.object.petra.sql.dsl.DynamicObjectRelationshipMappingTableFactory;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.expression.Predicate;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Luis Miguel Barcos
 */
public class ObjectEntryMtoMObjectRelatedModelsPredicateProviderImpl
	extends BaseObjectEntryObjectRelatedModelsPredicateProviderImpl {

	public ObjectEntryMtoMObjectRelatedModelsPredicateProviderImpl(
		ObjectDefinition objectDefinition,
		ObjectDefinitionLocalService objectDefinitionLocalService,
		ObjectFieldLocalService objectFieldLocalService) {

		super(objectDefinition, objectFieldLocalService);

		this.objectDefinitionLocalService = objectDefinitionLocalService;
	}

	@Override
	public String getObjectRelationshipType() {
		return ObjectRelationshipConstants.TYPE_MANY_TO_MANY;
	}

	@Override
	public Predicate getPredicate(
			ObjectRelationship objectRelationship, Predicate predicate,
			ObjectDefinition relatedObjectDefinition)
		throws PortalException {

		Column<?, ?> dynamicObjectDefinitionTableColumn =
			getPKObjectFieldColumn(
				getDynamicObjectDefinitionTable(objectDefinition),
				objectDefinition.getPKObjectFieldDBColumnName());

		DynamicObjectRelationshipMappingTable
			dynamicObjectRelationshipMappingTable =
				DynamicObjectRelationshipMappingTableFactory.create(
					objectDefinitionLocalService, objectRelationship,
					objectRelationship.isReverse());

		Column<DynamicObjectRelationshipMappingTable, ?>
			dynamicObjectRelationshipMappingTableColumn =
				(Column<DynamicObjectRelationshipMappingTable, ?>)
					getPKObjectFieldColumn(
						dynamicObjectRelationshipMappingTable,
						dynamicObjectRelationshipMappingTable.
							getPrimaryKeyColumn2(
							).getName());

		DynamicObjectDefinitionTable relatedDynamicObjectDefinitionTable =
			getDynamicObjectDefinitionTable(relatedObjectDefinition);
		DynamicObjectDefinitionTable relatedObjectDefinitionExtensionTable =
			getExtensionDynamicObjectDefinitionTable(relatedObjectDefinition);

		return dynamicObjectDefinitionTableColumn.in(
			DSLQueryFactoryUtil.select(
				getPKObjectFieldColumn(
					dynamicObjectRelationshipMappingTable,
					dynamicObjectRelationshipMappingTable.getPrimaryKeyColumn1(
					).getName())
			).from(
				dynamicObjectRelationshipMappingTable
			).where(
				dynamicObjectRelationshipMappingTableColumn.in(
					DSLQueryFactoryUtil.select(
						getPKObjectFieldColumn(
							relatedDynamicObjectDefinitionTable,
							relatedObjectDefinition.
								getPKObjectFieldDBColumnName())
					).from(
						relatedDynamicObjectDefinitionTable
					).innerJoinON(
						ObjectEntryTable.INSTANCE,
						ObjectEntryTable.INSTANCE.objectEntryId.eq(
							relatedDynamicObjectDefinitionTable.
								getPrimaryKeyColumn())
					).innerJoinON(
						relatedObjectDefinitionExtensionTable,
						relatedDynamicObjectDefinitionTable.getPrimaryKeyColumn(
						).eq(
							relatedObjectDefinitionExtensionTable.
								getPrimaryKeyColumn()
						)
					).where(
						predicate
					))
			));
	}

	protected final ObjectDefinitionLocalService objectDefinitionLocalService;

}