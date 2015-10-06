<%@ page language="java" contentType="text/html; charset=gbk"    pageEncoding="gbk"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type"	content="text/html; charset=gbk">
<title>证券后台管理系统-系统管理员</title>
         <!-- 页面左部分 -->
         <%@include file="left.jsp" %>
		<div id="main-content">
			<!-- Main Content Section with everything -->
			<!-- Page Head -->
			<h2>Service代码管理</h2>
			<p id="page-intro">&nbsp;</p>

			<ul class="shortcut-buttons-set">
				<li><a class="shortcut-button" href="#"><span> <img
							src="${pageContext.request.contextPath}/content/images/pencil_48.png" alt="icon"><br>
							添加Service
					</span></a></li>

				<li><a class="shortcut-button" href="#"><span> <img
							src="${pageContext.request.contextPath}/content/images/paper_content_pencil_48.png" alt="icon"><br>
							编辑Service
					</span></a></li>

				<li><a class="shortcut-button" href="#"><span> <img
							src="${pageContext.request.contextPath}/content/images/list.png" alt="icon"><br>
							浏览Service
					</span></a></li>
			</ul>
			<!-- End .shortcut-buttons-set -->

			<div class="clear"></div>
			<!-- End .clear -->
			<form action="processService.do" method="post" >
			<s:if test="sucessTip!=null">
			<div class="notification success png_bg">
				<a href="#" class="close"><img src="${pageContext.request.contextPath}/content/images/cross_grey_small.png" title="Close this notification" alt="close" /></a>
				<div>
					${sucessTip}
				</div>
			</div>
			</s:if>
			<s:if test="errorTip!=null">
			<div class="notification error png_bg">
				<a href="#" class="close"><img src="${pageContext.request.contextPath}/content/images/cross_grey_small.png" title="Close this notification" alt="close" /></a>
				<div>
					${errorTip}
				</div>
			</div>
			</s:if>
			<div style="display: block;" class="tab-content" id="tab2">
							<fieldset>
								<!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
								<p>
									<label>Entity名称</label> 
									<input	class="text-input small-input" id="small-input"name="s_entityName" type="text" value="${session.entityName}">								
								</p>
								<p>
									<label>Dao名称</label> 
									<input	class="text-input small-input" id="small-input"name="s_daoName" type="text" readonly="readonly">								
								</p>
								<p>
									<label>DaoImpl名称</label> 
									<input	class="text-input small-input" id="small-input"name="s_daoImplName" type="text" value="${session.daoImplName}" readonly="readonly">								
								</p>
								<p>
									<label>Service名称</label> 
									<input	class="text-input small-input" id="small-input"name="s_serviceName" type="text" <s:if test="#session.entityName!=null">value="${session.entityName}Service"</s:if>>								
								</p>				
								<p>
									<br/>
									<input class="button" value="提交" type="submit">
								</p>

							</fieldset>

							<div class="clear"></div>
							<!-- End .clear -->
					</div>
		<!-- End #main-content -->
		</form>
	</div>
	<div id="facebox" style="display: none;">
		<div class="popup">
			<table>
				<tbody>
					<tr>
						<td class="tl"></td>
						<td class="b"></td>
						<td class="tr"></td>
					</tr>
					<tr>
						<td class="b"></td>
						<td class="body">
							<div class="content"></div>
							<div class="footer">
								<a href="#" class="close"> <img
									src="${pageContext.request.contextPath}/content/images/closelabel.gif" title="close"
									class="close_image">
								</a>
							</div>
						</td>
						<td class="b"></td>
					</tr>
					<tr>
						<td class="bl"></td>
						<td class="b"></td>
						<td class="br"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>