/**
 * Copyright (C) 2015 Red Hat, Inc.
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
package io.fabric8.openshift.client.creators;

import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.ResourceCreator;
import io.fabric8.openshift.api.model.OAuthAccessToken;
import io.fabric8.openshift.client.OpenShiftClient;
import io.fabric8.openshift.client.dsl.internal.OAuthAccessTokenOperationsImpl;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

@Component
@Service
public class OAuthAccessTokenCreator implements ResourceCreator<OAuthAccessToken> {
  @Override
  public Class<OAuthAccessToken> getKind() {
    return OAuthAccessToken.class;
  }

  @Override
  public OAuthAccessToken create(Client client, String namespace, OAuthAccessToken item) {
    try (OpenShiftClient osClient = client.adapt(OpenShiftClient.class)) {
      return new OAuthAccessTokenOperationsImpl(osClient, namespace, null, true, item).create();
    }
  }
}
