<html>
<head>
    <link href="${pageContext.request.contextPath}/ressources/CSS/pageDevis.css" rel="stylesheet" type="text/css">
    <title>Devis</title>
</head>
<body>
    <h1>Devis</h1>

    <%@ include file="menu.jsp" %>

   <br>

    <!-- Liste Table Devis -->
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

    <!-- Liste Table Facture -->
    <table style="border-style: double">
        <tr>
            <th>idFacture</th>
            <th>dateFacturation</th>
            <th>delaiPaiementId</th>
            <th>datePaiement</th>
            <th>totalHt</th>
            <th>tauxTva100</th>
            <th>totalTtc</th>
            <th>statutFactureId</th>
            <th>modePaiementId</th>
        </tr>
        <c:forEach var="facture" items="${ listeFactures }">
            <tr>
                <td><c:out value="${ facture.idFacture }" /></td>
                <td><c:out value="${ facture.dateFacturation }" /></td>
                <td><c:out value="${ facture.delaiPaiementId }" /></td>
                <td><c:out value="${ facture.datePaiement }" /></td>
                <td><c:out value="${ facture.totalHt }" /></td>
                <td><c:out value="${ facture.tauxTva100 }" /></td>
                <td><c:out value="${ facture.totalTtc }" /></td>
                <td><c:out value="${ facture.statutFactureId }" /></td>
                <td><c:out value="${ facture.modePaiementId }" /></td>
            </tr>
        </c:forEach>
    </table>

    <h1>Ajouter un devis</h1>

    <%@ include file="form.jsp" %>

</body>
</html>



