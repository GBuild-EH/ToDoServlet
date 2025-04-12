<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - To Do List</title>
</head>
<body>
<header>
    <nav class="navbar" style="background-color: cadetblue">
        <ul>
            <li><a href="<%request.getContextPath();%>">Refresh List</a></li>
        </ul>
    </nav>
</header>
<h1 style="align-self: center">
    To Do List
</h1>
<div>
    <a href="<%=request.getContextPath()%>/new">New Task</a>
</div>
<br>
<table>
    <thead>
        <tr>
            <th>#</th>
            <th>Task</th>
            <th>Due</th>
            <th>Started</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="task" items="${taskList}">
            <tr>
                <td><c:out value="${task.id}"/></td>
                <td><c:out value="${task.description}"/></td>
                <td><c:out value="${task.dueDate}"/></td>
                <td><c:out value="${task.startDate}"/></td>
                <td><c:out value="${task.done}"/></td>

                <td><a href="edit?id=<c:out value='${task.id}' />">Edit</a>
                    &nbsp&nbsp&nbsp&nbsp
                    <a href="delete?id=<c:out value='${task.id}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<br/>
</body>
</html>