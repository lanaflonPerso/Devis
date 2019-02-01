<html>
<head>
    <link href="${pageContext.request.contextPath}/ressources/CSS/pageAccueil.css" rel="stylesheet" type="text/css">
    <title>Accueil</title>
</head>
<body>
    <h1>Accueil</h1>

    <%@ include file="menu.jsp" %>

    <!-- Utilisation des objets EL implicites ici param -->
    <p>Bienvenue <c:out value="${param.nom} ${paramValues.prenom[0]} ${paramValues.prenom[1]}" /></p>

</body>
</html>
