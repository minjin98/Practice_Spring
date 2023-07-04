 <%
   Vector<ProcessBean> vlist = regMgr.getRegisterList();
	int counter = vlist.size();
	for(int i = 0; i < vlist.size(); i++) {
   		ProcessBean proBean = vlist.get(i);
%>

RegisterMgr
