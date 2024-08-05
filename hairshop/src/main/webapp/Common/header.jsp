<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav
		class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
		id="ftco-navbar">
		<div class="container">
			<a class="navbar-brand" href="../Common/index.jsp"><span
				class="flaticon-scissors-in-a-hair-salon-badge"></span>미용실 예약 사이트</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#ftco-nav" aria-controls="ftco-nav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="oi oi-menu"></span> Menu
			</button>

			<div class="collapse navbar-collapse" id="ftco-nav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a href="../Common/index.jsp"
						class="nav-link">Home</a></li>
					<li class="nav-item"><a href="../Book/booking_main.jsp"
						class="nav-link">예약하기</a></li>
					<li class="nav-item"><a href="../gallery.jsp" class="nav-link">Gallery</a></li>
					<li class="nav-item"><a href="../about.jsp" class="nav-link">About</a></li>
					<li class="nav-item"><a href="../blog.jsp" class="nav-link">Blog</a></li>
					<%
					if (session.getAttribute("mid") == null) {
					%>
					<li class="nav-item active"><a href="../Member/login.jsp"
						class="nav-link">로그인</a></li>
					<%
					} else {
					%><li class="nav-item active"><a href="logout.jsp"
						class="nav-link">로그아웃</a></li>
					<li class="nav-item"><a href="user_profile.jsp"
						class="nav-link">회원정보</a></li>
					<%
					switch (session.getAttribute("mgrade").toString()) {
					case "1" : %>
						<li class="nav-item"><a href="../Cut/hairCutList.jsp"
						class="nav-link">시술표 관리</a></li>
						<%
						break;
					case "2" :
						break;
					}//switch end
					}//else end
					%>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>