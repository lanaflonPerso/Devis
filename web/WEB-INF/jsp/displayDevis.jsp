<html>

<head>
    <title>Devis</title>
    <link href="<c:url value="/ressources/CSS/display.css"/>" rel="stylesheet" type="text/css">
</head>

<body id="pageDevisBody">

   <br>

   <p class="h2"> Liste Table Devis  </p>

   <!-- TODO : est-il mieux de passer le param de la requête via un hidden input ? -->
   <c:url var="URL_DELETE_MULTIPLE_DEVIS" value="/delete"><c:param name="nameBean" value="devis"/></c:url>>

   <form method="post" action="${pageScope.URL_DELETE_MULTIPLE_DEVIS}">

   <table class="table table-responsive table-striped table-dark table-hover">
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
                <!-- TODO : est-il mieux de passer les params de la requête via des hidden inputs ? -->
                <c:url var="URL_DELETE_SINGLE_DEVIS" value="/delete"><c:param name="nameBean" value="devis"/><c:param name="idBean" value="${ pageScope.devis.idDevis }"/></c:url>
                <td><a class="btn btn-primary" href="${URL_DELETE_SINGLE_DEVIS}" role="button">Delete</a></td>
            </tr>
        </c:forEach>
    </table>

   </form>

   <br>

   <p class="h1"> Ajouter un devis </p>

   <%@ include file="../jspf/formDevis.jspf" %>

   <br><br>

   <p class="h2"> Liste Table Facture  </p>

    <table class="table table-striped table-dark table-hover">
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

   <p class="h2"> Liste Table TypeLivraison </p>

   <table class="table table-striped table-dark table-hover">
       <tr>
           <th scope="col" class="bg-primary">#</th>
           <th scope="col">idTypeLivraison</th>
           <th scope="col">libelle</th>
       </tr>
       <c:forEach var="typeLivraison" items="${ requestScope.listeTypesLivraison }" varStatus="status">
           <tr>
               <td class="bg-primary"><c:out value="${ pageScope.status.count }" /></td>
               <td><c:out value="${ pageScope.typeLivraison.idTypeLivraison }" /></td>
               <td><c:out value="${ pageScope.typeLivraison.libelle }" /></td>
           </tr>
       </c:forEach>
   </table>

   <br>

   <p class="h2"> Liste Table Entreprise </p>

   <table class="table table-striped table-dark table-hover">
       <tr>
           <th scope="col" class="bg-primary">#</th>
           <th scope="col">idEntreprise</th>
           <th scope="col">siret</th>
           <th scope="col">raisonSociale</th>
           <th scope="col">numTva</th>
           <th scope="col">tel</th>
           <th scope="col">fax</th>
           <th scope="col">adresseEntrepriseId</th>
       </tr>
       <c:forEach var="entreprise" items="${ requestScope.listeEntreprises }" varStatus="status">
           <tr>
               <td class="bg-primary"><c:out value="${ pageScope.status.count }" /></td>
               <td><c:out value="${ pageScope.entreprise.idEntreprise }" /></td>
               <td><c:out value="${ pageScope.entreprise.siret }" /></td>
               <td><c:out value="${ pageScope.entreprise.raisonSociale }" /></td>
               <td><c:out value="${ pageScope.entreprise.numTva }" /></td>
               <td><c:out value="${ pageScope.entreprise.tel }" /></td>
               <td><c:out value="${ pageScope.entreprise.fax }" /></td>
               <td><c:out value="${ pageScope.entreprise.adresseEntrepriseId }" /></td>
           </tr>
       </c:forEach>
   </table>

   <br>

   <p class="h2"> Liste Table EntrepriseContact </p>

   <table class="table table-striped table-dark table-hover">
       <tr>
           <th scope="col" class="bg-primary">#</th>
           <th scope="col">idEntrepriseContact</th>
           <th scope="col">entrepriseId</th>
           <th scope="col">civilite</th>
           <th scope="col">nom</th>
           <th scope="col">prenom</th>
           <th scope="col">tel</th>
           <th scope="col">fax</th>
           <th scope="col">email</th>
       </tr>
       <c:forEach var="entrepriseContact" items="${ requestScope.listeEntrepriseContacts }" varStatus="status">
           <tr>
               <td class="bg-primary"><c:out value="${ pageScope.status.count }" /></td>
               <td><c:out value="${ pageScope.entrepriseContact.idEntrepriseContact }" /></td>
               <td><c:out value="${ pageScope.entrepriseContact.entrepriseId }" /></td>
               <td><c:out value="${ pageScope.entrepriseContact.civilite }" /></td>
               <td><c:out value="${ pageScope.entrepriseContact.nom }" /></td>
               <td><c:out value="${ pageScope.entrepriseContact.prenom }" /></td>
               <td><c:out value="${ pageScope.entrepriseContact.tel }" /></td>
               <td><c:out value="${ pageScope.entrepriseContact.fax }" /></td>
               <td><c:out value="${ pageScope.entrepriseContact.email }" /></td>
           </tr>
       </c:forEach>
   </table>

   <br>

   <p class="h2"> Liste Table ClientInterlocuteur </p>

   <table class="table table-striped table-dark table-hover">
       <tr>
           <th scope="col" class="bg-primary">#</th>
           <th scope="col">idClientInterlocuteur</th>
           <th scope="col">civilite</th>
           <th scope="col">nom</th>
           <th scope="col">prenom</th>
           <th scope="col">tel</th>
           <th scope="col">email</th>
           <th scope="col">clientId</th>
           <th scope="col">adresseLivraisonId</th>
       </tr>
       <c:forEach var="clientInterlocuteur" items="${ requestScope.listeClientInterlocuteurs }" varStatus="status">
           <tr>
               <td class="bg-primary"><c:out value="${ pageScope.status.count }" /></td>
               <td><c:out value="${ pageScope.clientInterlocuteur.idClientInterlocuteur }" /></td>
               <td><c:out value="${ pageScope.clientInterlocuteur.civilite }" /></td>
               <td><c:out value="${ pageScope.clientInterlocuteur.nom }" /></td>
               <td><c:out value="${ pageScope.clientInterlocuteur.prenom }" /></td>
               <td><c:out value="${ pageScope.clientInterlocuteur.tel }" /></td>
               <td><c:out value="${ pageScope.clientInterlocuteur.email }" /></td>
               <td><c:out value="${ pageScope.clientInterlocuteur.clientId }" /></td>
               <td><c:out value="${ pageScope.clientInterlocuteur.adresseLivraisonId }" /></td>
           </tr>
       </c:forEach>
   </table>

   <br>

</body>
</html>



