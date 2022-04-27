package edu.school21.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NumberWorkerTest {

    NumberWorker numberWorker = new NumberWorker();

    @ParameterizedTest(name = "{index} - {0} is a prime")
    @ValueSource(ints = {3,5,7})
    public void isPrimeForPrimes(int number) {
        boolean ans = numberWorker.isPrime(number);
        assertTrue(ans);
    }

    @ParameterizedTest(name = "{index} - {0} is not  a prime")
    @ValueSource(ints = {15,10,900})
    public void isPrimeForNotPrimes(int number) {
        boolean ans = numberWorker.isPrime(number);
        assertFalse(ans);

    }

    @ParameterizedTest(name = "{index} - {0} is a incorrect")
    @ValueSource(ints = {0, -5, 1})
    public void isPrimeForIncorrectNumbers(int number) {
        assertThrows(RuntimeException.class, () -> numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void digitsSum(int i, int expected) {
        int testData = numberWorker.digitsSum(i);
        assertEquals(testData, expected);
    }
}