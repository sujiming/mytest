<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
#b0{margin:0 auto; background:#000 }
.b0{width:100%; height:5000px; background:#FFF;}
.b1{width:20%; height:650px; line-height:40px; margin-left:120px; font-size:25px; overflow:hidden; float:left; position:fixed ; background-color:#FF6666; }
.b2{width:70%; float:right; margin-top:0px;}

</style>
</head>

 <body>
   
 <div class="b0">
   	  <div class="b1">
        <div align="center">
        	<a><c:if test="${empty loginUser}">
    	您还未登录<a href="/login.jsp"><h3 class="c">登录</h3></a> 还没有账号?<a href="/regist.jsp">去<h3 class="c">注册</h3></a>
    </c:if>
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
		</div>
      </div>
    
   <!-- 显示用户登录状态-->
    
    <table width="1200"   height="640" border="0" align="center">
  <tr>
    <td  height="171" colspan="2" bgcolor="#FF6666">
    	<div align="center"><h3>订单列表</h3></div>
    </td>
  </tr>
  <tr>
    <td height="50"  width="260" bgcolor="#FF6666"></td>
    <td rowspan="7"  >
    
    <h1>订单列表</h1>
<!-- 显示订单列表信息  List -->
<c:if test="${empty orders}">
	<h1>没有您查询的订单！</h1>
</c:if>
<c:if test="${not empty orders}">
	<c:forEach items="${orders}" var="order">
		订单编号： ${ order.id} 下单用户 ：${order.user.nickname } 下单时间 ：${order.ordertime }<br/>
		金额 : ${order.totalmoney } 支付状态 ：${order.state==0?"未支付":"已支付" } 
		<c:if test="${order.state==0 and sessionScope.loginUser.role=='user'}">
			&nbsp;
			<!-- 订单必须未支付，取消订单必须是商城用户，不能是管理员 -->
			<a href="/cancelOrders?id=${order.id }"><input type="submit" value="取消订单"></input></a>
			<!-- 对未支付订单，而且必须普通用户身份，选择付款 -->			
			<!-- 将订单号 和  金额 传递 银行选择页面 -->
			&nbsp;&nbsp;
			<form action="/pay.jsp" method="post" style="display: inline;">
				<input type="hidden" name="order_id" value="${order.id }" />
				<input type="hidden" name="totalmoney" value="${order.totalmoney }" />
				<input type="submit" value="支付" />
			</form>
		</c:if>
		<br/>
		<p>收货人信息 : ${order.receiverinfo }</p>
		<!-- 订单项列表 -->
		<table border="1" width="100%">
			<tr>
				<th>编号</th>
				<th>名称</th>
				<th>单价</th>
				<th>购买数量</th>
			</tr>
			<c:forEach items="${order.orderItems}" var="orderitem">
				<tr>
					<td>${orderitem.product_id }</td>
					<td>${orderitem.product.name }</td>
					<td>${orderitem.product.price }</td>
					<td>${orderitem.buynum }</td>
				</tr>
			</c:forEach>
		</table>
		<hr/>
	</c:forEach>
</c:if>
    
    
    
    </td>
  </tr>
  <tr>
    <td height="50"  bgcolor="#FF6666"></td>
  </tr>
  <tr>
    <td height="50"  bgcolor="#FF6666"></td>
  </tr>
  <tr>
    <td height="50"  ></td>
  </tr>
  <tr>
    <td height="50"  ></td>
  </tr>
  <tr>
    <td height="50"  ></td>
  </tr>
  <tr>
    <td height="50"  ></td>
  </tr>
</table>
  
  </body>
</html>