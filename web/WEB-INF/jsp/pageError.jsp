<%@ page language="java" isErrorPage="true" %>
<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>

    <title>Page d'erreur</title>

    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.bundle.min.js" integrity="sha384-zDnhMsjVZfS3hiP7oCBRmfjkQC4fzxVxFhBx8Hkz2aZX8gEvA/jsP3eXRCvzTofP" crossorigin="anonymous"></script>

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
</body>
</html>