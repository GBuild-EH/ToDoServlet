<%--
  Created by IntelliJ IDEA.
  User: Rabid
  Date: 3/16/2025
  Time: 8:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<form action="update" method="post">

  <fieldset>
    <label>Task #</label> <input type="text" value="<c:out value='${task.id}'/>" name="id">
  </fieldset>

  <fieldset>
    <label>Task Description</label> <input type="text" value="<c:out value='${task.description}'/>" name="description">
  </fieldset>

  <fieldset>
    <label>Due Date</label> <input type="date" value="<c:out value='${task.dueDate}'/>" name="dueDate">
  </fieldset>

  <fieldset>
    <label>Start Date</label> <input type="date" value="<c:out value='${task.startDate}'/>" name="startDate">
  </fieldset>

  <fieldset>
    <label>Task Status</label> <select name="done">
      <option value="false">In Progress</option>
      <option value="true">Complete</option>
    </select>
  </fieldset>
  <button type="submit">Save Task</button>
</form>
</body>
</html>
