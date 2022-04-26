package edu.school21.numbers;

import org.junit.jupiter.params.ParameterizedTest;
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
    @ValueSource(ints = {2,10,900})
    public void isPrimeForNotPrimes(int number) {
        boolean ans = numberWorker.isPrime(number);
        assertFalse(ans);

    }

    @ParameterizedTest(name = "index} - {0} is a incorrect number")
    @ValueSource(ints = {-1,1,0})
    public void isPrimeForIncorrectNumbers




}