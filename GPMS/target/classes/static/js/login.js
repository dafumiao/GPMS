$(function() {
	$("#subbtn").on("click", login);
	$("#loginform").on("submit", login);
});

function login() {
	if ($("#username").val() == "") {
		$("#msg").text("请输入用户名");
		$("#username").focus();
		return false;
	}
	if (!cheStr($("#username").val())) {
		$("#msg").text("用户名中有非法字符");
		$("#username").focus();
		return false;
	}
	if ($("#password").val() == "") {
		$("#msg").text("请输入密码");
		$("#password").focus();
		return false;
	}
	$.ajax({
		type : "POST",
		url : "admin/user/login.jsp",
		data : {
			username : $("#username").val(),
			password : $("#password").val()
		},
		dataType : "text",
		success : function(data) {
			if ($.trim(data) == "ok") {
				window.location.href = "index.html";
			} else {
				$("#msg").text("用户名或密码错误，请重新输入！");
				reset();
			}
		}
	});
	return false;
}

function reset() {
	$("#password").val();
}