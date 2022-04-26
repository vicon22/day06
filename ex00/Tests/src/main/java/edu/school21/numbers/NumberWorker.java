package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int number){
        boolean answer = true;

        if (number < 2){
            throw new IllegalNumberException("Number < 2");
        }
        for(int i = 2; i < number; i++){
            if (number % i == 0) {
                answer = false;
                break;
            }
        }
        return answer;

    }

    public int digitsSum(int number){
        int answer = 0;

        while (number > 0){
            answer += number % 10;
            number = number / 10;
        }

        return answer;
    }

    private static class IllegalNumberException extends RuntimeException {
        public IllegalNumberException() {
            super();
        }

        public IllegalNumberException(String message) {
            super(message);
        }
    }
}