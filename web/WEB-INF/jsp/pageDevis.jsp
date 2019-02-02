<html>

<head>
    <title>Devis</title>

    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.bundle.min.js" integrity="sha384-zDnhMsjVZfS3hiP7oCBRmfjkQC4fzxVxFhBx8Hkz2aZX8gEvA/jsP3eXRCvzTofP" crossorigin="anonymous"></script>

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

   <br>

   <h1>Ajouter un devis</h1>

    <%@ include file="../jspf/form.jspf" %>

</body>
</html>



