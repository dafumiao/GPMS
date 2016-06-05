$(function() {
	$("#subbtn").on("click", login);
	$("#loginform").on("submit", login);
});

function login() {
	if ($("#number").val() == "") {
		$("#msg").text("请输入学号");
		$("#number").focus();
		return false;
	}
	if (!cheStr($("#number").val())) {
		$("#msg").text("用户名中有非法字符");
		$("#number").focus();
		return false;
	}
	if ($("#rolename").val() == "") {
		$("#msg").text("请输入角色");
		$("#rolename").focus();
		return false;
	}
	if ($("#password").val() == "") {
		$("#msg").text("请输入密码");
		$("#password").focus();
		return false;
	}
	$.ajax({
		type : "POST",
		url : "userLogin",
		data : {
			number : $("#number").val(),
			roleName : $("#roleName").val(),
			password : $("#password").val()
		},
		dataType : "text",
		success : function(data) {
			if (data == "ok") {
				$("#msg").text("成功登录！");
				window.location.href = "index.html";
			} else {
				$("#msg").text("学号或密码错误，请重新输入！");
			}
		}
	});
	return false;
}

function reset() {
	$("#password").val();
}