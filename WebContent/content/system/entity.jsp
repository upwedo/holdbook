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
			<h2>ENTITY��������</h2>
			<p id="page-intro">&nbsp;</p>

			<ul class="shortcut-buttons-set">
				<li><a class="shortcut-button" href="#"><span> <img
							src="${pageContext.request.contextPath}/content/images/pencil_48.png" alt="icon"><br>
							��ӱ�
					</span></a></li>

				<li><a class="shortcut-button" href="#"><span> <img
							src="${pageContext.request.contextPath}/content/images/paper_content_pencil_48.png" alt="icon"><br>
							�༭��
					</span></a></li>

				<li><a class="shortcut-button" href="#"><span> <img
							src="${pageContext.request.contextPath}/content/images/list.png" alt="icon"><br>
							�����
					</span></a></li>
			</ul>
			<!-- End .shortcut-buttons-set -->

			<div class="clear"></div>
			<!-- End .clear -->
			<form action="codeEntity.action" method="post" id="processEntity" name="processEntity">
			<s:if test="addEntityTip!=null">
			<div class="notification success png_bg">
				<a href="#" class="close"><img src="${pageContext.request.contextPath}/content/images/cross_grey_small.png" title="Close this notification" alt="close" /></a>
				<div>
					${addEntityTip}
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
			<div class="content-box">
				<!-- Start Content Box -->
				<div class="content-box-header">
					<h3 style="cursor: s-resize;">ʵ���� --</h3><s:textfield cssStyle="padding-top: 4px; margin-top: 6.5px;" name="t_entityName"></s:textfield>
					<div class="clear"></div>
				</div>
			</div>
			<div class="content-box">
				<!-- Start Content Box -->
                
				<div class="content-box-header">
					<h3 style="cursor: s-resize;">���ݱ��� -- </h3><s:textfield cssStyle="padding-top: 4px; margin-top: 6.5px;" name="t_name"></s:textfield>
					<div class="clear"></div>
				</div>
				<!-- End .content-box-header -->
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
						<!-- This is the target div. id must match the href of this div's tab -->
						
						<table width="80%">
							<thead>
								<tr>
									<th  width="6%">�ֶ���</th>
									<th>����</th>
									<th >����</th>
									<th>����</th>
									<th  width="8%">ע��</th>
									<th>����</th>
								</tr>
							</thead>
						<tbody>
								<tr class="alt-row" id="t_1">
								<td><input type="text" name="t_field" style="background-color: greenyellow;"></td>
								<td><select name="t_type">
											<option value="String">String</option>
											<option value="Date">Date</option>
											<option value="Integer">Integer</option>
											<option value="Double">Double</option>
											<option value="float">float</option>
									</select></td>
								<td><input type="text" name="t_size" size="20"></td>
								<td><input type="checkbox" name="t_key"></td>
									<td>
									<input type="text" name="t_note" size="20">
									</td>
								<td>
									<a href="javascript:addfield(2);" title="����"><img	src="${pageContext.request.contextPath}/content/images/addbutton.png"	alt="����"></a>
                                </td>
								</tr>	
								<tr id="t_2">
								</tr>	
							</tbody>
						</table>

					</div>
					<!-- End #tab1 -->
				</div>
				<!-- End .content-box-content -->
			</div>
			
			<!-- End .content-box -->
			<div class="clear"></div>
			<p align="right">
				 <input class="button" value="�ύ" type="submit">
			</p>
		   </form>
		</div>
	
		<!-- End #main-content -->
		
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
	<script>
              function addfield(num){
            	  var div=document.getElementById('t_'+num).outerHTML=
            		  '<tr id="t_'+num+'"><td><input type="text" name="t_field" style="background-color: greenyellow;" size="20"></td>'+
					 '<td><select name="t_type">'+
					 '			<option value="String">String</option>'+
					 '			<option value="Date">Date</option>'+
					 '			<option value="Integer">Integer</option>'+
					 '			<option value="Double">Double</option>'+
					 '			<option value="float">float</option>'+
					 '	</select></td>'+
					 '<td><input type="text" name="t_size" size="20"></td>'+
					 '<td><input type="checkbox" name="t_key"></td>'+
					 '	<td>'+
					 '	<input type="text" name="t_note" size="20">'+
					 '	</td>'+
					 '<td>'+
					 '	<a href="javascript:addfield('+(1+num)+');" title="����"><img	src="${pageContext.request.contextPath}/content/images/addbutton.png"	alt="����"></a>'+
                   '</td>'+
					 '</tr>	'+
					 '<tr id="t_'+(1+num)+'"></tr>';
              }
           </script>
</body>
</html>