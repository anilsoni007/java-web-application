package com.mt.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EmployeeServiceTest {

    private EmployeeService employeeService;
    
    @Mock
    private HttpServletRequest mockRequest;
    
    @Mock
    private HttpServletResponse mockResponse;
    
    @Mock
    private HttpSession mockSession;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeService = new EmployeeService();
    }

    @Test
    public void testUploadImage_ReturnsValidJsonString() throws JSONException {
        // Act
        String result = employeeService.uploadImage(mockRequest, mockResponse, mockSession);
        
        // Assert
        assertNotNull("Result should not be null", result);
        assertFalse("Result should not be empty", result.isEmpty());
        
        // Verify it's valid JSON
        JSONObject jsonResult = new JSONObject(result);
        assertNotNull("Should be valid JSON", jsonResult);
    }

    @Test
    public void testUploadImage_ContainsExpectedFields() throws JSONException {
        // Act
        String result = employeeService.uploadImage(mockRequest, mockResponse, mockSession);
        JSONObject jsonResult = new JSONObject(result);
        
        // Assert
        assertTrue("Should contain Name field", jsonResult.has("Name"));
        assertTrue("Should contain Calling Name field", jsonResult.has("Calling Name"));
        assertTrue("Should contain DOB field", jsonResult.has("DOB"));
        assertTrue("Should contain Hobbies field", jsonResult.has("Hobbies"));
        assertTrue("Should contain Places he like field", jsonResult.has("Places he like"));
    }

    @Test
    public void testUploadImage_ReturnsCorrectValues() throws JSONException {
        // Act
        String result = employeeService.uploadImage(mockRequest, mockResponse, mockSession);
        JSONObject jsonResult = new JSONObject(result);
        
        // Assert
        assertEquals("Name should be Anil Soni", "Anil Soni", jsonResult.getString("Name"));
        assertEquals("Calling Name should be Anil", "Anil", jsonResult.getString("Calling Name"));
        assertEquals("DOB should be 08-Nov-2011", "08-Nov-2011", jsonResult.getString("DOB"));
        assertEquals("Hobbies should match expected value", "Reading Technical Blogs,Teaching", jsonResult.getString("Hobbies"));
        assertEquals("Places he like should match expected value", "His native place", jsonResult.getString("Places he like"));
    }

    @Test
    public void testUploadImage_WithNullParameters() throws JSONException {
        // Act
        String result = employeeService.uploadImage(null, null, null);
        
        // Assert
        assertNotNull("Result should not be null even with null parameters", result);
        JSONObject jsonResult = new JSONObject(result);
        assertEquals("Should still return correct name", "Anil Soni", jsonResult.getString("Name"));
    }

    @Test
    public void testUploadImage_JsonStructureConsistency() throws JSONException {
        // Act - Call multiple times
        String result1 = employeeService.uploadImage(mockRequest, mockResponse, mockSession);
        String result2 = employeeService.uploadImage(mockRequest, mockResponse, mockSession);
        
        // Assert
        assertEquals("Results should be consistent across calls", result1, result2);
        
        JSONObject json1 = new JSONObject(result1);
        JSONObject json2 = new JSONObject(result2);
        
        assertEquals("JSON objects should have same number of keys", json1.length(), json2.length());
    }
}