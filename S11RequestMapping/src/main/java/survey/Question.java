package survey;

import java.util.Collections;
import java.util.List;

public class Question {

	private String title;				// 질문
	private List<String> options;		// 질문에 대한 다중 선택지

	public Question(String title, List<String> options) {
		this.title = title;
		this.options = options;
	}

	public Question(String title) {		// 선택지가 하나의 문자열인 경우, 배열 형태로 처리
		this(title, Collections.<String>emptyList());
	}

	public String getTitle() {
		return title;
	}

	public List<String> getOptions() {
		return options;
	}

	public boolean isChoice() {
		return options != null && !options.isEmpty();
	}

}
