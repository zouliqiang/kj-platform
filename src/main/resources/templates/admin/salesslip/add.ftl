<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>定时任务添加--${site.name}</title>
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
<form class="layui-form" style="width:70%;">
    <div class="layui-form-item">
        <label class="layui-form-label">客户类型</label>
        <div class="layui-input-block">
                <input type="radio" name="customerType" lay-filter="customerType" checked value="1" title="个人客户"/>
                <input type="radio" name="customerType" lay-filter="customerType"  value="2" title="企业客户"/>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
        <label class="layui-form-label">客户名称</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" maxlength="50" name="customerName" lay-verify="required" placeholder="请输入客户名称">
        </div>
        </div>
         <div class="layui-inline">
        <label class="layui-form-label"><span id="certificate">身份证号码</span></label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="certificateNo" maxlength="50" id="certificateNo" lay-verify="required|identity"  placeholder="请输入身份证号">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="customerMobile" maxlength="50" lay-verify="required|phone"  placeholder="请输入联系电话">
        </div>
    </div>
    </div>
    
     <div class="layui-form-item">
     <div class="layui-inline">
        <label class="layui-form-label">通讯地址</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="customerAddress" maxlength="200" lay-verify="required"  placeholder="请输入通信地址">
        </div>
        </div>
         <div class="layui-inline">
        <label class="layui-form-label">车品牌</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="vehicleBrand" maxlength="100" lay-verify="required"  placeholder="请输入车品牌">
        </div>
    </div>
      <div class="layui-inline">
        <label class="layui-form-label">车牌号</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="licencePlateNo" maxlength="50" lay-verify="required"  placeholder="请输入车牌号">
        </div>
    </div>
    </div>
    
     <div class="layui-form-item">
     <div class="layui-inline">
        <label class="layui-form-label">车架号</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="vehicleFrameNo" maxlength="100" lay-verify="required"   placeholder="请输入车架号">
        </div>
        </div>
        <div class="layui-inline">
        <label class="layui-form-label">发动机号</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="engineFrameNo"  maxlength="100" lay-verify="required"  placeholder="请输入发动机号">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">登记日期</label>
        <div class="layui-input-block">
            <input type="text" name="registrationDate" id="registrationDate"   maxlength="20" lay-verify="required"  lay-verify="date" placeholder="请选择登记日期" autocomplete="on" class="layui-input">
        </div>
    </div>
    </div>
     
     <div class="layui-form-item">
     <div class="layui-inline">
        <label class="layui-form-label">初始车价格（元）</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="vehiclePrice"  maxlength="20" lay-verify="required|number"  placeholder="初始车价格">
        </div>
        </div>
         <div class="layui-inline">
        <label class="layui-form-label">安装日期</label>
        <div class="layui-input-block">
            <input type="text" name="installDate" id="installDate"   maxlength="20" lay-verify="required"  lay-verify="date" placeholder="请选择安装日期" autocomplete="on" class="layui-input">
        </div>
    </div>
        <div class="layui-inline">
        <label class="layui-form-label">安装地点</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="installAddress" maxlength="200" lay-verify="required"  placeholder="请输入安装地点">
        </div>
    </div>
    </div>
    
        <div class="layui-form-item">
        <div class="layui-inline">
        <label class="layui-form-label">安装人</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="installser" lay-verify="required" maxlength="50"  placeholder="请输入安装人">
        </div>
        </div>
        <div class="layui-inline">
        <label class="layui-form-label">产品SN码</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="productNo" lay-verify="required"  maxlength="50" placeholder="请输入产品SN码">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">保险期限</label>
        <div class="layui-input-block">
            <select name="insuranceTerm" lay-verify="required" >
                <option value="">请选择保险期限</option>
                <option value="1">1年</option>
                <option value="2">2年</option>
                <option value="3">3年</option>
            </select>
        </div>
    </div>
    </div>
       <div class="layui-form-item">
       <div class="layui-inline">
        <label class="layui-form-label">保险开始时间</label>
        <div class="layui-input-block">
            <input type="text" name="insuranceStartDate" id="insuranceStartDate" maxlength="20"  lay-verify="required"  lay-verify="date" placeholder="请选择保险开始时间" autocomplete="on" class="layui-input">
        </div>
        </div>
          <div class="layui-inline">
        <label class="layui-form-label">赔偿限额（元）</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="compensatePrice" maxlength="20" lay-verify="required|number"  placeholder="请输入赔偿限额">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">第一受益人</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="firstBeneficiary" maxlength="200" lay-verify="required"  placeholder="第一受益人">
        </div>
    </div>
    </div>
      
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="addSalesSlip">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form','jquery','layer','laydate'],function(){
        var form      = layui.form,
                $     = layui.jquery,
                laydate = layui.laydate,
                layer = layui.layer;
        
        //初始赋值
        laydate.render({
            elem: '#installDate',
            value: ''
        });
        //初始赋值
        laydate.render({
            elem: '#insuranceStartDate',
            value: ''
        });
        
        //初始赋值
        laydate.render({
            elem: '#registrationDate',
            value: ''
        });
        
        form.on('radio(customerType)', function(data){
            if(data.value === "1"){
                $("#certificate").text("身份证号");
                $("#certificateNo").attr('placeholder',"请输入身份证号");
                $("#certificateNo").attr('lay-verify',"required|identity");
            }
            if(data.value === "2"){
                $("#certificate").text("组织机构代码");
                $("#certificateNo").attr('placeholder',"请输入组织机构代码");
                $("#certificateNo").attr('lay-verify',"required");
            }
        });
        
        form.on("submit(addSalesSlip)",function(data){
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
           
            $.ajax({
                url:"${base}/admin/salesslip/add",
                type:"POST",
                data:JSON.stringify(data.field),
                contentType:"application/json; charset=utf-8",
                dataType:"json",
                success: function(res){
                    layer.close(loadIndex);
                    if(res.success){
                        parent.layer.msg("保单录入成功！",{time:1000},function(){
                            parent.layer.close(parent.addIndex);
                            //刷新父页面
                            parent.location.reload();
                        });
                    }else{
                        layer.msg(res.message);
                    }
                }
            });
            return false;
        });

    });
</script>
</body>
</html>
