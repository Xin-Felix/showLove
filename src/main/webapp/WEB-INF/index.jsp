<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta charset="UTF-8"/>
    <title>填写Ta的信息</title>
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
            integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
            crossorigin="anonymous"
    />
    <script
            src="https://cdn.jsdelivr.net/npm/jquery@3.4.1/dist/jquery.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"
    ></script>
    <script
            src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"
    ></script>
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"
    ></script>
    <script type="text/javascript" src="/static/jquery.min.js"></script>
</head>
<style>
    .center {
        margin: 0 auto;

        padding: 20px;
    }
</style>
<body>
<div class="center">


    <div class="form-group">
        <label>请输入你的姓名</label>
        <input type="text" id="meName" class="form-control"/>
    </div>
    <div class="form-group">
        <label>请输入Ta的姓名</label>
        <input type="text" id="otherName" class="form-control"/>
    </div>
    <div class="form-group">
        <label>你想对Ta说的话</label>
        <textarea class="form-control" id="content" rows="5"></textarea>
    </div>
    <div class="form-group">
        <label>你们美好的开始</label>
        <input type="date" id="time" class="form-control"/>
    </div>
    <button class="btn btn-primary btn-block" id="commit">提交</button>

    <label style="margin-top: 50px">已经生成的链接,点击查看(每天最多三条,24:00清零)</label>

    <c:forEach var="item" items="${message}">
        <hr>
        <a href="/${item.id}">亲爱的${item.otherName}</a>
    </c:forEach>
</div>
<script>
    $("#commit").click(function () {

        var list = {
            meName: $("#meName").val(),
            otherName: $("#otherName").val(),
            content: $("#content").val(),
            time: $("#time").val(),
        }

        if ($("#meName").val().length > 4 || $("#meName").val().length == 0) {
            alert("请输入正确的名字")
            return
        }

        if ($("#otherName").val().length > 4 || $("#otherName").val().length == 0) {
            alert("请输入正确的名字")
            return
        }

        if ($("#content").val().length > 400) {
            alert("这么多话,见面说吧")
            return
        }
        if ($("#content").val().length == 0) {
            alert("请输入要说的话噢");
            return
        }

        $.ajax({
            //请求方式
            type: "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url: "/add/addMessage",
            //数据，json字符串
            data: JSON.stringify(list),
            success: function (data) {
                if (data.data == 5000) {
                    alert("每人每天可以添加三次")
                    return
                }
                location.href = "/" + data
            }
        })
    })


</script>
</body>
</html>
