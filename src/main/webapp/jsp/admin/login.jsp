<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  
<script type="text/javascript">  

</script>  
  
<div style="color:red; font-size:22px;">${message_login}</div>  
  
<form action="<%=request.getContextPath()%>/login/success.do" method="POST">  
    姓名：<input type="text" name="username" placeholder="mike"/><br/>  
    密码：<input type="text" name="password" placeholder="mike"/><br/>  
  <!--   验证：<input type="text" name="verifyCode"/>  
         &nbsp;&nbsp;  
         <img id="verifyCodeImage" onclick="reloadVerifyCode()" src="<%=request.getContextPath()%>/mydemo/getVerifyCodeImage"/><br/>-->  
    <input type="submit" value="确认"/>  
</form>
</body>
</html>