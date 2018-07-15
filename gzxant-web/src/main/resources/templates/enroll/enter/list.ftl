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
                                        <label for="type_input" class="control-label">参赛者状态 ：&nbsp;</label>
                                        <select id="type_input" name="search_like_type" class="form-control _search" style="width:100%;">
                                            <option value="">请选择</option>
		  								<#list enterType as type>
		  									<option value="${type.jvalue}">${type.jvalue}</option>
                                        </#list>
                                        </select>
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
                                <button type="button" class="btn btn-info" onclick="dt_import()">
                                    <i class="fa fa-plus-square" aria-hidden="true"></i> 导入
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
            },
            {
                field: 'name',
                title: '姓名',
                width: '100px'
            },

            {
                field: 'fraction',
                title: '分数',
                width: '100px'
            },
            {
                field: 'song',
                title: '歌曲',
                width: '100px'
            },
            {
                field: 'place',
                title: '报名地点',
            },
            {
                field: 'type',
                title: '类型'
            },
            {
                field: 'state',
                title: '状态',
                formatter : function(value, row, index) {
                    if (value == 'Y') {
                        return '<span class="label label-primary">成功</span>';
                    } else if (value == 'N') {
                        return '<span class="label label-danger">失败</span>';
                    }
                }
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