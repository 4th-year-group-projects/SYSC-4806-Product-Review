package application;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    //private Product product = new Product("Sandwich", "This is a sandwich", "www.google.com");
    // I really don't understand how to pass the object as form content with MockMvcRequestBuilders

    @BeforeAll
    public void testCreateProduct()  throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .post("/createproduct/")
                        .param("name", "Sandwich")
                        .param("description", "This is a sandwich")
                        .param("link", "www.google.com"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Success")));
    }

    @Test
    public void testGetProducts() throws Exception {
        this.mockMvc.perform(get("/viewproducts/")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Sandwich")));
    }
}
