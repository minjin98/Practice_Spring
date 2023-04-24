package chap07;

public class ImpeCalculator implements Calculator {

	@Override
	public long factorial(long num) {
		long result = 1;
		for (long i = 1; i <= num; i++) {
			System.out.printf("ImpeCalculator.factorial(%d)\n", i);
			result *= i;
		}
		return result;
	}

}
