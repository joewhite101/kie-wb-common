/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.forms.jbpm.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.kie.workbench.common.forms.editor.client.editor.EditorFieldTypesProvider;
import org.kie.workbench.common.forms.jbpm.model.authoring.document.type.DocumentFieldType;
import org.kie.workbench.common.forms.model.FieldType;

@ApplicationScoped
public class JBPMEditorFieldTypeProviderImpl implements EditorFieldTypesProvider {

    private List<FieldType> supportedFieldTypes = new ArrayList<>();

    @PostConstruct
    public void init() {
        supportedFieldTypes.add(new DocumentFieldType());
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public Collection<FieldType> getPaletteFieldTypes() {
        return supportedFieldTypes;
    }

    @Override
    public Collection<FieldType> getFieldPropertiesFieldTypes() {
        return supportedFieldTypes;
    }
}
