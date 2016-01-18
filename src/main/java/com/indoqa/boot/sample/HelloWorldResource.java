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

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.indoqa.boot.AbstractJsonResourcesBase;

@Named
public class HelloWorldResource extends AbstractJsonResourcesBase {

    @Inject
    private DateService dateService;

    @PostConstruct
    public void mount() {
        this.get("/", (req, res) -> new Message("Hello, world!", this.dateService.getDate()));
    }

    public static class Message {

        private final String value;
        private final Date date;

        public Message(String value, Date date) {
            this.value = value;
            this.date = date;
        }

        public Date getDate() {
            return this.date;
        }

        public String getValue() {
            return this.value;
        }
    }
}
