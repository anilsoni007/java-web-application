package com.mt.services;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class EmployeeServiceIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetEmployeeDetails_ReturnsJsonResponse() throws Exception {
        // Act & Assert
        MvcResult result = mockMvc.perform(get("/employee/getEmployeeDetails"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertNotNull("Response content should not be null", content);
        
        // Verify JSON structure
        JSONObject jsonResponse = new JSONObject(content);
        assertTrue("Should contain Name field", jsonResponse.has("Name"));
        assertEquals("Name should be Anil Soni", "Anil Soni", jsonResponse.getString("Name"));
    }

    @Test
    public void testGetEmployeeDetails_ValidJsonStructure() throws Exception {
        // Act
        MvcResult result = mockMvc.perform(get("/employee/getEmployeeDetails"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        JSONObject jsonResponse = new JSONObject(content);

        // Assert all expected fields are present
        String[] expectedFields = {"Name", "Calling Name", "DOB", "Hobbies", "Places he like"};
        for (String field : expectedFields) {
            assertTrue("Should contain field: " + field, jsonResponse.has(field));
            assertNotNull("Field should not be null: " + field, jsonResponse.getString(field));
            assertFalse("Field should not be empty: " + field, jsonResponse.getString(field).isEmpty());
        }
    }
}