<html>
<head>
    <title>Movies</title>
    <link rel="stylesheet" href="style.css">
    <script src="./script.js"></script>
</head>

<body>
  <h1>Ranking</h1>
  <h4>${date}</h4>
  <table>
    <thead>
        <tr>
          <th>Title</th>
          <th> </th>
          <th>Score</th>
        </tr>
    </thead>
    <tbody id="_body">
        <#list models as model>
        <tr>
          <td>${model.title}<td>
          <td>${model.score}<td>
        </tr>
        </#list>
    </tbody>
  </table>

  <div class="statistics">
    <canvas background-color:"white"  width="1000" height="500" id="myCanvas"></canvas>
  </div>
</body>
</html>