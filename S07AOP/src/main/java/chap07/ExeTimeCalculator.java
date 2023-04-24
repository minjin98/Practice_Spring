package chap07;

public class ExeTimeCalculator implements Calculator {

	private Calculator delegate;

	public ExeTimeCalculator(Calculator delegate) {
        this.delegate = delegate;
    }

	@Override
	public long factorial(long num) {
		long start = System.nanoTime(); // 계산시작시간(나도 : 10억분의 1초)
		long result = delegate.factorial(num); // 계산 : num!
		long end = System.nanoTime(); // 계산종료시간
		System.out.printf("%s.factorial(%d) 실행 시간 = %d\n",
				delegate.getClass().getSimpleName(),
				num, (end - start));
		return result;
	}

}
