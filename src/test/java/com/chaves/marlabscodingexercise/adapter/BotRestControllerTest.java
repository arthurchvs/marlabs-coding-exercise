package com.chaves.marlabscodingexercise.adapter;

import com.chaves.marlabscodingexercise.core.QuestionsCache;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BotRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuestionsCache questionsCache;

    @Test
    public void testGetQuestion() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.matchesRegex("Please sum the numbers( -?\\d+,)+ -?\\d+")));
    }

    @Test
    public void testCheckAnswer() throws Exception {
        String requestBody = "{" +
                "\"question\": \"Please sum the numbers 2, 3\"," +
                "\"answer\": 5" +
                "}";

        questionsCache.add("Please sum the numbers 2, 3");

        this.mockMvc
                .perform(post("/")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("Correct Answer"));
    }

    @Test
    public void testCheckAnswerBadRequest() throws Exception {
        String requestBody = "{" +
                "\"question\": \"Please sum the numbers a, d\"," +
                "\"answer\": 5" +
                "}";

        this.mockMvc
                .perform(post("/")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid Payload"));
    }

    @Test
    public void testCheckWrongAnswer() throws Exception {
        String requestBody = "{" +
                "\"question\": \"Please sum the numbers 2, 3\"," +
                "\"answer\": 50000" +
                "}";
        questionsCache.add("Please sum the numbers 2, 3");

        this.mockMvc
                .perform(post("/")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Wrong Answer"));
    }

    @Test
    public void testCheckQuestionNotInCache() throws Exception {
        String requestBody = "{" +
                "\"question\": \"Please sum the numbers 1, 2\"," +
                "\"answer\": 3" +
                "}";

        this.mockMvc
                .perform(post("/")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Question not in cache"));
    }


}