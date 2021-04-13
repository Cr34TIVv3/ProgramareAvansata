<html>
<head>
    <title>Movies</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
  <table class="movie">
    <thead>
        <tr>
              <th>Title</th>
              <th>Release Date</th>
              <th>Duration</th>
              <th>Score</th>
         </tr>
    </thead>
    <tbody>
        <#list movies as movie>
        <tr>
          <td>${movie.title}<td>
          <td>${movie.releaseDate}<td>
          <td>${movie.duration}<td>
          <td>${movie.score}<td>
        </tr>
        </#list>
    </tbody>
  </table>
</body>
</html>