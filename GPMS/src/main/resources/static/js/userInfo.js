$(function() {
	$.ajax({
		type : "POST",
		url : "getInfo",
		dataType : "json",
		success : function(data) {
			$('#fm').form('load', data);
		}
	});
});

function save() {
	$('#fm').form('submit', {
		url : "updateInfo",
		onSubmit : function() {
			if ($("#password").val() != $("#cpassword").val()) {
				$("#msg").text("两次密码不一致，请重新输入");
				$("#password").val('');
				$("#cpassword").val('');
				$("#password").focus();
				return false;
			}
			return $(this).form('validate');
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.success) {
				$.messager.show({
					title : '信息',
					msg : '已成功，请重新登录！'
				});
				setTimeout(function() {
					window.parent.toUrl();
				}, 2000);
			} else {
				$.messager.show({
					title : '错误',
					msg : result.msg
				});
			}
		}
	});
}

function restore() {
	$.ajax({
		type : "POST",
		url : "getInfo",
		dataType : "json",
		success : function(data) {
			$('#fm').form('load', data);
			$("#cpassword").val('');
		}
	});
}
