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
                        <input type="hidden" name="image" id="image"/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">标题：<span class="required">*</span></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="name"
                                       value="${enrollArticle.name}" placeholder="请输入标题"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">主图</label>
                            <div class="col-sm-2">
                                <div id="localImag" style="margin-left:15px;">
                                    <div class="img_box" id="imgBox">
                                        <img id="imgshowdiv" style="width: 60px" src=""
                                             onerror="javascript:errimg()" class="img_file img-rounded"/>
                                    </div>
                                </div>
                            </div>
                            <#if action !='detail'>
                            <script src="${rc.contextPath}/js/plugins/dropzone/dropzone.min.js"></script>
                            <link href="${rc.contextPath}/css/plugins/dropzone/dropzone.css" rel="stylesheet">
                            <div class="col-sm-4">
                                <div id="mydropzone" class="dropzone"></div>
                            </div>
                            <script type="text/javascript">
                                // --------------------------图片上传-------------------------------------------------- //
                                Dropzone.autoDiscover = false;
                                var myDropzone = new Dropzone("div#mydropzone", {
                                    url: base_url + "/file/upload/article",
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
                                            var img_url = result.message[0].url.split("/var/file/")[1];
                                            $("#image").val(img_url);
                                            $("#imgshowdiv").attr('src', "http://file.sitofang.top/" + img_url);
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
                                            $("#image").val("${enrollArticle.image}");
                                            $("#imgshowdiv").attr('src', "http://file.sitofang.top/${enrollArticle.image}");
                                            layer.alert('删除成功');
                                        });

                                    }
                                });
                            </script>
                            </#if>
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
        $("#image").val("/img/log9.png");
        $("#imgshowdiv").attr('src', "http://file.sitofang.top/img/log9.png");
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
                required:  "请输入标题"
            },
            content:{
                required: "请输入文章内容"
            }
        }
    });*/

</script>
