<html>
<head>
    <link url="${pageContext.request.contextPath}/ressources/CSS/page.css" rel="stylesheet" type="text/css">
    <title>Accueil</title>
</head>
<body>
    <h1>Accueil</h1>

    <%@ include file="menu.jsp" %>

    <!-- Utilisation des objets EL implicites ici param -->
    <p>Bienvenue <c:out value="${ param.nom }" /> <c:out value="${ param.prenom }" /></p>

</body>
</html>
