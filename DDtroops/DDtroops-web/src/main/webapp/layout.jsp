<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>


<s:layout-definition>
    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">
        <head>
            <title><f:message key="${titlekey}"/></title>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
            <s:layout-component name="header"/>
        </head>
        <body>
            <div id="hdr">  
            </div>
            <div id="mainHdr"></div>
            <div id="main">


                <div id="navigation">
                    <ul>
                        <li><s:link href="/index.jsp"><f:message key="navigation.index"/></s:link></li>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li><s:link beanclass="com.pa165.ddtroops.web.AdminActionBean" event="list"><f:message key="navigation.admin"/></s:link></li>
                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
                            <li><s:link beanclass="com.pa165.ddtroops.web.HeroActionBean" event="list"><f:message key="navigation.hero"/></s:link></li>
                            <li><s:link beanclass="com.pa165.ddtroops.web.RoleActionBean" event="list"><f:message key="navigation.role"/></s:link></li>
                            <li><s:link beanclass="com.pa165.ddtroops.web.TroopActionBean" event="list"><f:message key="navigation.troop"/></s:link></li>
                        </sec:authorize>
                    </ul>
                </div>
                                
                <div id="content">
                    
                    <div id="logout">
                        <sec:authorize url="/all">
                            <a href="<c:url value="/j_spring_security_logout" />">
                                <f:message key="login.logout"/>
                            </a>
                            <br/>
                            <f:message key="username"/>: <%= request.getUserPrincipal().getName()%>
                        </sec:authorize>
                        <sec:authorize access="isAnonymous()">
                            <br/>
                            <a href="${pageContext.request.contextPath}/registration.jsp">
                                <f:message key="login.signup"/>
                            </a>
                            <br/>
                            <a href="${pageContext.request.contextPath}/login.jsp">
                                <f:message key="login.signin"/>
                            </a><br/>
                        </sec:authorize>
                    </div>
                    
                    <h1><f:message key="${titlekey}"/></h1>
                    <s:messages/>
                    <s:layout-component name="body"/>

                </div>
            </div>
            <div id="mainFtr"></div>
        </body>
    </html>
</s:layout-definition>