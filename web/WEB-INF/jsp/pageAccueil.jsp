<html>

<head>
    <title>Accueil</title>
    <link href="${pageContext.request.contextPath}/ressources/CSS/pageAccueil.css" rel="stylesheet" type="text/css">
</head>

<body>

    <br>

    <!-- Utilisation des objets EL implicites ici param -->
    <p>Bienvenue à <c:out value="${param.nom} ${paramValues.prenom[0]} ${paramValues.prenom[1]}" /></p>
    <p>Date courante: ${requestScope.currentTime}</p>

</body>
</html>
