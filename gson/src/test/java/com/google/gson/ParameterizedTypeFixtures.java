/*
 * Copyright (C) 2008 Google Inc.
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

import com.google.gson.internal.$Gson$Types;
import com.google.gson.internal.Primitives;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * This class contains some test fixtures for Parameterized types. These classes should ideally
 * belong either in the common or functional package, but they are placed here because they need
 * access to package protected elements of com.google.gson.
 *
 * @author Inderjeet Singh
 * @author Joel Leitch
 */
public class ParameterizedTypeFixtures {
  private ParameterizedTypeFixtures() {}

  public static class MyParameterizedTypeInstanceCreator<T> {
    private final T instanceOfT;

    /**
     * Caution the specified instance is reused by the instance creator for each call. This means
     * that the fields of the same objects will be overwritten by Gson. This is usually fine in
     * tests since there we deserialize just once, but quite dangerous in practice.
     */
    public MyParameterizedTypeInstanceCreator(T instanceOfT) {
      this.instanceOfT = instanceOfT;
    }
  
    public T createInstance(Type type) {
      return instanceOfT;
    }
  }

  public static final class MyParameterizedTypeAdapter<T>
      implements JsonSerializer<T>, JsonDeserializer<T> {

    @Override
    public JsonElement serialize(T src, Type classOfSrc, JsonSerializationContext context) {
      JsonObject json = new JsonObject();
      json.add(src.getClass().getSimpleName(), context.serialize(src));
      return json;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      Type genericClass = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
      Class<?> rawType = $Gson$Types.getRawType(genericClass);
      String className = rawType.getSimpleName();
      JsonElement jsonElement = json.getAsJsonObject().get(className);

      T value;
      if (genericClass == Integer.class) {
        value = (T) Integer.valueOf(jsonElement.getAsInt());
      } else if (genericClass == String.class) {
        value = (T) jsonElement.getAsString();
      } else {
        value = context.deserialize(jsonElement, rawType);
      }

      if (Primitives.isPrimitive(genericClass)) {
        PrimitiveTypeAdapter typeAdapter = new PrimitiveTypeAdapter();
        value = typeAdapter.adaptType(value, rawType);
      }
      return value;
    }
  }
}
