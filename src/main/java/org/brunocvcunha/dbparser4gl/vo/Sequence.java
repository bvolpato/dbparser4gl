/**
 * Copyright (C) 2015 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.brunocvcunha.dbparser4gl.vo;

public class Sequence {

  private String name;
  private int initial;
  private int increment;
  private boolean cycleOnLimit;
  private long minVal;
  private long maxVal;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getInitial() {
    return initial;
  }

  public void setInitial(int initial) {
    this.initial = initial;
  }

  public int getIncrement() {
    return increment;
  }

  public void setIncrement(int increment) {
    this.increment = increment;
  }

  public boolean isCycleOnLimit() {
    return cycleOnLimit;
  }

  public void setCycleOnLimit(boolean cycleOnLimit) {
    this.cycleOnLimit = cycleOnLimit;
  }

  public long getMinVal() {
    return minVal;
  }

  public void setMinVal(long minVal) {
    this.minVal = minVal;
  }

  public long getMaxVal() {
    return maxVal;
  }

  public void setMaxVal(long maxVal) {
    this.maxVal = maxVal;
  }

  @Override
  public String toString() {
    return "Sequence [name=" + name + ", initial=" + initial + ", increment=" + increment
        + ", cycleOnLimit=" + cycleOnLimit + ", minVal=" + minVal + ", maxVal=" + maxVal + "]";
  }
  
  
}
