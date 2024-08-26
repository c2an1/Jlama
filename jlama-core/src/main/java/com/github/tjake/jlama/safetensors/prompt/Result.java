/*
 * Copyright 2024 T Jake Luciani
 *
 * The Jlama Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.github.tjake.jlama.safetensors.prompt;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.tjake.jlama.util.JsonSupport;

/**
 * Result
 */
@JsonPropertyOrder({Result.JSON_PROPERTY_TOOL_NAME, Result.JSON_PROPERTY_TOOL_ID, Result.JSON_PROPERTY_RESULT})
public class Result {
    public static final String JSON_PROPERTY_TOOL_NAME = "name";
    public final String name;

    public static final String JSON_PROPERTY_TOOL_ID = "id";
    public final String id;

    public static final String JSON_PROPERTY_RESULT = "result";
    private final Object result;

    private Result(String name, String id, Object result) {
        this.name = name;
        this.id = id;
        this.result = result;
    }

    public static Result from(String name, String id, Object result) {
        return new Result(name, id, result);
    }

    public Object getResult() {
        return result;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String toJson() {
        return JsonSupport.toJson(this);
    }
}
