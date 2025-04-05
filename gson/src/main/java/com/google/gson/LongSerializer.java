package com.google.gson;

public class LongSerializer {

  private LongSerializationStrategy strategy;

  public LongSerializer(LongSerializationStrategy strategy) {
    this.strategy = strategy;
  }

  public void setSerializationStrategy(LongSerializationStrategy strategy) {
    this.strategy = strategy;
  }

  public JsonElement serialize(Long value) {
    return strategy.serialize(value);
  }
}
