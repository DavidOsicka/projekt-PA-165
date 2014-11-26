<%-- 
    Document   : create troop
    Created on : 26.11.2014, 15:57:50
    Author     : Jakub Szotkowski
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="troop.new.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.pa165.ddtroops.web.TroopActionBean" var="actionBean"/>

        <s:form beanclass="com.pa165.ddtroops.web.TroopActionBean">
            <fieldset><legend><f:message key="troop.list.newtroop"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="troop.list.newtroopcreate"/></s:submit>
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>
