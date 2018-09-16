$(document).ready(function(){
	$("#addrowsbtn").bind("click",addRow);
});

function addRow(){
	$.ajax({
		url:'InsertRow',
		type: 'post',
		data: $("#insertfrm").serialize(),
		success: function(result){
			$("#insertable").html(result);
		}
	});
}
