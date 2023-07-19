 <%
   Vector<ProcessBean> vlist = regMgr.getRegisterList();
	int counter = vlist.size();
	for(int i = 0; i < vlist.size(); i++) {
   		ProcessBean proBean = vlist.get(i);
%>

RegisterMgr


<ProcessDao>
// DB에서 여러개의 행을 받아와서 rs.getString을 이용해 칼럼별로 분리
// 무조건 칼럼 기준이 아니라 행을 기준으로 계산한다.
public List<ProcessBean> select_rate() {
		List<ProcessBean> results = jdbcTemplate.query("select * from prod_rate",
				new RowMapper<ProcessBean>() {
					@Override
					public ProcessBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						ProcessBean process = new ProcessBean(
								rs.getString("goodprod_rate"),
								rs.getString("badprod_rate"));
						return process;				
					}
			});
	return results;
	}




@GetMapping("/process3") // 주소창에 process3 입력시 실행
    public String manage(Model model) { // 파라미터에 Model model 선언
    	// ProcessDao에서 선언한 selectAll을 통해  데이터를 불러와 List<ProcessBean>에 저장
		List<ProcessBean> processList = processDao.selectAll();
		// processList에 저장된 값들을 "processList" 이름으로 JSP에 전달 
		model.addAttribute("processList", processList);
    	return "test3.jsp";
   }


// ProcessController에서 processList로 넘겨준 데이터를 별도의 선언 없이
// 바로 사용
<c:forEach var="proc" items="${processList}" >
    <span>${proc.prodName}</span>
</c:forEach>	



콤보박스 항목에 호출 값을 지정해서 콤보박스 항목 선택시 해당된 값들이 호출(작업해야함)
Bar chart 의 cycletime 데이터 랜덤 출력
Controller 집중적으로 보기

String 으로 받은 객체는 for:each 로 반복할 수 없다.

// c:if = "${not empty vals}"  vals 값이 비어있지 않을때만 실행 
<c:if test="${not empty process_gauge}">
     		var process_gauge_val = parseFloat(${process_gauge.process_gauge});
     		gaugeChart(process_gauge_val);
 		</c:if>


<Controller 사용법>
1. Controller 생성
2. ControllerConfig에 생성한 Controller 등록

<통합>
1. web.xml 주석부분으로 수정
2. MvcConfig 주석 해제
3. OracleDbConfig, OracleInfo 수정
4. 나머지 Process 코드들 수정
											