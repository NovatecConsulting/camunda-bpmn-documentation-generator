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
<p>${bpmn.documentation}</p>
<p>${bpmn.image}</p>

<table>
    <thead>
    <th>ID</th>
    <th>Name</th>
    <th>Version</th>
    <th>Documentation</th>
    </thead>
    <#list bpmn.elements as item>
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.version}</td>
            <td>${item.documentation}</td>
        </tr>
    </#list>
</table>
</body>
</html>