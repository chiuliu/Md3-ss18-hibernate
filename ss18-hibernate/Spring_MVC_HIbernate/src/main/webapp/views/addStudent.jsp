<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h1>INSERT STUDENT</h1>
        <h3 class="text-danger">${err}</h3>
        <form:form action="insertStudent" modelAttribute="s" method="post">
            <div class="mb-3">
                <label for="stuName" class="form-label">Student Name</label>
                <form:input path="stuName" class="form-control" id="stuName"/>
            </div>
            <div class="form-check form-check-inline">
                <form:radiobutton class="form-check-input" path="gender" id="male" value="true"/>
                <label class="form-check-label" for="male">Male</label>
            </div>
            <div class="form-check form-check-inline">
                <form:radiobutton class="form-check-input" path="gender" id="female" value="false"/>
                <label class="form-check-label" for="female">Female</label>
            </div>
            <div class="mb-3">
                <label for="birthday" class="form-label">Birthday</label>
                <form:input path="birthday" class="form-control" id="birthday" type="date"/>
            </div>
            <div class="mb-3">
                <label for="address" class="form-label">Address</label>
                <form:textarea path="address" class="form-control" id="address"/>
            </div>
            <div class="mb-3">
                <label for="className" class="form-label">Class Name</label>
                <form:select path="className" class="form-control" id="className">
                    <form:option value="JV221112">JV221112</form:option>
                    <form:option value="JV221113">JV221113</form:option>
                    <form:option value="JV221114">JV221114</form:option>
                    <form:option value="JV221115">JV221115</form:option>
                </form:select>
            </div>
            <button type="submit" class="btn btn-primary">Insert</button>
            <button type="reset" class="btn btn-secondary">Clear</button>
        </form:form>
    </div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>