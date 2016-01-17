<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/recentlist.js"></script>
<style type="text/css">
#popup{width: 120px; height: 110px; padding: 10px; border: 2px solid #005e75 ; background-color: white; position: absolute;
top: 380px; left: 810px; display: none;}
</style>
<script type="text/javascript">
function printXY(n) {
	var popup=document.getElementById("popup");
	popup.style.display="block";	
	popup.style.position= "absolute";
	popup.style.width= "100px";
	popup.style.left= "300px";

	var div=document.getElementById("divaa");	
	div.style.width="10";
	div.style.height='10';	
	if(n=='1'){
		div.innerHTML = "<h3>北海道 </h3><h3>ほっかいどう</h3><h5>홋카이도</h5>";
	} else if (n == '2') {
		div.innerHTML = "<h3>東北 </h3><h3>とうほく</h3><h5>토-호쿠</h5>";
	} else if (n == '3') {
		div.innerHTML = "<h3>北陸 </h3><h3>ほくりく</h3><h5>호쿠리쿠</h5>";
	} else if (n == '4') {
		div.innerHTML = "<h3>関東 </h3><h3>かんとう</h3><h5>칸토-</h5>";
	} else if (n == '5') {
		div.innerHTML = "<h3>東海 </h3><h3>かんさい</h3><h5>토-카이</h5>";
	} else if (n == '6') {
		div.innerHTML = "<h3>関西 </h3><h3>かんさい</h3><h5>칸사이</h5>";
	} else if (n == '7') {
		div.innerHTML = "<h3>中国  </h3><h3>ちゅうごく</h3><h5>츄-고쿠</h5>";
	} else if (n == '8') {
		div.innerHTML = "<h3>四国 </h3><h3>しこく</h3><h5>시코쿠</h5>";
	} else if (n == '9') {
		div.innerHTML = "<h3>九州 </h3><h3>きゅうしゅう</h3><h5>큐슈</h5>";
	} else if (n == '10') {
		div.innerHTML = "<h3>沖縄 </h3><h3>おきなわ</h3><h5>오키나와</h5>";
	}
}
function hidePopup() {
	var popup = document.getElementById("popup");
	popup.style.display = "none";
}

</script>
<div class="content">
<h1>JAPAN MAP</h1>
<div>
<ul id="imagemap">
<li id="hokkaido"><a href="javascript:printXY(1)"><span>홋카이도</span></a></li>
<li id="touhoku"><a href="javascript:printXY(2)"><span>東北 토호쿠</span></a></li>
<li id="hokuriku"><a href="javascript:printXY(3)"><span>北陸 호쿠리코</span></a></li>
<li id="kanto"><a href="javascript:printXY(4)"><span>関東관동</span></a></li>
<li id="tokai"><a href="javascript:printXY(5)"><span>東海토카이</span></a></li>
<li id="kansai"><a href="javascript:printXY(6)"><span>関西칸사이</span></a></li>
<li id="chugoku"><a href="javascript:printXY(7)"><span>中国히로시마</span></a></li>
<li id="shikoku"><a href="javascript:printXY(8)"><span>四国시코쿠</span></a></li>
<li id="kyushu"><a href="javascript:printXY(9)"><span>九州큐슈</span></a></li>
<li id="okinawa"><a href="javascript:printXY(10)"><span>沖縄오키나와</span></a></li>
</ul>
</div>
	<div name="city">
		<h3>recentJapan Intro 에 대한 최근 글</h3>
		<div id="recentJaIntro" ></div>
	</div>
	<div name="city">
		<h3>JAPANINFO 대한 최근 글</h3>
		<div id="recentJaInfo"></div>
	</div>
	<div name="city">
		<h3>REPORT에 대한 최근 글</h3>
		<div id="recentReInfo"></div>
	</div>
</div>
<div id="popup" align="center">
<div style="text-align: right;"><a href="javascript:hidePopup()" style="font-size: 7px">닫기</a>
</div><br>
<div id="divaa">
</div>
</div></div>