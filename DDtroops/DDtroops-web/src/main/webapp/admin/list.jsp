<%-- 
    Document   : list admin
    Created on : 26.11.2014, 23:24:46
    Author     : Jakub Kovarik
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="admin.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.pa165.ddtroops.web.AdminActionBean" var="actionBean"/>

        <p><f:message key="admin.list.alladmins"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="admin.name"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.admins}" var="admin">
                <tr>
                    <td>${admin.id}</td>
                    <td><c:out value="${admin.name}"/></td>
                    <td>
                     <s:link beanclass="com.pa165.ddtroops.web.AdminActionBean" event="edit"><s:param name="admin.id" value="${admin.id}"/><f:message key="admin.list.edit"/></s:link>
                    </td>
                    <td>
                        <s:form beanclass="com.pa165.ddtroops.web.AdminActionBean">
                            <s:hidden name="admin.id" value="${admin.id}"/>
                            <s:submit name="delete"><f:message key="admin.list.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>
                
        <s:link beanclass="com.pa165.ddtroops.web.AdminActionBean" event="create"><f:message key="admin.list.create"/></s:link>
    </s:layout-component>
</s:layout-render>
