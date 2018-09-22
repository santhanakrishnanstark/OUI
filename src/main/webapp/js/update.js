var current_cell_id,current_id_val,current_row_class,current_index,current_col_name,current_col_value,primary_col_name,primary_col_index,primary_col_value;
$(document).ready(function(){
	$("input[value='"+name+"'").prop('checked', true);
	showUpdateTable();
});

function showUpdateTable(){
	$.ajax({
		url : 'ShowUpTable',
        type: 'post',
        data : {table_name : $('.radio:checked').val(), stb:name },
          success : function(result){
	          $("#updatetablepan").html(result);
	      }
	});
}

function getTableData(el){
	current_cell_id = $(el).attr("id"); //console.log(current_cell_id);
	//current_id_val = $("#"+current_cell_id).val(); //console.log(current_id_val);
	current_row_class = $(el).attr("class");
	  //console.log("current cell id: "+current_cell_id+" current row class: "+current_row_class);
	//console.log("current index : "+$('#'+current_cell_id).parent().index());
	current_index = $('#'+current_cell_id).parent().index();
	current_col_name = $('#dbtable tr th').get(current_index).innerHTML;
	console.log("current index column value : "+ $('#dbtable tr th').get(current_index).innerHTML);
	$.ajax({
		url : 'getprimarycolumn',
        type: 'post',
        data : {table_name : $('.radio:checked').val(), stb:name },
          success : function(result){
	         primary_col_index = result;
	         console.log(primary_col_index);
	         primary_col_name = $('#dbtable tr th').get(Number(primary_col_index-1)).innerHTML;
	         console.log("primary clo name : "+primary_col_name);
	         var res =  $("#dbtable tr:eq('"+current_row_class+"') td").get(Number(primary_col_index-1)).innerHTML;
	         primary_col_value = $(res).attr("value");
	         console.log($(res).attr("value"));
	      }
	});
	
}

function updateRecord(){ 
	current_id_val = $("#"+current_cell_id).val(); 
	$.ajax({
		url : 'Update',
        type: 'post',
        data : { currentcolname:current_col_name, currentcolvalue:current_id_val , primarycolname:primary_col_name , primarycolvalue:primary_col_value },
         success : function(result){
	          console.log(result);
	      }
	});
}