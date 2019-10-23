$(function(){

	var error_name = false;
	var error_password = false;
	var error_check_password = false;
	var error_email = false;
	var error_check = false;


	$('#user_name').blur(function() {
		check_user_name();
	});

	$('#pwd').blur(function() {
		check_pwd();
	});

	$('#cpwd').blur(function() {
		check_cpwd();
	});

	$('#email').blur(function() {
		check_email();
	});

	$('#allow').click(function() {
		if($(this).is(':checked'))
		{
			error_check = false;
			$(this).siblings('span').hide();
		}
		else
		{
			error_check = true;
			$(this).siblings('span').html('请勾选同意');
			$(this).siblings('span').show();
		}
	});


	function check_user_name(){
		var len = $('#user_name').val().length;
		if(len<5||len>20)
		{
			$('#user_name').next().html('请输入5-20个字符的用户名')
			$('#user_name').next().show();
			error_name = true;
		}
		else
		{
			$('#user_name').next().hide();
                    $.ajax({
                            type:"post",
                            url:'http://localhost:8080/user/checkUsername',
                            data:"username="+$('#user_name').val(),
                            success:function (ret) {
                                if(ret.code==false) {

                                    $('#user_name').next().html('用户名已存在')
                                    $('#user_name').next().show()
                                    $("#btn").attr('disabled',true);
                                }else {

                                    $('#user_name').next().html('用户名可用')
                                    $('#user_name').next().show();
                                    $("#btn").attr('disabled',false);
                                }
                            }
                        }
                    )
			error_name = false;
		}
	}
	function check_pwd(){
		var len = $('#pwd').val().length;
		if(len<5||len>10)
		{
			$('#pwd').next().html('密码最少5位，最长10位')
			$('#pwd').next().show();
            $("#btn").attr('disabled',true);
			error_password = true;
		}
		else
		{
			$('#pwd').next().hide();
            $("#btn").attr('disabled',false);
			error_password = false;
		}
	}


	function check_cpwd(){
		var pass = $('#pwd').val();
		var cpass = $('#cpwd').val();

		if(pass!=cpass)
		{
			$('#cpwd').next().html('两次输入的密码不一致')
			$('#cpwd').next().show();
            $("#btn").attr('disabled',true);
			error_check_password = true;
		}
		else {
			$('#cpwd').next().hide();
            $("#btn").attr('disabled',false);
			error_check_password = false;
		}
	}

	function check_email(){
		var re = /^[a-z0-9][\w\.\-]*@[a-z0-9\-]+(\.[a-z]{2,5}){1,2}$/;

		if(re.test($('#email').val()))
		{
			$('#email').next().hide();
            $.ajax({
                    type:"post",
                    url:'/user/checkEmail',
                    data:"email="+$('#email').val(),
                    success:function (ret) {
                        if(ret.code==false) {
                            $('#email').next().html('邮箱已注册')
                            $('#email').next().show()
                            $("#btn").attr('disabled',true);
                        }else {
                            $('#email').next().html('邮箱可用')
                            $('#email').next().show();
                            $("#btn").attr('disabled',false);
                        }
                    }
                }
            )
            //error_email = false;
		}
		else
		{
			$('#email').next().html('你输入的邮箱格式不正确')
			$('#email').next().show();
            $("#btn").attr('disabled',true);
			error_check_password = true;
		}
	};
	$('#reg_form').submit(function() {
		check_user_name();
		check_pwd();
		check_cpwd();
		check_email();
		if(error_name == false && error_password == false && error_check_password == false && error_email == false && error_check == false)
		{
		    $.ajax({
                async:true,
                type:"post",
                url:"/user/register",
                data:$('#reg_form').serialize(),
                success:function (ret){
                    if(ret.status=="ok"){
                     alert("注册成功，是否立即前去登录？3秒后自动登录....")
                     location.href="/user/login"
                    }
                }
            })
			return false;
		}
		else
		{
			return false;
		}
	});
})