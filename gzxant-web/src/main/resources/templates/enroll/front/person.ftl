<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
	<title>唱响春天</title>
	<link rel="shortcut icon" href="${rc.contextPath}${gzxant.photo}">
	<link href="${rc.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
	<script src="${rc.contextPath}/js/jquery.min.js?v=2.1.4"></script>
	<script src="${rc.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${rc.contextPath}/js/plugins/layer/layer.min.js"></script>
	<script src="${rc.contextPath}/js/util/validate-util.js"></script>
	<script src="${rc.contextPath}/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${rc.contextPath}/js/plugins/validate/messages_zh.min.js"></script>

	<style type="text/css">
		body {
			background:url(${rc.contextPath}/img/enroll/front/person/background.png) top center no-repeat;
			background-size:cover;
		}

		.box {
			margin: auto;
			margin-top: 65%;
			position: absolute;
			top: 0;
			left: 0;
			right: 0;
			bottom: 0;
			width: 90%;
			height: 25rem;
		}

		.box img {
			width: 100%;
			height: 0.5rem;
			margin: 5px auto;
		}

		.box span {
			padding-left: 0px;
			font-size: 2rem;
			color: #FFEF9E;
			font-family: "BigruixianBlackGBV1.0";
			font-weight: bold;
		}

		.btn-block {
			background: -webkit-linear-gradient(#DDC14E, #FFAC49); /* Safari 5.1 - 6.0 */
			background: -o-linear-gradient(#DDC14E, #FFAC49); /* Opera 11.1 - 12.0 */
			background: -moz-linear-gradient(#DDC14E, #FFAC49); /* Firefox 3.6 - 15 */
			background: linear-gradient(#DDC14E, #FFAC49); /* 标准的语法 */
			border-color: #CC8E12;
			margin-bottom: 1rem;
		}

		.left-span {
			text-align: right;
		}

		.right-span {
			text-align: left;
		}

		.bottom {
			width: 100%;
			position: absolute;
			bottom: 3rem;
			text-align: center;
			line-height: 6rem;
		}

		.bottom img {
			width: 13%;
			float: left;
		}

		.bottom span {
			width: 80%;
			font-size: 2rem;
			color: #FFEF9E;
			font-family: "BigruixianBlackGBV1.0";
			font-weight: bold;
		}

		.rotate-img {
			transform:rotate(180deg);
			-ms-transform:rotate(180deg); 	/* IE 9 */
			-moz-transform:rotate(180deg); 	/* Firefox */
			-webkit-transform:rotate(180deg); /* Safari 和 Chrome */
			-o-transform:rotate(180deg); 	/* Opera */
		}
	</style>
</head>
<body>
	<div class="box row">
		<img class="col-xs-12 col-sm-12 col-md-12" src="${rc.contextPath}/img/enroll/front/person/line.png" />
		<span class="col-xs-6 col-sm-6 col-md-6 left-span">阶段：</span>
		<span class="col-xs-6 col-sm-6 col-md-6 right-span" id="type">决赛</span>
		<img class="col-xs-12 col-sm-12 col-md-12" src="${rc.contextPath}/img/enroll/front/person/line.png" />
		<span class="col-xs-6 col-sm-6 col-md-6 left-span">姓名：</span>
		<span class="col-xs-6 col-sm-6 col-md-6 right-span" id="name">传过无神</span>
		<img class="col-xs-12 col-sm-12 col-md-12" src="${rc.contextPath}/img/enroll/front/person/line.png" />
		<span class="col-xs-6 col-sm-6 col-md-6 left-span">得分：</span>
		<span class="col-xs-6 col-sm-6 col-md-6 right-span" id="score">99.9</span>
		<img class="col-xs-12 col-sm-12 col-md-12" src="${rc.contextPath}/img/enroll/front/person/line.png" />
		<span class="col-xs-6 col-sm-6 col-md-6 left-span">排名：</span>
		<span class="col-xs-6 col-sm-6 col-md-6 right-span" id="sort">2</span>
		<img class="col-xs-12 col-sm-12 col-md-12" src="${rc.contextPath}/img/enroll/front/person/line.png" />
	</div>
	<div class="bottom">
		<img src="${rc.contextPath}/img/enroll/front/person/bottom.png" class="rotate-img" />
		<span id="tip">恭喜晋级下一论比赛</span>
		<img src="${rc.contextPath}/img/enroll/front/person/bottom.png" style="float: right;" />
	</div>
	<!--<div class="col-xs-6 col-sm-6 col-md-6 col-xs-offset-3 col-sm-offset-3 col-md-offset-3">
		<a class="btn btn-block btn-info" href="${rc.contextPath}">返回</a>
	</div>-->
</body>
<script type="text/javascript">
	var info_api = "${rc.contextPath}/api/enter/${id}}";
	$.get(info_api, {}, function(data){
		if (validate.isNotEmpty(data)
			&& data.hasOwnProperty("code")
			&& data.code == 1000) {
			var info = data.message;
			$("#type").html(info.type);
			$("#name").html(info.name);
			$("#score").html(info.fraction);
			$("#sort").html(info.sort);
			if (info.state == "Y") {
				$("#tip").html("恭喜晋级下一轮比赛");
			} else {
				$("#tip").html("很遗憾，您未通过" + info.type);
			}
		}
	});
</script>
</html>