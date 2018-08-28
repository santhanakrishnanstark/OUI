/**
 *  script for ORacle UI page
 */
$(document).ready(function(){
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
					$("#tablelist").append("<tr><td> <label onClick='showTName(this)' id='"+result[i]+"'> <input type='radio' class='option-input radio' name='example'  /> "+result[i]+" </label> </td></tr>");
	        	  }
	      }
	});
}

function showTName(e){
	console.log($(e).attr('id'));
}