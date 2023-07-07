 <%
   Vector<ProcessBean> vlist = regMgr.getRegisterList();
	int counter = vlist.size();
	for(int i = 0; i < vlist.size(); i++) {
   		ProcessBean proBean = vlist.get(i);
%>

RegisterMgr



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

<통합>
1. web.xml 주석부분으로 수정
2. MvcConfig 주석 해제
3. OracleDbConfig, OracleInfo 수정
4. 나머지 Process 코드들 수정
											