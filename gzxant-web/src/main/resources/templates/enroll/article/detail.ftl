<style>
    .error{
        color:red;
    }
    input[type=file] {
        display: inline;
        margin: 0px;
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
        var $summernote = $('.summernote').summernote({
            toolbar: [
                // [groupName, [list of button]]
                ['style', ['bold', 'italic', 'underline', 'clear']],
                ['font', ['strikethrough', 'superscript', 'subscript']],
                ['fontsize', ['fontsize']],
                ['color', ['color']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['insert', ['link', 'picture', 'hr']]
            ],
            height: 200,
            lang: 'zh-CN',
            focus: true,
            //调用图片上传
            callbacks: {
                onImageUpload: function (files) {
                    sendFile($summernote, files[0]);
                }
            }
        });

        //ajax上传图片
        function sendFile($summernote, file) {
            var filename = false;
            try {
                filename = file['name'];       //获得文件名 头像.jpg
            } catch (e) {
                filename = false;
            }

            var formData = new FormData();
            formData.append("file", file);
            formData.append("key", filename);

            $.ajax({
                url: "/gzxant/enroll/upload",//路径是你控制器中上传图片的方法，下面controller里面我会写到
                type: 'POST',
                data: formData,
                cache: false,
                dataType: 'json',
                contentType: false,
                processData: false,
                success: function (data) {
                    //将图片插入编辑框
                    /* $summernote.summernote('insertImage', data, function ($image) {
                         $image.attr('src', "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1016502073,2105174218&fm=27&gp=0.jpg");
                     });*/
                    // $summernote.summernote('insertImage', "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1016502073,2105174218&fm=27&gp=0.jpg");
                    $summernote.summernote('insertImage', data.message.path + data.message.fileName);
                    //将第一张上传的图片放到image中
                    var $img = $("#image");
                    if ($img.val() == "") {
                        $img.val(data.message.path + data.message.fileName);
                    }
                    // alert($img.val());
                },
            });
        }
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
                                <textarea class="summernote" id="summernote">${enrollArticle.content}</textarea>
                                <input type="hidden" name="content" id="content"/>
                            </div>
                        </div>

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
                        <input type="hidden" name="image" id="image"/>

                        <#if action !='detail'>
                            <div class="form-actions fluid">
                                <div class="col-md-offset-3 col-md-9">
                                    <button type="button" onclick="infoNextStep()" class="btn btn-success">保存</button>
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

    $(function() {
        if (action == "detail") {
            $('#summernote').summernote('disable');
            $("input[name='name']").prop("disabled",true);
            $("input[name='isRelease']").prop("disabled",true);
        }
    });

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
        var title = $("input[name='name']").val();
        if (title == "") {
            layer.alert("标题不能为空");
            return false;
        }
        if (content != "<p><br></p>") {
            $("#content").val(content);
            saveForm();
        } else {
            layer.alert("文章内容不能为空");
            return false;
        }
        // info_validate.form();
    }

    /*var info_validate = $('#gzxantForm');
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
                required: "请输入标题"
            },
            content:{
                required: "请输入文章内容"
            }
        }
    });*/

</script>
