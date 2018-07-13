<div class="wrapper wrapper-content animated fadeInRight" id="app">
    <div class="col-sm-12">
        <div class="ibox">
            <div class="ibox-body">
                <div class="bootstrap-table">
                    <div class="fixed-table-toolbar">
                        <div class="bars pull-left">
                            <div id="exampleToolbar" role="group">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">查询条件</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">

                                            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group">
                                                <label for="nameInput" class="control-label">登录名：</label>
                                                <input type="text" class="form-filter form-control _search" id="nameInput" name="search_eq_login_name">
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group">
                                                <label for="nameInput" class="control-label">工号：</label>
                                                <input type="text" class="form-filter form-control _search" id="nameInput" name="search_eq_no">
                                            </div>
                                            <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group">
                                                <label for="nameInput" class="control-label">姓名：</label>
                                                <input type="text" class="form-filter form-control _search" id="nameInput" name="search_like_name">
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
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="fixed-table-container" style="padding-bottom: 0px;">
                        <div class="fixed-table-header" style="display: none;">
                            <table></table>
                        </div>
                        <div class="fixed-table-body">
                            <div class="fixed-table-loading" style="top: 0px; display: none;">正在努力地加载数据中，请稍候……</div>
                            <table class="table table-hover table-striped" id="exampleTable" data-mobile-responsive="true" style="margin-top: 0px;">
                                <thead style="display: none;">
                                <tr>
                                    <th class="bs-checkbox " style="width: 36px; " data-field="0" tabindex="0">
                                        <div class="th-inner ">
                                            <input name="btSelectAll" type="checkbox">
                                        </div>
                                        <div class="fht-cell"></div>
                                    </th>
                                    <th style="" data-field="id" tabindex="0">
                                        <div class="th-inner ">id</div>
                                        <div class="fht-cell"></div>
                                    </th>
                                    <th style="" data-field="name" tabindex="0">
                                        <div class="th-inner ">姓名</div>
                                        <div class="fht-cell"></div>
                                    </th>
                                    <th style="text-align: center; " data-field="id" tabindex="0">
                                        <div class="th-inner ">操作</div>
                                        <div class="fht-cell"></div>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr v-for="customer in customers">
                                    <td colspan="12">
                                        <div class="card-view">
                                            <input data-index="0" name="btSelectItem" type="checkbox">
                                        </div>
                                        <div class="card-view">
                                            <span class="title" style="">id</span>
                                            <span class="value">{{customer.id}}</span>
                                        </div>
                                        <div class="card-view">
                                            <span class="title" style="">姓名</span>
                                            <span class="value">{{customer.name}}</span>
                                        </div>
                                        <div class="card-view">
                                            <span class="title" style="text-align: center; ">id</span>
                                            <span class="value">
                            <a class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="dt_update('###TODO')">
                              <i class="fa fa-edit"></i>
                            </a>
                            <a class="btn btn-success btn-sm" href="#" title="详情" mce_href="#" onclick="dt_detail('###TODO')">
                              <i class="fa fa-info-circle"></i>
                            </a>
                            <a class="btn btn-warning btn-sm" href="#" title="删除" mce_href="#" onclick="remove('###TODO')">
                              <i class="fa fa-remove"></i>
                            </a>
                          </span>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="fixed-table-footer" style="display: none;">
                            <table>
                                <tbody>
                                <tr></tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="fixed-table-pagination" style="display: block;">
                            <div class="pull-left pagination-detail">
                                <span class="pagination-info">显示第 1 到第 2 条记录，总共 2 条记录</span>
                                <span class="page-list" style="display: none;">每页显示
                  <span class="btn-group dropup">
                    <button type="button" class="btn btn-default  btn-outline dropdown-toggle" data-toggle="dropdown">
                      <span class="page-size">10</span>
                      <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                      <li class="active">
                        <a href="javascript:void(0)">10</a>
                      </li>
                    </ul>
                  </span> 条记录</span>
                            </div>
                            <div class="pull-right pagination" style="display: none;">
                                <ul class="pagination pagination-outline">
                                    <li class="page-first disabled">
                                        <a href="javascript:void(0)">«</a>
                                    </li>
                                    <li class="page-pre disabled">
                                        <a href="javascript:void(0)">‹</a>
                                    </li>
                                    <li class="page-number active">
                                        <a href="javascript:void(0)">1</a>
                                    </li>
                                    <li class="page-next disabled">
                                        <a href="javascript:void(0)">›</a>
                                    </li>
                                    <li class="page-last disabled">
                                        <a href="javascript:void(0)">»</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</div>

<!-- vue -->
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<!-- <script src="https://unpkg.com/vue/"></script> -->
<!-- script to render the page -->
<script>
    var app = new Vue({
        el: '#app',
        data: {
            customers: [],
        },
        created() {
            // todo some need some wrapper of fetch
            fetch("http://localhost:8081/gzxant/web/customer/info/customer", {
                credentials: 'include'
            })
                    .then(response => response.json())
        .then(
                    json => {
                this.customers = json.customers
        }
        )
        }
    })
</script>

