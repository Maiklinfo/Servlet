<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pet Management</title>
</head>
<body>
    <h1>Welcome to Pet Management</h1>
    <form action="createPet" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br>
        <label for="type">Type:</label>
        <input type="text" id="type" name="type" required><br>
        <label for="age">Age:</label>
        <input type="number" id="age" name="age" required><br>
        <input type="submit" value="Create Pet">
    </form>

    <hr>

    <h2>All Pets</h2>
    <ul>
        <%
            Map<Integer, com.example.Pet> allPets = (Map<Integer, com.example.Pet>) request.getAttribute("allPets");
            for (Map.Entry<Integer, com.example.Pet> entry : allPets.entrySet()) {
        %>
            <li><%= entry.getValue().getName() %> - <%= entry.getValue().getType() %> - <%= entry.getValue().getAge() %> years old</li>
        <%
            }
        %>
    </ul>

    <hr>

    <form action="deletePet" method="delete">
        <label for="deleteId">Enter ID to delete:</label>
        <input type="number" id="deleteId" name="id" required>
        <input type="submit" value="Delete Pet">
    </form>

    <hr>

    <form action="updatePet" method="put">
        <label for="updateId">Enter ID to update:</label>
        <input type="number" id="updateId" name="id" required><br>
        <label for="updateName">New Name:</label>
        <input type="text" id="updateName" name="name" required><br>
        <label for="updateType">New Type:</label>
        <input type="text" id="updateType" name="type" required><br>
        <label for="updateAge">New Age:</label>
        <input type="number" id="updateAge" name="age" required><br>
        <input type="submit" value="Update Pet">
    </form>
</body>
</html>

