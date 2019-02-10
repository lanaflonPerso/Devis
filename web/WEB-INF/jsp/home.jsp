<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="fr">

<head>
    <%-- TODO : Comme config.getServletName() ne renvoie pas "Accueil" comme prévu (renvoie en pratique "jsp")
    on utilise plutôt request.getServletContext().getServletContextName() qui renvoie le <display-name> du web.xml càd le nom de l'application --%>
    <title><%=request.getServletContext().getServletContextName() %> - Accueil</title>
    <link href="<c:url value="/ressources/CSS/home.css"/>" rel="stylesheet" type="text/css">
</head>

<body>

    <br>

    <p class="h1">
        <%-- Utilisation des objets EL implicites ici param --%>
        Bienvenue <c:out value="${param.nom} ${paramValues.prenom[0]} ${paramValues.prenom[1]}" default="YOYO" /> <%-- TODO: Le default ne s'affiche pas...--%>
    </p>

    <p class="h2">
        Date courante: ${requestScope.currentDateTime}
    </p>


</body>
</html>
