/**
 *  script for ORacle UI page
 */
var total_column;
$(document).ready(function(){
	$('#tablecreationpan').hide();
	$('#createtablelink').bind("click",showViewPan);
	showTables(); viewTableOnLoad();
	$('#go').bind("click",showTForm);
	$('#createtb').bind("click",createTable); 
	
	 $(".usermenu .usermenu-icon").bind("click",showMenu);
	
});
function showMenu(){ 
	  $(".ouimenu").toggleClass("showmenu"); 
}

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
function viewTableOnLoad(){
	$('#tablecreationpan,#tcreationform2').hide(); $('#tablepan').show();
	$.ajax({
		url : 'ShowTable',
        type: 'post',
        data : {table_name : $('.radio:checked').val(), sessiontb : tbname },
          success : function(result){
	          $("#tablepan").html(result);
	      }
	});
}

function showViewPan(){
	$('#tablecreationpan').show();
	$('#tablepan,#tcreationform2').hide();
}

function showTName(e){ 
	//alert($('.radio:checked').val());
	$('#tablecreationpan,#tcreationform2').hide(); $('#tablepan').show();
	$.ajax({
		url : 'ShowTable',
        type: 'post',
        data : {table_name : $('.radio:checked').val() },
          success : function(result){
	          $("#tablepan").html(result);
	      }
	});
	$.ajax({
		url : 'ShowUpTable',
        type: 'post',
        data : {table_name : $('.radio:checked').val() },
          success : function(result){
	          $("#updatetablepan").html(result);
	      }
	});
	$.ajax({
		url: 'showsqlcolumn',
		type: 'post',  
		success: function(result){
			$("#coltxt").html(result);
		}
	});
}

function showTForm(){ 
	$("#tablepan,#tablecreationpan").hide();
	$("#tcreationform2").show();
	let tname = $('#tname').val();
	let tcol = $('#tcol').val();
	let tquery="";
	let tquerybegin = "<form id='frm'>" +
			"<input type='hidden' name='tablename' value="+tname+" />" +
				"<input type='hidden' name='column' value="+tcol+" />" +
			"<table id='tcreate'><thead>" +
			"<tr>" +
			"<th>Column Name</th>" +
			" <th>Datatype</th> " +
			"<th>Length</th>" +
			"<th>Primary</th>" +
			"<th>Unique</th>" +
			"<th>Not Null</th>" +
			"</tr></thead><tbody>";
	for(let i=0; i<tcol; i++){
		tquery+="<tr>" +
				"<td><input type='text' name='column"+i+"'></td>" +
				"<td> <select name='type"+i+"'> "+
					   "<option title='NUMBER'>NUMBER</option> "+
					   "<option title='VARCHAR'>VARCHAR</option> "+
					   "<option title='DATE'>DATE</option> "+
					"</select> "+
				 "</td>" +
				 "<td><input type='number' width='10' name='length"+i+"' style='width:120'></td>" +
				 "<td><input type='checkbox' value='PRIMARY KEY' name='p"+i+"'></td>" +
				 "<td><input type='checkbox' value='UNIQUE' name='u"+i+"'></td>" +
				 "<td><input type='checkbox' value='NOT NULL' name='n"+i+"'></td>" +
				"</tr>";
	}
	let tqueryend="</tbody></table>" +
					"<ul style=' float: right; margin-right: 12%;' >"+
					"<li><input type='button' onClick='showQuery()' id='showquery' class='btn' value='ShowQuery' > </li>" +
					"<li><input type='button' onClick='createTable()' id='createtb' class='btn' value='Create' > </li	> " +
					"</ul>" +
					" </form>";
	$('#tcreationform2').html(tquerybegin+tquery+tqueryend);	
}
function showQuery(){
	$.ajax({
		url : 'showquery',
        type: 'post',
        data : $("#frm").serialize(),
          success : function(result){
	          alert(result);
	      }
	});
}

function createTable(){
	$.ajax({
		url : 'CreateTable',
        type: 'post',
        data : $("#frm").serialize(),
          success : function(result){
	         // $("#message").html(result);
	          alert(result);
	          location.reload(true);
	      }
	});
}

function deleteRecord(dis){
	let row_class =$(dis).parent().attr('class') ; let i=0;
	 $("#dbtable").find("tr td."+row_class).each(function(index){
		    i++;
		  });
	total_column = (i-1);
	let colval = new Array(); let j=1;
	
	 $("#dbtable").find("tr td."+row_class).each(function(index){
		    colval[index] = $('#dbtable tr td#'+row_class+''+j).text();
		    j++;
	 });
	 
	 $.ajax({
		 url : 'deleterecord',
		 type: 'post',
		 data: {record:colval},
		 success: function(result){
			 alert(result);
			 showTName();
		 }
	 });
}



