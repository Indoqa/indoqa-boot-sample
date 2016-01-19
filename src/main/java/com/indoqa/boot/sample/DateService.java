/*
 * Licensed to the Indoqa Software Design und Beratung GmbH (Indoqa) under
 * one or more contributor license agreements. See the NOTICE file distributed
 * with this work for additional information regarding copyright ownership.
 * Indoqa licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.indoqa.boot.sample;

import java.util.Date;

import javax.inject.Named;

import com.indoqa.boot.AbstractIndoqaBootApplication;

/**
 * A simple service that is annotated by the @Named annotation. The {@link AbstractIndoqaBootApplication} ensures that all classes
 * marked with this annotation are loaded as Spring beans.
 * 
 * Since it is usually a good idea to keep the core business logic free from vendor specific code, it is recommended to use the JSR330
 * annotations instead of the Spring specific annotations @Component, @Autowired, etc. in order to reduce the dependencies on Spring.
 * This helps to migrate the code more easily to alternative dependency injection frameworks or without dependency injection frameworks
 * at all.
 */
@Named
public class DateService {

    public Date getDate() {
        return new Date();
    }
}
