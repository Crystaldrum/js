<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src = "./resources/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#get").on("click",function(){
		$.ajax({
			type : "POST", 
			url : "test11",
			contentType : "application/json;charset=utf-8",
			data : {
				loadCode : "113504130298"
			},
			dataType : "json",
			success : function(data){
				console.log(data);
			},
			error : function(e){
				console.log(e);
			}
		});
	});
	$("#loca").on("click",function(){
		$.ajax({
			url : "http://apis.vworld.kr/2ddata/ademd/data?apiKey=CCA36BB7-0DA8-3EE7-8836-D4814D529510&domain=http://localhost:8888&emdCd=11110101&srsName=EPSG:4326&output=json",
			dataType : "jsonp",
			jsonp : "callback",
			success : function(rtndata){
				console.log(rtndata.featureCollection.features[0].geometry.coordinates);
			},
			error : function(e){
				console.log(e);
			} 
		});
	});
});
function jsonpcallback(rtndata) {
	
	 alert('asdfa');
	
	   // Get the id from the returned JSON string and use it to reference the target jQuery object.
	
	   var posList = "#"+rtndata.posList;
	
	   $(posList).feedback(rtndata.message, {duration: 4000, above: true});
	
	}


</script>
<title>Insert title here</title>
</head>
<body>

입력<input type="button" id = "get">
<input type="button" id = "loca" value="loca">


</body>
</html>