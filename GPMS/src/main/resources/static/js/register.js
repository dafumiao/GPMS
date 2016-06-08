function newUser() {
	$('#dlg').dialog('open').dialog('setTitle', '添加');
	$('regFm').form('clear');
	url = "newUser";
	flag = "add";
}

function saveUser() {
	$('#regFm').form('submit', {
		url : url,
		onSubmit : function() {
			alert("@@@");
			if (flag == "add") {
				if (!cheStr($("#regNumber").val())) {
					$("#regMsg").text("学号/职工号中有非法字符");
					$("#regNumber").focus();
					return false;
				}
				if (!cheStr($("#regName").val())) {
					$("#regMsg").text("姓名中有非法字符");
					$("#regName").focus();
					return false;
				}
				if (!cheStr($("#regName").val())) {
					$("#regMsg").text("姓名中有非法字符");
					$("#regName").focus();
					return false;
				}
				if ($("#regPassword").val() != $("#regcPassword").val()) {
					$("#regMsg").text("两次密码不一致，请重新输入");
					$("#regPassword").val('');
					$("#regcPassword").val('');
					$("#regPassword").focus();
					return false;
				}
			}
			return $(this).form('validate');
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.success) {
				$('#dlg').dialog('close');
				window.parent.toUrl();
			} else {
				$.messager.show({
					title : '错误',
					msg : result.msg
				});
			}
		}
	});
}