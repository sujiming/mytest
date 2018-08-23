<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<body>

</body>
<body>
   
 <div class="b0">
   	  <div class="b1">
        <div align="center">
        	<a>
    <c:if test="${not empty loginUser}">
    	欢迎您 ，<h5 class="c">${loginUser.nickname } ${loginUser.role=='user'?"商城用户":"管理员" }</h5>  <a href="/invalidate.jsp" class="s">注销</a> 
    </c:if>
    </a><br />
			<a href="/regist.jsp" >用户注册</a><br />
			<a href="/add_product.jsp" >添加商品</a><br />
			<a href="/listProduct">查看商品列表</a><br />
			<a href="/cart.jsp">查看购物车</a><br />
			<a href="/listOrders">查看订单</a><br />
			<a href="/showRank">查看榜单</a><br />
			<a href="/pay.jsp">在线付款</a><br />
		</div>
      </div>
    
   <!-- 显示用户登录状态-->
    
    <table width="1200"   height="640" border="0" align="center">
  <tr>
    <td  height="171" colspan="2" bgcolor="#FF6666">
    	<div align="center"><h3>欢迎来到Estore商城</h3></div>
    </td>
  </tr>
  <tr>
    <td height="50"  width="260" bgcolor="#FF6666"></td>
    <td rowspan="7"  >
    
    	<!-- 确认付款 -->
<form action="https://www.yeepay.com/app-merchant-proxy/node" method="post" target="_blank">
	<h2>订单号：${ p2_Order},付款金额：${money }</h2>
	<input type="hidden" name="pd_FrpId" value="${pd_FrpId }" />
	<input type="hidden" name="p0_Cmd" value="${p0_Cmd }" />
	<input type="hidden" name="p1_MerId" value="${p1_MerId }" />
	<input type="hidden" name="p2_Order" value="${p2_Order }" />
	<input type="hidden" name="p3_Amt" value="${p3_Amt }" />
	<input type="hidden" name="p4_Cur" value="${p4_Cur }" />
	<input type="hidden" name="p5_Pid" value="${p5_Pid }" />
	<input type="hidden" name="p6_Pcat" value="${p6_Pcat }" />
	<input type="hidden" name="p7_Pdesc" value="${p7_Pdesc }" />
	<input type="hidden" name="p8_Url" value="${p8_Url }" />
	<input type="hidden" name="p9_SAF" value="${p9_SAF }" />
	<input type="hidden" name="pa_MP" value="${pa_MP }" />
	<input type="hidden" name="pr_NeedResponse" value="${pr_NeedResponse }" />
	<input type="hidden" name="hmac" value="${hmac }" />
	<input type="submit" value="确认付款" />
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