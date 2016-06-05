function toUrl() {
	window.location.href = '../../login.html';
}

$(function() {
	$('#contactsList').datagrid({
		url : 'contactsList',
		rownumbers : true,
		singleSelect : true,
		toolbar : '#toolbar',
		pagination : true,
		remoteSort : false,
		fitColumns : true
	});
	$('#contactsList').datagrid('load', {});
});

$(function() {
	$('#messagesList').datagrid({
		url : 'messagesList',
		rownumbers : true,
		singleSelect : true,
		toolbar : '#mtoolbar',
		pagination : true,
		remoteSort : false,
		fitColumns : true
	});
	$('#messagesList').datagrid('load', {});
});

$(function() {
	$.ajax({
		type : "Get",
		url : "userName",
		dataType : "text",
		success : function(data) {
			if ($.trim(data) == "error") {
				alert('当前用户已过期，请重新登录!');
				window.location.href = 'login.html';
			} else {
				$("#userName").text($.trim(data));
			}
		}
	});

	$('#tree').tree({
		url : 'getTree',
		onClick : function(node) {
			if (node.attributes.url != "") {
				addTab(node.text, node.attributes.url);
			}
			return;
		}
	});

	$("#exit").on("click", function() {
		$.ajax({
			type : "Get",
			url : "exit",
			dataType : "text",
			success : function(data) {
				window.location.href = '../login.html';
			}
		});
	});

	$('#center').tabs({
		plain : true
	})

});

function addTab(title, url) {
	if ($('#center').tabs('exists', title)) {
		$('#center').tabs('select', title);
	} else {
		var content = '<iframe scrolling="auto" frameborder="0"  src="' + url
				+ '" style="width:100%;height:100%;"></iframe>';
		$('#center').tabs('add', {
			title : title,
			content : content,
			closable : true
		});
	}
}

function sendMessage() {
	$('#messageDlg').dialog('open').dialog('setTitle', '发送消息');
	$('regFm').form('clear');
}

function saveMessage() {
	$.ajax({
		type : "POST",
		url : "sendMessage",
		data : {
			content : $("#content").val(),
			receiverid : $('#contactsList').datagrid('getSelected').contactid
		},
		dataType : "text",
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.success) {
				$('#messageDlg').dialog('close');
				$.messager.show({
					title : '消息',
					msg : '成功发送消息！'
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

function deleteMessage() {
	var row = $('#messagesList').datagrid('getSelected');
	if (row) {
		$.messager.confirm('确认删除', '您确认要删除吗?', function(r) {
			if (r) {
				$.post('deleteMessage', {
					id : row.id
				}, function(result) {
					$('#messagesList').datagrid('reload');
					$.messager.show({
						title : '消息',
						msg : '成功删除消息！'
					});
				}, 'json');
			}
		});

	}

}