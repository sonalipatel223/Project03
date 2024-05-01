<%@page import="in.co.rays.project_3.controller.PaymentListCtl"%>
<%@page import="in.co.rays.project_3.dto.PaymentDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.project_3.model.ModelFactory"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Payment List</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/CheckBox11.js"></script>
<style>
.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/jeshoots.jpg');
	background-size: cover;
	background-repeat: no-repeate;
	padding-top: 6%;
}

.p1 {
	padding: 4px;
	width: 200px;
	font-size: bold;
}

.text {
	text-align: center;
}
</style>
</head>

<body class="hm">
	<%@include file="Header.jsp"%>
	<%@include file="calendar.jsp"%>
	<div></div>
	<div>
		<form class="pb-5" action="<%=ORSView.PAYMENT_LIST_CTL%>" method="post" >
			<jsp:useBean id="dto" class="in.co.rays.project_3.dto.PaymentDTO"
				scope="request"></jsp:useBean>
			<%
				List list1 = (List) request.getAttribute("roleList");
				List ulist = (List) request.getAttribute("lastName");
			%>


			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());
			

				List list = ServletUtility.getList(request);
				
				Iterator<PaymentDTO> it = list.iterator();
				if (list.size() != 0) {
			%>
			<center>
				<h1 class="text-primary font-weight-bold pt-3">
					<u>Payment List</u>
				</h1>
			</center>
			<div class="row">
				<div class="col-md-4"></div>
				<%
					if (!ServletUtility.getSuccessMessage(request).equals("")) {
				%>

				<div class="col-md-4 alert alert-success alert-dismissible"
					style="background-color: #80ff80">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="#008000"><%=ServletUtility.getSuccessMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div class="col-md-4"></div>
			</div>
			<div class="row">
				<div class="col-md-4"></div>

				<%
					if (!ServletUtility.getErrorMessage(request).equals("")) {
				%>
				<div class=" col-md-4 alert alert-danger alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div class="col-md-4"></div>
			</div>

			<div class="row">


				 <div class="col-sm-2"></div>
				<div class="col-sm-2">
					<input type="text" name="name" placeholder="Enter shop Name"
						class="form-control"
						value="<%=ServletUtility.getParameter("name", request)%>">
				</div>

				&emsp;
				<div class="col-sm-2">
					<input type="text" name="type" placeholder="Enter order type"
						class="form-control"
						value="<%=ServletUtility.getParameter("type", request)%>">
				</div>
				<%-- &emsp;
				<div class="col-sm-2"><%=HTMLUtility.getList("Role", String.valueOf(dto.getRoleId()), list1)%></div> --%>

				<%--  &emsp;
				<div class="col-sm-2"><%=HTMLUtility.getList("firstName", String.valueOf(dto.getLastName()), ulist)%></div>  --%>
				&emsp;
				<%-- 	
				<div class="col-sm-2">
					<input type="text" name="dob" class="form-control" placeholder="Enter Date Of Birth" id="datepicker" readonly="readonly"
							value="<%=DataUtility.getDateString(dto.getDob())%>">
				</div> --%>

			 	<div class="col-sm-2">
					<input type="submit" class="btn btn-primary btn-md"
						style="font-size: 15px" name="operation"
						value="<%=PaymentListCtl.OP_SEARCH%>"> &emsp; <input
						type="submit" class="btn btn-dark btn-md" style="font-size: 15px"
						name="operation" value="<%=PaymentListCtl.OP_RESET%>">
				</div> 


				<div class="col-sm-2"></div>
			</div>

			</br>
			<div style="margin-bottom: 20px;" class="table-responsive">
				<table class="table table-bordered table-dark table-hover">
					<thead>
						<tr style="background-color: #8C8C8C;">

							<!-- <th width="10%"><input type="checkbox" id="select_all"
								name="Select" class="text"> Select All</th> -->
							<th width="5%" class="text">S.NO</th>
							<th width="15%" class="text">Shop Name</th>
							<th width="15%" class="text">Order Name</th>
							<th width="15%" class="text">Quantity</th>
							<th width="15%" class="text">Address</th>
							<th width="15%" class="text">Opretion</th>
							
						</tr>
					</thead>
					<%
						while (it.hasNext()) {
							
							 dto=it.next();

					%>
					<tbody>
						<tr>
							<!-- <td align="center"><input type="checkbox" class="checkbox"
								name="ids" ></th> -->
							<td class="text"><%=index++%></td>
							<td class="text"><%=dto.getName()%></td>
							<td class="text"><%=dto.getType()%></td>
							<td class="text"><%=dto.getAmount()%></td>
							
							
							<td> <a class="btn btn-primary" href="PaymentCtl?id=<%=dto.getId()%>">Edit</a></td>
							<%--<td><a class="btn btn-danger" href="EmployeeList?action=delete&eid=<%=e.getEmpid()%>
		"onclick="return function(){confirm('Are you sure do want to delete this record??'))} ">delete</a></td>--%>
		
							<td><a class="btn btn-danger" href="OrderListCtl?id=<%=dto.getId()%>"onclick="return function(){confirm('Are you sure do want to delete this record??'))} ">delete</a></td>
						</tr>
					</tbody>
					<%
						}
					%>
				</table>
			</div>
			<%-- <table width="100%">
				<tr>
					<td><input type="submit" name="operation"
						class="btn btn-warning btn-md" style="font-size: 17px"
						value="<%=OrderListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>

					<td><input type="submit" name="operation"
						class="btn btn-primary btn-md" style="font-size: 17px"
						value="<%=OrderListCtl.OP_NEW%>"></td>

					<td><input type="submit" name="operation"
						class="btn btn-danger btn-md" style="font-size: 17px"
						value="<%=BankListCtl.OP_DELETE%>"></td>

					<td align="right"><input type="submit" name="operation"
						class="btn btn-warning btn-md" style="font-size: 17px"
						style="padding: 5px;" value="<%=OrderListCtl.OP_NEXT%>"
						<%=(nextPageSize != 0) ? "" : "disabled"%>></td>
				</tr>
				<tr></tr>
			</table> --%>

			<%
				}
				if (list.size() == 0) {
			%>
			<center>
				<h1 style="font-size: 40px; color: #162390;">Order List</h1>
			</center>
			</br>
			<div class="row">
				<div class="col-md-4"></div>

				<%
					if (!ServletUtility.getErrorMessage(request).equals("")) {
				%>
				<div class=" col-md-4 alert alert-danger alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>




				<%
					if (!ServletUtility.getSuccessMessage(request).equals("")) {
				%>

				<div class="col-md-4 alert alert-success alert-dismissible"
					style="background-color: #80ff80">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="#008000"><%=ServletUtility.getSuccessMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div style="padding-left: 48%;">
					<input type="submit" name="operation"
						class="btn btn-primary btn-md" style="font-size: 17px"
						value="<%=PaymentListCtl.OP_BACK%>">
				</div>

				<div class="col-md-4"></div>
			</div>

			<%
				}
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>


	</div>


</body>
<%@include file="FooterView.jsp"%>
</html>