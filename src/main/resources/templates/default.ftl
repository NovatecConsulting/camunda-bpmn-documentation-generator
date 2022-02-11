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
<p>Version: ${bpmn.version}</p>
<p>Documentation: ${bpmn.documentation}</p>
<div><#if bpmn.image??><img src="./images/${bpmn.image}"></#if></div>
<table>
    <thead>
    <th>ID</th>
    <th>Name</th>
    <th>Version</th>
    <th>Documentation</th>
    <th>Type</th>
    </thead>
    <#list bpmn.elements as item>
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.version}</td>
            <td>${item.documentation}</td>
            <td>${item.type}</td>
        </tr>
    </#list>
</table>
<p>Call Activities:</p>
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