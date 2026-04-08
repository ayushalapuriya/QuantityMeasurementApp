package com.app.quantitymeasurement;

import com.app.quantitymeasurement.dto.QuantityRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class QuantitySecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // ✅ CREATE USER BEFORE EACH TEST
    @BeforeEach
    void setupUser() throws Exception {

        String userJson = """
            {
              "email":"test@gmail.com",
              "password":"1234"
            }
            """;

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson));
    }

    // ---------- LOGIN TEST ----------
    @Test
    void shouldLoginAndReturnToken() throws Exception {

        String loginJson = """
            {
              "email":"test@gmail.com",
              "password":"1234"
            }
            """;

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    // ---------- SECURED API TEST ----------
    @Test
    void shouldAccessProtectedAddAPI() throws Exception {

        String loginJson = """
            {
              "email":"test@gmail.com",
              "password":"1234"
            }
            """;

        String response = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String token = objectMapper
                .readTree(response)
                .get("token")
                .asText();

        QuantityRequestDTO dto = new QuantityRequestDTO();
        dto.value1 = 1;
        dto.unit1 = "FEET";
        dto.value2 = 12;
        dto.unit2 = "INCHES";
        dto.targetUnit = "FEET";

        mockMvc.perform(post("/quantity/add")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }
}