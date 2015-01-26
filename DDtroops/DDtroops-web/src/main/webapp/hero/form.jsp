<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:errors/>
<table>
    <sec:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin" />
    <c:choose>
        <c:when test="${isAdmin}">
            <tr>
                <th><s:label for="h1" name="hero.name"/></th>
                <td><s:text id="h1" name="hero.name"/></td>
            </tr>
            <tr>
                <th><s:label for="h2" name="hero.race"/></th>
                <td><s:text id="h2" name="hero.race"/></td>
            </tr>
            <tr>
                <th><s:label for="h3" name="hero.xp"/></th>
                <td><s:text id="h3" name="hero.xp"/></td>
            </tr>
        </c:when>
        <c:otherwise>
            <s:hidden name="hero.name" value="${hero.name}" />
            <s:hidden name="hero.race" value="${hero.race}" />
            <s:hidden name="hero.xp" value="${hero.xp}" />
        </c:otherwise>
    </c:choose>
    
    <tr>
        <th><s:label for="h4" name="hero.troop"/></th>
        <td>
            <s:select id="h4" name="hero.troop.id">
                <s:option value=""><f:message key="hero.edit.notroop"/></s:option>
                <s:options-collection collection="${actionBean.allTroops}" value="id" label="name" />
            </s:select>
        </td>
    </tr>
    <tr>
        <th><s:label for="h5" name="hero.roles"/></th>
        <td>
            <c:forEach items="${actionBean.allRoles}" var="role">
                <s:checkbox id="role${role.id}" name="newRoles[]" value="${role.id}" />
                <s:label for="role${role.id}" name="${role.name}"/>
            </c:forEach>
        </td>
    </tr>
</table>