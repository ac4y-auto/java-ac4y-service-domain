# java-ac4y-service-domain - Architektúra Dokumentáció

## Áttekintés

Alap service domain modul. Fluent API-val, process result kezeléssel és request/response mintákkal.

**Verzió:** 1.0.0
**Java verzió:** 1.8
**Szervezet:** ac4y-auto

## Fő Komponensek

### 1. Ac4yProcessResult

**Csomag:** `ac4y.service.domain`

Operáció eredmény állapotkódokkal és fluent builder-rel.

**Állapotkódok:**
- `-1` = Hiba (`itWasFailed()`)
- `0` = Semmi sem történt (`itWasNothingToDo()`)
- `1` = Sikeres (`itWasSuccessful()`)

**Fluent metódusok:**
- `error(description)` → kód -1
- `success()` → kód 1
- `nothingHappened(message, narrative)` → kód 0
- `addedRequestId(requestId)` → request ID hozzáadás
- `addedDescription(description)` → leírás hozzáadás

### 2. Ac4yServiceResponse

**Csomag:** `ac4y.service.domain`

Service válasz fluent API-val és factory metódusokkal.

**Factory metódusok:**
- `getSuccessServiceResponse()`
- `getErrorServiceResponse(description)`
- `getNothingHappenedServiceResponse(narrative)`

**Fluent metódusok:**
- `error(description)`, `success()`, `nothingHappened(narrative)`
- `addedRequestId()`, `addedDescription()`

### 3. Ac4yService

JPA `@Entity` és JAXB `@XmlRootElement` annotált. Konstruktorban auto-init request/response.

### 4. Ac4yRestServiceClient

REST kliens HTTP kérésekhez (GET/POST, JSON content-type).

### 5. Algebra osztályok

- `Ac4yProcessResultAlgebra` - code, message, description, requestId
- `Ac4yServiceAlgebra` - request/response kezelés
- `Ac4yServiceRequestAlgebra` - üres request alap
- `Ac4yServiceResponseAlgebra` - result és threadId kezelés

## Függőségek

```xml
<dependency>
    <groupId>ac4y</groupId>
    <artifactId>ac4y-base</artifactId>
    <version>1.1.0</version>
</dependency>
```

## AI Agent Használati Útmutató

### Gyors Döntési Fa

1. **Operáció eredmény kell?** → `Ac4yProcessResult` fluent builder
2. **Service válasz kell?** → `Ac4yServiceResponse` fluent API
3. **REST hívás kell?** → `Ac4yRestServiceClient.request()`
4. **Service entitás kell?** → `Ac4yService` (JPA + JAXB)

### Token-hatékony Tudás

**Mit tartalmaz:**
- Process result (error/success/nothing happened)
- Service response fluent API
- REST kliens adapter
- JPA + JAXB integráció

**Függőségek:**
- ac4y-base (Ac4y base class, context, database)

## Eredetileg

`IJAc4yServiceModule/IJAc4yServiceDomain` modulból kiemelve.

---
**Utolsó frissítés:** 2026-02-06
