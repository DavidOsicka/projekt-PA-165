<%-- 
    Document   : list troop
    Created on : 26.11.2014, 16:00:50
    Author     : Jakub Szotkowski
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="troop.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.pa165.ddtroops.web.TroopActionBean" var="actionBean"/>

        <p><f:message key="troop.list.alltroops"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="troop.name"/></th>
                <th><f:message key="troop.mission"/></th>
                <th><f:message key="troop.amountOfGM"/></th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th></th>
                <th></th>
                </sec:authorize>
            </tr>
            <c:forEach items="${actionBean.troops}" var="troop">
                <tr>
                    <td>${troop.id}</td>
                    <td><c:out value="${troop.name}"/></td>
                    <td><c:out value="${troop.mission}"/></td>
                    <td><c:out value="${troop.amountOfGM}"/></td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td>
                     <s:link beanclass="com.pa165.ddtroops.web.TroopActionBean" event="edit"><s:param name="troop.id" value="${troop.id}"/><f:message key="troop.list.edit"/></s:link>
                    </td>
                    <td>
                        <s:form beanclass="com.pa165.ddtroops.web.TroopActionBean">
                            <s:hidden name="troop.id" value="${troop.id}"/>
                            <s:submit name="delete"><f:message key="troop.list.delete"/></s:submit>
                        </s:form>
                    </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
        <sec:authorize access="hasRole('ROLE_ADMIN')">        
        <s:link beanclass="com.pa165.ddtroops.web.TroopActionBean" event="create"><f:message key="troop.list.create"/></s:link>
        </sec:authorize>
    </s:layout-component>
</s:layout-render>
