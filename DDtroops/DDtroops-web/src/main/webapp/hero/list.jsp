<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<s:layout-render name="/layout.jsp" titlekey="hero.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.pa165.ddtroops.web.HeroActionBean" var="actionBean"/>

        <p><f:message key="hero.list.allheroes"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="hero.name"/></th>
                <th><f:message key="hero.race"/></th>
                <th><f:message key="hero.xp"/></th>
                <th><f:message key="hero.roles"/></th>
                <th><f:message key="hero.troop"/></th>                
                <th></th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th></th>
                </sec:authorize>
            </tr>
            <c:forEach items="${actionBean.heroes}" var="hero">
                <tr>
                    <td>${hero.id}</td>
                    <td><c:out value="${hero.name}"/></td>
                    <td><c:out value="${hero.race}"/></td>
                    <td><c:out value="${hero.xp}"/></td>
                    <td><c:forEach items="${hero.role}" var="role"><c:out value="${role.name}"/> </c:forEach></td>
                    <td><c:out value="${hero.troop.name}"/></td>
                    
                    <td>
                     <s:link beanclass="com.pa165.ddtroops.web.HeroActionBean" event="edit"><s:param name="hero.id" value="${hero.id}"/><f:message key="hero.list.edit"/></s:link>
                    </td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td>
                        <s:form beanclass="com.pa165.ddtroops.web.HeroActionBean">
                            <s:hidden name="hero.id" value="${hero.id}"/>
                            <s:submit name="delete"><f:message key="hero.list.delete"/></s:submit>
                        </s:form>
                    </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
        <sec:authorize access="hasRole('ROLE_ADMIN')">        
        <s:link beanclass="com.pa165.ddtroops.web.HeroActionBean" event="create"><f:message key="hero.list.create"/></s:link>
        </sec:authorize>
    </s:layout-component>
</s:layout-render>