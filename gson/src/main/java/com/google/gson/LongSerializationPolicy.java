package com.google.gson;

public class LongSerializationPolicy {

  // Stratégie par défaut (DEFAULT)
  public static final LongSerializationStrategy DEFAULT = new DefaultLongSerializationStrategy();

  // Stratégie STRING
  public static final LongSerializationStrategy STRING = new StringLongSerializationStrategy();

  private LongSerializer serializer;

  public LongSerializationPolicy(LongSerializationStrategy strategy) {
    this.serializer = new LongSerializer(strategy);
  }

  public JsonElement serialize(Long value) {
    return serializer.serialize(value);
  }

  public void setSerializationStrategy(LongSerializationStrategy strategy) {
    this.serializer.setSerializationStrategy(strategy);
  }

  public static JsonElement serializeWithDefault(Long value) {
    return new LongSerializer(DEFAULT).serialize(value);
  }

  public static JsonElement serializeWithString(Long value) {
    return new LongSerializer(STRING).serialize(value);
  }
}
