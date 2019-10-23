<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>天天生鲜-登录</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
</head>
<body>
<div class="login_top clearfix">
    <a href="${pageContext.request.contextPath}/user/index" class="login_logo"><img src="${pageContext.request.contextPath}/images/logo02.png"></a>
</div>

<div class="login_form_bg">
    <div class="login_form_wrap clearfix">
        <div class="login_banner fl"></div>
        <div class="slogan fl">日夜兼程 · 急速送达</div>
        <div class="login_form fr">
            <div class="login_title clearfix">
                <h1>用户登录</h1>
                <a href="${pageContext.request.contextPath}/user/register">立即注册</a>
            </div>
            <div class="form_input">
                <form method="post" id="form1">
                    <input type="text" name="username" class="name_input" placeholder="请输入用户名" id="username">
                    <div class="user_error" id="user"></div>
                    <input type="password" name="password" class="pass_input" placeholder="请输入密码">
                    <div class="pwd_error" id="pwd"></div>
                    <div style="position: absolute;left: 0;top: 120px;">
                        <img src="${pageContext.request.contextPath}/user/capthca" height="25px" width="60px" id="cap" onclick="refreshCode(this)" style="vertical-align: middle;"> <input type="text" style="background: #fdfdfd;height: 25px;width: 100px;vertical-align: middle; " id="cap1" name="cap">
                        <img src="" style="height: 25px;width: auto;vertical-align: middle;" id="img">
                    </div>
                    <div class="more_input clearfix">
                        <input type="checkbox" name="auto">
                        <label>记住用户名</label>
                        <a href="#">忘记密码</a>
                    </div>
                    <input type="submit" name="" value="登录" class="input_submit">
                </form>
            </div>
        </div>
    </div>
</div>

<div class="footer no-mp">
    <div class="foot_link">
        <a href="#">关于我们</a>
        <span>|</span>
        <a href="#">联系我们</a>
        <span>|</span>
        <a href="#">招聘人才</a>
        <span>|</span>
        <a href="#">友情链接</a>
    </div>
    <p>CopyRight © 2016 北京天天生鲜信息技术有限公司 All Rights Reserved</p>
    <p>电话：010-****888    京ICP备*******8号</p>
</div>
<script type="text/javascript">
function refreshCode(a){
    //刷新验证码
    a.src="${pageContext.request.contextPath}/user/capthca?"+new Date().getTime();
}
$('#username').change(function () {
    $.ajax({
        type:"post",
        url:"/user/checkUsername",
        data:"username="+$('#username').val(),
        success:function (ret) {
            if(ret.code==true){
                $('#username').next().html('用户名可用')
                $('#username').next().show();
            }else {
                $('#username').next().html('用户名不存在')
                $('#username').next().show();
            }
        }
    })
})
    $('#form1').submit(function () {
        $.ajax({
            async:false,
            type:"post",
            url:"/user/logic",
            data:$('#form1').serialize(),
            success:function (ret){
                if(ret.code==true){
                    confirm("登录成功，是否前往主页？")
                    location.href="${pageContext.request.contextPath}/user/index"
                }
            }
        })
        return false;
    })
    $('#cap1').change(function () {
        $.ajax({
            type:"post",
            url:"/user/checkCode",
            data:"cap="+$('#cap1').val(),
            success:function (ret) {
                if(ret.code==true){
                    $('#img').attr("disabled",false)
                    $('#img').attr("src","${pageContext.request.contextPath}/images/correct.jpg")
                }else {
                    $('#img').attr("src","${pageContext.request.contextPath}/images/error.jpg")
                    $('#cap').attr("src","${pageContext.request.contextPath}/user/capthca?"+new Date().getTime())
                }
            }
        })
    })
</script>

</body>
</html>
