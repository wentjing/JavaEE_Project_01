<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="width:500px; margin:0px auto">
    <div style="width:500px; margin:0px auto; text-align:center"><h2>员工详细信息</h2></div>
    <div>
        <table width="500" border="1" cellspacing="0" cellpadding="0">
            <tr>
                <td width="145">员工编号</td>
                <td width="349">${requestScope.employeeVo.empid}</td>
            </tr>
            <tr>
                <td>员工姓名</td>
                <td>${requestScope.employeeVo.empname}</td>
            </tr>
            <tr>
                <td>基本工资</td>
                <td>${requestScope.employeeVo.bsaralry}</td>
            </tr>
            <tr>
                <td>入职时间</td>
                <td><fmt:formatDate value="${requestScope.employeeVo.hiredate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
            </tr>
            <tr>
                <td>家庭住址</td>
                <td>${requestScope.employeeVo.address}</td>
            </tr>
            <tr>
                <td>所属部门</td>
                <td>${requestScope.employeeVo.depname}</td>
            </tr>
            <tr>
                <td colspan="2" align="center" valign="middle">
                    <input type="button" name="btnReturn" id="btnReturn" value="返回首页" onclick="javascript:history.go(-1)"/></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>

