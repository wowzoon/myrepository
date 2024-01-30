<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>비밀번호체크</title>
		<script>
			if(window.name=="update"){
				// 부모창에서 화면전환
				window.opener.location.href="BoardServlet?command=board_update_form&num=${param.num}";
			}else if(window.name=="delete"){
				//부모창에서 화면전환
				alert("삭제되었습니다.")
				window.opener.location.href="BoardServlet?command=board_list&num=${param.num}";
			}
			window.close(); //창닫기
		</script>
	</head>
	<body>
	
	</body>
</html>