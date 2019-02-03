<html>

<head>
    <title>Accueil</title>
    <link href="<c:url value="/ressources/CSS/home.css"/>" rel="stylesheet" type="text/css">
</head>

<body>

    <br>

    <div class="card border-primary mx-auto mb-3" style="max-width: 18rem;">
        <div class="card-header">
            <!-- Utilisation des objets EL implicites ici param -->
            Bienvenue à <c:out value="${param.nom} ${paramValues.prenom[0]} ${paramValues.prenom[1]}" default="YOYO" /> <!-- TODO: Le default ne s'affiche pas...-->
        </div>
        <div class="card-body text-primary">
            <h5 class="card-title">
                Application bac à sable
            </h5>
            <p class="card-text">
                Date courante: ${requestScope.currentTime}
            </p>
        </div>
    </div>

</body>
</html>
