<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Estore商城</title>
</head>
<body>
<div  align="center">
  <table width="1027" height="708" border="0" background="/img/timg.jpg" >
    <tr>
      <td width="331" height="161">&nbsp;</td>
      <td width="489">&nbsp;</td>
      <td width="331">&nbsp;</td>
    </tr>
    <tr>
      <td height="393">&nbsp;</td>
      <td align="center">
      	<h1>欢迎登录Estore商城</h1>
      		<form action="/login" method="post" >
				<table>
					<tr>
						<td>用户名</td>
						<td><input type="text" name="username" /></td>
					</tr>
					<tr>
						<td>密码</td>
						<td>
						<input type="password" name="password" />
						</td>
					</tr>
					<!-- 记住用户名密码、自动登录Filter -->
					<tr>
						<td><input type="submit" value="登录" /></td>
						<td><input type="button" value="还没有账户，去注册" onclick="location.href='/regist.jsp';"/></td>
					</tr>
				</table>
			</form>
      </td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td height="41">&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><div align="center">www.estore.com Reserved</div></td>
      <td>&nbsp;</td>
    </tr>
  </table>
</div>


</body>
</html>