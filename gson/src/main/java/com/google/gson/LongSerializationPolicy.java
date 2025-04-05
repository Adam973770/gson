/*
 * Copyright (C) 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson;

public class LongSerializationPolicy {

  // Stratégie par défaut (DEFAULT)
  public static final LongSerializationStrategy DEFAULT = new DefaultLongSerializationStrategy();

  // Stratégie STRING
  public static final LongSerializationStrategy STRING = new StringLongSerializationStrategy();

  private LongSerializer serializer;

  /**
   * Defines the expected format for a {@code long} or {@code Long} type when it is serialized.
   *
   * @since 1.3
   * @author Inderjeet Singh
   * @author Joel Leitch
   */
  public LongSerializationPolicy(LongSerializationStrategy strategy) {
    this.serializer = new LongSerializer(strategy);
  }

  /**
   * Serialize this {@code value} using this serialization policy.
   *
   * @param value the long value to be serialized into a {@link JsonElement}
   * @return the serialized version of {@code value}
   */
  public JsonElement serialize(Long value) {
    return serializer.serialize(value);
  }

  public void setSerializationStrategy(LongSerializationStrategy strategy) {
    this.serializer.setSerializationStrategy(strategy);
  }

  /**
   * This is the "default" serialization policy that will output a {@code Long} object as a JSON
   * number. For example, assume an object has a long field named "f" then the serialized output
   * would be: {@code {"f":123}}
   *
   * <p>A {@code null} value is serialized as {@link JsonNull}.
   */
  public static JsonElement serializeWithDefault(Long value) {
    return new LongSerializer(DEFAULT).serialize(value);
  }

  /**
   * Serializes a long value as a quoted string. For example, assume an object has a long field
   * named "f" then the serialized output would be: {@code {"f":"123"}}
   *
   * <p>A {@code null} value is serialized as {@link JsonNull}.
   */
  public static JsonElement serializeWithString(Long value) {
    return new LongSerializer(STRING).serialize(value);
  }
}
