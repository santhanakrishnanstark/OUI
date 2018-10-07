$(document).ready(function(){
	$("#dropbtn").bind("click",dropTable);
	$("#truncatebtn").bind("click",truncateTable);
	showTableList();
});
function showTableList(){
	$.ajax({
		url : 'ListTable',
        type: 'post',
        dataType : 'json',
          success : function(result){
	          console.log(result);
        	  var count = Object.keys(result).length;
        	  for(var i=1; i<=count; i++){
					$("#tablenames").append("<option value="+result[i]+"> "+result[i]+" </option> ");
	        	  }
	      }
	});
}
function dropTable(e){
	e.preventDefault();
	$.ajax({
		url : 'droptable',
        type: 'post',
        data : $('#dropfrm').serialize(),
        success : function(result){
        	  alert(result);
        	  location.reload(true);
          }
	});
}
function truncateTable(e){
	e.preventDefault();
	$.ajax({
		url : 'truncatetable',
        type: 'post',
        data : $('#dropfrm').serialize(),
        success : function(result){
        	  alert(result);
        	  location.reload(true);
          }
	});
}