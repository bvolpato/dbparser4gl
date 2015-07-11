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

public class Field {

  private String name;
  private String type;
  private String description;
  private String format;
  private String initial;
  private String label;
  private int position;
  private int maxWidth;
  private String columnLabel;
  private String help;
  private int order;
  private boolean mandatory;
  private String validation;
  private String validationMessage;
  private boolean extent;
  private int extentValue;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getDescription() {
    if (description == null) {
      description = "";
    }

    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public String getInitial() {
    if (initial == null) {
      initial = "";
    }

    return initial;
  }

  public void setInitial(String initial) {
    this.initial = initial;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public int getMaxWidth() {
    return maxWidth;
  }

  public void setMaxWidth(int maxWidth) {
    this.maxWidth = maxWidth;
  }

  public String getColumnLabel() {
    return columnLabel;
  }

  public void setColumnLabel(String columnLabel) {
    this.columnLabel = columnLabel;
  }

  public String getHelp() {
    return help;
  }

  public void setHelp(String help) {
    this.help = help;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public boolean isMandatory() {
    return mandatory;
  }

  public void setMandatory(boolean mandatory) {
    this.mandatory = mandatory;
  }

  public String getValidation() {
    return validation;
  }

  public void setValidation(String validation) {
    this.validation = validation;
  }

  public String getValidationMessage() {
    return validationMessage;
  }

  public void setValidationMessage(String validationMessage) {
    this.validationMessage = validationMessage;
  }

  public boolean isExtent() {
    return extent;
  }

  public void setExtent(boolean extent) {
    this.extent = extent;
  }

  public int getExtentValue() {
    return extentValue;
  }

  public void setExtentValue(int extentValue) {
    this.extentValue = extentValue;
  }

  @Override
  public String toString() {
    return "Field [name=" + name + ", type=" + type + ", description=" + description + ", format="
        + format + ", initial=" + initial + ", label=" + label + ", position=" + position
        + ", maxWidth=" + maxWidth + ", columnLabel=" + columnLabel + ", help=" + help + ", order="
        + order + ", mandatory=" + mandatory + ", validation=" + validation
        + ", validationMessage=" + validationMessage + ", extent=" + extent + ", extentValue="
        + extentValue + "]";
  }

  


}
