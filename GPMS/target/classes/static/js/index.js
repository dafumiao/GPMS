function toUrl() {
	window.location.href = '../../login.html';
}

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
				window.location.href = 'login.html';
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