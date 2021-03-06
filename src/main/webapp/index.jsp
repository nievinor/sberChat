<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Application</title>
</head>
<body>

    <h2>Chat</h2>
    <p>
    <c:forEach var="msg" items="${messages}">
      <br><b><c:out value="${msg.login}" /></b></br>
      <br><c:out value="${msg.date}" /></br>
      <br><c:out value="${msg.message}" /></br>
    </c:forEach>

     <form action="chat" method="post">
          <p>Message [${cookie.login.value}]:<br>
          <textarea name="message" cols="40" rows="3"></textarea></p>
          <p><input type="submit" value="Send">
          <input type="reset" value="Clear"></p>
     </form>
     <form action="chat" method="POST">
        <p><input name="exit" type="submit" value="Exit" />
     </form>


</body>
</html>
