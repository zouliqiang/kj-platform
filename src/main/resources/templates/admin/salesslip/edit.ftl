<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>保单编辑--${site.name}</title>
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
<input type="hidden" name="id" value="${salesSlip.id}"/>
      <div class="layui-form-item">
     <div class="layui-form-item">
        <label class="layui-form-label">客户类型</label>
        <div class="layui-input-block">
        <#if salesSlip.customerType == 1>
        <input type="radio" name="customerType" lay-filter="customerType" checked value="1" title="个人客户"/>
                <input type="radio" name="customerType" lay-filter="customerType"  value="2" title="企业客户"/>
        <#else>
        <input type="radio" name="customerType" lay-filter="customerType"  value="1" title="个人客户"/>
                <input type="radio" name="customerType" lay-filter="customerType" checked value="2" title="企业客户"/>
        </#if>
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">客户名称</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" maxlength="50" name="customerName" value="${salesSlip.customerName}"  lay-verify="required">
        </div>
     </div>  
      <div class="layui-inline">
        <label class="layui-form-label"><span id="certificate">身份证号码</span></label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="certificateNo" maxlength="50" value="${salesSlip.certificateNo}" id="certificateNo"  lay-verify="required|identity">
        </div>
    </div>
     <div class="layui-inline">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="customerMobile" maxlength="50" value="${salesSlip.customerMobile}"  lay-verify="required|phone">
        </div>
        </div>
    </div>
    <div class="layui-form-item">
     <div class="layui-inline">
        <label class="layui-form-label">通讯地址</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="customerAddress" maxlength="200"  value="${salesSlip.customerAddress}"  lay-verify="required">
        </div>
    </div>
     <div class="layui-inline">
        <label class="layui-form-label">车品牌</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="vehicleBrand" maxlength="100" value="${salesSlip.vehicleBrand}"  lay-verify="required">
        </div>
    </div>
      <div class="layui-inline">
        <label class="layui-form-label">车牌号</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="licencePlateNo" maxlength="50" value="${salesSlip.licencePlateNo}"   lay-verify="required">
        </div>
        </div>
    </div>
    
     <div class="layui-form-item">
         <div class="layui-inline">
        <label class="layui-form-label">车架号</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="vehicleFrameNo" maxlength="100" value="${salesSlip.vehicleFrameNo}"  lay-verify="required">
        </div>
    </div>
       <div class="layui-inline">
        <label class="layui-form-label">发动机号</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="engineFrameNo"  maxlength="100" value="${salesSlip.engineFrameNo}"   lay-verify="required">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">登记日期</label>
        <div class="layui-input-block">
            <input type="text" name="registrationDate" id="registrationDate"   maxlength="20" value="${salesSlip.registrationDate?string('yyyy-MM-dd')}" lay-verify="required"   class="layui-input">
        </div>
        </div>
    </div>
    
    <div class="layui-form-item">
        <div class="layui-inline">
        <label class="layui-form-label">初始车价格（元）</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="vehiclePrice"  maxlength="20" value="${salesSlip.vehiclePrice}"  lay-verify="required|number">
        </div>
       </div>
    <div class="layui-inline">
        <label class="layui-form-label">安装日期</label>
        <div class="layui-input-block">
            <input type="text" name="installDate"  class="layui-input" id="installDate"   maxlength="20" value="${salesSlip.installDate?string('yyyy-MM-dd')}"  lay-verify="required" >
        </div>
    </div>
     <div class="layui-inline">
        <label class="layui-form-label">安装地点</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="installAddress" maxlength="200" value="${salesSlip.installAddress}"  lay-verify="required">
        </div>
        </div>
    </div>
        <div class="layui-form-item">
         <div class="layui-inline">
        <label class="layui-form-label">安装人</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="installser"  lay-verify="required" value="${salesSlip.installser}" maxlength="50">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">产品SN码</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="productNo"  lay-verify="required" value="${salesSlip.productNo}"  maxlength="50">
        </div>
    </div>
     <div class="layui-inline">
        <label class="layui-form-label">保险期限(年)</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="insuranceTerm"  lay-verify="required" value="${salesSlip.insuranceTerm}"  maxlength="50">
        </div>
    </div>
       <div class="layui-form-item">
        </div>
         <div class="layui-inline">
        <label class="layui-form-label">保险开始时间</label>
        <div class="layui-input-block">
            <input type="text"  class="layui-input" name="insuranceStartDate" id="insuranceStartDate"  value="${salesSlip.insuranceStartDate?string('yyyy-MM-dd')}" maxlength="20"   lay-verify="required"  autocomplete="on">
        </div>
        </div>
       <div class="layui-inline">
        <label class="layui-form-label">赔偿限额（元）</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="compensatePrice" maxlength="20" value="${salesSlip.compensatePrice}"  lay-verify="required|number">
        </div>
    </div>
     <div class="layui-inline">
        <label class="layui-form-label">第一受益人</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="firstBeneficiary" maxlength="200" value="${salesSlip.firstBeneficiary}"  lay-verify="required">
        </div>
    </div>
    </div>
        <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="editSalesSlip">保存</button>
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
        
        form.on("submit(editSalesSlip)",function(data){
            var loadIndex = layer.load(2, {
                shade: [0.3, '#333']
            });
           
            $.ajax({
                url:"${base}/admin/salesslip/edit",
                type:"POST",
                data:JSON.stringify(data.field),
                contentType:"application/json; charset=utf-8",
                dataType:"json",
                success: function(res){
                    layer.close(loadIndex);
                    if(res.success){
                        parent.layer.msg("保单编辑成功！",{time:1000},function(){
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
