/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.stunner.client.widgets.presenters.session;

import com.google.gwt.user.client.ui.IsWidget;
import org.kie.workbench.common.stunner.client.widgets.palette.PaletteWidget;
import org.kie.workbench.common.stunner.client.widgets.presenters.Viewer;
import org.kie.workbench.common.stunner.client.widgets.presenters.diagram.DiagramViewer;
import org.kie.workbench.common.stunner.client.widgets.toolbar.Toolbar;
import org.kie.workbench.common.stunner.core.client.canvas.CanvasHandler;
import org.kie.workbench.common.stunner.core.client.components.palette.model.definition.DefinitionSetPalette;
import org.kie.workbench.common.stunner.core.client.session.ClientSession;
import org.kie.workbench.common.stunner.core.diagram.Diagram;

/**
 * A session's presenter type for generic client session instances.
 * <p>
 * A session presenter is a client side component that has same goals as a SessionViewer/Editor, so displaying a diagram
 * and handling the different controls for either viewing or authoring purposes, but it provides some additional
 * features:
 * - It opens and destroys the session instance once opening or clear/destroying the presenter instance. So if you
 * have session aware components on same screen, they'll automatically open/close as this session presenter instance
 * is being opened/closed.
 * - Both SessionViewer and SessionEditor expects sessions built and initialized with the diagram instance, but the session's
 * presenter allows to bind a diagram instance, which can be ported from server side or just a new recently created
 * instance, to a given session.
 * - Implementations can provide a Toolbar and/or a Palette component as well integrated into the user interface
 * with some default actions and behaviors.
 * - It provides a more complex view type that supports adding views on left and top sides of the canvas area,
 * which are used for the Palette and Toolbar component's views, if present. The view can display both notifications
 * and error messages generated for the active session, if enabled.
 * @param <S> The session type.
 * @param <H> The canvas handler type.
 * @param <D> The diagram type.
 */
public interface SessionPresenter<S extends ClientSession, H extends CanvasHandler, D extends Diagram>
        extends Viewer<S, H, SessionPresenter.View, SessionPresenter.SessionPresenterCallback<S, D>> {

    interface SessionPresenterCallback<S extends ClientSession, D extends Diagram> extends DiagramViewer.DiagramViewerCallback<D> {

        void afterSessionOpened();
    }

    interface View extends IsWidget {

        View setCanvasWidget(final IsWidget widget);

        View setToolbarWidget(final IsWidget widget);

        View setPaletteWidget(final IsWidget widget);

        View showLoading(final boolean loading);

        View showError(final String error);

        View showMessage(final String message);

        void destroy();
    }

    SessionPresenter<S, H, D> withToolbar(final boolean hasToolbar);

    SessionPresenter<S, H, D> withPalette(final boolean hasPalette);

    SessionPresenter<S, H, D> displayNotifications(final boolean showNotifications);

    SessionPresenter<S, H, D> displayErrors(final boolean showErrors);

    void open(final D diagram,
              final S session,
              final SessionPresenter.SessionPresenterCallback<S, D> callback);

    Toolbar<S> getToolbar();

    PaletteWidget<DefinitionSetPalette> getPalette();
}
