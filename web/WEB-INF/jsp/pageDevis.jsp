<html>

<head>
    <title>Devis</title>
    <link href="${pageContext.request.contextPath}/ressources/CSS/pageDevis.css" rel="stylesheet" type="text/css">
</head>

<body id="pageDevisBody">

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
        <c:forEach var="devis" items="${ requestScope.listeDevis }">
            <tr>
                <td><c:out value="${ pageScope.devis.idDevis }" /></td>
                <td><c:out value="${ pageScope.devis.numDevis }" /></td>
                <td><c:out value="${ pageScope.devis.dateDevis }" /></td>
                <td><c:out value="${ pageScope.devis.dateFinValidite }" /></td>
                <td><c:out value="${ pageScope.devis.commentaire }" /></td>
                <td><c:out value="${ pageScope.devis.clientInterlocuteurId }" /></td>
                <td><c:out value="${ pageScope.devis.typeLivraisonId }" /></td>
                <td><c:out value="${ pageScope.devis.entrepriseContactId }" /></td>
                <td><c:out value="${ pageScope.devis.entrepriseId }" /></td>
                <td><c:out value="${ pageScope.devis.factureId }" /></td>
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
        <c:forEach var="facture" items="${ requestScope.listeFactures }">
            <tr>
                <td><c:out value="${ pageScope.facture.idFacture }" /></td>
                <td><c:out value="${ pageScope.facture.dateFacturation }" /></td>
                <td><c:out value="${ pageScope.facture.delaiPaiementId }" /></td>
                <td><c:out value="${ pageScope.facture.datePaiement }" /></td>
                <td><c:out value="${ pageScope.facture.totalHt }" /></td>
                <td><c:out value="${ pageScope.facture.tauxTva100 }" /></td>
                <td><c:out value="${ pageScope.facture.totalTtc }" /></td>
                <td><c:out value="${ pageScope.facture.statutFactureId }" /></td>
                <td><c:out value="${ pageScope.facture.modePaiementId }" /></td>
            </tr>
        </c:forEach>
    </table>

   <br>

   <h1>Ajouter un devis</h1>

    <%@ include file="../jspf/form.jspf" %>

</body>
</html>



