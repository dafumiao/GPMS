$(document).ready(function() {
	// 页面加载完初始化日历
	$('#calendar').fullCalendar({
		// 设置选项和回调
		dayClick : function(date, allDay, jsEvent, view) {
			$('#dlg').dialog('open').dialog('setTitle', '添加');
			$(function() {
				$.ajax({
					type : "POST",
					data : {
						selDate : $.fullCalendar.formatDate(date, 'yyyy-MM-dd')
					},
					url : "getEventDate",
					dataType : "json",
					success : function(data) {
						$('#fm').form('load', data);
					}
				});
			});
		},
		eventClick : function(calEvent, jsEvent, view) {
			$.messager.confirm('确认删除', '您确认要删除吗?', function(r) {
				if (r) {
					$.post('deleteEvent', {
						id : calEvent.id
					}, function(result) {
						$('#calendar').fullCalendar('refetchEvents');
						$.messager.show({
							title : '消息',
							msg : '成功删除日程！'
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
			url : 'getCalendar', // 你的controller的地址
			type : 'POST',
			error : function() {
				alert('there was an error while fetching events!');
			},
		}
	})
});

function saveEvent() {
	$('#fm').form('submit', {
		url : "newEvent",
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.success) {
				$.messager.show({
					title : '信息',
					msg : '已成功设置日程！'
				});
				$('#calendar').fullCalendar('refetchEvents');
				$('#dlg').dialog('close');
			} else {
				$.messager.show({
					title : '错误',
					msg : '您于今日已有申请！'
				});
			}
		}
	});
}
