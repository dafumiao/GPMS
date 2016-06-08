$(function() {
	$('#fileList').datagrid({
		url : 'fileList',
		rownumbers : true,
		singleSelect : true,
		toolbar : '#toolbar',
		pagination : true,
		remoteSort : false,
		fitColumns : true
	});
	$('#fileList').datagrid('load', {});
});

var flag;

function fileUpload() {
	$('#dlg').dialog('open').dialog('setTitle', '上传文件');
}
function fileDownload() {
	var row = $('#fileList').datagrid('getSelected');
	if (row) {
		url = 'fileDownload?id=' + row.id;
	}
	window.location.href = url;
}