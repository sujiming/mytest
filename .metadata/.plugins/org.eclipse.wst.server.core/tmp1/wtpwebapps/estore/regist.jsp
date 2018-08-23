<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Estore商城</title>
<script type="text/javascript">
	// 验证注册表单
	function checkForm(){
		// 将需要验证表单组件 提供id属性
		var username = document.getElementById("username").value;
		if(username==null||username==""){
			alert("用户名不能为空");
			return false;
		}	
		// 为其它字段添加非空校验

		var password = document.getElementById("password").value;
		var repassword = document.getElementById("repassword").value;
		if(password != repassword){
			alert("两次密码必须一致！");
			return false;
		}	
	}

	// 切换验证码
	function change(){
		document.getElementById("myimg").src = "/checkcode?"+new Date().getTime();
	}
</script>
</head>
<body>
<div  align="center">
  <table width="1027" height="706" border="0" background="/img/timg.jpg" >
    <tr>
      <td width="331" height="83">&nbsp;</td>
      <td width="489">&nbsp;</td>
      <td width="331">&nbsp;</td>
    </tr>
    <tr>
      <td height="486">&nbsp;</td>
      <td align="center">
      	<h1>注册</h1>
      		<h3 style="color: red">${msg }</h3>
<form action="/regist" method="post" onsubmit="return checkForm();">
	<table>
		<tr>
			<td>用户名</td>
			<td><input type="text" name="username" id="username"/></td>
		</tr>
		<tr>
			<td>密码</td>
			<td><input type="password" name="password" id="password"/></td>
		</tr>
		<tr>
			<td>确认密码</td>
			<td><input type="password" name="repassword" id="repassword"/></td>
		</tr>
		<tr>
			<td>昵称</td>
			<td><input type="text" name="nickname" /></td>
		</tr>
		<tr>
			<td>邮箱</td>
			<td><input type="text" name="email" /></td>
		</tr>
		<tr>
			<td>验证码</td>
			<td><input type="text" name="checkcode" /><img id="myimg" src="/checkcode" style="cursor: pointer;" onclick="change();"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="注册" /></td>
			<td>
				<!-- 登陆 链接登陆页面 ，返回  返回之前页面 -->
				<input type="button" value="返回" onclick="history.go(-1);" />
			</td>
		</tr>
	</table>
</form>
      </td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td height="24">&nbsp;</td>
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
</form>
</body>
</html>