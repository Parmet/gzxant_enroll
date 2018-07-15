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

	<script src="${rc.contextPath}/plugins/LArea/js/LArea.js"></script>
	<script src="${rc.contextPath}/plugins/LArea/js/LAreaData1.js"></script>
	<link rel="stylesheet" href="${rc.contextPath}/plugins/LArea/css/LArea.css" />
	<style type="text/css">
		body {
			background:url(${rc.contextPath}/img/enroll/front/index.jpg) top center no-repeat;
			background-size:cover;
		}

		.btn-box {
			width: 90%;
			position: absolute;
			bottom: 2rem;
			right: 2rem;
		}

		.box {
			margin: 5rem auto;
			width: 90%;
			background: #3E0C0F;
			border: #FFC739 2px solid;
			border-radius: 12px;
		}

		.box label {
			color: #fff;
			font-family : "PingFangSC";
		}

		.box form {
			padding: 1rem;
			margin-top: 3rem;
		}

		.btn-block {
			background: -webkit-linear-gradient(#DDC14E, #FFAC49); /* Safari 5.1 - 6.0 */
			background: -o-linear-gradient(#DDC14E, #FFAC49); /* Opera 11.1 - 12.0 */
			background: -moz-linear-gradient(#DDC14E, #FFAC49); /* Firefox 3.6 - 15 */
			background: linear-gradient(#DDC14E, #FFAC49); /* 标准的语法 */
			border-color: #CC8E12;
			margin-bottom: 1rem;
		}

		.em { color: red; font-weight: bold; padding-right: .25em; }
	</style>
</head>
<body>
	<div class="box">
		<form class="form-horizontal form-inline">
			<div class="form-group">
				<label for="place" class="col-xs-4 col-sm-4 col-md-4 control-label">地区</label>
				<div class="col-xs-8 col-sm-8 col-md-8">
					<input type="text" class="form-control" readonly="" name="place" id="place" placeholder="请选择地区 ">
					<input type="hidden" name="portId" id="portId"/>
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-xs-4 col-sm-4 col-md-4 control-label">姓名</label>
				<div class="col-xs-8 col-sm-8 col-md-8">
					<input type="text" class="form-control" name="name" id="name" placeholder="请输入姓名">
				</div>
			</div>
			<div class="form-group">
				<label for="idCard" class="col-xs-4 col-sm-4 col-md-4 control-label">身份证号</label>
				<div class="col-xs-8 col-sm-8 col-md-8">
					<input type="text" class="form-control" name="idCard" id="idCard" placeholder="请输入身份证号">
				</div>
			</div>
			<div class="form-group">
				<label for="company" class="col-xs-4 col-sm-4 col-md-4 control-label">工作单位</label>
				<div class="col-xs-8 col-sm-8 col-md-8">
					<input type="text" class="form-control" name="company" id="company" placeholder="请输入工作单位">
				</div>
			</div>
			<div class="form-group">
				<label for="profession" class="col-xs-4 col-sm-4 col-md-4 control-label">职位</label>
				<div class="col-xs-8 col-sm-8 col-md-8">
					<input type="text" class="form-control" name="profession" id="profession" placeholder="请输入职业">
				</div>
			</div>
			<div class="form-group">
				<label for="style" class="col-xs-4 col-sm-4 col-md-4 control-label">演唱风格</label>
				<div class="col-xs-8 col-sm-8 col-md-8">
					<input type="text" class="form-control" name="style" id="style" placeholder="请输入演唱风格">
				</div>
			</div>
			<div class="form-group">
				<label for="account" class="col-xs-4 col-sm-4 col-md-4 control-label">手机号</label>
				<div class="col-xs-8 col-sm-8 col-md-8">
					<input type="number" class="form-control" name="phone" id="phone" placeholder="请输入手机号">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-xs-4 col-sm-4 col-md-4 control-label">密码</label>
				<div class="col-xs-8 col-sm-8 col-md-8">
					<input type="password" class="form-control" id="password" maxlength="16" name="password" placeholder="请输入密码">
				</div>
			</div>
			<div class="form-group">
				<label for="confirmPassword" class="col-xs-4 col-sm-4 col-md-4 control-label">确认密码</label>
				<div class="col-xs-8 col-sm-8 col-md-8">
					<input type="password" class="form-control" id="confirmPassword" maxlength="16" name="confirmPassword" placeholder="请输入密码">
				</div>
			</div>
			<div class="row">
				<label class="col-xs-12 col-sm-12 col-md-12">备注：本次活动须支付报名费<span style="color:red;">100.00</span>元</label>
			</div>
		</form>
	</div>
	<div class="col-xs-6 col-sm-6 col-md-6 col-xs-offset-3 col-sm-offset-3 col-md-offset-3">
		<button class="btn btn-block btn-info" onclick="payAndSubmit();">报名并支付</button>
	</div>
</body>
<script type="text/javascript">
	var return_url = "http://api.gzxant.com/gzxant/enroll/front/index";
	var pay_api = "http://api.gzxant.com/gzxant/enroll/api/pay?returnUrl=" + return_url + "&openid=${openid}";
	var enroll_api = "${rc.contextPath}/api/enroll";

	var area = new LArea();
	area.init({
		'trigger': '#place',//触发选择控件的文本框，同时选择完毕后name属性输出到该位置
		'valueTo':'#portId',//选择完毕后id属性输出到该位置
		'keys':{id:'id',name:'name'},//绑定数据源相关字段 id对应valueTo的value属性输出 name对应trigger的value属性输出
		'type':1,//数据源类型
		'data':LAreaData//数据源
	});

	 var vo = $("form").validate({
		errorElement: 'span',
		errorClass: 'em',
		focusInvalid: true,
		rules: {
 			name: {
                required: true,
                remote: '${rc.contextPath}/personnel/check/'
            },
            phone: {
                required: true,
                number:true,
                isMobile:true,
                maxlength:11,
                maxlength:11
            },
            password: {
                required: true,
                minlength:6
            },
            idCard: {
                required: true,
                idCard2:true
            },
            confirmPassword : {
            	required: true,
            	confirmPassword2: true
            }
		},
		messages: {
			phone:{
				required: "请输入手机号码",
				number:"只能输入数字",
				isMobile:"请输入正确的手机号码",
				maxlength:"请输入11位的手机号码",
				maxlength:"请输入11位的手机号码"
			},
			password:{
				required:  "请输入密码",
				minlength:"密码不能小于6位数"
			},
            idCard: {
                required:"请输入身份号码",
                idCard2:"请输入正确的身份号码"
            }
		}
	});

	$.validator.addMethod('idCard2', function (value, element) {
        var vcity = {
            11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古",
            21: "辽宁", 22: "吉林", 23: "黑龙江", 31: "上海", 32: "江苏",
            33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南",
            42: "湖北", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆",
            51: "四川", 52: "贵州", 53: "云南", 54: "西藏", 61: "陕西", 62: "甘肃",
            63: "青海", 64: "宁夏", 65: "新疆", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外"
        };

        // 判断是否为空
        isEmpty = function(card){
            if (/^\s*$/.test(card) === true) {
                return true;
            }
        }
        //检查号码是否符合规范，包括长度，类型
        isCardNo = function (card) {
            if( isEmpty(card) ){
                return true;
            }
            //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
            var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
            if (reg.test(card) === false) {
                return false;
            }
            return true;
        };

        //取身份证前两位,校验省份
        checkProvince = function (card) {
            if( isEmpty(card) ){
                return true;
            }
            var province = card.substr(0, 2);
            if (vcity[province] == undefined) {
                return false;
            }
            return true;
        };

        //检查生日是否正确
        checkBirthday = function (card) {
            if( isEmpty(card) ){
                return true;
            }
            var len = card.length;
            //身份证15位时，次序为省（3位）市（3位）年（2位）月（2位）日（2位）校验位（3位），皆为数字
            if (len == '15') {
                var re_fifteen = /^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/;
                var arr_data = card.match(re_fifteen);
                var year = arr_data[2];
                var month = arr_data[3];
                var day = arr_data[4];
                var birthday = new Date('19' + year + '/' + month + '/' + day);
                return verifyBirthday('19' + year, month, day, birthday);
            }
            //身份证18位时，次序为省（3位）市（3位）年（4位）月（2位）日（2位）校验位（4位），校验位末尾可能为X
            if (len == '18') {
                var re_eighteen = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;
                var arr_data = card.match(re_eighteen);
                var year = arr_data[2];
                var month = arr_data[3];
                var day = arr_data[4];
                var birthday = new Date(year + '/' + month + '/' + day);
                return verifyBirthday(year, month, day, birthday);
            }
            return false;
        };

        //校验日期
        verifyBirthday = function (year, month, day, birthday) {
            var now = new Date();
            var now_year = now.getFullYear();
            //年月日是否合理
            if (birthday.getFullYear() == year && (birthday.getMonth() + 1) == month && birthday.getDate() == day) {
                //判断年份的范围（3岁到100岁之间)
                var time = now_year - year;
                if (time >= 3 && time <= 100) {
                    return true;
                }
                return false;
            }
            return false;
        };

        //校验位的检测
        checkParity = function (card) {
            if( isEmpty(card) ){
                return true;
            }
            //15位转18位
            card = changeFivteenToEighteen(card);
            var len = card.length;
            if (len == '18') {
                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                var cardTemp = 0, i, valnum;
                for (i = 0; i < 17; i++) {
                    cardTemp += card.substr(i, 1) * arrInt[i];
                }
                valnum = arrCh[cardTemp % 11];
                if (valnum == card.substr(17, 1)) {
                    return true;
                }
                return false;
            }
            return false;
        };

        //15位转18位身份证号
        changeFivteenToEighteen = function (card) {
            if( isEmpty(card) ){
                return true;
            }
            if (card.length == '15') {
                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                var cardTemp = 0, i;
                card = card.substr(0, 6) + '19' + card.substr(6, card.length - 6);
                for (i = 0; i < 17; i++) {
                    cardTemp += card.substr(i, 1) * arrInt[i];
                }
                card += arrCh[cardTemp % 11];
                return card;
            }
            return card;
        };

        //checkCard = function () {
        var card = value;
        //校验长度，类型
        if (isCardNo(card) === false) {
            //alert('您输入的身份证号码不正确，请重新输入');
            //document.getElementById('card_no').focus;
            return false;
        }
        //检查省份
        if (checkProvince(card) === false) {
            return false;
        }
        //校验生日
        if (checkBirthday(card) === false) {
            return false;
        }
        //检验位的检测
        if (checkParity(card) === false) {
            return false;
        }

        return true;
    });

    // 手机号码验证
	$.validator.addMethod("confirmPassword2", function(value, element) {
		var pwd = $("#password").val();
		var conPwd = $("#confirmPassword").val();
		if (validate.isEmpty(pwd) || validate.isEmpty(conPwd) || (pwd != conPwd)) {
			return false;
		}

		return true;
	}, "密码不一致，请重新输入");

	// 手机号码验证
	$.validator.addMethod("isMobile", function(value, element) {
		var length = value.length;
		var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})|(16[0-9]{9})$/;
		return this.optional(element) || (length == 11 && mobile.test(value));
	}, "请正确填写您的手机号码");

	function payAndSubmit() {
		if (!vo.form()) {
			return ;
		}

		$.post(enroll_api, {
		    "place": $("#place").val(),
			"phone": $("#phone").val(),
			"password": $("#password").val(),
			"name": $("#name").val(),
			"company": $("#company").val(),
			"idCard": $("#idCard").val(),
			"profession": $("#profession").val(),
			"style": $("#style").val(),
			"openid": "${openid}"
		}, function(data) {
			if (validate.isNotEmpty(data)
				&& data.hasOwnProperty("code")
				&& data.code == 1000) {
				window.location.href = pay_api;
				layer.alert("报名成功！");
			} else {
				layer.alert(data.error);
			}
		}).error(function(xhr, status, info) {
			layer.msg("系统繁忙，请稍后重试");
		});
    }

	function onBridgeReady(data){
		var time_stamp = Date.parse(new Date());
		time_stamp = time_stamp.substring(0, time_stamp.length - 3);
	    WeixinJSBridge.invoke(
		    'getBrandWCPayRequest', {
			    "appId": data.appId,     //公众号名称，由商户传入
			    "timeStamp": data.timeStamp,         //时间戳，自1970年以来的秒数
			    "nonceStr":data.nonceStr, //随机串
			    "package":"prepay_id=" + nonceStr.prepayId,
			    "signType":"MD5",         //微信签名方式：
			    "paySign":nonceStr.paySign //微信签名
		    },
		    function(res){
			    if(res.err_msg == "get_brand_wcpay_request:ok" ) {}     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
		    }
	    );
	}


</script>


</html>