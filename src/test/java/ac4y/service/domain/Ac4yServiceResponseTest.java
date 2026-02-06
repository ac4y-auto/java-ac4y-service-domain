package ac4y.service.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Ac4yServiceResponse class
 * Tests response creation, status handling, and fluent API
 */
public class Ac4yServiceResponseTest {

    private Ac4yServiceResponse response;

    @BeforeEach
    public void setUp() {
        response = new Ac4yServiceResponse();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(response);
        assertTrue(response.hasResult(), "Response should have a default result");
    }

    @Test
    public void testErrorResponse() {
        String errorDescription = "Database connection failed";
        response.error(errorDescription);

        assertNotNull(response.getResult());
        assertTrue(response.itWasFailed());
        assertEquals(-1, response.getResult().getCode());
        assertEquals(errorDescription, response.getResult().getDescription());
    }

    @Test
    public void testSuccessResponse() {
        response.success();

        assertNotNull(response.getResult());
        assertTrue(response.itWasSuccessful());
        assertEquals(1, response.getResult().getCode());
        assertEquals("success!", response.getResult().getMessage());
    }

    @Test
    public void testNothingHappenedResponse() {
        String narrative = "No records matched the query";
        response.nothingHappened(narrative);

        assertNotNull(response.getResult());
        assertTrue(response.itWasNothingToDo());
        assertEquals(0, response.getResult().getCode());
        assertEquals(narrative, response.getResult().getDescription());
    }

    @Test
    public void testGetErrorProcessResult() {
        String errorDesc = "Validation failed";
        Ac4yProcessResult result = response.getErrorProcessResult(errorDesc);

        assertNotNull(result);
        assertTrue(result.itWasFailed());
        assertEquals(errorDesc, result.getDescription());
    }

    @Test
    public void testGetTimeoutProcessResult() {
        Ac4yProcessResult result = response.getTimeoutProcessResult();

        assertNotNull(result);
        assertTrue(result.itWasFailed());
        assertEquals("timeout!", result.getDescription());
    }

    @Test
    public void testGetSuccessProcessResult() {
        Ac4yProcessResult result = response.getSuccessProcessResult();

        assertNotNull(result);
        assertTrue(result.itWasSuccessful());
    }

    @Test
    public void testGetNothingHappenedProcessResult() {
        String narrative = "Empty result set";
        Ac4yProcessResult result = response.getNothingHappenedProcessResult(narrative);

        assertNotNull(result);
        assertTrue(result.itWasNothingToDo());
        assertEquals(narrative, result.getDescription());
    }

    @Test
    public void testGetSuccessServiceResponse() {
        Ac4yServiceResponse successResponse = response.getSuccessServiceResponse();

        assertNotNull(successResponse);
        assertTrue(successResponse.itWasSuccessful());
    }

    @Test
    public void testGetErrorServiceResponse() {
        String errorDescription = "Service unavailable";
        Ac4yServiceResponse errorResponse = response.getErrorServiceResponse(errorDescription);

        assertNotNull(errorResponse);
        assertTrue(errorResponse.itWasFailed());
        assertEquals(errorDescription, errorResponse.getResult().getDescription());
    }

    @Test
    public void testGetNothingHappenedServiceResponse() {
        String narrative = "No updates required";
        Ac4yServiceResponse nothingResponse = response.getNothingHappenedServiceResponse(narrative);

        assertNotNull(nothingResponse);
        assertTrue(nothingResponse.itWasNothingToDo());
    }

    @Test
    public void testFluentBuilderPattern() {
        String requestId = "REQ-SERV-001";
        String description = "Enhanced description";

        Ac4yServiceResponse builtResponse = response
            .success()
            .addedRequestId(requestId)
            .addedDescription(description);

        assertSame(response, builtResponse);
        assertEquals(requestId, response.getResult().getRequestId());
        assertEquals(description, response.getResult().getDescription());
    }

    @Test
    public void testAddedRequestId() {
        String requestId = "SERVICE-123";
        Ac4yServiceResponse returned = response.addedRequestId(requestId);

        assertSame(response, returned);
        assertEquals(requestId, response.getResult().getRequestId());
    }

    @Test
    public void testAddedDescription() {
        String description = "Detailed operation description";
        Ac4yServiceResponse returned = response.addedDescription(description);

        assertSame(response, returned);
        assertEquals(description, response.getResult().getDescription());
    }

    @Test
    public void testStatusChecks() {
        // Test error status
        response.error("Error occurred");
        assertTrue(response.itWasFailed());
        assertFalse(response.itWasSuccessful());
        assertFalse(response.itWasNothingToDo());

        // Test success status
        response = new Ac4yServiceResponse();
        response.success();
        assertFalse(response.itWasFailed());
        assertTrue(response.itWasSuccessful());
        assertFalse(response.itWasNothingToDo());

        // Test nothing to do status
        response = new Ac4yServiceResponse();
        response.nothingHappened("Nothing changed");
        assertFalse(response.itWasFailed());
        assertFalse(response.itWasSuccessful());
        assertTrue(response.itWasNothingToDo());
    }

    @Test
    public void testComplexFluentChain() {
        Ac4yServiceResponse complexResponse = new Ac4yServiceResponse()
            .error("Complex error scenario")
            .addedRequestId("COMPLEX-001")
            .addedDescription("Multi-step error handling");

        assertTrue(complexResponse.itWasFailed());
        assertEquals("COMPLEX-001", complexResponse.getResult().getRequestId());
        assertEquals("Multi-step error handling", complexResponse.getResult().getDescription());
    }

    @Test
    public void testThreadIdHandling() {
        String threadId = "THREAD-12345";
        response.setThreadId(threadId);

        assertEquals(threadId, response.getThreadId());
    }
}
