<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="hero.new.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="com.pa165.ddtroops.web.HeroActionBean" var="actionBean"/>

        <s:form beanclass="com.pa165.ddtroops.web.HeroActionBean">
            <fieldset><legend><f:message key="hero.list.newhero"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="hero.list.newherocreate"/></s:submit>
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>