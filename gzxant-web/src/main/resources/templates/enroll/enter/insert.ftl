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
                            <label class="col-sm-3 control-label">参赛编号：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="numbers"
                                       value="" placeholder="参赛编号"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">姓名：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="name"
                                       value="" placeholder="请输入姓名"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">类型：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <select name="type" class="form-control">
                                    <option value="">请选择</option>
                                            <#list enterType as type>
		  									<option value="${type.jvalue}">${type.jvalue}</option>
                                            </#list>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">分数：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="fraction"
                                       value="" placeholder="请输入分数"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">歌曲：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="song"
                                       value="" placeholder="请输入歌曲"/>
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
                            <label class="col-sm-3 control-label">是否成功：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <select name="state" class="form-control">
                                    <option value="">请选择</option>
                                    <option value="Y">成功</option>
                                    <option value="N">失败</option>
                                </select>
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


        // // 手机号码验证
        // $.validator.addMethod("isMobile", function(value, element) {
        //         var length = value.length;
        //     var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
        //          return this.optional(element) || (length == 11 && mobile.test(value));
        //     }, "请正确填写您的手机号码");


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
            numbers:{
                required: true,
                remote: '${rc.contextPath}/enter/check/'
            },
            name: {
                required: true
            },
            type:{
                required: true
            },
            fraction:{
                required: true
            },
            song:{
                required: true
            },
            state:{
                required: true
            },
            place:{
                required: true
            }
        },
        messages: {
            numbers:{
                required:  "请输入编号",
                remote:  "还没有改参赛人员，请选添加"
            },
            name:{
                required:  "请输入登录名"
            },
            place:{
                required: "请输入报名地点"
            },
            type:{
                required: "请选择类型"
            },
            fraction:{
                required: "请输入分数"
            },
            state:{
                equired: "请选择状态"
            },
            song:{
                equired: "请输入歌曲"
            }


        }
    });



</script>
