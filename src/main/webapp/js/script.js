$(document).ready(function(){
    $("#signin").bind("click",showLogin);
    $("#signup").bind("click",showRegister);
});

function showLogin(){
    $(".logindiv").addClass("showlogin").css({"display":"block"});
    $(".registerdiv").removeClass("showregister");
}

function showRegister(){
    $(".registerdiv").addClass("showregister");
    $(".logindiv").removeClass("showlogin");
    $(".logindiv").css({"display":"none"});
}