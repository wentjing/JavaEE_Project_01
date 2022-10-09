<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="width:900px; margin:0px auto;">
<div style="width:900px; text-align:center">
    <h2>员工信息列表</h2>
</div>
<div style="width:880px; text-align:right; padding-right:20px; line-height:25px; height:25px;"><a href="${pageContext.request.contextPath}/pre_add">添加员工信息</a>
</div>
<div style="width:900px; text-align:center">
    <form id="searchForm" action="${pageContext.request.contextPath}/pageList" method="post">
        <input type="hidden" name="pageNum" id="pageNum">
        部门名称：
        <select name="depid" id="depid">
            <option value="-1">-请选择部门名称--</option>
            <c:if test="${requestScope.departLsit!=null}">
                <c:forEach items="${requestScope.departLsit}" var="depart">
                    <option value="${depart.depid}"  <c:if test="${depart.depid==requestScope.conditionVo.depid}">selected="selected"</c:if>>${depart.depname}</option>
                </c:forEach>
            </c:if>
        </select>
        家庭地址：
        <input name="address" type="text" id="address" size="10" value="${requestScope.conditionVo.address}"/>
        基本工资：
        <input name="min_bsaralry" type="text" id="min_bsaralry" size="10" value="${requestScope.conditionVo.min_bsaralry}"/>
        到
        <input name="max_bsaralry" type="text" id="max_bsaralry" size="10" value="${requestScope.conditionVo.max_bsaralry}"/>
        <input type="submit" name="btnSearch" id="btnSearch" value="查询员工"/>
    </form>
</div>
<div style="width:900px; text-align:center">
    <table width="900" border="1" cellspacing="0" cellpadding="0">
        <tr>
            <td>工号</td>
            <td>姓名</td>
            <td>基本工资</td>
            <td>入职日期</td>
            <td>家庭地址</td>
            <td>所属部门</td>
            <td>详细</td>
            <td>删除</td>
            <td>修改</td>
        </tr>
        <c:if test="${requestScope.pageInfo.list!=null}">
            <c:forEach items="${requestScope.pageInfo.list}" var="empVo">
                <tr>
                    <td>${empVo.empid}</td>
                    <td>${empVo.empname}</td>
                    <td>${empVo.bsaralry}</td>
                    <td><fmt:formatDate value="${empVo.hiredate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                    <td>${empVo.address}</td>
                    <td>${empVo.depname}</td>
                    <td><a href="${pageContext.request.contextPath}/viewEmp/${empVo.empid}">详细</a></td>
                    <td><a href="javascript:del(${empVo.empid})">删除</a></td>
                    <td><a href="${pageContext.request.contextPath}/pre_update/${empVo.empid}">修改</a></td>
                </tr>
            </c:forEach>
        </c:if>
        <tr>
            <td colspan="9" align="center" valign="middle">
                <a href="javascript:doPage(1)">首页</a>&nbsp;
                <a href="javascript:doPage(${requestScope.pageInfo.pageNum -1})">上一页</a>&nbsp;
                <a href="javascript:doPage(${requestScope.pageInfo.pageNum +1})">下一页</a>&nbsp;
                <a href="javascript:doPage(${requestScope.pageInfo.pages})">末页</a>&nbsp;
                ${requestScope.pageInfo.pageNum}/${requestScope.pageInfo.pages}页
            </td>
        </tr>
    </table>
</div>
</body>
<script type="application/javascript">
    function doPage(pageNo) {
        document.getElementById("pageNum").value = pageNo;
        document.getElementById("searchForm").submit();
    }
    function del(empid){
        var url = "${pageContext.request.contextPath}/deleteEmp?empid=" + empid;
        if(confirm("您确认要删除吗？")){
            location.href = url;
            return true;
        }else{
            return false;
        }
    }
</script>
</html>
