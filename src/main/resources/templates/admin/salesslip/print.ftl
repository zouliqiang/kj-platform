<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>${site.name}</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<meta name="description" content="${site.description}" />
<meta name="keywords" content="${site.keywords}" />
<meta name="author" content="${site.author}" />
<link rel="icon" href="${site.logo}">
<link rel="stylesheet" href="${base}/static/layui/css/layui.css"
	media="all" />

</head>
<div class="layui-btn-group" style="position: absolute;">
		<button class="layui-btn layui-btn-danger layui-btn-radius"
			id="printBtn">打印</button>
</div>
<body class="childrenBody">
	<div class="container"  id="printDiv">
		<div class="content">
			<div class="chapter1">
				<img src="${base}/static/images/seal1.png">
			</div>
			<div class="chapter2">
				<img src="${base}/static/images/seal2.png">
			</div>
			<div class="title"><img src="${base}/static/images/renshou.png">
			<br>
			全车盗抢综合保险客户服务单</div><br><br><br>
			<div class="guaranteeNum">
				<span> 保单号： </span> <span>${salesSlip.policyNo}</span>
			</div>
			<div class="guaranteeNum guaranteeNum-No">
				<span> NO. </span>： <span style="font-weight: bold;font-size: 18px;color:red;">${salesSlip.no}</span>
			</div>
			<div class="table-content">
				<p class="desc">请您准确、完整的填写全车盗抢综合保险客户服务单表格列明的信息，并对填写真实有效性负责，否则，由此产生的责任将由您自行承担</p>
				<table border="1">
					<tr>
						<td class="nameTd">客户名称</td>
						<td class="">${salesSlip.customerName}</td>
						<td class="nameTd">证件类型/号码</td>
						<td>${salesSlip.certificateNo}</td>
					</tr>
					<tr>
						<td class="nameTd">联系电话</td>
						<td class="">${salesSlip.customerMobile}</td>
						<td class="nameTd">通讯地址</td>
						<td>${salesSlip.customerAddress}</td>
					</tr>
					<tr>
						<td class="nameTd">车辆品牌/型号</td>
						<td class="">${salesSlip.vehicleBrand}</td>
						<td class="nameTd">车架号（VIN码）</td>
						<td>${salesSlip.vehicleFrameNo}</td>
					</tr>
					<tr>
						<td class="nameTd">车 牌 号</td>
						<td class="">${salesSlip.licencePlateNo}</td>
						<td class="nameTd">发动机号</td>
						<td>${salesSlip.engineFrameNo}</td>
					</tr>
					<tr>
						<td class="nameTd">登记日期</td>
						<td class="">${(salesSlip.registrationDate?string("yyyy-MM-dd"))!}</td>
						<td class="nameTd">初始购车价</td>
						<td>${salesSlip.vehiclePrice}</td>
					</tr>
					<tr>
						<td class="nameTd">安装日期</td>
						<td class="">${(salesSlip.installDate?string("yyyy-MM-dd"))!}</td>
						<td class="nameTd">安装地点</td>
						<td>${salesSlip.installAddress}</td>
					</tr>
					<tr>
						<td class="nameTd">安装人</td>
						<td class="">${salesSlip.installser}</td>
						<td class="nameTd">产品SN 码</td>
						<td>${salesSlip.productNo}</td>
					</tr>
				</table>
			</div>
			<div class="detail-content">
				<div class="title">中国人寿财产保险股份有限公司全车盗抢责任承保声明</div>
				<div class="detail detail1">
					<span class="title-next">保障范围：</span>凡在中华人民共和国境内安装了河北酷嘉汽车服务有限公司销售的酷嘉车载智能终端产品的车辆，该车辆必须手续齐全，合法有效。
				</div>
				<div class="detail detail2">
					<span class="title-next">保险期限:</span> <span class="year"> <#if salesSlip.insuranceTerm == 1>壹<#elseif salesSlip.insuranceTerm == 2>贰<#else>叁</#if> </span>年，自设备安装之日起生效，在保险期间内如发生车辆转让，保险责任终止；车辆所有人或管理人应及时通知河北酷嘉汽车服务有限公司办理变更手续，手续变更次日零时保险责任恢复，否则保险人不承担赔偿责任。
				</div>
				<div class="detail detail3">
					<span class="title-next">保险责任：</span>保险合同（保单号：<span class="guarantee-slip">${salesSlip.policyNo}</span>）项下约定的责任，即保障范围内车辆被全车盗窃、抢劫、抢夺，经县级以上公安刑侦部门立案证实，满三个月未查明下落的，保险人根据保险合同约定进行赔偿。
				</div>
				<div class="detail detail4">
					<span class="title-next">赔偿处理：</span><br>
					1.出险车辆未购买盗抢险的，按被盗车辆折旧后价格的100%进行赔偿；出险车辆已购买盗抢险的，在机动车辆保险先行赔付的前提下，本保险对机动车盗抢险20%免赔部分在赔偿限额内进行差额赔付。折旧率计算方法参照中国人寿财产保险股份有限公司机动车辆保险对应的车辆种类或型号和折旧率。<br>
					2.我公司确认索赔单证齐全有效后20个工作日内支付赔款。获取赔款后，运营商及车主应协助我公司向责任者进行追偿。本保险赔偿限额最高为RMB
					<span class="money"><#setting number_format="0.00">${(salesSlip.compensatePrice/10000)?string("0.####")}</span> 万元。<br>
					3、特别声明：若投保车辆为银行、金融公司贷款所购，发生保险事故，未还清银行、金融公司贷款时，保险赔款将直接支付给所属银行或金融公司。第一受益人：<span
						class="people">${salesSlip.firstBeneficiary}</span>
				</div>
				<div class="detail detail5">
					<span class="title-next">有关义务：</span><br>
					发生全车被盗抢后，车辆所有人应在24小时内向当地公安机关报案。车辆被盗后应当通过河北酷嘉汽车服务有限公司向保险人索赔。索赔时，车主必须提供车辆保险单证复印件（包括交强险）、受损人驾驶证及身份证复印件、机动车行驶证、机动车登记证书、购车原始发票、车辆购置税完税证明或免税证明、保险证明（全车盗抢责任承保声明），被盗抢原车的钥匙、公安机关立案证明、未破案证明、机动车钥匙及机动车所有权权益转让书（由车主向中国人寿财险北京分公司转让）、保险人要求的其他材料。
				</div>
				<div class="detail detail6">
					<span class="title-next">除外责任：</span><br> 1、非全车遭受盗抢，仅车上零部件或附属设备被盗抢、被损坏；<br>
					2、被他人诈骗造成全车或部分损失；<br> 3、车主因违反政府有关法律、法规，车辆被有关国家机关罚没、扣押；<br>
					4、车主因与他人的民事、经济纠纷而被抢劫、抢夺；<br> 5、租赁车辆与承租人同时失踪；<br>
					6、车主的故意行为；<br> 7、客户违反本产品中规定的操作规程或擅自拆卸等引起的损失；<br>
					8、产品用户未按时交纳移动通信费用被停机，导致该设备无法正常工作的；<br> 9、除保险责任外的其他一切责任；<br>
				</div>
				<div class="detail detail7">
					<span class="title-next">保险人：</span>中国人寿财产保险股份有限公司北京市分公司 <br> 地
					址：北京市朝阳区朝外大街16号中国人寿大厦15层 <br> 保险服务咨询：010-95519
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 河北酷嘉服务咨询电话：0311-68033315
				</div>
			</div>
			<div class="detail-bottom">
				保险期限：自<span class="time">${(salesSlip.insuranceStartDate?string("yyyy"))!}</span>年<span class="time">${(salesSlip.insuranceStartDate?string("MM"))!}</span>月<span
					class="time">${(salesSlip.insuranceStartDate?string("dd"))!}</span> 日 0 时 起 至<span class="time">${(salesSlip.insuranceEndDate?string("yyyy"))!}</span>年<span
					class="time">${(salesSlip.insuranceEndDate?string("MM"))!}</span>月<span class="time">${(salesSlip.insuranceEndDate?string("dd"))!}</span>日24时止。 <br>
				本保险由河北酷嘉汽车服务有限公司向中国人寿财产保险股份有限公司北京市分公司直属业务二部投保。<br>
				注：本产品安装之日起保修三十六个月，如产品在保修期内出现故障，经告知，但用户未到指定部门维修，后果自负。

			</div>
		</div>
		<style type="text/css">
.container {
	width: 700px;
	margin: 0 auto;
	padding: 10px 30px 0;
}

.content {
	width: 100%;
	position: relative;
}

.content .chapter1, .content .chapter2 {
	width: 160px;
	height: 160px;
	position: absolute;
	bottom: 40px !important;
	z-index: 100;
}

.content .chapter1 {
	left: 40px;
}

.content .chapter2 {
	bottom: 150px;
	right: 40px;
}

.content .chapter1 img, .content .chapter2 img {
	width: 100%;
}

.content .title {
	height: 30px;
	line-height: 30px;
	text-align: center;
	font-size: 24px;
}

.guaranteeNum {
	display: flex;
	align-items: center;
	justify-content: flex-end;
	height: 26px;
	line-height: 26px;
	margin: 10px 0 0;
	text-align: right;
	color: #333;
	font-size: 14px;
}

.guaranteeNum span:first-child {
	width: 80px;
	text-align: right;
}

.guaranteeNum span:last-child {
	color: black;
	font-size: 14px;
	width: 170px;
	text-align: left;
}

.guaranteeNum-No {
	margin-top: 0;
}

.guaranteeNum-No>span:first-child {
	font-weight: bold;
}

.table-content>.desc {
	font-size: 13px;
	line-height: 18px;
	color: rgba(0, 0, 0, 0.8);
	margin: 0;
}

.table-content table {
	border-collapse: collapse
}

.table-content table td {
	border-color: #000;
}

.table-content table td:first-child, .table-content table td:nth-child(3)
	{
	width: 140px;
	font-size: 13px;
	padding-left: 10px;
}

.table-content table td:last-child, .table-content table td:nth-child(2)
	{
	width: 250px;
}

.detail-content {
	width: 100%;
	margin: 10px auto 0;
	padding-bottom: 5px;
	border: 1px solid #000;
	box-sizing: border-box;
}

.detail-content .title {
	height: 28px;
	line-height: 28px;
	font-size: 16px;
}

.detail-content .detail {
	padding: 3px 10px 5px;
	font-size: 13px;
	border-top: 1px solid #000;
	line-height: 18px;
	box-sizing: border-box;
}

/* .detail span {
	font-weight: bold;
} */

.detail1 {
	height: 44px;
}

.detail .year, .detail .money, .detail .people, .detail-bottom .time {
	width: 50px;
	height: 15px;
	display: inline-block;
	border-bottom: 1px solid #000;
	position: relative;
	bottom: 1px;
    text-align: center;
}

.detail2 .year {
	width: 50px;
}

.detail4 .money {
	width: 100px;
}

.detail4 .people {
	width: 330px;
}

.detail .guarantee-slip {
	color: balck;
	font-weight: normal;
}

.detail-bottom {
	font-size: 13px;
}
.title-next{
   font-weight: bold;
}
</style>
	<script type="text/javascript"
		src="${base}/static/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript"
		src="${base}/static/js/jquery.jqprint-0.3.js"></script>

	<script>
		$(function() {
			$("#printBtn").click(function() {
				$("#printDiv").jqprint();
			})
		})
	</script>
	</div>
</body>
</html>