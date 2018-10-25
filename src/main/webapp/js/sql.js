var sqlquery;
$(document).ready(function(){
	showColumnNames();
	$("#runbtn").bind("click",executeQuery);
	$("#insertbtn").bind("click",generateInsertQuery);
	$("#updatebtn").bind("click",generateUpdateQuery);
	$("#deletebtn").bind("click",generateDeleteQuery);
	$("#clearbtn").click(()=>{ $("#sqltxt").val("") });
});

function showColumnNames(){
	$.ajax({
		url: 'showsqlcolumn',
		type: 'post',  
		success: function(result){
			$("#coltxt").html(result);
		}
	});
}

function executeQuery(){
	sqlquery = $("#sqltxt").val();
	$.ajax({
		url: 'executequery',
		type: 'post',  
		data:{query: sqlquery },
		success: function(result){
			alert(result);
		}
	});
}

function generateInsertQuery(){
	$.ajax({
		url: 'generateinsertquery',
		type: 'post',  
		success: function(result){
			$("#sqltxt").val(result);
		}
	});
}

function generateUpdateQuery(){
	$.ajax({
		url: 'generateupdatequery',
		type: 'post',  
		success: function(result){
			$("#sqltxt").val(result);
		}
	});
}
function generateDeleteQuery(){
	$.ajax({
		url: 'generatedeletequery',
		type: 'post',  
		success: function(result){
			$("#sqltxt").val(result);
		}
	});
}