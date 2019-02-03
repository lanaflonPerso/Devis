<html>

<head>
    <title>Devis</title>
    <link href="<c:url value="/ressources/CSS/display.css"/>" rel="stylesheet" type="text/css">
</head>

<body id="pageDevisBody">

   <br>

    <!-- Liste Table Devis -->
   <c:url var="URL_DELETE_MULTIPLE_DEVIS" value="/delete"/>

   <form method="post" action="${pageScope.URL_DELETE_MULTIPLE_DEVIS}">

   <table class="table table-responsive table-striped table-dark">
        <tr>
            <th scope="col" class="bg-primary">#</th>
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
            <th scope="col"><button class="btn btn-primary" type="submit">Delete</button></th>
            <th scope="col">Single delete</th>
        </tr>
        <c:forEach var="devis" items="${ requestScope.listeDevis }" varStatus="status">
            <tr>
                <td class="bg-primary"><c:out value="${ pageScope.status.count }" /></td>
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
                <td>
                    <div class="form-check">
                        <input class="form-check-input position-static" type="checkbox" name="deleteIdDevis" id="blankCheckbox" value="${ pageScope.devis.idDevis }" aria-label="...">
                    </div>
                </td>
                <c:url var="URL_DELETE_SINGLE_DEVIS" value="/delete"><c:param name="nameBean" value="devis"/><c:param name="idBean" value="${ pageScope.devis.idDevis }"/></c:url>
                <td><a class="btn btn-primary" href="${URL_DELETE_SINGLE_DEVIS}" role="button">Delete</a></td>
            </tr>
        </c:forEach>
    </table>

   </form>

   <br>

    <!-- Liste Table Facture -->
    <table class="table table-striped table-dark">
        <tr>
            <th scope="col" class="bg-primary">#</th>
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
        <c:forEach var="facture" items="${ requestScope.listeFactures }" varStatus="status">
            <tr>
                <td class="bg-primary"><c:out value="${ pageScope.status.count }" /></td>
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



