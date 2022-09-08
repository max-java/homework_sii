package com.example.homework.mvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.lang.Nullable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/sequence.sql", "/transaction_entity.sql", "/transaction_entity_data.sql"})
public abstract class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    protected URI makeUri(@Nullable MultiValueMap<String, String> params, String... pathSegments) {
        return UriComponentsBuilder.newInstance()
                .pathSegment(pathSegments)
                .queryParams(params)
                .build()
                .toUri();
    }

    protected URI makeUri(@NotNull String... pathSegments) {
        return makeUri(null, pathSegments);
    }

    protected MultiValueMap<String, String> makeQuery(String... param) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        for (int i = 0; i < param.length; i += 2) {
            params.add(param[i], param[i + 1]);
        }
        return params;
    }

    protected <T> T testGetEntityModel(URI uri,
                                       TypeReference<EntityModel<T>> typeReference) throws Exception {
        EntityModel<T> entityModel = testGet(uri, typeReference);
        return entityModel.getContent();
    }

    protected <T> List<T> testGetPagedModel(URI uri,
                                            TypeReference<PagedModel<EntityModel<T>>> typeReference) throws Exception {
        final PagedModel<EntityModel<T>> paged = testGet(uri, typeReference);
        return paged.getContent().stream().map(e -> e.getContent()).collect(Collectors.toList());
    }

    private  <T> T testGet(URI uri, TypeReference<T> valueTypeRef) throws Exception {
        MvcResult mvcResult =
                mockMvc
                        .perform(get(uri))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
        return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), valueTypeRef);
    }

    protected String testPost(URI uri, Object obj) throws Exception {
        MvcResult mvcResult =
                mockMvc
                        .perform(post(uri)
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(obj)))
                        .andExpect(status().isCreated())
                        .andReturn();
        String location = mvcResult.getResponse().getHeaderValue("Location").toString();
        return location.substring(location.indexOf(uri.toString()) + uri.toString().length() + 1);
    }

    protected void testPut(URI uri, Object obj) throws Exception {
        mockMvc
                .perform(put(uri)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(obj)))
                .andExpect(status().isNoContent());
    }

    protected void testPatch(URI uri, Object obj) throws Exception {
        mockMvc
                .perform(patch(uri).contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(obj)))
                .andExpect(status().isNoContent());
    }

    protected void testDelete(final URI uri) throws Exception {
        mockMvc
                .perform(delete(uri))
                .andExpect(status().isNoContent());
        mockMvc
                .perform(get(uri))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
