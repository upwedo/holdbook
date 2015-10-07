<%@ page language="java" contentType="text/html; charset=gbk"    pageEncoding="gbk"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type"	content="text/html; charset=gbk">
<title>֤ȯ��̨����ϵͳ-ϵͳ����Ա</title>
         <!-- ҳ���󲿷� -->
         <%@include file="left.jsp" %>
		<div id="main-content">
			<!-- Main Content Section with everything -->
			<!-- Page Head -->
			<h2>ServiceImpl�������</h2>
			<p id="page-intro">&nbsp;</p>

			<ul class="shortcut-buttons-set">
				<li><a class="shortcut-button" href="#"><span> <img
							src="${pageContext.request.contextPath}/content/images/pencil_48.png" alt="icon"><br>
							���ServiceImpl
					</span></a></li>

				<li><a class="shortcut-button" href="#"><span> <img
							src="${pageContext.request.contextPath}/content/images/paper_content_pencil_48.png" alt="icon"><br>
							�༭ServiceImpl
					</span></a></li>

				<li><a class="shortcut-button" href="#"><span> <img
							src="${pageContext.request.contextPath}/content/images/list.png" alt="icon"><br>
							���ServiceImpl
					</span></a></li>
			</ul>
			<!-- End .shortcut-buttons-set -->

			<div class="clear"></div>
			<!-- End .clear -->
			<form action="codeServiceImpl.do" method="post" >
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
									<label>Entity����</label> 
									<input	class="text-input small-input" id="small-input"name="sp_entityName" type="text" value="${session.entityName}">								
								</p>
								<p>
									<label>Dao����</label> 
									<input	class="text-input small-input" id="small-input"name="sp_daoName" type="text" readonly="readonly">								
								</p>
								<p>
									<label>DaoImpl����</label> 
									<input	class="text-input small-input" id="small-input"name="sp_daoImplName" type="text" value="${session.daoImplName}" readonly="readonly">								
								</p>
								<p>
									<label>Service����</label> 
									<input	class="text-input small-input" id="small-input"name="sp_serviceName" type="text" value="${session.serviceName}" readonly="readonly">								
								</p>
								<p>
									<label>ServiceImpl����</label> 
									<input	class="text-input small-input" id="small-input"name="sp_serviceImplName" type="text" <s:if test="#session.entityName!=null">value="${session.entityName}ServiceImpl"</s:if>>								
								</p>
								<p>
									<label>@Service</label> 
									<input	class="text-input small-input" id="small-input"name="sp_service" type="text" >								
								</p>	
									<small>Ĭ��Ϊ��service��������ĸСд</small>			
								<p>
								<p>
									<label>@Resource Daoע��</label> 
									<input	class="text-input small-input" id="small-input"name="sp_dao" type="text" >								
								</p>	
									<small>Ĭ��Ϊ��entity��������ĸСд����"Dao"</small>			
								<p>
									<br/>
									<input class="button" value="�ύ" type="submit">
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