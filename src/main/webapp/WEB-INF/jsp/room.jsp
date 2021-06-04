<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<script type="text/javascript">

	var ws;
	function connectWs() {
		ws = new WebSocket('ws://' + location.host + '/ws');
		ws.onmessage = function(data) {
			console.log(data.data);
			$("<p>"+data.data+"</p>").prependTo('#chat');
		}

		$('#startBtn').hide();
	}

	function send() {
		ws.send($("#chatting").val());
		$('#chatting').val("");
	}
</script>

<body>

	<h1>약속방 입니다</h1>
	<button onclick="connectWs()" id="startBtn"> 위치 공유 시작하기</button>
	<input id="chatting"></input><button onclick="send()"> 보내기 </button>
	<div>
		<p id="chat"></p>
	</div>
</body>
</html>