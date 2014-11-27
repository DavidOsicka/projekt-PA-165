<%-- 
    Document   : create
    Created on : Nov 26, 2014, 1:47:56 PM
    Author     : Martin Peska
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="role.new.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.pa165.ddtroops.web.RoleActionBean" var="actionBean"/>

        <s:form beanclass="com.pa165.ddtroops.web.RoleActionBean">
            <fieldset><legend><f:message key="role.list.newrole"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="role.list.newrolecreate"/></s:submit>
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>
