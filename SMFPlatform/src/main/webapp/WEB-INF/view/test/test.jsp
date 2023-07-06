<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="spring.auth.AuthInfo" %>
<%@ page import="java.util.*,controller.process.Process"%>
<jsp:useBean id="proMgr" class="controller.process.ProcessDao"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>TEST</title>
        <link href="resources/css/styles.css" rel="stylesheet" />
        <link href="resources/css/customstyle.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
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
                        <c:if test="${sessionScope.authInfo.getAdmin()}">
	                        <li><a class="dropdown-item" href="manage">Manage Settings</a></li>
	                        <li><hr class="dropdown-divider" /></li>
                        </c:if>
                        <li><a class="dropdown-item" href="logout">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <!-- Side and Main Area-->
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
                                    <a class="nav-link" href="process">공정명령</a>
                                    <a class="nav-link" href="report">공정결과</a>
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
                        <h1 class="mt-4">Test</h1>
                    </div>
                     <div class="row" style="width: 100%; padding-left: 30px;">
                        <div class="col-md-3">
                            <section class ="widget header"; style="width: 95%; border: 4px solid rgb(241, 237, 225) " >
                                <header>
                                    <h4>
                                        <span style = "font-size: 20px;">제품명</span>
                                    </h4>
                                </header>
                                   <%
								   List<Process> vlist = proMgr.selectAll();
									int counter = vlist.size();
									for(int i = 0; i < vlist.size(); i++) {
								   		Process proBean = vlist.get(i);
									%>
                                <div class = "body" style="height: 50px; min-height: 50px;" id="total">
                                    <div class = "chart_content" id = "total1" style = "height: inherit; font-size : 35px;
                                    padding-top: 12.5px; padding-bottom: 12.5px;">
                                        <ul class= "multi_val">
                                            <ul style ="text-align : center; line-height : 25px; width : 100%;" class ="vertical multi-val-el">
                                               <span class="multi-val-label"></span> 
                                               <span class="multi-val-value multi-val-value-0">
                                                    <span class="multi_value_text" style ="color:black;font-size:30px;">
                                                    <%=proBean.getProdName()%>
                                                    </span>
                                                        
                                            </ul>
                                        </ul>
                                    </div>
                                </div>
                            </section>
                        </div>
                        <div class="col-md-3">
                            <section class ="widget header"; style="width: 95%; border: 4px solid rgb(241, 237, 225) " >
                                <header>
                                    <h4>
                                        <span style = "font-size: 20px;">양품생산수량</span>
                                    </h4>
                                </header>
                                <div class = "body" style="height: 50px; min-height: 50px;" id="total">
                                    <div class = "chart_content" id = "total1" style = "height: inherit; font-size : 35px;
                                    padding-top: 12.5px; padding-bottom: 12.5px;">
                                        <ul class= "multi_val">
                                            <ul style ="text-align : center; line-height : 25px; width : 100%;" class ="vertical multi-val-el">
                                               <span class="multi-val-label"></span> 
                                               <span class="multi-val-value multi-val-value-0">
                                                    <span class="multi_value_text" style ="color:#0059ff;font-size:inherit;">
                                                    <%=proBean.getGood_count()%>
                                                    </span>
                                                    <span class = "multi_value_unit">EA</span>
                                               </span>
                                            </ul>
                                        </ul>
                                    </div>
                                </div>
                            </section>
                        </div>
                        <div class="col-md-3">
                            <section class ="widget header"; style="width: 95%; border: 4px solid rgb(241, 237, 225) " >
                                <header>
                                    <h4>
                                        <span style = "font-size: 20px;">불량생산수량</span>
                                    </h4>
                                </header>
                                <div class = "body" style="height: 50px; min-height: 50px;" id="total">
                                    <div class = "chart_content" id = "total1" style = "height: inherit; font-size : 35px;
                                    padding-top: 12.5px; padding-bottom: 12.5px;">
                                        <ul class= "multi_val">
                                            <ul style ="text-align : center; line-height : 25px; width : 100%;" class ="vertical multi-val-el">
                                               <span class="multi-val-label"></span> 
                                               <span class="multi-val-value multi-val-value-0">
                                                    <span class="multi_value_text" style ="color:red;font-size:inherit;">
                                                    <%=proBean.getBad_count()%>
                                                    </span>
                                                    <span class = "multi_value_unit">EA</span>
                                               </span>
                                            </ul>
                                        </ul>
                                    </div>
                                </div>
                            </section>
                        </div>
                        <div class="col-md-3">
                            <section class ="widget header"; style="width: 95%; border: 4px solid rgb(241, 237, 225) " >
                                <header>
                                    <h4>
                                        <span style = "font-size: 20px;">이슈 발생 건수</span>
                                    </h4>
                                </header>
                                <div class = "body" style="height: 50px; min-height: 50px;" id="total">
                                    <div class = "chart_content" id = "total1" style = "height: inherit; font-size : 35px;
                                    padding-top: 12.5px; padding-bottom: 12.5px;">
                                        <ul class= "multi_val">
                                            <ul style ="text-align : center; line-height : 25px; width : 100%;" class ="vertical multi-val-el">
                                               <span class="multi-val-label"></span> 
                                               <span class="multi-val-value multi-val-value-0">
                                                    <span class="multi_value_text" style ="color:orange;font-size:inherit;">
                                                    <%=proBean.getIssue_count()%>
                                                    </span>
                                                    <%} %>
                                                    <span class = "multi_value_unit">건</span>
                                               </span>
                                            </ul>
                                        </ul>
                                    </div>
                                </div>
                            </section>
                        </div>
                    <div class="row" style="width: 100%; padding-left: 10px;">
                       <div class="col-xl-6">
                            <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-bar me-1"></i>
                                        Pie Chart Example
                                    </div>
                                    <div class="card-body"><canvas id="myPieChart" width="100%" height="30"></canvas></div>
                            </div>
                   	 </div>
                	  <div class="col-xl-6">
                           <div class="card mb-4">
                               <div class="card-header">
                                   <i class="fas fa-chart-area me-1"></i>
                                   Bar Chart Example
                               </div>
                               <div class="card-body"><canvas id="myBarChart" width="100%" height="40"></canvas></div>
                           </div>
                       </div>
                 </div>
                 
               </main>
           </div>
       </div>
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
       <script src="resources/js/scripts.js"></script>
       <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
       <script src="assets/demo/chart-area-demo.js"></script>
       <!-- <script src="resources/js/kor_clock.js"></script> -->
       <script src="resources/js/chart-pie.js"></script>
		<script src="resources/js/chart-bar.js"></script>        
       <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
       <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>