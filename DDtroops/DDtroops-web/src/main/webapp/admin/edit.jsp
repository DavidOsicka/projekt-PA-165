<%-- 
    Document   : edit admin
    Created on : 27.11.2014, 0:45:51
    Author     : Jakub Kovarik
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="admin.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.pa165.ddtroops.web.AdminActionBean" var="actionBean"/>

        <s:form beanclass="com.pa165.ddtroops.web.AdminActionBean">
            <s:hidden name="admin.id"/>
            <fieldset><legend><f:message key="admin.edit.edit"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="save"><f:message key="admin.edit.save"/></s:submit>
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>
