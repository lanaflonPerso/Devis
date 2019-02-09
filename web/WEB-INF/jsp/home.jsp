<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="fr">

<head>
    <title><%=request.getServletContext().getServletContextName() %> - Accueil</title> <%-- TODO : config.getServletName() ne renvoie pas "Accueil" (renvoie "jsp") --%>
    <link href="<c:url value="/ressources/CSS/home.css"/>" rel="stylesheet" type="text/css">
</head>

<body>

    <br>

    <p class="h1">
        <%-- Utilisation des objets EL implicites ici param --%>
        Bienvenue à <c:out value="${param.nom} ${paramValues.prenom[0]} ${paramValues.prenom[1]}" default="YOYO" /> <%-- TODO: Le default ne s'affiche pas...--%>
    </p>

    <p class="h2">
        Date courante: ${requestScope.currentDateTime}
    </p>


</body>
</html>
