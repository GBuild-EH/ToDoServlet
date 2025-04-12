<%--
  Created by IntelliJ IDEA.
  User: Rabid
  Date: 3/16/2025
  Time: 2:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>To Do List Application</title>
</head>
<body>
    <header>
        <nav class="navbar" style="background-color: cadetblue">
            <ul>
                <li><a href="<%request.getContextPath();%>/ToDoServlet">Return to List</a></li>
            </ul>
        </nav>
    </header>

    <form action="add" method="post">
        <fieldset>
            <label>Task Description</label> <input type="text" name="description">
        </fieldset>

        <fieldset>
            <label>Due Date</label> <input type="date" name="dueDate">
        </fieldset>

        <fieldset>
            <label>Start Date</label> <input type="date" name="startDate">
        </fieldset>
        <button type="submit">Save Task</button>
    </form>
</body>
</html>
