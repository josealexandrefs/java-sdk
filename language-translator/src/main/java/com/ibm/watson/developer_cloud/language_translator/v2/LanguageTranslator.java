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
package com.ibm.watson.developer_cloud.language_translator.v2;

import com.google.gson.JsonObject;
import com.ibm.watson.developer_cloud.http.RequestBuilder;
import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.language_translator.v2.model.CreateModelOptions;
import com.ibm.watson.developer_cloud.language_translator.v2.model.DeleteModelOptions;
import com.ibm.watson.developer_cloud.language_translator.v2.model.GetModelOptions;
import com.ibm.watson.developer_cloud.language_translator.v2.model.IdentifiableLanguages;
import com.ibm.watson.developer_cloud.language_translator.v2.model.IdentifiedLanguages;
import com.ibm.watson.developer_cloud.language_translator.v2.model.IdentifyOptions;
import com.ibm.watson.developer_cloud.language_translator.v2.model.ListIdentifiableLanguagesOptions;
import com.ibm.watson.developer_cloud.language_translator.v2.model.ListModelsOptions;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationModel;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationModels;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;
import com.ibm.watson.developer_cloud.service.WatsonService;
import com.ibm.watson.developer_cloud.util.GsonSingleton;
import com.ibm.watson.developer_cloud.util.RequestUtils;
import com.ibm.watson.developer_cloud.util.ResponseConverterUtils;
import com.ibm.watson.developer_cloud.util.Validator;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Language Translator translates text from one language to another. The service offers multiple domain-specific models
 * that you can customize based on your unique terminology and language. Use Language Translator to take news from
 * across the globe and present it in your language, communicate with your customers in their own language, and more.
 *
 * @version v2
 * @see <a href="http://www.ibm.com/watson/developercloud/language-translator.html">Language Translator</a>
 */
public class LanguageTranslator extends WatsonService {

  private static final String SERVICE_NAME = "language_translator";
  private static final String URL = "https://gateway.watsonplatform.net/language-translator/api";

  /**
   * Instantiates a new `LanguageTranslator`.
   *
   */
  public LanguageTranslator() {
    super(SERVICE_NAME);
    if ((getEndPoint() == null) || getEndPoint().isEmpty()) {
      setEndPoint(URL);
    }
  }

  /**
   * Instantiates a new `LanguageTranslator` with username and password.
   *
   * @param username the username
   * @param password the password
   */
  public LanguageTranslator(String username, String password) {
    this();
    setUsernameAndPassword(username, password);
  }

  /**
   * Translate.
   *
   * Translates the input text from the source language to the target language.
   *
   * @param translateOptions the {@link TranslateOptions} containing the options for the call
   * @return a {@link ServiceCall} with a response type of {@link TranslationResult}
   */
  public ServiceCall<TranslationResult> translate(TranslateOptions translateOptions) {
    Validator.notNull(translateOptions, "translateOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post("/v2/translate");
    final JsonObject contentJson = new JsonObject();
    contentJson.add("text", GsonSingleton.getGson().toJsonTree(translateOptions.text()));
    if (translateOptions.modelId() != null) {
      contentJson.addProperty("model_id", translateOptions.modelId());
    }
    if (translateOptions.source() != null) {
      contentJson.addProperty("source", translateOptions.source());
    }
    if (translateOptions.target() != null) {
      contentJson.addProperty("target", translateOptions.target());
    }
    builder.bodyJson(contentJson);
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(TranslationResult.class));
  }

  /**
   * Identify language.
   *
   * Identifies the language of the input text.
   *
   * @param identifyOptions the {@link IdentifyOptions} containing the options for the call
   * @return a {@link ServiceCall} with a response type of {@link IdentifiedLanguages}
   */
  public ServiceCall<IdentifiedLanguages> identify(IdentifyOptions identifyOptions) {
    Validator.notNull(identifyOptions, "identifyOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post("/v2/identify");
    builder.bodyContent(identifyOptions.text(), "text/plain");
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(IdentifiedLanguages.class));
  }

  /**
   * Identify language.
   *
   * Identifies the language of the input text.
   *
   * @param identifyOptions the {@link IdentifyOptions} containing the options for the call
   * @return a {@link ServiceCall} with a response type of {@link IdentifiedLanguages}
   */
  public ServiceCall<IdentifiedLanguages> identifyPlain(IdentifyOptions identifyOptions) {
    Validator.notNull(identifyOptions, "identifyOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post("/v2/identify");
    builder.bodyContent(identifyOptions.text(), "text/plain");
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(IdentifiedLanguages.class));
  }

  /**
   * List identifiable languages.
   *
   * Lists the languages that the service can identify. Returns the language code (for example, `en` for English or `es`
   * for Spanish) and name of each language.
   *
   * @param listIdentifiableLanguagesOptions the {@link ListIdentifiableLanguagesOptions} containing the options for the
   *          call
   * @return a {@link ServiceCall} with a response type of {@link IdentifiableLanguages}
   */
  public ServiceCall<IdentifiableLanguages> listIdentifiableLanguages(
      ListIdentifiableLanguagesOptions listIdentifiableLanguagesOptions) {
    RequestBuilder builder = RequestBuilder.get("/v2/identifiable_languages");
    if (listIdentifiableLanguagesOptions != null) {
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(IdentifiableLanguages.class));
  }

  /**
   * List identifiable languages.
   *
   * Lists the languages that the service can identify. Returns the language code (for example, `en` for English or `es`
   * for Spanish) and name of each language.
   *
   * @return a {@link ServiceCall} with a response type of {@link IdentifiableLanguages}
   */
  public ServiceCall<IdentifiableLanguages> listIdentifiableLanguages() {
    return listIdentifiableLanguages(null);
  }

  /**
   * Create model.
   *
   * Uploads a TMX glossary file on top of a domain to customize a translation model. Depending on the size of the file,
   * training can range from minutes for a glossary to several hours for a large parallel corpus. Glossary files must be
   * less than 10 MB. The cumulative file size of all uploaded glossary and corpus files is limited to 250 MB.
   *
   * @param createModelOptions the {@link CreateModelOptions} containing the options for the call
   * @return a {@link ServiceCall} with a response type of {@link TranslationModel}
   */
  public ServiceCall<TranslationModel> createModel(CreateModelOptions createModelOptions) {
    Validator.notNull(createModelOptions, "createModelOptions cannot be null");
    Validator.isTrue((createModelOptions.forcedGlossary() != null) || (createModelOptions.parallelCorpus() != null)
        || (createModelOptions.monolingualCorpus() != null),
        "At least one of forcedGlossary, parallelCorpus, or monolingualCorpus must be supplied.");
    RequestBuilder builder = RequestBuilder.post("/v2/models");
    builder.query("base_model_id", createModelOptions.baseModelId());
    if (createModelOptions.name() != null) {
      builder.query("name", createModelOptions.name());
    }
    MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
    multipartBuilder.setType(MultipartBody.FORM);
    if (createModelOptions.forcedGlossary() != null) {
      RequestBody forcedGlossaryBody = RequestUtils.inputStreamBody(createModelOptions.forcedGlossary(),
          "application/octet-stream");
      multipartBuilder.addFormDataPart("forced_glossary", createModelOptions.forcedGlossaryFilename(),
          forcedGlossaryBody);
    }
    if (createModelOptions.parallelCorpus() != null) {
      RequestBody parallelCorpusBody = RequestUtils.inputStreamBody(createModelOptions.parallelCorpus(),
          "application/octet-stream");
      multipartBuilder.addFormDataPart("parallel_corpus", createModelOptions.parallelCorpusFilename(),
          parallelCorpusBody);
    }
    if (createModelOptions.monolingualCorpus() != null) {
      RequestBody monolingualCorpusBody = RequestUtils.inputStreamBody(createModelOptions.monolingualCorpus(),
          "text/plain");
      multipartBuilder.addFormDataPart("monolingual_corpus", createModelOptions.monolingualCorpusFilename(),
          monolingualCorpusBody);
    }
    builder.body(multipartBuilder.build());
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(TranslationModel.class));
  }

  /**
   * Delete model.
   *
   * Deletes a custom translation model.
   *
   * @param deleteModelOptions the {@link DeleteModelOptions} containing the options for the call
   * @return a {@link ServiceCall} with a response type of Void
   */
  public ServiceCall<Void> deleteModel(DeleteModelOptions deleteModelOptions) {
    Validator.notNull(deleteModelOptions, "deleteModelOptions cannot be null");
    RequestBuilder builder = RequestBuilder.delete(String.format("/v2/models/%s", deleteModelOptions.modelId()));
    return createServiceCall(builder.build(), ResponseConverterUtils.getVoid());
  }

  /**
   * Get model details.
   *
   * Gets information about a translation model, including training status for custom models.
   *
   * @param getModelOptions the {@link GetModelOptions} containing the options for the call
   * @return a {@link ServiceCall} with a response type of {@link TranslationModel}
   */
  public ServiceCall<TranslationModel> getModel(GetModelOptions getModelOptions) {
    Validator.notNull(getModelOptions, "getModelOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v2/models/%s", getModelOptions.modelId()));
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(TranslationModel.class));
  }

  /**
   * List models.
   *
   * Lists available translation models.
   *
   * @param listModelsOptions the {@link ListModelsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a response type of {@link TranslationModels}
   */
  public ServiceCall<TranslationModels> listModels(ListModelsOptions listModelsOptions) {
    RequestBuilder builder = RequestBuilder.get("/v2/models");
    if (listModelsOptions != null) {
      if (listModelsOptions.source() != null) {
        builder.query("source", listModelsOptions.source());
      }
      if (listModelsOptions.target() != null) {
        builder.query("target", listModelsOptions.target());
      }
      if (listModelsOptions.defaultModels() != null) {
        builder.query("default", String.valueOf(listModelsOptions.defaultModels()));
      }
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(TranslationModels.class));
  }

  /**
   * List models.
   *
   * Lists available translation models.
   *
   * @return a {@link ServiceCall} with a response type of {@link TranslationModels}
   */
  public ServiceCall<TranslationModels> listModels() {
    return listModels(null);
  }

}
