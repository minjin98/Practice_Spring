1. Global Validator
	- public Validator getValidator()가 재정의 되어 있으면 글로벌 Validator로 작동
	- 검증 대상 RegisterRequestValidator의 supports() 메소드에 지정된 객체가 대상
	- 설정
		public class MvcConfig implements WebMvcConfigurer {
		@Override
		public Validator getValidator() {
			return new RegisterRequestValidator();
		}

2. Local Validator
	- 해당하는 컨트롤러에서 애너테이션 @InitBinder를 지정한 메서드가 Validator 대상
	- 설정 : RegisterControllerWithLocalValidator.java
		public class RegisterControllerWithLocalValidator {
			@InitBinder
			protected void initBinder(WebDataBinder binder) {
				binder.addValidators(new RegisterRequestValidatorLocal());
			}
		}
	