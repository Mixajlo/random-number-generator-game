package com.xessable.interview.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xessable.interview.api.dto.GuessedNumber;
import com.xessable.interview.model.GameState;
import com.xessable.interview.service.NumberGeneratorService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NumberGeneratorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private NumberGeneratorService numberGeneratorService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Test for verifying the functionality of the startNewGame endpoint,
     * which ensures the controller responds with the expected message.
     */
    @Test
    void startNewGame_ShouldReturnExpectedMessage() throws Exception {
        mockMvc.perform(get("/api"))
                .andExpect(status().isOk())
                .andExpect(content().string("Greetings from Spring Boot!"));
    }

    /**
     * Test for verifying successful guessing of a number within valid range.
     */
    @Test
    void guessNumber_WithValidNumber_ShouldReturnExpectedResponse() throws Exception {
        GuessedNumber guessedNumber = new GuessedNumber(500);
        GameState expectedResponse = new GameState(500);
        HttpSession mockSession = Mockito.mock(HttpSession.class);


        Mockito.when(numberGeneratorService.guessNumber(any(GuessedNumber.class), eq(mockSession)))
                .thenReturn(expectedResponse);

        mockMvc.perform(post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(guessedNumber)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
    }

    /**
     * Test for verifying that guessing a number outside the valid range results in a bad request.
     */
    @Test
    void guessNumber_WithInvalidNumber_ShouldReturnBadRequest() throws Exception {
        GuessedNumber guessedNumber = new GuessedNumber(1500);

        mockMvc.perform(post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(guessedNumber)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test for verifying that guessing a null number results in a bad request.
     */
    @Test
    void guessNumber_WithNullNumber_ShouldReturnBadRequest() throws Exception {
        GuessedNumber guessedNumber = new GuessedNumber(null);

        mockMvc.perform(post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(guessedNumber)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test for verifying that guessing a number below the minimum value results in a bad request.
     */
    @Test
    void guessNumber_WithNumberBelowMinimum_ShouldReturnBadRequest() throws Exception {
        GuessedNumber guessedNumber = new GuessedNumber(0);

        mockMvc.perform(post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(guessedNumber)))
                .andExpect(status().isBadRequest());
    }
}