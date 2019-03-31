<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>保单号分配列表--${site.name}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <meta name="description" content="${site.description}"/>
    <meta name="keywords" content="${site.keywords}"/>
    <meta name="author" content="${site.author}"/>
    <link rel="icon" href="${site.logo}">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
    <link rel="stylesheet" href="${base}/static/css/user.css" media="all" />
</head>
<body class="childrenBody">
<fieldset class="layui-elem-field">
    <legend>保单分配检索</legend>
    <div class="layui-field-box">
    <form class="layui-form">
        <div class="layui-inline" style="margin-left: 15px">
            <label>保单号:</label>
            <div class="layui-input-inline">
                <input type="text" value="" name="s_policyno" id="policyno" placeholder="请输入保单号" class="layui-input search_input">
            </div>
        </div>
        <div class="layui-inline" style="margin-left: 15px">
            <label>用户名:</label>
            <div class="layui-input-inline">
                <input type="text" value="" name="s_userName" id="userName" placeholder="请输入用户名" class="layui-input search_input">
            </div>
        </div>
        <div class="layui-inline">
            <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
        </div>
        <div class="layui-inline" >
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
        <div class="layui-inline">
            <a class="layui-btn layui-btn-normal" data-type="policyNoUser">分配保单号</a>
        </div>
 </form>
    </div>
</fieldset>
<div class="layui-form users_list">
    <table class="layui-table" id="test" lay-filter="demo"></table>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    </script>
</div>
<div id="page"></div>
<script>
    var baseDir = '${base}';
</script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script>
    layui.use(['layer','form','table'], function() {
        var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form,
                table = layui.table,
                t;                  //表格数据变量

        t = {
            elem: '#test',
            url:'${base}/admin/policynouser/list',
            method:'post',
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 4, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits:[10,20, 50, 100]
            },
            cellMinWidth: 80,
            cols: [[
                {type:'checkbox'},
                {field:'policyNo', title: '保单号'},
                {field:'userName',  title: '用户名'},
                {field:'allocationNumber',     title: 'NO.已分配数量'},
                {field:'useNumber',       title: '创建保单数量'},
                {field:'createDate',  title: '创建时间',templet:'<div>{{ layui.laytpl.toDateString(d.createDate)}}</div>',unresize: true}, //单元格内容水平居中
                /* {fixed: 'right', title: '操作',width: '10%',align: 'center',toolbar: '#barDemo'} */
            ]]
        };
        table.render(t);

        //监听工具条
        table.on('tool(demo)', function(obj){
        	var data = obj.data;
            if(obj.event === 'edit'){
                var editIndex = layer.open({
                    title : "编辑保单号",
                    type : 2,
                    content : "${base}/admin/policyno/edit?id="+parseInt(data.id),
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击此处返回保单号设置列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    }
                });
          }
        });
        
        var activeAdd={
        		policyNoUser : function(){
                    var addIndex = layer.open({
                        title : "选择用户",
                        type : 2,
                        content : "${base}/admin/policynouser/add",
                        success : function(layero, addIndex){
                            setTimeout(function(){
                                layer.tips('点击此处返回保单分配列表', '.layui-layer-setwin .layui-layer-close', {
                                    tips: 3
                                });
                            },500);
                        }
                    });
                    //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                    $(window).resize(function(){
                        layer.full(addIndex);
                    });
                    layer.full(addIndex); 	
                }
            };

        $('.layui-inline .layui-btn').on('click', function(){
            var type = $(this).data('type');
            activeAdd[type] ? activeAdd[type].call(this) : '';
        });

        //搜索
        form.on("submit(searchForm)",function(data){
            t.where = data.field;
            table.reload('test', t);
            return false;
        });
        

    });
</script>
</body>
</html>