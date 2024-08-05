<%@page import="hairshop.dao.ReviewDAO"%>
<%@page import="java.util.List"%>
<%@page import="hairshop.dto.ReviewDTO"%>
<%@ page contentType="text/html; charset=utf-8"%>
 <%
 ReviewDAO reviewDAO = new ReviewDAO();

 // 검색 조건 처리
 String searchField = request.getParameter("searchField");
 String searchWord = request.getParameter("searchWord");
 if (searchField == null) searchField = "title";
 if (searchWord == null) searchWord = "";

 // 전체 리뷰 수 가져오기
 int totalCount = reviewDAO.getTotalReviewCount(searchField,searchWord);

 // 페이징 처리
 int pageSize = 10;
 int blockPage = 5;
 int totalPage = (int) Math.ceil((double) totalCount / pageSize);

 int pageNum = 1;
 String pageTemp = request.getParameter("pageNum");
 if (pageTemp != null && !pageTemp.equals("")) {
     pageNum = Integer.parseInt(pageTemp);
 }

 int start = (pageNum - 1) * pageSize + 1;
 int end = pageNum * pageSize;

 // 리뷰 목록 가져오기
 List<ReviewDTO> reviewList = reviewDAO.getReviewList(start, end, searchField, searchWord);
%>
<html>
<head>
 <link rel ="stylesheet" href ="./resources/css/bootstrap.min.css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
   
<script type="text/javascript" src="./resources/js/validation.js"></script>
<title>review</title>
<script type="text/javascript">
function validateForm(form) { 
    if (form.name.value == "") {
        alert("작성자를 입력하세요.");
        form.name.focus();
        return false;
    }
    if (form.title.value == "") {
        alert("제목을 입력하세요.");
        form.title.focus(); // 커서 이동
        return false;
    }

}
</script>

</head>
<body>
<div class="container py-4">
    <h1 class="display-5 fw-bold text-center">리뷰 게시판</h1>
    
    <!-- 검색 폼 -->
    <form method ="get">
     <table border ="1" width ="90%">
      <tr> <!-- 1줄 -->
         <td align ="center"> <!-- 1칸 : 가운데 정렬 -->
           <select name ="searchField">
              <option value = "tiitle">제목</option>
              <option value ="writer">작성자</option>              
           </select> <!-- 검색 필드 성정 -->
           <input type ="text" name= "searchWord"/> <!-- 검색단어 -->
           <input type = "submit" value ="검색하기"/> <!-- 버튼 -->
         </td>
      </tr>     
    </table>
   </form>

    <!-- 리뷰 목록 -->
    <table class="table table-bordered">
        <thead>
            <tr>
                <th width="10%">번호</th>
                <th width="15%">작성자</th>
                <th width="50%">내용</th>
                <th width="10%">조회수</th>
                <th width="15%">작성일</th>
            </tr>
        </thead>
        <tbody>
            <% if (reviewList.isEmpty()) { %>
                <tr>
                    <td colspan="5" class="text-center">등록된 리뷰가 없습니다.</td>
                </tr>
            <% } else { %>
                <% int virtualNum = totalCount - ((pageNum - 1) * pageSize); %>
                <% for (ReviewDTO dto : reviewList) { %>
                    <tr>
                        <td><%= virtualNum-- %></td>
                        <td><a href="viewReview.jsp?rno=<%= dto.getRno() %>"><%= dto.getRwriter() %></a></td>
                        <td><%= dto.getRwriter() %></td>
                        <td><%= dto.getRdate() %></td>
                    </tr>
                <% } %>
            <% } %>
        </tbody>
    </table>

    <!-- 페이징 -->
    <div class="d-flex justify-content-center">
       <%--  <%= BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, request.getRequestURI()) %> --%>
    </div>

    <!-- 리뷰 작성 버튼 -->
    <div class="text-end mt-4">
        <button type="button" class="btn btn-success" onclick="location.href='writeReview.jsp';">리뷰 등록</button>
    </div>
</div>
</body>
</html>