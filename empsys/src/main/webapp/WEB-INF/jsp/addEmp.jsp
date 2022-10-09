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
        <h2>添加员工信息</h2></div>
    <div>
        <form action="${pageContext.request.contextPath}/addEmp" method="post">
            <table width="500" border="1" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="145">员工姓名</td>
                    <td width="349">
                        <input type="text" name="empname" id="empname"/></td>
                </tr>
                <tr>
                    <td>基本工资</td>
                    <td><input type="text" name="bsaralry" id="bsaralry"/></td>
                </tr>
                <tr>
                    <td>入职时间</td>
                    <td><input type="date" name="hiredate" id="hiredate"/></td>
                </tr>
                <tr>
                    <td>家庭住址</td>
                    <td><input type="text" name="address" id="address"/></td>
                </tr>
                <tr>
                    <td>所属部门</td>
                    <td>
                        <select name="depid" id="depid">
                            <option value="-1">--请选择部门--</option>
                            <c:if test="${requestScope.departLsit!=null}">
                                <c:forEach items="${requestScope.departLsit}" var="depart">
                                    <option value="${depart.depid}">${depart.depname}</option>
                                </c:forEach>
                            </c:if>
                        </select></td>
                </tr>
                <tr>
                    <td colspan="2" align="center" valign="middle">
                        <input type="submit" name="btnAdd" id="btnAdd" value="添加员工"/>
                        &nbsp;<input type="reset" name="btnReset" id="btnReset" value="重置员工"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>

