<html>

<head>
    <title>Devis</title>
    <link href="${pageContext.request.contextPath}/ressources/CSS/pageDevis.css" rel="stylesheet" type="text/css">
</head>

<body id="pageDevisBody">

   <br>

    <!-- Liste Table Devis -->
    <table class="table table-striped table-dark">
        <tr>
            <th scope="col">idDevis</th>
            <th scope="col">numDevis</th>
            <th scope="col">dateDevis</th>
            <th scope="col">dateFinValidite</th>
            <th scope="col">commentaire</th>
            <th scope="col">clientInterlocuteurId</th>
            <th scope="col">typeLivraisonId</th>
            <th scope="col">entrepriseContactId</th>
            <th scope="col">entrepriseId</th>
            <th scope="col">factureId</th>
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
    <table class="table table-striped table-dark">
        <tr>
            <th scope="col">idFacture</th>
            <th scope="col">dateFacturation</th>
            <th scope="col">delaiPaiementId</th>
            <th scope="col">datePaiement</th>
            <th scope="col">totalHt</th>
            <th scope="col">tauxTva100</th>
            <th scope="col">totalTtc</th>
            <th scope="col">statutFactureId</th>
            <th scope="col">modePaiementId</th>
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



