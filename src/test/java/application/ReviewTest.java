package application;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReviewTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeAll
    public void testCreateReview() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/register")
                        .param("username", "Sandwich")
                        .param("password", "mysandwich"))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .param("username", "Sandwich")
                        .param("password", "mysandwich"))
                .andExpect(status().isOk());

        MockHttpSession mockHttpSession = new MockHttpSession(webApplicationContext.getServletContext());

        mockHttpSession.setAttribute("username", "Sandwich");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/createproduct/")
                        .param("name", "Sandwich")
                        .param("description", "This is a sandwich")
                        .param("link", "www.google.com"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Success")));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/createreview/2")
                        .session(mockHttpSession)
                        .param("reviewRating", "8")
                        .param("reviewComment", "This is too smooth"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Success")));
    }

    @Test
    public void testViewReviews() throws Exception {
        this.mockMvc.perform(get("/viewreviews/2")).andExpect(status().isOk())
                .andExpect(content().string(containsString("8"))).andExpect(content()
                        .string(containsString("This is too smooth")));
    }
}
