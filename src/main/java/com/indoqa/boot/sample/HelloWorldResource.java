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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.indoqa.boot.AbstractJsonResourcesBase;

import spark.Response;
import spark.Spark;

/**
 * All resources are implemented as Spring beans so that they can get access to other Spring beans by using the @Inject annotation.
 */
@Named
public class HelloWorldResource extends AbstractJsonResourcesBase {

    @Inject
    private DateService dateService;

    /**
     * Register the Spark routes with Spark. The {@link AbstractJsonResourcesBase} provides helper methods that use a Json renderer
     * based on Jackson 2. Of course the standard methods provided by Spark can be used too - see the /plain-text-message resource.
     * 
     * Note that Indoqa Boot sets application/json as default content type if the content type is not set explicitly at the response
     * object.
     * 
     * See http://sparkjava.com/documentation.html for more details on how to use JavaSpark.
     */
    @PostConstruct
    public void mount() {
        this.get("/", (req, res) -> new Message("Hello, world!", this.dateService.getDate()));

        Spark.get("/plain-text-message", (req, res) -> this.writePlainTextMessage(res));
    }

    private String writePlainTextMessage(Response res) {
        res.type("text/plain");
        return "Hello, world!";
    }

    /**
     * This class is used by the route definition above. Of course the Jackson databind annotations can be used.
     */
    public static class Message {

        private final String value;
        private final Date date;

        @JsonIgnore
        private final String otherValue = "other value which will be ignored by Jackson";

        public Message(String value, Date date) {
            this.value = value;
            this.date = date;
        }

        public Date getDate() {
            return this.date;
        }

        public String getOtherValue() {
            return this.otherValue;
        }

        public String getValue() {
            return this.value;
        }
    }
}
