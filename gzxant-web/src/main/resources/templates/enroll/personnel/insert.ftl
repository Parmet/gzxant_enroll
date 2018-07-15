<style>
    .error{
        color:red;
    }
</style>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins" >
                <div class="">
                    <form class="form-horizontal form-bordered" id="gzxantForm">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">姓名：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="name"
                                       value="" placeholder="请输入注册姓名"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">手机号码：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="phone"
                                value="" placeholder="请输入手机号码"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">密码：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" name="password"
                                       value="" placeholder="请输入密码"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">身份证号码：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="idCard"
                                       value="" placeholder="请输入身份证号码"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">报名地点：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="place"
                                       value="" placeholder="请输入报名地点"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">工作单位：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="company"
                                       value="" placeholder="请输入工作单位"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">职业：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="profession"
                                       value="" placeholder="请输入职业"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">演唱风格：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="style"
                                       value="" placeholder="请输入演唱风格"/>
                            </div>
                        </div>




                            <div class="form-actions fluid">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="submit" onclick="infoNextStep()" class="btn btn-success">保存</button>
                            </div>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">

    action = "${action}";
    function  cusFunction() {
        console.info("提交之前，最后执行自定义的函数");
    }


        // 手机号码验证
        $.validator.addMethod("isMobile", function(value, element) {
                var length = value.length;
            var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
                 return this.optional(element) || (length == 11 && mobile.test(value));
            }, "请正确填写您的手机号码");


    function infoNextStep() {
        info_validate.form();
    }

    var info_validate = $('#gzxantForm');
    var error = $('.alert-danger', info_validate);
    info_validate.validate({
        errorElement: 'span',
        errorClass: 'error',
        focusInvalid: true,
        rules: {
            name: {
                required: true,
                maxlength:10
            },
            phone: {
                required: true,
                number:true,
                isMobile:true,
                minlength:11,
                maxlength:11
            },
            profession:{
                maxlength:20
            },
            company:{
                maxlength:100
            },
            place:{
                required: true,
                maxlength:100
            },
            password: {
                required: true,
                minlength:6
            },
            idCard: {
                required: true,
                idCard2:true
            },
            style: {
                required: true,
                maxlength:30
            }
        },
        messages: {
            name:{
                required:  "请输入登录名",
                maxlength:"姓名不能超过10个字"
            },
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
            place:{
                required: "请输入报名地点",
                maxlength:"报名不能超过100个字"
            },
            idCard: {
                required:"请输入身份号码",
                idCard2:"请输入正确的身份号码"
            },
            style:{
                required:"请输入演唱风格",
                maxlength:"风格不能超过30个字"
            },
            profession:{
                maxlength:"职业不能超过20个字"
            },
            company:{
                maxlength:"工作单位不能超过100个字"
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

</script>
