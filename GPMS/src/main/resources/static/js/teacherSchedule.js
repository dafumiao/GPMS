function getStudentCalendar() {
	row = $('#contactsList').datagrid('getSelected');
	studentid = row.studentid;
	if (row) {
		window.location.href = "teacherStudentSchedule.html?studentid="
				+ studentid;
	}
}

$(function() {
	var studentid;
	var row;
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
	$('#calendar').fullCalendar({
		// 设置选项和回调
		eventClick : function(calEvent, jsEvent, view) {
			$.messager.confirm('处理学生请求', '您同意学生的申请吗?', function(r) {
				if (r) {
					$.post('agreeEvent', {
						id : calEvent.id
					}, function(result) {
						$('#calendar').fullCalendar('refetchEvents');
						$.messager.show({
							title : '消息',
							msg : '学生申请通过！'
						});
					}, 'json');
				}
			});
		},
		header : {// 设置日历头部信息
			left : 'prev,next today',
			center : 'title',
			right : 'month,agendaWeek,agendaDay'
		},
		firstDay : 1,// 每行第一天为周一
		// editable : true,// 可以拖动
		events : {
			url : 'getStudentCalendar' + location.search, // location.search,获取URL中?及其后面的部分
			type : 'GET',
			error : function() {
				alert('there was an error while fetching events!');
			},
		}
	})
});
