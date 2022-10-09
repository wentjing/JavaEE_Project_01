<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="width:500px; margin:0px auto">
    <div style="width:500px; margin:0px auto; text-align:center">
        <h2>修改员工信息</h2></div>
    <div>
        <script type="application/javascript">
                 function update(){
                     if(confirm("您确认要修改吗？")){
                         document.getElementById("updateform").submit();
                         return true;
                     }else{
                         return false;
                     }
                 }
        </script>
        <form id="updateform" action="${pageContext.request.contextPath}/updateEmp" method="post" onsubmit="return update()">
            <table width="500" border="1" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="145">员工姓名</td>
                    <td width="349">
                        <input type="hidden" name="empid" id="empid" value="${requestScope.employee.empid}"/>
                        <input type="text" name="empname" id="empname" value="${requestScope.employee.empname}"/></td>
                </tr>
                <tr>
                    <td>基本工资</td>
                    <td><input type="text" name="bsaralry" id="bsaralry" value="${requestScope.employee.bsaralry}"/></td>
                </tr>
                <tr>
                    <td>入职时间</td>
                    <td><input type="date" name="hiredate" id="hiredate" value='<fmt:formatDate value="${requestScope.employee.hiredate}" pattern="yyyy-MM-dd"></fmt:formatDate>'/></td>
                </tr>
                <tr>
                    <td>家庭住址</td>
                    <td><input type="text" name="address" id="address" value="${requestScope.employee.address}"/></td>
                </tr>
                <tr>
                    <td>所属部门</td>
                    <td>
                        <select name="depid" id="depid">
                            <c:if test="${requestScope.departLsit!=null}">
                                <c:forEach items="${requestScope.departLsit}" var="depart">
                                    <option value="${depart.depid}"  <c:if test="${depart.depid==requestScope.employee.depid}">selected="selected"</c:if>>${depart.depname}</option>
                                </c:forEach>
                            </c:if>
                        </select></td>
                </tr>
                <tr>
                    <td colspan="2" align="center" valign="middle">
                        <input type="submit" name="btnUpdate" id="btnUpdate" value="修改员工" />
                        &nbsp;<input type="reset" name="btnReset" id="btnReset" value="重置员工" /></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>

