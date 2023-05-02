[Bean Validation]

1. Bean Validation 1.1
	- @EnableWebMvc 사용하면 OptionalValidatorFactoryBean을 글로벌 범위 Validator로 등록을 해 준다.
	- @EnableWebMvc 사용하면 Validator 설정을 특별히 해 줄 필요가 없다. 
2. 주요 애너테이션
	- @Valid 
	- @NotNull
	- @Digits
	- @Size
	...

(pom.xml)
<dependency>
	<groupId>javax.validation</groupId>
	<artifactId>validation-api</artifactId>
	<version>1.1.0.Final</version>
</dependency>

<dependency>
	<groupId>org.hibernate</groupId>
	<artifactId>hibernate-validator</artifactId>
	<version>5.4.2.Final</version>
</dependency>

-----------------------------------------------------------
(RegisterController.java)
@Controller
public class RegisterController {
	
	@PostMapping("/register/step3")
	public String handleStep3(@Valid RegisterRequest regReq, Errors errors) {
		if (errors.hasErrors()) {
			return "register/step2";
		}
	}
	...
}

(RegisterRequest.java)
public class RegisterRequest {
	@NotBlank
	@Email
	private String email;
	@Size(min = 6)
	private String password;
	@NotEmpty
	private String confirmPassword;
	@NotEmpty
	private String name;

	...
}
