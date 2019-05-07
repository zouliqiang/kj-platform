<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>保单列表--${site.name}</title>
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
    <style type="text/css">
    .laytable-cell-1-10 {
        width: 170px !important;
     }
    </style>
</head>
<body class="childrenBody">
<fieldset class="layui-elem-field">
    <legend>保单检索</legend>
    <div class="layui-field-box">
    <form class="layui-form">
        <div class="layui-inline" style="margin-left: 15px">
            <label>客户姓名:</label>
            <div class="layui-input-inline">
                <input type="text" value="" name="s_customername" id ="customername" placeholder="请输入客户姓名" class="layui-input search_input">
            </div>
        </div>
         <div class="layui-inline" style="margin-left: 15px">
            <label>证件号码:</label>
            <div class="layui-input-inline">
                <input type="text" value="" name="s_certificateNo" id ="certificateNo" placeholder="请输入证件号码" class="layui-input search_input">
            </div>
        </div>
        <div class="layui-inline" style="margin-left: 15px">
            <label>手机号:</label>
            <div class="layui-input-inline">
                <input type="text" value="" name="s_tel" id ="tel" placeholder="请输入手机号" class="layui-input search_input">
            </div>
        </div>
        <div class="layui-inline" style="margin-left: 15px">
            <label>4S店名:</label>
            <div class="layui-input-inline">
                <input type="text" value="" name="s_website" id ="website" placeholder="请输入4S店名" class="layui-input search_input">
            </div>
        </div>
         <div class="layui-inline">
      <label class="layui-form-label">录入时间</label>
      <div class="layui-input-inline">
        <input type="text" class="layui-input" id="test6" name="s_insurance_range" placeholder=" - ">
      </div>
        </div>
        
        <div class="layui-inline">
            <a class="layui-btn" lay-submit="" lay-filter="searchForm">查询</a>
        </div>
        <div class="layui-inline" >
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
        <div class="layui-inline">
            <a class="layui-btn layui-btn-normal" data-type="addSalesSlip">添加保单</a>
        </div>
          <div class="layui-inline">
            <a class="layui-btn layui-btn-warm" data-type="exportSalesSlip">结果导出</a>
        </div>
          <div class="layui-inline">
            <a class="layui-btn layui-btn-danger" data-type="exportSummary">导出汇总单</a>
        </div>
 </form>
    </div>
</fieldset>
<div class="layui-form users_list">
    <table class="layui-table" id="test" lay-filter="demo"></table>
</div>
<div id="page"></div>
<script>
    var baseDir = '${base}';
</script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/tools.js"></script>
<script>
    layui.use(['layer','form','table','laydate'], function() {
        var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form,
                laydate = layui.laydate,
                table = layui.table,
                t;                  //表格数据变量
         //日期范围
        laydate.render({
            type: 'date',
            elem: '#test6' ,
            range: true,
            theme: '#393D49'  //自定义主题颜色
        });
        t = {
            elem: '#test',
            url:'${base}/admin/salessliphistory/historylist',
            method:'post',
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 4, //只显示 1 个连续页码
                first: "首页", //显示首页
                last: "尾页", //显示尾页
                limits:[10,20, 50, 100]
            },
            width: $(parent.window).width()-223,
            cols: [[
                {field:'website', title: '4S店名'},
                {field:'customerName',     title: '客户姓名'},
                {field:'certificateNo',     title:'证件号'},
                {field:'customerMobile',       title: '联系电话'},
                {field:'insuranceStartDate',    title: '保险开始时间',templet:'<div>{{ layui.laytpl.toDateString(d.insuranceStartDate,"yyyy-MM-dd")}}</div>',unresize: true},
                {field:'insuranceEndDate',    title: '保险结束时间',templet:'<div>{{ layui.laytpl.toDateString(d.insuranceEndDate,"yyyy-MM-dd")}}</div>',unresize: true},
                {field:'productNo',       title: '产品SN码'},
                {field:'editCount',    title: '编辑次数'},
                {field:'name',    title: '录入人'},
                {field:'createDate',  title: '录入时间',templet:'<div>{{ layui.laytpl.toDateString(d.createDate)}}</div>',unresize: true}, //单元格内容水平居中
            ]]
        };
        table.render(t);
        

        $('.layui-inline .layui-btn').on('click', function(){
            var type = $(this).data('type');
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