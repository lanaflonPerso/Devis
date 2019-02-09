<!DOCTYPE html>
<html lang="fr">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>${application.name} - A propos</title>
    <link rel="stylesheet" href="../style/custom.css" />
</head>


<body>

<div class="container">
    <ul>
        <li>Application : ${application.name}</li>
        <li>Version : ${project.version}</li>
        <li>Date du build : ${maven.build.timestamp}</li>
        <li><a href="${organization.url}">${organization.name}</a></li>
    </ul>
</div>

</body>
</html>