# Word Riddle

* This Java application loads a list of English words and identifies all **N-letter words** that are *reducible*. A word is considered **reducible** if you can remove one letter at a time — in any order — and each resulting word is still a valid English word, all the way down to a single-letter word like `"A"` or `"I"`.

- `ISLANDERS → SLANDERS → SANDERS → SANDER → SANER → SANE → SAN → AN → A`
- `STARTLING → STARTING → STARING → STRING → STING → SING → SIN → IN → I`
 
## Tech

- Java 17
- Maven
- JUnit 5 (Testing)

## Building the application
* Execute "mvnw clean install -DskipTests" in the root folder of the project to build without tests, or "mvnw clean install" to build with tests

## Running the Performance Test

To verify that the algorithm runs within the expected time limit (2 seconds) and returns the correct result (775 reducible 9-letter words), run the dedicated performance test:

```bash
./mvnw -Dtest=WordUtilsTest#testPerformanceUnderTwoSeconds test
