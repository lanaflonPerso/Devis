<html>

<head>
    <title>Accueil</title>
    <link href="<c:url value="/ressources/CSS/home.css"/>" rel="stylesheet" type="text/css">
</head>

<body>

    <br>

    <!-- Utilisation des objets EL implicites ici param -->
    <p>Bienvenue à <c:out value="${param.nom} ${paramValues.prenom[0]} ${paramValues.prenom[1]}" default="YOYO" /></p> <!-- TODO: Le default ne s'affiche pas...-->
    <p>Date courante: ${requestScope.currentTime}</p>

</body>
</html>
