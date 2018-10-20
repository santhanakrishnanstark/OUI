$(document).ready(function(){
    $("#signin").bind("click",showLogin);
    $("#signup").bind("click",showRegister);
    $("#registerbtn").bind("click",doRegister);
});

function showLogin(){
    $(".logindiv").addClass("showlogin").css({"display":"block"});
    $(".registerdiv").removeClass("showregister");
    $(".arrow,.errormessage").hide();
}

function showRegister(){
    $(".registerdiv").addClass("showregister");
    $(".logindiv").removeClass("showlogin");
    $(".logindiv").css({"display":"none"});
    $(".arrow,.errormessage").hide();
}
function doRegister(e){
	e.preventDefault();
	$.ajax({
		url:'uiregister',
		method:'post',
		data:$('#registerfrm').serialize(),
		success:function(result){
			if(result!=null){
			alert(result); }
			location.reload(true);
		}
	});
}