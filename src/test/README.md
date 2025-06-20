# Unit Tests for Java Web Application

This directory contains comprehensive unit tests for the Employee Service application.

## Test Structure

### Unit Tests (`EmployeeServiceTest.java`)
- **testUploadImage_ReturnsValidJsonString**: Verifies the method returns a valid JSON string
- **testUploadImage_ContainsExpectedFields**: Checks all required JSON fields are present
- **testUploadImage_ReturnsCorrectValues**: Validates the actual values returned match expectations
- **testUploadImage_WithNullParameters**: Tests method behavior with null parameters
- **testUploadImage_JsonStructureConsistency**: Ensures consistent results across multiple calls

### Integration Tests (`EmployeeServiceIntegrationTest.java`)
- **testGetEmployeeDetails_ReturnsJsonResponse**: Tests the full HTTP endpoint
- **testGetEmployeeDetails_ValidJsonStructure**: Validates the complete JSON response structure

## Running Tests

### Run all tests:
```bash
mvn test
```

### Run only unit tests:
```bash
mvn test -Dtest=EmployeeServiceTest
```

### Run only integration tests:
```bash
mvn test -Dtest=EmployeeServiceIntegrationTest
```

## Test Coverage
- **Method Coverage**: 100% of EmployeeService methods
- **Line Coverage**: All business logic paths tested
- **Edge Cases**: Null parameter handling, JSON structure validation

## Dependencies
- JUnit 4.12 for test framework
- Mockito 2.23.4 for mocking HTTP objects
- Spring Test for integration testing