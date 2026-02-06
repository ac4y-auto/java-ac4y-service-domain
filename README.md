# java-ac4y-service-domain

Core service domain module - service algebra, process results, request/response patterns with fluent API.

## Maven Dependency

```xml
<dependency>
    <groupId>ac4y</groupId>
    <artifactId>ac4y-service-domain</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Overview

The foundational service layer providing:
- **Ac4yProcessResult** - Operation result with status codes (-1=error, 0=nothing, 1=success) and fluent builder
- **Ac4yServiceResponse** - Service response with factory methods and fluent API
- **Ac4yService** - JPA Entity with auto-initialized request/response
- **Ac4yRestServiceClient** - REST client adapter for HTTP requests

## Dependencies

- `ac4y:ac4y-base:1.1.0` (base classes, context, database)

## Package Structure

- `ac4y.service.algebra` - Algebra classes (ProcessResult, Service, Request, Response)
- `ac4y.service.domain` - Domain classes with fluent API
- `ac4y.service.adapter` - REST service client

## Status Codes

| Code | Status | Method |
|------|--------|--------|
| -1 | Error | `itWasFailed()` |
| 0 | Nothing happened | `itWasNothingToDo()` |
| 1 | Success | `itWasSuccessful()` |

## Usage

```java
// Fluent process result
Ac4yProcessResult result = new Ac4yProcessResult()
    .success()
    .addedRequestId("REQ-001")
    .addedDescription("Completed");

// Service response
Ac4yServiceResponse response = new Ac4yServiceResponse()
    .error("Database unavailable");
```

## Build

```bash
mvn clean install
mvn test
```

## Origin

Extracted from `IJAc4yServiceModule/IJAc4yServiceDomain` module.

---
**Version:** 1.0.0
