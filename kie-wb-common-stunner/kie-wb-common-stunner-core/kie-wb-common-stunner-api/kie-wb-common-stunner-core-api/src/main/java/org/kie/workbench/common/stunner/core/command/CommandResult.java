/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
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

package org.kie.workbench.common.stunner.core.command;

/**
 * Result from the execution of a command
 */
public interface CommandResult<T> {

    enum Type {
        ERROR(3),
        WARNING(2),
        INFO(1);

        private final int severity;

        Type(final int severity) {
            this.severity = severity;
        }

        public int getSeverity() {
            return severity;
        }
    }

    /**
     * Type of Result
     * @return
     */
    Type getType();

    /**
     * Detailed message for the Result
     * @return
     */
    String getMessage();

    Iterable<T> getViolations();
}