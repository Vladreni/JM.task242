<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://thymeleaf.org">
<head>
    <title>Update user</title>
</head>
<body>
<form th:action="@{/users/{id}(id=${user.id}) }" th:method="PATCH" th:object="${user}">
    <label for="name">Enter name: </label>
    <input type="text" th:field="*{name}" id="name"/>
    <br/>
    <label for="age">Enter age: </label>
    <input type="text" th:field="*{age}" id="age"/>
    <br/>
    <input type="submit" value="Update!"/>
</form>
</body>
</html>
