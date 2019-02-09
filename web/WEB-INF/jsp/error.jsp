<%@ page language="java" isErrorPage="true" %>
<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
    <title><%=request.getServletContext().getServletContextName() %> - Page d'erreur</title>
</head>

<body bgcolor="#FFFFFF">
<div>Une erreur s'est produite !</div>
<h1>Attribut de requete</h1>
<div class="requestAttributs">
    <%
        java.util.Enumeration en = request.getAttributeNames();
        while (en.hasMoreElements()) {
            String name = (String) en.nextElement();
            if(request.getAttribute(name) == null) {
    %>
    <div>
        <%=name%>=null
    </div>
    <%
    } else {
    %>
    <div>
        <%=name%>=<%=request.getAttribute(name).toString()%>
    </div>
    <%
            }
        }
    %>
</div>

<div class="requestParameters">
    <%
        en=request.getParameterNames();
        while (en.hasMoreElements()) {
            String name = (String) en.nextElement();
            if(request.getParameter(name) == null) {
    %>
    <div>
        <%=name%>=null
    </div>
    <%
    } else {
    %>
    <div>
        <%=name%>=<%=request.getParameter(name).toString()%>
    </div>
    <%
            }
        }
    %>
</div>

<h1>Exception</h1>
<div>
    <%
        Throwable th = pageContext.getException();
        if(th != null) {
            printRecursiveException(out,th);
        }
    %>
</div>

<%! public void printRecursiveException(JspWriter out, Throwable th) throws java.io.IOException {
    out.println("<div>" + th.getClass().getName() + ":" + th.getMessage() + "</div>");
    StackTraceElement ste;
    for(int x=0;x < th.getStackTrace().length;x++) {
        out.println("<div>" + th.getStackTrace()[x].toString() + "</div>");
    }

    Throwable parent = th.getCause();
    if(parent != null) {
        out.println("<div class=\"parentCause\">");
        printRecursiveException(out,parent);
        out.println("</div>");
    }
}
%>

<%-- TEST NOUVELLE ERROR PAGE --%>
<h1>Opps...</h1>
<table width = "100%" border = "1">
    <tr valign = "top">
        <td width = "40%"><b>Error:</b></td>
        <td>${pageContext.exception}</td>
    </tr>

    <tr valign = "top">
        <td><b>URI:</b></td>
        <td>${pageContext.errorData.requestURI}</td>
    </tr>

    <tr valign = "top">
        <td><b>Status code:</b></td>
        <td>${pageContext.errorData.statusCode}</td>
    </tr>

    <tr valign = "top">
        <td><b>Stack trace:</b></td>
        <td>
            <c:forEach var = "trace"
                       items = "${pageContext.exception.stackTrace}">
                <p>${trace}</p>
            </c:forEach>
        </td>
    </tr>
</table>
<%-- TEST NOUVELLE ERROR PAGE --%>

</body>
</html>
