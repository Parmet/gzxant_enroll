<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no;">
	<title>唱响春天</title>
	<link rel="shortcut icon" href="${rc.contextPath}${gzxant.photo}">
	<link href="${rc.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
	<script src="${rc.contextPath}/js/jquery.min.js?v=2.1.4"></script>
	<script src="${rc.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${rc.contextPath}/js/plugins/layer/layer.min.js"></script>
	<script src="${rc.contextPath}/js/util/validate-util.js"></script>

	<style type="text/css">
		html, body {
			margin: 0;
			padding: 0;
			height: 100%;
		}
		p{
			font: 16px/26px Verdana,Tahoma,Helvetica,Arial,sans-serif;
		}
		.container{
			display: flex;
			flex-direction: column;
			align-items: center;
		}
		.head,.main,.foot{
			width: 100%;
		}
		.head{
			height: 200rpx;
		}
		.main{
			height: auto;
		}
		.foot{
			height: 100rpx;
		}
		.img{
			max-width: 100%;
		}
	</style>
</head>
<body>
	<div class="container">
		<div class="head">
			<h3></h3>
		</div>
		<div class="main">
			<p>
				<img class="img" src="" style="margin: 28px auto 0px;">
			</p>
		</div>
		<div class="foot">
		</div>
	</div>
</body>

<script type="text/javascript">
	$.get("${rc.contextPath}/article/mobile/${id}", function (data) {
		if (data.code == 200) {
			data = data.message;
			$("h3").html(data.title);
			$(".img").attr("src", "http://file.sitofang.top/" + data.image);
			$(".foot").html(data.content);
		}
	});
</script>

</html>