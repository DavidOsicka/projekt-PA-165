<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="hero.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.pa165.ddtroops.web.HeroActionBean" var="actionBean"/>

        <s:form beanclass="com.pa165.ddtroops.web.HeroActionBean">
            <s:hidden name="hero.id"/>
            <fieldset><legend><f:message key="hero.edit.edit"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="save"><f:message key="hero.edit.save"/></s:submit>
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>