<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://thymeleaf.org">
<head>
    <title>Title</title>
</head>
<body>
<p th:utext="${userOne.name}">user</p>
<p th:utext="${userOne.password}">password</p>
<p th:utext="${userOne.id}">id</p>
<p th:each="role : ${userOne.getRoles()}" th:utext="${role.getRole()}">Roles</p>
<button>
<a th:href="@{/admin/{id}/edit(id=${userOne.id})}">Edit</a>
</button>
<br/>
<form th:action="@{/admin/{id}(id=${userOne.id})}" th:method="delete">
    <input  class="button button-block" type="submit" value="Delete!"/>
</form>
<!-- <a href="/logout">Logout</a> -->
</body>
</html>
