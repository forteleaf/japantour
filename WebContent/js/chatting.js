function chatEnter(e){
	var key = e.keyCode;
	if(key == 13){
		chatManager.write(this);
	}
}

var chatManager = new function(){
	var chatlistXHR = getXHR();
	var interval = 5000;

	chatlistXHR.onreadystatechange = function(){
		if (chatlistXHR.readyState == 4 && chatlistXHR.status == 200) {
		var res = JSON.parse(chatlistXHR.responseText); //JSON 으로 date 변환 할때 오류가 있다.
		chatManager.show(res.chats);
		}
	}

	this.show = function(data){
		var ll = document.getElementById("clist");
		var str = "";
		for (var i=0;i<data.length;i++){
			var id = document.getElementById("id").value;
			if( id == data[i].id){
				str += "<span style='color:red;font-weight: bold;text-align:left'>"+data[i].id+"</span>";
			}else{
				str += data[i].id;
			}
			str += ">>" + data[i].msg +"<br>";
		}
		ll.innerHTML = str;
		ll.scrollTop = ll.scrollHeight;
	}
	
	// 글 입력
	this.write = function(){
		var msg = document.getElementById("msg");
		if (msg.value == "") {
			alert("내용을 입력해 주세요.");
			msg.focus();
		}
		xhrwrite = getXHR();
		xhrwrite.open("post", "chat?cmd=write", true);
		xhrwrite.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		var params = "msg=" + msg.value;
		xhrwrite.send(params);
		msg.value = "";
		
		chatManager.proc();
	}
	// list 보여주기
	this.proc = function (){
		chatlistXHR.open("get","chat?cmd=list",true);
		chatlistXHR.send();
	}
	setInterval(this.proc,interval);
}