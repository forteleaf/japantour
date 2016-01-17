/**
 * 
 */

// recentInfo 에 대한 최근 글
var recentJintro = new function (){
	var XHRJintro = getXHR();
	
	XHRJintro.onreadystatechange = function(){
		if( XHRJintro.readyState == 4 && XHRJintro.status == 200 ){
			var data = JSON.parse(XHRJintro.responseText);
			//최근 목록 보여주기.
			recentJintro.show(data.recentintro);
		}
	}
	// 글 내용 출력하기
	this.show = function(list){
		var span = document.getElementById("recentJaIntro");
		var str = "";
		for (var i=0 ; i<list.length ; i++) {
			str += "<a href='intro?cmd=getInfo&num="+ list[i].num + "'>" +list[i].title+"</a><br>";
		}
		span.innerHTML = str;
	}
	// 글 내용 가져오기
	this.proc = function(){
		XHRJintro.open("get","recentBoard?board=japanintro",true);
		XHRJintro.send();
	}
	this.proc();
}

var recentJinfo = new function (){
	var XHRJinfo = getXHR();
	
	XHRJinfo.onreadystatechange = function(){
		if( XHRJinfo.readyState == 4 && XHRJinfo.status == 200 ){
			var list = JSON.parse(XHRJinfo.responseText);
			//최근 목록 보여주기.
			recentJinfo.show(list.recentInfo);
			//console.log(list);
		}
	}
	// 글 내용 출력하기
	this.show = function(list){
		var span = document.getElementById("recentJaInfo");
		var str = "";
		for (var i=0 ; i<list.length ; i++) {
			str += "<a href='japaninfo?cmd=view&inum=" + list[i].inum + "'>" +list[i].title+"</a><br>";
		}
		
		span.innerHTML = str;
	}
	// 글 내용 가져오기
	this.proc = function(){
		XHRJinfo.open("get","recentBoard?board=japaninfo",true);
		XHRJinfo.send();
	}
	this.proc();
}
// recentReport 에 대한 최근 글
var recentJreport = new function (){
	var XHRJreport = getXHR();
	
	XHRJreport.onreadystatechange = function(){
		if( XHRJreport.readyState == 4 && XHRJreport.status == 200 ){
			var list = JSON.parse(XHRJreport.responseText);
			//최근 목록 보여주기.
			recentJreport.show(list.recentInfo);
			//console.log(list);
		}
	}
	// 글 내용 출력하기
	this.show = function(list){
		var span = document.getElementById("recentReInfo");
		var str = "";
		for (var i=0 ; i<list.length ; i++) {
			str += "<a href='report?cmd=detail&rnum="+list[i].rnum+"'>"+list[i].title+"</a><br>";
		}
		span.innerHTML = str;
	}
	// 글 내용 가져오기
	this.proc = function(){
		XHRJreport.open("get","recentBoard?board=report",true);
		XHRJreport.send();
	}
	this.proc();
}
