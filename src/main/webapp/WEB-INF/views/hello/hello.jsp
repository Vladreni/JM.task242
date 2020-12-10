<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>

<form th:action="@{/admin}" th:method="get">
    <input  class="button button-block" type="submit" value="Go to table!"/>
</form>
<div>
    <th:block th:each="msg : ${messages}">
        <h1 th:text="${msg}"></h1>
    </th:block>
</div>
</body>
</html>