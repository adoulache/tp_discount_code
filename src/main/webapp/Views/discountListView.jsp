<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!-- On definit la configuration d'acces a  la base -->
<sql:setDataSource 
	driver="org.apache.derby.jdbc.ClientDriver"
	url="jdbc:derby://localhost:1527/sample"
	user="app" password="app"
/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Saisie d'un taux de remise</title>
    </head>
    <body>
        <h1>Edition des taux de remise</h1>
        <form method='GET'>
            Code : <input name="code" size="1" maxlength="1" pattern="[A-Z]{1}+" title="Une lettre en MAJUSCULES"><br/>
            Taux : <input name="taux" type="number" step="0.01" min="0.0" max="99.99" size="5"><br/>
                <input type="hidden" name="action" value="ADD">
                <input type="submit" value="Ajouter">
        </form>
        
        <div><h4>${error}</h4></div>
    
        <div>
            <table border="1">
                <tr><th>Code</th><th>Taux</th><th>Action</th></tr>
                <c:forEach var="discount" items="${discountList}">
                    <tr>
                            <td>${discount.code}</td>
                            <td>${discount.taux}</td>
                            <td><a href="?action=DELETE&code=${discount.taux}">delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
