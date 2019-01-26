<html>
<head>
    <title>Devis</title>
</head>
<body>
    <h1>Devis</h1>

    <%@ include file="menu.jsp" %>

   <br>

    <table style="border-style: double">
        <tr>
            <th>idDevis</th>
            <th>numDevis</th>
            <th>dateDevis</th>
            <th>dateFinValidite</th>
            <th>commentaire</th>
            <th>clientInterlocuteurId</th>
            <th>typeLivraisonId</th>
            <th>entrepriseContactId</th>
            <th>entrepriseId</th>
            <th>factureId</th>
        </tr>
        <c:forEach var="devis" items="${ listeDevis }">
            <tr>
                <td><c:out value="${ devis.idDevis }" /></td>
                <td><c:out value="${ devis.numDevis }" /></td>
                <td><c:out value="${ devis.dateDevis }" /></td>
                <td><c:out value="${ devis.dateFinValidite }" /></td>
                <td><c:out value="${ devis.commentaire }" /></td>
                <td><c:out value="${ devis.clientInterlocuteurId }" /></td>
                <td><c:out value="${ devis.typeLivraisonId }" /></td>
                <td><c:out value="${ devis.entrepriseContactId }" /></td>
                <td><c:out value="${ devis.entrepriseId }" /></td>
                <td><c:out value="${ devis.factureId }" /></td>
            </tr>
        </c:forEach>
    </table>

    <br>

    <p>Ajouter un devis</p>

    <%@ include file="form.jsp" %>

    <p><c:if test="${isAddOk}">"Ajout Devis OK"</c:if></p>

</body>
</html>



