/*
 * Copyright (C) 2011 Google Inc.
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
package com.google.gson.metrics;

import com.google.common.base.Objects;

/**
 * Class with a bunch of primitive fields
 *
 * @author Inderjeet Singh
 */
public class BagOfPrimitives {
  public static final long DEFAULT_VALUE = 0;
  public long longValue;
  public float floatValue;
  public boolean booleanValue;
  public String stringValue;

  public BagOfPrimitives() {
    this(DEFAULT_VALUE, 0, false, "");
  }

  public BagOfPrimitives(
      long longValue, float floatValue, boolean booleanValue, String stringValue) {
    this.longValue = longValue;
    this.floatValue = floatValue;
    this.booleanValue = booleanValue;
    this.stringValue = stringValue;
  }

  public String getExpectedJson() {
    return "{"
        + "\"longValue\":"
        + longValue
        + ","
        + "\"floatValue\":"
        + floatValue
        + ","
        + "\"booleanValue\":"
        + booleanValue
        + ","
        + "\"stringValue\":\""
        + stringValue
        + "\""
        + "}";
  }

  @Override
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = prime * result + (booleanValue ? 1231 : 1237);
    result = prime * result + Float.floatToIntBits(floatValue);
    result = prime * result + (int) (longValue ^ (longValue >>> 32));
    result = prime * result + ((stringValue == null) ? 0 : stringValue.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BagOfPrimitives)) {
      return false;
    }
    BagOfPrimitives that = (BagOfPrimitives) o;
    return longValue == that.longValue
        && Float.compare(floatValue, that.floatValue) == 0
        && booleanValue == that.booleanValue
        && Objects.equal(stringValue, that.stringValue);
  }

  @Override
  public String toString() {
    return String.format(
        "(longValue=%d,floatValue=%f,booleanValue=%b,stringValue=%s)",
        longValue, floatValue, booleanValue, stringValue);
  }
}
