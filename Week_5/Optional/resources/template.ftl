<html>
<head>

</head>
<body>
  <h1>${title}</h1>

  <ul>
    <#list items as item>
      <li>${item_index + 1}. ${item.describe()}</li>
    </#list>
  </ul>

</body>
</html>