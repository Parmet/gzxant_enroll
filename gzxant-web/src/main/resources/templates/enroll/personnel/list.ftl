<div class="wrapper wrapper-content animated fadeInRight">
        <div class="col-sm-12">
            <div class="ibox">
                <div class="ibox-body">
                    <div id="exampleToolbar" role="group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">查询条件</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 form-group">
                                        <label for="nameInput" class="control-label">姓名：</label>
                                        <input type="text" class="form-filter form-control _search"  name="search_like_name"  />
                                    </div>

                                    <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 form-group">
                                        <label for="type_input" class="control-label">演唱风格：&nbsp;</label>
                                        <input type="text" class="form-filter form-control _search"  name="search_like_style"  />
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer">
                                <button type="button" class="btn btn-success" onclick="re_load()">
                                    <i class="fa fa-search" aria-hidden="true"></i> 查询
                                </button>
                                <button type="button" class="btn btn-primary" onclick="reset()">
                                    <i class="fa fa-circle-thin" aria-hidden="true"></i> 重置
                                </button>
                                <button type="button" class="btn btn-danger" onclick="batch_remove()">
                                    <i class="fa fa-trash" aria-hidden="true"></i> 删除
                                </button>
                                <button type="button" class="btn btn-info" onclick="dt_insert()">
                                    <i class="fa fa-plus-square" aria-hidden="true"></i> 添加
                                </button>
                                <button type="button" class="btn btn-info" onclick="dt_explort_buttont()">
                                    <i class="fa fa-plus-square" aria-hidden="true"></i> 导出
                                </button>
                            </div>
                        </div>
                    </div>

                    <table class="table" id="exampleTable" data-mobile-responsive="true">
                    </table>
                </div>
            </div>
        </div>
</div>

<script type="text/javascript">

    function getcolumns() {
        var c = [
            {
                checkbox: true
            },
            {
                field: 'numbers',
                title: '编号'
            }
            ,
            {
                field: 'name',
                title: '姓名'
            },
            {
                field: 'phone',
                title: '手机号码'
            },
            {
                field: 'idCard',
                title: '身份证号'
            },
            {
                field: 'place',
                title: '报名地点'
            },
            {
                field: 'company',
                title: '工作单位'
            },
            {
                field: 'profession',
                title: '职业'
            },
            {
                field: 'style',
                title: '演唱风格'
            },
            {
                title: '操作',
                field: 'id',
                align: 'center',
                formatter: function (value, row, index) {

                    return dt_edit_button(row)+dt_detail_button(row)+dt_delete_button(row);
                }
            }];

        return c;
    }

    load_data( getcolumns(), {"createDate": "desc"});

    function dt_explort_buttont() {
        location.href=url + "excel";
    }
</script>