function getXHR(){
	if(window.XMLHttpRequest){//ie7이상,ie이외의 브라우져
		return new XMLHttpRequest();
	}else{
		//ie6이하
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}
//replace(원본문자열,찾는문자,바꿀문자)
//replace("Hello world","w","W") ==> 결과 : Hello World
function replace(str1,str2,str3){
	var ch='';
	var str='';
	for(var i=0;i<str1.length;i++){
		ch=str1.charAt(i);
		if(ch==str2){
			str += str3;
		}else{
			str += ch;
		}
	}
	return str;
}
















