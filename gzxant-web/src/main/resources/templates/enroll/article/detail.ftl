<style>
    .error{
        color:red;
    }
    input[type=file] {
        display: inline;
        margin: 3px 0px 10px 16px;
    }
    #isReleaseLabel {
        margin-bottom: 0px;
        margin-top: 3px;
    }

    .m{ width: 800px; margin-left: auto; margin-right: auto; }

</style>

<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
<link href="${rc.contextPath}/plugins/summernote/dist/summernote.css" rel="stylesheet"/>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="http://www.jq22.com/jquery/bootstrap-3.3.4.js"></script>
<script src="${rc.contextPath}/plugins/summernote/dist/summernote.js"></script>
<script src="${rc.contextPath}/plugins/summernote/dist/lang/summernote-zh-CN.js"></script>

<script>
    $(function(){
        $('.summernote').summernote({
            height: 200,
            tabsize: 2,
            lang: 'zh-CN'
        });
    });
</script>

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins" >
                <div class="">
                    <form class="form-horizontal form-bordered" id="gzxantForm">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">标题：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="name"
                                       value="${enrollArticle.name}" placeholder="请输入标题"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">文章内容：<span class="required">*</span></label>
                            <div class="m col-sm-8">
                                <div class="summernote" id="summernote">${enrollArticle.content}</div>
                            </div>
                        </div>
                        <input type="hidden" name="content" id="content"/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">是否发布：<span class="required">*</span></label>
                            <label id="isReleaseLabel" class="col-sm-3">
                                <#if enrollArticle.isRelease == 1>
                                    是<input type="radio" name="isRelease" value="1" checked/>&nbsp;&nbsp;&nbsp;
                                    否<input type="radio" name="isRelease" value="0"/>
                                <#else>
                                    是<input type="radio" name="isRelease" value="1" />&nbsp;&nbsp;&nbsp;
                                    否<input type="radio" name="isRelease" value="0" checked/>
                                </#if>
                            </label>
                        </div>

                        <input type="hidden" name="id" value="${enrollArticle.id}"/>

                        <#if action !='detail'>
                            <div class="form-actions fluid">
                                <div class="col-md-offset-3 col-md-9">
                                    <button type="submit" onclick="infoNextStep()" class="btn btn-success">保存</button>
                                </div>
                            </div>
                        </#if>
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

    /**
     * 错误图片的默认处理
     */
    function errimg() {
        $("#image").val("${rc.contextPath}/img/log9.png");
        $("#imgshowdiv").attr('src', "${rc.contextPath}/img/log9.png");
    }

    /**
     * 删除头像
     */
    function doDeleteImg() {
        var name = $("#image").val();
        layer.confirm('确定要删除头像吗？', {
            btn: ['确定', '取消']
        }, function () {
            $.ajax({
                url: url + "delete/photo",
                type: "POST",
                data: {
                    'name': name
                },
                success: function (r) {
                    if (r.code == 200) {
                        errimg();

                    } else {
                        layer.msg(r.error);
                    }
                }
            });
        })

    }

    function infoNextStep() {
        //summernote取值
        var content = $("#summernote").summernote('code');
        $("#content").val(content);
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
                required: true
            },
            content:{
                required: true
            }
        },
        messages: {
            name:{
                required:  "请输入标题"
            },
            content:{
                required: "请输入文章内容"
            }
        }
    });

</script>
