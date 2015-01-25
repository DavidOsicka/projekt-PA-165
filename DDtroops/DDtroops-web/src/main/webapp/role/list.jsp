<%-- 
    Document   : list
    Created on : Nov 26, 2014, 2:02:49 PM
    Author     : Martin Peska
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="role.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.pa165.ddtroops.web.RoleActionBean" var="actionBean"/>

        <p><f:message key="role.list.allroles"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="role.name"/></th>
                <th><f:message key="role.description"/></th>
                <th><f:message key="role.energy"/></th>
                <th><f:message key="role.attack"/></th>
                <th><f:message key="role.defense"/></th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th></th>
                <th></th>
                </sec:authorize>
            </tr>
            <c:forEach items="${actionBean.roles}" var="role">
                <tr>
                    <td>${role.id}</td>
                    <td><c:out value="${role.name}"/></td>
                    <td><c:out value="${role.description}"/></td>
                    <td><c:out value="${role.energy}"/></td>
                    <td><c:out value="${role.attack}"/></td>
                    <td><c:out value="${role.defense}"/></td>
                     <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td>
                     <s:link beanclass="com.pa165.ddtroops.web.RoleActionBean" event="edit"><s:param name="role.id" value="${role.id}"/><f:message key="role.list.edit"/></s:link>
                    </td>
                    <td>
                        <s:form beanclass="com.pa165.ddtroops.web.RoleActionBean">
                            <s:hidden name="role.id" value="${role.id}"/>
                            <s:submit name="delete"><f:message key="role.list.delete"/></s:submit>
                        </s:form>
                    </td>
                     </sec:authorize>
                </tr>
            </c:forEach>
        </table>
        <sec:authorize access="hasRole('ROLE_ADMIN')">        
        <s:link beanclass="com.pa165.ddtroops.web.RoleActionBean" event="create"><f:message key="role.list.create"/></s:link>
        </sec:authorize>
    </s:layout-component>
</s:layout-render>
