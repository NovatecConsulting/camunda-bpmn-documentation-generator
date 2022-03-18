<html>
<style>
    table, th, td {
        border: 1px solid black;
    }
</style>
<head>
    <title>${bpmn.name}</title>
</head>
<body>
<h1>${bpmn.name}</h1>
<p>ID: ${bpmn.id}</p>
<p>Documentation: ${bpmn.documentation}</p>
<p>Version: ${bpmn.version}</p>
<p>${bpmn.image}</p>
<img src="../../${bpmn.image}">

<li>CallActivities:</li>
<table>
    <thead>
    <th>ID</th>
    <th>Name</th>
    <th>Called Element</th>
    <th>Documentation</th>
    </thead>
    <#list bpmn.callActivityList as item>
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.calledElement}</td>
            <td>${item.documentation}</td>
        </tr>
    </#list>
</table>
</body>
</html>