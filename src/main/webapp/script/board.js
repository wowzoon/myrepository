function boardCheck(){
	if(document.frm.name.value.length==0){
		alert("이름을 입력하세요");
		document.frm.name.focus(); // focus 주기
		return false;
	}
	if(document.frm.pass.value.length==0){
		alert("비밀번호를 입력하세요");
		document.frm.pass.focus(); // focus 주기
		return false;
	}
	if(document.frm.title.value.length==0){
		alert("제목을 입력하세요");
		document.frm.title.focus(); // focus 주기
		return false;
	}
	if(document.frm.content.value==""){
		alert("내용을 입력하세요");
		document.frm.content.focus(); // focus 주기
		return false;
	}
}
function open_win(url,name){
	if(name=="delete"){
		if(!confirm("삭제 하시겠습니까?")){
			return;
		}
	}
	// url: 팝업창에 로딩할 주소, name:팝업창 이름(이름이 없으면 팝업창이 계속 뜹니다.)
	window.open(url,name,"width=500,height=230");
}
function passCheck(){
	if(document.frm.pass.value.lengh==0){
		alert("비밀 번호를 입력하세요");
		document.frm.pass.focus();
		return false;
	}
}