package ac4y.service.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Ac4yProcessResult class
 * Tests the fluent builder pattern and status checking methods
 */
public class Ac4yProcessResultTest {

    private Ac4yProcessResult result;

    @BeforeEach
    public void setUp() {
        result = new Ac4yProcessResult();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(result);
    }

    @Test
    public void testParameterizedConstructor() {
        Ac4yProcessResult result = new Ac4yProcessResult(1, "Success", "Operation completed");
        assertEquals(1, result.getCode());
        assertEquals("Success", result.getMessage());
        assertEquals("Operation completed", result.getDescription());
    }

    @Test
    public void testErrorStatus() {
        result.error("Test error description");

        assertEquals(-1, result.getCode());
        assertEquals("error!", result.getMessage());
        assertEquals("Test error description", result.getDescription());
        assertTrue(result.itWasFailed());
        assertFalse(result.itWasSuccessful());
        assertFalse(result.itWasNothingToDo());
    }

    @Test
    public void testSuccessStatus() {
        result.success();

        assertEquals(1, result.getCode());
        assertEquals("success!", result.getMessage());
        assertTrue(result.itWasSuccessful());
        assertFalse(result.itWasFailed());
        assertFalse(result.itWasNothingToDo());
    }

    @Test
    public void testNothingHappenedWithMessage() {
        result.nothingHappened("Custom message", "Custom narrative");

        assertEquals(0, result.getCode());
        assertEquals("Custom message", result.getMessage());
        assertEquals("Custom narrative", result.getDescription());
        assertTrue(result.itWasNothingToDo());
        assertFalse(result.itWasFailed());
        assertFalse(result.itWasSuccessful());
    }

    @Test
    public void testNothingHappenedWithNullMessage() {
        result.nothingHappened(null, "Narrative only");

        assertEquals(0, result.getCode());
        assertEquals("nothing happened!", result.getMessage());
        assertEquals("Narrative only", result.getDescription());
    }

    @Test
    public void testNothingHappenedWithNullNarrative() {
        result.nothingHappened("Custom message", null);

        assertEquals(0, result.getCode());
        assertEquals("Custom message", result.getMessage());
        assertNull(result.getDescription());
    }

    @Test
    public void testFluentBuilderPattern() {
        String requestId = "REQ-12345";
        String description = "Updated description";

        Ac4yProcessResult builtResult = result
            .success()
            .addedRequestId(requestId)
            .addedDescription(description);

        assertSame(result, builtResult); // Verify fluent chaining
        assertEquals(requestId, result.getRequestId());
        assertEquals(description, result.getDescription());
        assertTrue(result.itWasSuccessful());
    }

    @Test
    public void testAddedRequestId() {
        String requestId = "TEST-REQ-001";
        Ac4yProcessResult returnedResult = result.addedRequestId(requestId);

        assertEquals(requestId, result.getRequestId());
        assertSame(result, returnedResult);
    }

    @Test
    public void testAddedDescription() {
        String description = "Test description";
        Ac4yProcessResult returnedResult = result.addedDescription(description);

        assertEquals(description, result.getDescription());
        assertSame(result, returnedResult);
    }

    @Test
    public void testStatusCodeChecks() {
        // Test error code
        result.setCode(-1);
        assertTrue(result.itWasFailed());
        assertFalse(result.itWasSuccessful());
        assertFalse(result.itWasNothingToDo());

        // Test success code
        result.setCode(1);
        assertFalse(result.itWasFailed());
        assertTrue(result.itWasSuccessful());
        assertFalse(result.itWasNothingToDo());

        // Test nothing to do code
        result.setCode(0);
        assertFalse(result.itWasFailed());
        assertFalse(result.itWasSuccessful());
        assertTrue(result.itWasNothingToDo());
    }

    @Test
    public void testComplexFluentChain() {
        Ac4yProcessResult complexResult = new Ac4yProcessResult()
            .error("Initial error")
            .addedRequestId("REQ-999")
            .addedDescription("Detailed error information");

        assertTrue(complexResult.itWasFailed());
        assertEquals("REQ-999", complexResult.getRequestId());
        assertEquals("Detailed error information", complexResult.getDescription());
    }
}
