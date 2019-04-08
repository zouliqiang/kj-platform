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
    <input value="${PolicyNoUser.id}" name="id" type="hidden">
     <div class="layui-form-item">
        <label class="layui-form-label">No.已分配数量</label>
        <div class="layui-input-block">
            <input  type="text"  class="layui-input" name="allocationNumber"  maxlength="20" value="${PolicyNoUser.allocationNumber}"   lay-verify="required|number">
        </div>
    </div>
    </div>
     <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="editPolicyNoUser">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
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
        form.on("submit(editPolicyNoUser)",function(data){
     	   
         var loadIndex = layer.load(2, {
             shade: [0.3, '#333']
         });
         $.ajax({
             url:"${base}/admin/policynouser/edit",
             type:"POST",
             data:JSON.stringify(data.field),
             contentType:"application/json; charset=utf-8",
             dataType:"json",
             success: function(res){
                 layer.close(loadIndex);
                 if(res.success){
                     parent.layer.msg("保单号分配编辑成功！",{time:1000},function(){
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