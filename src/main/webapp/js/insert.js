
$(document).ready(function(){
	$("#addrowsbtn").bind("click",addRow);
	 $("#remove").bind("click",remove);
	  $("#add").bind("click",add);
	  //alert(document.querySelector("table").rows[0].cells.length);
	  var col=document.querySelector("table").rows[0].cells.length;
	 
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
function remove(){ 
	  var len = $('#insertb  tr').length-2;
	 if(len>1) {
	        $("#insertb tr:last").remove();
	  } else {
	       alert("Sorry!! Can't remove first row!");
	    }
	}
function add(){
  let tlen = $("#insertb tr").length-2;
  var data = $("#insertb  tr:eq(2)").clone(true).appendTo("#insertb");
              data.find("input").val('').attr("class",tlen);
  let i=0;
  data.find("td input").each(function( index ) {
    $(this).attr("id",tlen+''+i++);
  //console.log( index + ": " + $( this ).text() );
});
}

function insert(){
	let column=document.querySelector("#insertb").rows[0].cells.length;
	let row= ($("#insertb tr").length-2);
	
	var r = row, c=column;
	var q = new Array();
	for(i=0; i<r; i++){
		q[i] = new Array();
		for(j=0; j<c; j++){
			var pick = "#"+i+j; console.log(pick);
			q[i][j] = $(pick).val();
		}
	  }
	  console.log(q);
	  console.log(typeof q); 
	//to print
	 $.ajax({
		url: 'Insert',
		type: 'post',
		data: {records :q, row: r,field: c },   //{records : q, rec: r,field: c },
		success: function(result){
			//$("#result").html(result);
			alert(result);
			location.reload(true);
		}
	 });
}
