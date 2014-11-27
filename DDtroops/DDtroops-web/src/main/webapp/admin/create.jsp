<%-- 
    Document   : create admin
    Created on : 27.11.2014, 0:22:33
    Author     : Jakub Kovarik
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="admin.new.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.pa165.ddtroops.web.AdminActionBean" var="actionBean"/>

        <s:form beanclass="com.pa165.ddtroops.web.AdminActionBean">
            <fieldset><legend><f:message key="admin.list.newadmin"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="admin.list.newadmincreate"/></s:submit>
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>
