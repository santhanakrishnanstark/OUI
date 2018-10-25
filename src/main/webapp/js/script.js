$(document).ready(function(){
	$("#load").delay(4000).fadeOut(300); 
    $("#signin").bind("click",showLogin);
    $("#signup").bind("click",showRegister);
    $("#registerbtn").bind("click",doRegister);
    $("#changepasspan").hide();
    $("#changepassbtn").bind("click",changePass);
});

function tableprint(){ 
window.print();
}
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

function checkusername(){
	let username = $("#unameid").val();
	$.ajax({
		url:'checkusername',
		method:'post',
		data:{uname:username},
		success:function(result){
			if(result == true){ $("#unameid").addClass("error");
			}else{ 
				$("#unameid").removeClass("error");
			}
		}
	});
}
function checkpass(){
	let pass = $("#pass").val();
	let cpass= $("#cpass").val();
	if(pass == cpass){ $("#passerror").text("password matched").css({"color":"green"}); }
	else{ $("#passerror").text("password doesnot matched").css({"color":"red"}); }
}

function changePassword(){
	window.location="help"
	$("#changepasspan").show();
	$("#useraccount").hide();
}
function chcurPassword(){
	$("#changepasspan").show();
	$("#useraccount").hide();
}
function showUserAccount(){
	window.location="help"
	$("#changepasspan").hide();
	$("#useraccount").show();
}
function showUAccount(){
	$("#changepasspan").hide();
	$("#useraccount").show();
}
function changePass(){
	let p1 = $("#newpass").val();
	let p2 = $("#confirmnewpass").val();
	if(p1==p2){
		$.ajax({
			url:'changepassword',
			method:'post',
			data:{password:p1},
			success:function(result){
				alert(result);
			}
		});
	}else{
		$("#passerror").text("password does not match");
	}
}

