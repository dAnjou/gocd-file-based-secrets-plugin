/*
 * Copyright 2019 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cd.go.plugin.secret.filebased.executors;

import cd.go.plugin.secret.filebased.db.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GetIconRequestExecutorTest {
    @Test
    void shouldReturnIconInBase64Encoding() {
        GoPluginApiResponse response = new GetIconRequestExecutor().execute();
        Map<String, String> hashMap = new Gson().fromJson(response.responseBody(), new TypeToken<Map<String, String>>() {
        }.getType());
        assertThat(hashMap.size()).isEqualTo(2);
        assertThat(hashMap.get("content_type")).isEqualTo("image/png");
        assertThat(Util.readResourceBytes("/plugin-icon.png")).isEqualTo(Base64.getDecoder().decode(hashMap.get("data")));
    }
}
