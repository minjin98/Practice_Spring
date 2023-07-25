<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="spring.auth.AuthInfo, java.util.List, controller.process.*" %>
<%@ page import="controller.process.ProcessBean" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>공정명령</title>
        <link href="resources/css/styles.css" rel="stylesheet" />
        <link href="resources/css/customstyle.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://fastly.jsdelivr.net/npm/echarts@5.4.2/dist/echarts.min.js"></script>
        <script type="text/javascript" src="resources/js/jquery-1.12.4.js"></script>
    </head>
    <%-- attr --%>
    <%-- select 태그에서 선택한 값의 value 값을 <a></a> 태그에 실어서 보내기 위함 --%>
    <%-- lineSelect는 <a> 태그의 id --%>
    <script>
    
	        $(document).ready(function() {
		        $(".lineSelect").click(function() { // "lineSelect"가 클릭되었을때 함수 실행
		        	var lineid = $(this).prop("id"); // "lineid의 value 값 저장"
		            var sel = $("#line"+lineid).val();
		        	alert(sel);
		  			var lineSelect = $(this).attr("href"); // ID가 "lineSelect"인 태그의 "href" 속성을 변수에 저장
		  			lineSelect += sel; // 저장된 "href"의 속성에 lineid 값을 붙이기
		  			$(this).attr("href", lineSelect); // ID가 "lineSelect"인 태그의 "href" 속성을 lineSelect 변수값으로 교체
		  			//alert($("#lineSelect").attr("href"));
		        });	
			});
	        
        </script>
    
    <body class="sb-nav-fixed">
        <!-- Top Nav Area -->
        <script src="resources/js/kor_clock.js"></script>
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="main">Platform Name</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle"><i class="fas fa-bars"></i></button>
            <!-- Navbar Clock -->
            <div class="navbar-clock justify-content-end align-items-md-end text-end" id="navbar-clock">
		        <div id="date" class="date"></div>
		        <div id="time" class="time"></div>
            </div>
            <!-- Navbar-->
            <ul class="navbar-nav justify-content-end align-items-md-end">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">Settings</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <!-- contents for admin -->
                        < <c:if test="${sessionScope.authInfo.getAdmin()}">
	                        <li><a class="dropdown-item" href="manage">Manage Settings</a></li>
	                        <li><hr class="dropdown-divider" /></li>
                        </c:if>
                        <li><a class="dropdown-item" href="logout">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <!-- Side and Main Area-->
        <!-- 사이드바 + 메인 -->
        <div id="layoutSidenav">
            <!-- Side Nav Area-->
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Menu</div>
                            <a class="nav-link" href="plan">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                계획관리
                            </a>
                            <a class="nav-link" href="inventory">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                재고관리
                            </a>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                생산관리
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                
                                <%--<a class="nav-link" href="process">공정명령</a>
                                    <a class="nav-link" href="report">공정결과</a> --%>
                                    <a class="nav-link" href="testOR">공정명령</a>
                                    <a class="nav-link" href="test">공정결과</a>
                                </nav>
                            </div>
                            <a class="nav-link" href="logout">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                보고서관리
                            </a>
                            <!-- Menu For Test-->
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                Pages
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
                                        Authentication
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                    <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <a class="nav-link" href="login">Login</a>
                                            <a class="nav-link" href="register.html">Register</a>
                                        </nav>
                                    </div>
                                </nav>
                            </div>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as:</div>
                        ${sessionScope.authInfo.getName()}
                    </div>
                </nav>
            </div>
            <!-- Inner Contents Area(main) -->
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                    <div class = "row">
                    	<div class="col-md-4">
                        	<h1 class="mt-4"><span>공정명령</span></h1>
                        	<h4 class="mt-4">공정의 진행 여부 확인</h4>
                        </div>
                        <div class="col-md-4">
                        	<h1 class="mt-4"></h1>
                        </div>
             	 	<div class="col-xl-6" style ="padding-left-right:35px; padding-top:30px; width:100%">
                    	<div class="card mb-4" style="height:500px">
	                        <div class="card-header">
	                        	<i class="fas fa-table me-1"></i>
	                        	데이터테이블
	                        </div>
	                        <div class="card-body">
	                        	<table id="datatablesSimple">
	                            	<thead>
		                                <tr>
		                                	<th>번호</th>
		                                	<th>생산제품</th>
		                                    <th>생산계획기간</th>
		                                    <th>담당자</th>
		                                    <th>라인선택</th>
		                                    <th>진행여부</th>
		                                </tr>
	                            	</thead>
                                    <tbody>
                                    	<c:forEach var = "order" items ="${orderlist}">
	                                    	<tr>
	                                    		<td>${order.num}</td>
	                                    		<td>${order.prodNo}</td>
	                                    		<td>${order.startDate} ~ ${order.endDate}</td>
	                                    		<td>${order.name}</td>
	                                    		<td>
	                                    			<select id="lineordernum${order.num}" name="lineid"> <%--select id = lineid로 수정 --%>
													  <option selected>라인선택</option>
													  <option value="1">1번라인</option>
													  <option value="2">2번라인</option>
													  <option value="3">3번라인</option>
													</select>
													
												</td>
	                                    		<td>
	                                    			<c:if test="${order.proCheck != 'Y'}">
		                                    			<button type="button" class="btn btn-success">
		                                    				<a id="ordernum${order.num}" class="lineSelect" href="/SMFPlatform/testORstart?prodNo=${order.prodNo}&value="; style="text-decoration-line:none; color : white">공정</a> 
		                                    			</button>
		                                    			<button type="button" class="btn btn-danger">
		                                    			
		                                    				<%--forEach 로 꺼낸 데이터들 중에서 항목에 해당하는 값 전송
		                                    					a hef="" 로 Controller의 Mapping을 호출
		                                    					호출할 때 파라미터의 값을 같이 전송
		                                    					Controller에서는 @RequestParm으로 넘어온 데이터 받기--%>
		                                    				<a href="/SMFPlatform/testORDelete?num=${order.num}"; style="text-decoration-line:none; color : white">삭제</a>
		                                    			</button>
	                                    			</c:if>
	                                    			<c:if test="${order.proCheck == 'Y'}">
		                                    				<a id="lineSelected"; style="text-decoration-line:none; color : black">진행중</a>
	                                    			</c:if>
	                                    		</td>
	                                    	</tr>
                                    	</c:forEach>
                                    </tbody>
                                      	<tfoot>
                                        <tr>
                                            <th>번호</th>
		                                	<th>생산제품</th>
		                                    <th>생산계획기간</th>
		                                    <th>담당자</th>
		                                    <th>라인선택</th>
		                                    <th>진행여부</th>
                                        </tr>
                                    </tfoot>
                                </table>
                       		</div>
                	 	</div>
               		</div>
               </div>
             </main>
         </div>
     </div>
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
     <script src="resources/js/scripts.js"></script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
     <script src="resources/js/chart-gauge.js"></script>
     <script src="resources/js/chart-bar-leadtime.js"></script>
     <script src="resources/js/chart-bar-produce.js"></script>
     <script src="resources/js/chart-bar-produce2.js"></script>
     <script src="resources/js/chart-bar-produce3.js"></script>
     <script src="resources/js/chart-pie.js"></script>
     <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
     <script src="resources/js/datatables-simple-demo.js"></script>
     <script>
	</script>
  </body>
</html>
