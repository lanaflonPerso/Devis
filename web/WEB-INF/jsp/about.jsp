<!DOCTYPE html>
<html lang="fr">

<head>
    <title><%=request.getServletContext().getServletContextName() %> - A propos</title>
    <link href="<c:url value="/ressources/CSS/display.css"/>" rel="stylesheet" type="text/css">
</head>


<body id="pageBody">

<%-- TODO : Récupérer ces éléments de Maven plutôt que depuis les <context-param><param-name> du web.xml
     Cf. https://openclassrooms.com/fr/courses/4503526-organisez-et-packagez-une-application-java-avec-apache-maven/4609764-packagez-vos-livrables --%>
<div class="container d-flex align-items-center flex-column justify-content-center h-100" >
    <ul class="list-group" >
        <li class="list-group-item"><strong>Application</strong> : ${requestScope.application_name}</li>
        <li class="list-group-item"><strong>Version</strong> : ${requestScope.project_version}</li>
        <li class="list-group-item"><strong>Date du build</strong> : ${requestScope.maven_build_timestamp}</li>
        <li class="list-group-item"><strong>DataBase</strong> : url : ${requestScope.poolProperties.url} // user : ${requestScope.poolProperties.username} </li>
        <li class="list-group-item"><strong>Server</strong> : ${requestScope.serverInfo}</li>
        <li class="list-group-item"><strong>Organisation</strong> : <a href="${requestScope.organization_url}">${requestScope.organization_name}</a></li>
    </ul>
</div>

</body>
</html>