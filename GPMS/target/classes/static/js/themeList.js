$(function() {
	$('#themeList').datagrid({
		url : 'themeList',
		rownumbers : true,
		singleSelect : true,
		toolbar : '#toolbar',
		pagination : true,
		remoteSort : false,
		fitColumns : true
	});
	$('#themeList').datagrid('load', {});
});

var flag;

function myThemes() {
	$('#teacherThemeDlg').dialog('open').dialog('setTitle', '上传的题目');
	$(function() {
		$('#teacherThemeList').datagrid({
			url : 'teacherThemeList',
			singleSelect : true,
			toolbar : '#teacherTheme',
			remoteSort : false,
			fitColumns : true
		});
		$('#teacherThemeList').datagrid('load', {});
	});
}

function students() {
	$('#studentsDlg').dialog('open').dialog('setTitle', '学生申请');
	$(function() {
		$('#studentsList').datagrid({
			url : 'studentsList',
			singleSelect : true,
			remoteSort : false,
			fitColumns : true
		});
		$('#studentsList').datagrid('load', {});
	});
}

function agreeStudent() {
	$.ajax({
		type : "POST",
		url : "agreeStudent",
		data : {
			id : $('#studentsList').datagrid('getSelected').id
		},
		dataType : "text",
		success : function(data) {
			if (data == "ok") {
				$.messager.show({
					title : '信息',
					msg : '学生成功选课！'
				});
				$('#themeList').datagrid('reload');
				$('#studentsList').datagrid('reload');
			} else {
				$.messager.show({
					title : '信息',
					msg : '学生已选课题！'
				});
			}
		}
	});
}

function themeInfo() {
	$('#themeInfoDlg').dialog("open");
	var id = $('#themeList').datagrid('getSelected').id;
	$.ajax({
		type : "POST",
		url : "getThemeInfo",
		data : {
			id : id
		},
		dataType : "json",
		success : function(data) {
			$('#fm').form('load', data);
		}
	});
}

function themeUpload() {
	$('#themeUploadDlg').dialog('open').dialog('setTitle', '上传题目');
	$('regFm').form('clear');
	url = "themeUpload";
}

function saveTheme() {
	$('#regFm').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.success) {
				$('#themeList').datagrid('reload');
				$('#themeUploadDlg').dialog('close');
				$.messager.show({
					title : '消息',
					msg : '成功上传题目！'
				});
			} else {
				$.messager.show({
					title : '错误',
					msg : result.msg
				});
			}
		}
	});
}

function themeDelete() {
	var row = $('#teacherThemeList').datagrid('getSelected');
	if (row) {
		$.messager.confirm('确认删除', '您确认要删除吗?', function(r) {
			if (r) {
				$.post('themeDelete', {
					id : row.id
				}, function(result) {
					$('#themeList').datagrid('reload');
					$('#teacherThemeDlg').dialog("close");
					$.messager.show({
						title : '信息',
						msg : '题目已删除！'
					});
				}, 'json');
			}
		});
	}
}

function themeChoose() {
	$.ajax({
		type : "GET",
		url : "themeChoose",
		data : {
			id : $('#themeList').datagrid('getSelected').id
		},
		dataType : "text",
		success : function(data) {
			if (data == "ok") {
				$.messager.show({
					title : '信息',
					msg : '申请选题已发送！'
				});
				$('#themeList').datagrid('reload');
			} else {
				$.messager.show({
					title : '信息',
					msg : '您已选题，请不要重复选择！'
				});
			}
		}
	});
}