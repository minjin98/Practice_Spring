 <%
   Vector<ProcessBean> vlist = regMgr.getRegisterList();
	int counter = vlist.size();
	for(int i = 0; i < vlist.size(); i++) {
   		ProcessBean proBean = vlist.get(i);
%>

RegisterMgr


// 값을 받아오기 위한 new RegisterRequest()를 만듦
model.addAttribute("registerRequest", new RegisterRequest());	 