$(document).ready(function(){
	$("#connect").bind("click",connectoOracle);
	$("#uiloginbt").bind("click",connect);
});

function connectoOracle(){
	$.ajax({
		url: 'Login',
		type: 'post',
		data: $("#loginform").serialize(),
		success: function(result){
			console.log(result);
		}
	});
}

function connect(){
	$.ajax({
		url: 'UiLogin',
		type: 'post',
		data: $("#uilogin").serialize(),
		success: function(result){
			console.log(result);
			if(result){
				window.location='index2.html';
			}
		}
	});
}