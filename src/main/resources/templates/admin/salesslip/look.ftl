<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>查看保单--${site.name}</title>
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
    <div class="layui-inline">
        <label class="layui-form-label">客户名称</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" maxlength="50" name="customerName" value="${salesSlip.customerName}" readonly="readonly"  lay-verify="required">
        </div>
     </div>  
       <div class="layui-inline">
        <label class="layui-form-label">客户类型</label>
        <div class="layui-input-block">
           <#if salesSlip.customerName==1>
            <input  type="text"  class="layui-input" maxlength="50" name="customerType" value="个人客户" readonly="readonly" lay-verify="required">
            <#else>
            <input  type="text"  class="layui-input" maxlength="50" name="customerType" value="企业客户" readonly="readonly" lay-verify="required">
            </#if>
        </div>
    </div>
      <div class="layui-inline">
        <label class="layui-form-label"><span id="certificate">身份证号码</span></label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="certificateNo" maxlength="50" value="${salesSlip.certificateNo}" id="certificateNo" readonly="readonly"  lay-verify="required|identity">
        </div>
    </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="customerMobile" maxlength="50" value="${salesSlip.customerMobile}" readonly="readonly"  lay-verify="required|phone">
        </div>
        </div>
     <div class="layui-inline">
        <label class="layui-form-label">通讯地址</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="customerAddress" maxlength="200"  value="${salesSlip.customerAddress}" readonly="readonly"  lay-verify="required">
        </div>
    </div>
     <div class="layui-inline">
        <label class="layui-form-label">车品牌</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="vehicleBrand" maxlength="100" value="${salesSlip.vehicleBrand}" readonly="readonly"  lay-verify="required">
        </div>
    </div>
    </div>
    
     <div class="layui-form-item">
       <div class="layui-inline">
        <label class="layui-form-label">车牌号</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="licencePlateNo" maxlength="50" value="${salesSlip.licencePlateNo}"  readonly="readonly"  lay-verify="required">
        </div>
        </div>
         <div class="layui-inline">
        <label class="layui-form-label">车架号</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="vehicleFrameNo" maxlength="100" value="${salesSlip.vehicleFrameNo}" readonly="readonly"  lay-verify="required">
        </div>
    </div>
       <div class="layui-inline">
        <label class="layui-form-label">发动机号</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="engineFrameNo"  maxlength="100" value="${salesSlip.engineFrameNo}"  readonly="readonly"  lay-verify="required">
        </div>
    </div>
    </div>
    
    <div class="layui-form-item">
     <div class="layui-inline">
        <label class="layui-form-label">登记日期</label>
        <div class="layui-input-block">
            <input type="text" name="registrationDate" id="registrationDate"   maxlength="20" value="${salesSlip.registrationDate?string('yyyy-MM-dd')}" lay-verify="required"  readonly="readonly"  class="layui-input">
        </div>
        </div>
        <div class="layui-inline">
        <label class="layui-form-label">初始车价格（元）</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="vehiclePrice"  maxlength="20" value="${salesSlip.vehiclePrice}" readonly="readonly"  lay-verify="required|number">
        </div>
       </div>
    <div class="layui-inline">
        <label class="layui-form-label">安装日期</label>
        <div class="layui-input-block">
            <input type="text" name="installDate"  class="layui-input" id="installDate"   maxlength="20" value="${salesSlip.installDate?string('yyyy-MM-dd')}" readonly="readonly"  lay-verify="required" >
        </div>
    </div>
    </div>
        <div class="layui-form-item">
        <div class="layui-inline">
        <label class="layui-form-label">安装地点</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="installAddress" maxlength="200" value="${salesSlip.installAddress}" readonly="readonly"  lay-verify="required">
        </div>
        </div>
         <div class="layui-inline">
        <label class="layui-form-label">安装人</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="installser" readonly="readonly"  lay-verify="required" value="${salesSlip.installser}" maxlength="50">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">产品SN码</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="productNo" readonly="readonly"  lay-verify="required" value="${salesSlip.productNo}"  maxlength="50">
        </div>
    </div>
    </div>
       <div class="layui-form-item">
           <div class="layui-inline">
        <label class="layui-form-label">保险期限</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="productNo" readonly="readonly"  lay-verify="required" value="${salesSlip.insuranceTerm}年"  maxlength="50">
        </div>
        </div>
         <div class="layui-inline">
        <label class="layui-form-label">保险开始时间</label>
        <div class="layui-input-block">
            <input type="text"  class="layui-input" name="insuranceStartDate" id="insuranceStartDate"  value="${salesSlip.insuranceStartDate?string('yyyy-MM-dd')}" maxlength="20"  readonly="readonly"  lay-verify="required"  autocomplete="on">
        </div>
        </div>
       <div class="layui-inline">
        <label class="layui-form-label">赔偿限额（元）</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="compensatePrice" maxlength="20" value="${salesSlip.compensatePrice}" readonly="readonly"  lay-verify="required|number">
        </div>
    </div>
    </div>
   <div class="layui-form-item">
        <label class="layui-form-label">第一受益人</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="firstBeneficiary" maxlength="50" value="${salesSlip.firstBeneficiary}" readonly="readonly"  lay-verify="required">
        </div>
    </div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['form','jquery','layer'],function(){
        var form      = layui.form,
                $     = layui.jquery,
                laydate = layui.laydate,
                layer = layui.layer;
    });
</script>
</body>
</html>