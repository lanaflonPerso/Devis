<html>
<head>
    <link url="${pageContext.request.contextPath}/ressources/CSS/page.css" rel="stylesheet" type="text/css">
    <title>Accueil</title>
</head>
<body>
    <h1>Accueil</h1>

    <%@ include file="menu.jsp" %>

    <p>Bienvenue <c:out value="${ nom }" /> <c:out value="${ prenom }" /></p>

</body>
</html>
