[S04Autowired]

1. 의존 자동 주입
2. @Autowired를 이용한 의존 자동 주입

(소스구성)
1. config
	- 빈 객체 등록
2. main
	- 실행 모듈(main)
3. spring
	- 서비스 모듈
	
(빈 연결)
1. 일치하는 빈이 없을 경우 오류
2. 빈으로 지정된 메소드의 이름이 다르더라도 해당하는 빈 객체를 리턴하는 
	메소드가 하나만 있으면 정상 처리
3. 동일한 빈 객체를 리턴하는 타입이 여러개 있더라도 
	메소드의 이름이 다르면 정상 처리
4. 오류 : 동일한 빈 객체를 여러개 리턴하는 이름이 다른 메소드가 여러개 있을 경우
	@Autowired로 지정된 이름과 일치하는 메소드가 없으면 오류 
	
(@Qualifier)
1. 자동 주입 가능한 빈이 두 개 이상일 때 자동 주입할 빈을 지정
2. @Qualifier("빈객체")
	- AppCtx.java : @Qualifier("printer")
	
(@Autowired)	
1. @Autowired(required = false)
	- 해당하는 빈객체가 없어도 예외가 발생하지 않음
	- 해당하는 빈객체가 없으면 자동 주입을 하지 않음
	- 실행되지 않음
	- 예제 : MemberPrinter.SetDateFormatter(...)
	
2. @Autowired : Optional<...> : 자바8부터 지원 
	- 빈 객체가 존재하면 Optional.isPresent()가 true 리턴
	- 예제 :
	@Autowired
	public void setDateFormatter(Optional<DateTimeFormatter> formatterOpt) {
	}
3. @Autowired : 
	public void setDateFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {		
	this.dateTimeFormatter = dateTimeFormatter;
	}
	