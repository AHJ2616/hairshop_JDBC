 <%@page import="hairshop.dao.ReviewDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>writeReview</title>
 <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />

    <link
      href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700"
      rel="stylesheet"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Barlow+Condensed:500,600,700&display=swap"
      rel="stylesheet"
    />

    <link rel="stylesheet" href="../css/open-iconic-bootstrap.min.css" />
    <link rel="stylesheet" href="../css/animate.css" />

    <link rel="stylesheet" href="../css/owl.carousel.min.css" />
    <link rel="stylesheet" href="../css/owl.theme.default.min.css" />
    <link rel="stylesheet" href="../css/magnific-popup.css" />

    <link rel="stylesheet" href="../css/aos.css" />

    <link rel="stylesheet" href="../css/ionicons.min.css" />

    <link rel="stylesheet" href="../css/bootstrap-datepicker.css" />
    <link rel="stylesheet" href="../css/jquery.timepicker.css" />

    <link rel="stylesheet" href="../css/flaticon.css" />
    <link rel="stylesheet" href="../css/icomoon.css" />
    <link rel="stylesheet" href="../css/style.css" />
     <style>
        .hero-wrap {
            height: 50vh;
        }

        .form-group {
            margin-bottom: 1rem;
        }

        .col-sm-6 {
            padding: 0 15px;
        }

        .full-width {
            width: 100%;
        }

        .appointment-form {
            padding: 2rem;
            border-radius: 10px;
            background: rgba(255, 255, 255, 0.9);
        }
    </style>
</head>
<body>
<% String rdno= request.getParameter("rdno");  //get으로 넘어온 bdno를 rdno로 받기
ReviewDAO rdao = new ReviewDAO();
	String rsno = rdao.get_rsno(rdno);
	
%>
	<%@ include file="../Common/header.jsp" %>
	
	<script>
		function validateForm(form) { // validateForm을 호출하면 form 정보를 받는다. (32~37)
			if (!form.content.value) { // form 안에 content안에 value값이 없으면,
				alert("리뷰를 입력하세요."); // 경고창 출력
				return false; // false를 반환
			}
		}
	</script>

        <section class="hero-wrap hero-wrap-2">
        <div class="overlay"></div>
        <div class="container">
            <div class="row no-gutters slider-text js-fullheight align-items-center justify-content-center">
                <div class="col-md-9 ftco-animate pb-4 text-center">
                    <form method="post" action="writeProcess.jsp" class="appointment-form" onsubmit="return validateForm(this);">
                        <div class="row-4">
                            <div class="col-sm-10">
                                <div class="form -group ">           
                                 <textarea class="form-control" name="content" placeholder="리뷰를 작성해주세요." rows="4" required></textarea>                           
                                </div>
                                
                            </div>
                            <div class="col-sm-10" style="margin-top: 15px;">
                                <div class="form">
                                    <input type="file" name="attachedFile" class="col-sm-5"/>
                                    <input type="submit" value="리뷰등록" class="btn btn-primary" />
                                    <input type="hidden" name="rdno" value="<%=rdno%>" />
                                    <input type="hidden" name="rsno" value="<%=rsno%>" />
                                    <input type="button" value="리뷰등록 취소" class="btn btn-primary" onclick="history.back();" />
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
		<!-- 스크립트들 -->
<script src="../js/jquery.min.js"></script>
<script src="../js/jquery-migrate-3.0.1.min.js"></script>
<script src="../js/popper.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/jquery.easing.1.3.js"></script>
<script src="../js/jquery.waypoints.min.js"></script>
<script src="../js/jquery.stellar.min.js"></script>
<script src="../js/owl.carousel.min.js"></script>
<script src="../js/jquery.magnific-popup.min.js"></script>
<script src="../js/aos.js"></script>
<script src="../js/jquery.animateNumber.min.js"></script>
<script src="../js/bootstrap-datepicker.js"></script>
<script src="../js/jquery.timepicker.min.js"></script>
<script src="../js/scrollax.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
<script src="../js/google-map.js"></script>
<script src="../js/main.js"></script>
		
</body>
</html>