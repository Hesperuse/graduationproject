<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>玩转spring boot——简单登录认证</title>
</head>
<body>
<h1>玩转spring boot——简单登录认证</h1>
<form action="/loginPost" method="post">
    用户名：<input type="text" name="account"/> <br/>
    密码：<input type="password" name="password"/>
    <br />
    <input type="submit" value="登录" />
    <div>${message!""}</div>
</form>
</body>
</html>