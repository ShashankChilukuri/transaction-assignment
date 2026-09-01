### Understanding of the Problem

The application is a REST API for managing customer transactions. It supports creating transactions, retrieving a transaction by its Transaction ID, updating the status of a transaction, and retrieving all transactions for a Customer ID.

The application uses Spring Boot, Spring Data JPA and an H2 database to store and manage transaction data.

### Assumptions

- Transaction IDs and Customer IDs are positive integers.
- Transaction IDs are unique.
- Only INR is supported for this assigned variant.
- Transaction types are DEPOSIT, WITHDRAWAL, and TRANSFER.
- A newly created transaction can have PENDING, COMPLETED, or FAILED status.
- COMPLETED and FAILED are treated as final statuses.

### API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/transactions` | Create a new transaction |
| GET | `/transactions/{tranId}` | Get a transaction by Transaction ID |
| GET | `/customer/transactions/{cusId}` | Get all transactions for a Customer ID |
| PUT | `/transactions/{tranId}/status` | Update the status of a transaction |


### Validation Rules

The following validation rules are applied when creating a transaction:

### Transaction ID
- Transaction ID must be a positive integer.
- Transaction ID must be unique.
- If the Transaction ID already exists, the request is rejected with `409 Conflict`.

### Customer ID
- Customer ID must be greater than `0`.
- A zero or negative Customer ID is rejected with `400 Bad Request`.

### Amount
- Amount is required and must not be `null`.
- Amount must be greater than `0`.
- Zero and negative amounts are rejected with `400 Bad Request`.
- `BigDecimal` is used for the amount to avoid floating-point precision issues.

### Currency
- Currency is required and must not be `null`.
- Only `INR` is accepted for this assigned variant.
- Any other currency is rejected with `400 Bad Request`.

### Transaction Type
Only the following transaction types are accepted:

- `DEPOSIT`
- `WITHDRAWAL`
- `TRANSFER`

Null or any other transaction type is rejected with `400 Bad Request`.

Transaction types are case-sensitive.

Examples:
- `DEPOSIT` → valid
- `WITHDRAWAL` → valid
- `TRANSFER` → valid
- `deposit` → invalid
- `PAYMENT` → invalid
- `null` → invalid

### Transaction Status
The following statuses are accepted:

- `PENDING`
- `COMPLETED`
- `FAILED`

Null or any other status is rejected with `400 Bad Request`.

### Status Transition Rules

A transaction can be updated only while it is in `PENDING` status.

Allowed transitions:

`PENDING → COMPLETED`

`PENDING → FAILED`

Once a transaction reaches `COMPLETED` or `FAILED`, it is treated as a final state and cannot be changed.

The following transitions are rejected:

`COMPLETED → PENDING`

`COMPLETED → FAILED`

`FAILED → PENDING`

`FAILED → COMPLETED`

Updating a transaction to its existing status is also rejected because there is no state change.

### Known Limitations
- Currently, only INR is supported because of the assigned variant.
- Authentication and authorization are not implemented.
- Pagination is not implemented for customer transaction lookup.
- The API currently uses simple response handling rather than a more advanced error-handling mechanism.

### Testing

The complete test suite was executed successfully using Maven and Junit.

## Run Tests

On Windows: mvn clean test

## Test Results

` 
[INFO] Results:
[INFO] 
[INFO] Tests run: 19, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  10.921 s
[INFO] Finished at: 2026-08-31T19:01:43+05:30
[INFO] ------------------------------------------------------------------------
`

## What I Would Improve With More Time

- Use enums for transaction type and status instead of strings.
- Add Bean Validation annotations for request validation.
- Add more API-level integration tests.
- Add pagination for customer transaction results.
- Improve the response structure and error messages further.