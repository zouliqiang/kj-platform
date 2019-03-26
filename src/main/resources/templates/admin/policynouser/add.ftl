<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>保单添加--${site.name}</title>
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
    <style type="text/css">
        .layui-form-item .layui-inline{ width:33.333%; float:left; margin-right:0; }
        @media(max-width:1240px){
            .layui-form-item .layui-inline{ width:100%; float:none; }
        }
        .layui-form-item .role-box {
            position: relative;
        }
        .layui-form-item .role-box .jq-role-inline {
            height: 100%;
            overflow: auto;
        }

    </style>
</head>
<body class="childrenBody">
<fieldset class="layui-elem-field">
    <legend>用户检索</legend>
    <div class="layui-field-box">
    <form class="layui-form">
        <div class="layui-inline" style="margin-left: 15px">
            <label>用户名:</label>
            <div class="layui-input-inline">
                <input type="text" value="" name="s_loginName" id="loginName" placeholder="请输入用户名" class="layui-input search_input">
            </div>
        </div>
        <div class="layui-inline" style="margin-left: 15px">
            <label>手机号:</label>
            <div class="layui-input-inline">
                <input type="text" value="" name="s_tel" id="tel" placeholder="请输入手机号" class="layui-input search_input">
            </div>
        </div>
        <div class="layui-inline">
            <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
        </div>
        <div class="layui-inline" >
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
 </form>
 </div>
</fieldset>
<div class="layui-form users_list">
    <table class="layui-table" id="test" lay-filter="demo"></table>
    <script type="text/html" id="barDemo">
        <a class="layui-btn  layui-btn-xs layui-btn-normal" lay-event="add">分配保单</a>
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
            url:'${base}/admin/policynouser/userlist',
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
                {field:'loginName', title: '用户名'},
                {field:'nickName',  title: '昵称'},
                {field:'tel',     title: '手机号'},
                {field:'email',       title: '邮箱'},
                {field:'createDate',  title: '创建时间',width: '13%',templet:'<div>{{ layui.laytpl.toDateString(d.createDate)}}</div>'}, //单元格内容水平居中
                {fixed: 'right', title:'操作',  width: '15%', toolbar: '#barDemo'}
            ]]
        };
        table.render(t);

        //监听工具条
        table.on('tool(demo)', function(obj){
        	var data = obj.data;
            if(obj.event === 'add'){
                var editIndex = layer.open({
                    title : "分配保单",
                    type : 2,
                    area: ['600px', '400px'],
                    content : "${base}/admin/policynouser/distribution?id="+parseInt(data.id),
                    success : function(layero, index){
                        setTimeout(function(){
                            layer.tips('点击此处返回分配保单', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    }
                });
          }
        });
        

        $('.layui-inline .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
            activeAdd[type] ? activeAdd[type].call(this) : '';
            activeExport[type] ? activeExport[type].call(this) : '';
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