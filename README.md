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

"# transaction-assignment" 
