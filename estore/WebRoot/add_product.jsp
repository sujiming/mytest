<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Estore商城</title>
<style type="text/css">
body{ margin:0; padding:0;}
a{ text-decoration: none; color:#333; font:14px; }
#category{margin:0 auto;}
.head{ width:100%; height:40px; }
.head_left{ line-height:40px; margin-left:150px;} 
.head_left a{ margin-right:40px;}
.head_right{ line-height:40px; float:right; margin-right:150px; margin-top:-40px;}
.head_right a{ margin-left:40px;}
.head_3{ margin-left:150px; margin-top:10px}
.head_3 a{margin-left:40px;}
.head_4{ line-height:40px; float:right; margin-right:150px; margin-top:-40px;}
.head_4 a{ margin-left:40px;}
.top{ margin:0 auto}
.top_1{height: 55px; line-height: 62px; margin-left:150px;; font-size: 24px; color: #333; margin-top:60px;}
.top_2{ line-height:40px; float:right; margin-right:150px; margin-top:-40px;}
.top_2 a{ margin-left:40px;}
.c{color: yellow}
.s{color:blue}
#b0{margin:0 auto; background:#000 }
.b0{width:100%; height:5000px; background:#FFF;}
.b1{width:20%; height:650px; line-height:40px; margin-left:120px; font-size:25px; overflow:hidden; float:left; position:fixed ; background-color:#FF6666; }
.b2{width:70%; float:right; margin-top:0px;}

</style>
<script type="text/javascript">
	function checkForm(){
		var name = document.getElementById("name").value;
		
		// 校验form 表单 ，想校验哪一项 添加id
		var price = document.getElementById("price").value;
		if(name==null || name == ""){
			alert("商品名称必须输入！");
			return false;
		}
		if(price==null || price == ""){
			alert("价格必须输入！");
			return false;
		}else if(isNaN(price)||parseFloat(price)<= 0){ // is not a number
			alert("输出价格必须为数字且大于0！");
			return false;
		}	
		// 其它的这里 暂不校验 
		
	}
	
</script>

</head>

<body>
     <!-- 显示用户登录状态-->
 <div class="b0">
   	  <div class="b1">
        <div align="center">
        	<a>
    			
    				欢迎您 ，<h5 class="c">${loginUser.nickname } ${loginUser.role=='user'?"商城用户":"管理员" }</h5>  <a href="/invalidate.jsp" class="s">注销</a> 
    			
    			
    		</a><br />
    		<a></a><br>
    		<a></a><br>
			<a href="/regist.jsp" >用户注册</a><br />
			<a href="/listProduct">查看商品列表</a><br />
			<div id = "btn"></div>
			<a href="/cart.jsp">查看购物车</a><br />
			<a href="/listOrders">查看订单</a><br />
			<a href="/showRank">查看榜单</a><br />
			<a href="/pay.jsp">在线付款</a><br />
		</div>
      </div>
    
 
    
    <table width="1200"   height="640" border="0" align="center">
  <tr>
    <td  height="171" colspan="2" bgcolor="#FF6666">
    	<div align="center"><h3>添加商品</h3></div>
    </td>
  </tr>
  <tr>
    <td height="50"  width="260" bgcolor="#FF6666"></td>
    <td rowspan="7"  >
    
    <!-- 文件上传：form post提交，input file必须name属性，enctype: multipart/form-data -->
<form action="/addProduct" method="post" enctype="multipart/form-data" onsubmit="return checkForm();">
	<table>
		<tr>
			<td>商品名称</td>
			<td><input type="text" name="name" id="name"/> </td>
		</tr>
		<tr>
			<td>商品价格</td>
			<td><input type="text" name="price" id="price" /></td>
		</tr>
		<tr>
			<td>商品分类</td>
			<td>
				<select name="category">
					<option value="数码产品">数码产品</option>
					<option value="电脑及IT硬件">电脑及IT硬件</option>
					<option value="家用电器">家用电器</option>
					<option value="日常用品">日常用品</option>
				</select>		
			</td>
		</tr>
		<tr>
			<td>商品描述</td>
			<td>
				<textarea rows="6" cols="80" name="description"></textarea>
			</td>
		</tr>
		<tr>
			<td>上传图片</td>
			<td><input type="file" name="img" /> </td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="添加商品" />
			</td>
		</tr>
	</table>
</form>
    
    </td>
  </tr>
  <tr>
    <td height="50"  bgcolor="#FF6666"></td>
  </tr>
  <tr>
    <td height="50"  bgcolor="#FF6666"></td>
  </tr>
  <tr>
    <td height="50"  bgcolor="#FF6666"></td>
  </tr>
  <tr>
    <td height="50"  bgcolor="#FF6666"></td>
  </tr>
  <tr>
    <td height="50"  bgcolor="#FF6666"></td>
  </tr>
  <tr>
    <td height="50"  bgcolor="#FF6666"></td>
  </tr>
</table>
  
  </body>
</html>