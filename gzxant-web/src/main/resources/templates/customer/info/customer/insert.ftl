<style>
    .error{
        color:red;
    }
</style>
<link href="${rc.contextPath}/css/plugins/dropzone/dropzone.css" rel="stylesheet">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins" >
                <div class="">
                    <form class="form-horizontal form-bordered" id="gzxantForm">
                        <input type="hidden" name="id" value="${customer.id}"/>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">姓名：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="name"
                                       value="" placeholder="输入姓名"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">性别：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <select name="gender" class="form-control">
                                    <option value="">请选择</option>
                                    <#list genderList as gender>
                                        <option value="${gender.jkey}">${gender.jvalue}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">电话：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="tel"
                                       value="" placeholder="输入手机号码"/>
                            </div>
                        </div>

                        <#-- hidden value for certificate foreign key -->
                        <input type="hidden" name="certificateId" value="${customer.getCertificateId}"
                            id="certificateId"/>


                        <div class="form-group">

                            <label class="col-sm-3 control-label">证件图片：<span class="required">*</span></label>
                            <#-- photo location input -->
                            <input type="hidden" name="attachment" value="${sysUser.photo}" id="photo"/>

                            <#-- drop zone area -->
                            <div class="col-sm-4">
                                <div id="mydropzone" class="dropzone"></div>
                            </div>
                        </div>



                        <div class="form-group">
                            <label class="col-sm-3 control-label">证件类型：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <select name="type" class="form-control">
                                    <option value="">请选择</option>
                                    <#list typeList as type>
                                        <option value="${type.jkey}">${type.jvalue}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">证件号码：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="number"
                                       value="" placeholder="请输入证件号码"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">发证日期：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control datepicker" name="issueDate">

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">有效期至：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control datepicker" name="validityPeriod">
                            </div>

                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">客户状态：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <select name="status" class="form-control">
                                    <option value="">请选择</option>
                                    <#list statusList as status>
                                        <option value="${status.jkey}">${status.jvalue}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">地址：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="address"
                                       value="" placeholder="输入客户地址"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-3 control-label">准驾车型：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <select name="driveCapability" class="form-control">
                                    <option value="">请选择</option>
                                    <#list capabilityList as capability>
                                        <option value="${capability.jkey}">${capability.jvalue}</option>
                                    </#list>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">邮件地址：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="email"
                                       value="" placeholder="输入客户邮件地址"/>
                            </div>
                        </div>



                        <div class="form-group">
                            <label class="col-sm-3 control-label">紧急联络人：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="emergencyContact"
                                       value="" placeholder="输入紧急联络人" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">紧急联络人电话：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="emergencyContactTel"
                                       value="" placeholder="输入紧急联络人电话" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">与紧急联络人的关系：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="emergencyContactRelationship"
                                       value="" placeholder="请填写与紧急联络人的关系" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">备注：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="remark"
                                       value="" placeholder="输入客户备注" />
                            </div>
                        </div>

                        <div class="form-actions row">
                            <div class="col-sm-offset-3 col-sm-9">
                                <button type="submit" onclick="infoNextStep()" class="btn btn-success">提交</button>
                            </div>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

<#--import dataPicker to support date pick-->
<script src="${rc.contextPath}/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<#-- import the dropzone to support file upload -->
<script src="${rc.contextPath}/js/plugins/dropzone/dropzone.min.js"></script>
<script type="text/javascript">
    // startup the datepicker
    $('.datepicker').datepicker();

    action = "${action}";
    function  cusFunction() {
        console.info("提交之前，最后执行自定义的函数");
    }
    function infoNextStep() {
        info_validate.form();
    }
    // 手机号码验证
    $.validator.addMethod("isMobile", function(value, element) {
        var length = value.length;
        var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写您的手机号码");
    // $.validator.addMethod("isTel", function(value, element) {
    //     var length = value.length;
    //     var phone = /(^(\d{3,4}-)?\d{6,8}$)|(^(\d{3,4}-)?\d{6,8}(-\d{1,5})?$)|(\d{11})/;
    //     return this.optional(element) || (phone.test(value));
    // }, "请填写正确的固定电话");//可以自定义默认提示信息



    var info_validate = $('#gzxantForm');
    var error = $('.alert-danger', info_validate);
    info_validate.validate({
        errorElement: 'span',
        errorClass: 'error',
        focusInvalid: true,
        rules: {
            name:{
                required: true,
            },
            gender:{
                required: true
            },
            tel:{
                required: false,
                isMobile:true
            },
            status:{
                required: true
            },
            address:{
                required: true
            },
            driveCapability:{
                required: true
            },
            email:{
                required: true,
                email:true
            },
            emergencyContact:{
                required: true
            },
            emergencyContactTel:{
                required: true,
                isMobile:true,
            },
            type:{
                required: true,
            },
            number:{
                required: true
            },
            issueDate:{
                required: false
            },
            validityPeriod:{
                required: true
            },
            attachment:{
                required: true
            },
        },
        messages: {
            // customer part 
            name:"请填写客户姓名",
            gender:"请选择客户性别",
            tel:"请填写客户电话",
            status:"请选择客户状态",
            address:"请填写客户地址",
            driveCapability:"请填写客户准驾类型",
            email:"请填写客户邮箱",
            emergencyContact:"请填写客户的紧急联系人",
            emergencyContactTel:"请填写客户的紧急联系人电话",
            //certificate part 
            type:"请选择证件类型",
            number:"请填写证件号码",
            issueDate:"请填写发证日期",
            validityPeriod:"请填写证件有效截至日期",
            attachment:"请上传客户的证件照片",
        }
    });


    // --------------------------图片上传-------------------------------------------------- //
    Dropzone.autoDiscover = false;
    var myDropzone = new Dropzone("div#mydropzone", {
        url: "/gzxant/web/file/upload/id_img",
        filesizeBase: 1024,//定义字节算法 默认1000
        maxFiles: 2,//最大文件数量
        maxFilesize: 100, //MB
        fallback: function () {
            layer.alert('暂不支持您的浏览器上传!');
        },
        uploadMultiple: false,
        addRemoveLinks: true,
        dictFileTooBig: '您的文件超过' + 100 + 'MB!',
        dictInvalidInputType: '不支持您上传的类型',
        dictMaxFilesExceeded: '您的文件超过1个!',
        init: function () {
            this.on('queuecomplete', function (files) {
                // layer.alert('上传成功');
            });
            this.on('success', function (uploadimfo, result) {
                console.info(result);
                $("#photo").val(result.message[0].s_url);
                $("#imgshowdiv").attr('src', result.message[0].s_url);
                layer.alert('上传成功');
            });
            this.on('error', function (a, errorMessage, result) {
                if (!result) {
                    layer.alert(result.error || '上传失败');
                }
            });
            this.on('maxfilesreached', function () {
                this.removeAllFiles(true);
                layer.alert('文件数量超出限制');
            });
            this.on('removedfile', function () {
                $("#photo").val("${rc.contextPath}${sysUser.photo}");
                $("#imgshowdiv").attr('src', "${rc.contextPath}${sysUser.photo}");
                layer.alert('删除成功');
            });

        }
    });
</script>
