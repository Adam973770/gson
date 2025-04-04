package com.google.gson;

public class DefaultLongSerializationStrategy implements LongSerializationStrategy {
  @Override
  public JsonElement serialize(Long value) {
    if (value == null) {
      return JsonNull.INSTANCE;
    }
    return new JsonPrimitive(value);
  }
}
