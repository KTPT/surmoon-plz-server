package com.ktpt.surmoon.service.survey.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktpt.surmoon.service.survey.adapter.presentation.advice.ErrorResponse;
import com.ktpt.surmoon.service.survey.domain.model.member.Member;
import com.ktpt.surmoon.service.survey.domain.model.member.MemberRepository;
import com.ktpt.surmoon.service.survey.domain.model.survey.Survey;
import com.ktpt.surmoon.service.survey.domain.model.survey.SurveyRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IntegrationTest {
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    protected <T, U> U post(T request, String uri, Class<U> response) {
        try {
            String body = objectMapper.writeValueAsString(request);

            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(body))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.header().string(HttpHeaders.LOCATION, Matchers.matchesRegex(uri + "/\\d*")))
                    .andReturn();

            return objectMapper.readValue(result.getResponse().getContentAsString(), response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("test fails");
        }
    }

    protected <T> ErrorResponse postFails(T request, String uri) {
        try {
            String body = objectMapper.writeValueAsString(request);

            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(body))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andReturn();

            return objectMapper.readValue(result.getResponse().getContentAsString(), ErrorResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("test fails");
        }
    }

    protected <T, U> U put(T request, String uri, Class<U> response) {
        try {
            String body = objectMapper.writeValueAsString(request);

            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(body))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();

            return objectMapper.readValue(result.getResponse().getContentAsString(), response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("test fails");
        }
    }

    protected <T> ErrorResponse putFails(T request, String uri) {
        try {
            String body = objectMapper.writeValueAsString(request);

            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(body))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andReturn();

            return objectMapper.readValue(result.getResponse().getContentAsString(), ErrorResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("test fails");
        }
    }

    protected Member findAnyMember() {
        return memberRepository.findAll().stream()
                .findAny()
                .orElseThrow(() -> new AssertionError("there is no member"));
    }

    protected Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    protected Survey findAnySurvey() {
        return surveyRepository.findAll().stream()
                .findAny()
                .orElseThrow(() -> new AssertionError("there is no survey"));
    }
}
