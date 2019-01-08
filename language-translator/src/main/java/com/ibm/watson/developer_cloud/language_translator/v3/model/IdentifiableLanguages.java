/*
 * Copyright 2018 IBM Corp. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.ibm.watson.developer_cloud.language_translator.v3.model;

import java.util.List;

import com.ibm.watson.developer_cloud.service.model.GenericModel;

/**
 * IdentifiableLanguages.
 */
public class IdentifiableLanguages extends GenericModel {

  private List<IdentifiableLanguage> languages;

  /**
   * Gets the languages.
   *
   * A list of all languages that the service can identify.
   *
   * @return the languages
   */
  public List<IdentifiableLanguage> getLanguages() {
    return languages;
  }
}