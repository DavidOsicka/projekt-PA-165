<%-- 
    Document   : edit
    Created on : Nov 26, 2014, 1:25:18 PM
    Author     : Martin Peska
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="role.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.pa165.ddtroops.web.RoleActionBean" var="actionBean"/>

        <s:form beanclass="com.pa165.ddtroops.web.RoleActionBean">
            <s:hidden name="role.id"/>
            <fieldset><legend><f:message key="role.edit.edit"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="save"><f:message key="role.edit.save"/></s:submit>
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>