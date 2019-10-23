<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="subnav fl">
    <c:forEach var="goodsType" items="${goodsTypes}">
        <li><a href="#model0${goodsType.id}"  class="${goodsType.className}">${goodsType.title}</a></li>
    </c:forEach>
</ul>
