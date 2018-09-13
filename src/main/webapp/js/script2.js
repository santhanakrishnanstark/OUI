/**
 *  script for ORacle UI page
 */
$(document).ready(function(){
	$('#tablecreationpan').hide();
	$('#createtablelink').bind("click",showViewPan);
	showTables();
	
	    
});

function showTables(){
	$.ajax({
		url : 'ListTable',
        type: 'post',
        dataType : 'json',
          success : function(result){
	          console.log(result);
        	  var count = Object.keys(result).length;
        	  for(var i=1; i<=count; i++){
					$("#tablelist").append("<tr><td> <label> <input type='radio' class='option-input radio' onClick='showTName()' name='tablename' value="+result[i]+"  /> "+result[i]+" </label> </td></tr>");
	        	  }
	      }
	});
}

function showViewPan(){
	$('#tablecreationpan').show();
	$('#tablepan').hide();
}

function showTName(e){ 
	//alert($('.radio:checked').val());
	$('#tablecreationpan').hide(); $('#tablepan').show();
	$.ajax({
		url : 'ShowTable',
        type: 'post',
        data : {table_name : $('.radio:checked').val() },
          success : function(result){
	          $("#tablepan").html(result);
	      }
	});
}