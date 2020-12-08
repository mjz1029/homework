<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>班种列表</title>
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
	$(function() {	
		//datagrid初始化 
	    $('#dataList').datagrid({ 
	        title:'班种列表', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible: false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        url:"DutyServlet?method=DutyList&t="+new Date().getTime(),
	        idField:'id', 
	        singleSelect: false,//是否单选 
	        pagination: true,//分页控件 
	        rownumbers: true,//行号 
	        sortName:'id',
	        sortOrder:'DESC', 
	        remoteSort: false,
	        columns: [[  
				{field:'chk',checkbox: true,width:50},
 		        {field:'id',title:'ID',width:50, sortable: true},    
 		        {field:'name',title:'班种名称',width:200},
 		       	{field:'staff1Id',title:'值班员工',width:200,
 		        	formatter: function(value,row,index){
 						if (row.staff1Id){
 							var staff1List = $("#staff1List").combobox("getData");
 							for(var i=0;i<staff1List.length;i++ ){
 								//console.log(departmentList[i]);
 								if(row.staff1Id == staff1List[i].id)return staff1List[i].name;
 							}
 							return row.departmentId;
 						} else {
 							return 'not found';
 						}
 					}	
 		       	},
 		       	{field:'dutyDate',title:'上班时间',width:200},
 		        {field:'selectedNum',title:'已选人数',width:200},
 		        {field:'maxNum',title:'最大可选人数',width:200},
	 		]], 
	        toolbar: "#toolbar",
	        onBeforeLoad : function(){
	        	try{
	        		$("#staff1List").combobox("getData")
	        	}catch(err){
	        		preLoadDepartment();
	        	}
	        }
	    }); 
		//提前加载教师信息
	    function preLoadDepartment(){
	  		$("#staff1List").combobox({
		  		width: "150",
		  		height: "25",
		  		valueField: "id",
		  		textField: "name",
		  		multiple: false, //可多选
		  		editable: false, //不可编辑
		  		method: "post",
		  		url: "Staff1Servlet?method=Staff1List&t="+new Date().getTime()+"&from=combox",
		  		onChange: function(newValue, oldValue){
		  			//加载班级下的学生
		  			//$('#dataList').datagrid("options").queryParams = {departmentid: newValue};
		  			//$('#dataList').datagrid("reload");
		  		}
		  	});
	  	}
		
	  //设置分页控件 
	    var p = $('#dataList').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [10,20,30,50,100],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	    });
	   	
	    //设置工具类按钮
	    $("#add").click(function(){
	    	$("#addDialog").dialog("open");
	    });
	    
	  //设置编辑按钮
	    $("#edit").click(function(){
	    	table = $("#editTable");
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	if(selectRows.length != 1){
            	$.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
            } else{
		    	$("#editDialog").dialog("open");
            }
	    });
	    
	  //编辑班种信息
	  	$("#editDialog").dialog({
	  		title: "修改班种信息",
	    	width: 450,
	    	height: 400,
	    	iconCls: "icon-edit",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'提交',
					plain: true,
					iconCls:'icon-user_add',
					handler:function(){
						var validate = $("#editForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							var staff1id = $("#edit_staff1List").combobox("getValue");
							var id = $("#dataList").datagrid("getSelected").id;
							var name = $("#edit_name").textbox("getText");
							var dutyDate = $("#edit_duty_date").textbox("getText");
							var maxNum = $("#edit_max_num").numberbox("getValue");
							var info = $("#edit_info").val();
							var data = {id:id, staff1id:staff1id, name:name,dutyDate:dutyDate,info:info,maxnum:maxNum};
							
							$.ajax({
								type: "post",
								url: "DutyServlet?method=EditDuty",
								data: data,
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","修改成功!","info");
										//关闭窗口
										$("#editDialog").dialog("close");
										//清空原表格数据
										$("#edit_name").textbox('setValue', "");
										$("#edit_duty_date").textbox('setValue', "");
										$("#edit_info").val("");
										
										//重新刷新页面数据
							  			$('#dataList').datagrid("reload");
							  			$('#dataList').datagrid("uncheckAll");
										
									} else{
										$.messager.alert("消息提醒","修改失败!","warning");
										return;
									}
								}
							});
						}
					}
				},
				{
					text:'重置',
					plain: true,
					iconCls:'icon-reload',
					handler:function(){
						$("#edit_name").textbox('setValue', "");
						$("#edit_phone").textbox('setValue', "");
						$("#edit_qq").textbox('setValue', "");
						
						$(table).find(".chooseTr").remove();
						
					}
				},
			],
			onBeforeOpen: function(){
				var selectRow = $("#dataList").datagrid("getSelected");
				//设置值
				$("#edit_name").textbox('setValue', selectRow.name);
				$("#edit_duty_date").textbox('setValue', selectRow.dutyDate);
				$("#edit_max_num").numberbox('setValue', selectRow.maxNum);
				$("#edit_info").val(selectRow.info);
				//$("#edit-id").val(selectRow.id);
				var staff1Id = selectRow.staff1Id;
				setTimeout(function(){
					$("#edit_staff1List").combobox('setValue', staff1Id);
				}, 100);
			},
			onClose: function(){
				$("#edit_name").textbox('setValue', "");
				$("#edit_duty_date").textbox('setValue', "");
				$("#edit_info").val("");
				//$("#edit-id").val('');
			}
	    });
	    
	    //删除
	    $("#delete").click(function(){
	    	var selectRow = $("#dataList").datagrid("getSelections");
        	if(selectRow == null){
            	$.messager.alert("消息提醒", "请选择数据进行删除!", "warning");
            } else{
            	var ids = [];
            	$(selectRow).each(function(i, row){
            		ids[i] = row.id;
            	});
            	$.messager.confirm("消息提醒", "将删除与班种相关的所有数据，确认继续？", function(r){
            		if(r){
            			$.ajax({
							type: "post",
							url: "DutyServlet?method=DeleteDuty",
							data: {ids: ids},
							success: function(msg){
								if(msg == "success"){
									$.messager.alert("消息提醒","删除成功!","info");
									//刷新表格
									$("#dataList").datagrid("reload");
								} else{
									$.messager.alert("消息提醒","删除失败!","warning");
									return;
								}
							}
						});
            		}
            	});
            }
	    });
	  	
	  	//设置添加窗口
	    $("#addDialog").dialog({
	    	title: "添加班种",
	    	width: 450,
	    	height: 400,
	    	iconCls: "icon-add",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'添加',
					plain: true,
					iconCls:'icon-book-add',
					handler:function(){
						var validate = $("#addForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							$.ajax({
								type: "post",
								url: "DutyServlet?method=AddDuty",
								data: $("#addForm").serialize(),
								success: function(msg){
									if(msg == "success"){
										$.messager.alert("消息提醒","添加成功!","info");
										//关闭窗口
										$("#addDialog").dialog("close");
										//清空原表格数据
										$("#add_name").textbox('setValue', "");
										//刷新
										$('#dataList').datagrid("reload");
									} else{
										$.messager.alert("消息提醒","添加失败!","warning");
										return;
									}
								}
							});
						}
					}
				},
				{
					text:'重置',
					plain: true,
					iconCls:'icon-book-reset',
					handler:function(){
						$("#add_name").textbox('setValue', "");
					}
				},
			]
	    });
	  	
	  //下拉框通用属性
	  	$("#add_staff1List, #edit_staff1List,#staff1List").combobox({
	  		width: "200",
	  		height: "30",
	  		valueField: "id",
	  		textField: "name",
	  		multiple: false, //不可多选
	  		editable: false, //不可编辑
	  		method: "post",
	  	});
	  	//添加信息教师选择框
	    $("#add_staff1List").combobox({
	  		url: "Staff1Servlet?method=Staff1List&t="+new Date().getTime()+"&from=combox",
	  		onLoadSuccess: function(){
				//默认选择第一条数据
				var data = $(this).combobox("getData");
				$(this).combobox("setValue", data[0].id);
	  		}
	  	});
	  //编辑信息教师选择框
	    $("#edit_staff1List").combobox({
	  		url: "Staff1Servlet?method=Staff1List&t="+new Date().getTime()+"&from=combox",
	  		onLoadSuccess: function(){
				//默认选择第一条数据
				var data = $(this).combobox("getData");
				$(this).combobox("setValue", data[0].id);
	  		}
	  	});
	  	
	    //搜索按钮监听事件
	  	$("#search-btn").click(function(){
	  		$('#dataList').datagrid('load',{
	  			name: $('#dutyName').val(),
	  			staff1id: $("#staff1List").combobox('getValue') == '' ? 0 : $("#staff1List").combobox('getValue')
	  		});
	  	});
	});
	</script>
</head>
<body>
	<!-- 数据列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0"> 
	    
	</table> 
	<!-- 工具栏 -->
	<div id="toolbar">
		<div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a></div>
		<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>
		<div style="float: left; margin-right: 10px;"><a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a></div>
		<div style="float: left;" class="datagrid-btn-separator"></div>
		<div style="margin-top: 3px;">
			班种名称：<input id="dutyName" class="easyui-textbox" name="departmentName" />
			值班员工：<input id="staff1List" class="easyui-textbox" name="department" />
			<a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
		</div>
	</div>
	
	<!-- 添加数据窗口 -->
	<div id="addDialog" style="padding: 10px">  
    	<form id="addForm" method="post">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>班种名称:</td>
	    			<td><input id="add_name" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="name" data-options="required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td style="width:40px">值班员工:</td>
	    			<td colspan="3">
	    				<input id="add_staff1List" style="width: 200px; height: 30px;" class="easyui-textbox" name="staff1id" />
	    			</td>
	    			<td style="width:80px"></td>
	    		</tr>
	    		<tr>
	    			<td>上班时间:</td>
	    			<td><input id="add_duty_date" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="duty_date" data-options="required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td>最多可选人数:</td>
	    			<td><input id="add_max_num" style="width: 200px; height: 30px;" class="easyui-numberbox" type="text" name="maxnum" data-options="min:0,precision:0,required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td>班种介绍:</td>
	    			<td>
	    				<textarea id="info" name="info" style="width: 200px; height: 60px;" class="" ></textarea>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	
	<!-- 编辑数据窗口 -->
	<div id="editDialog" style="padding: 10px">  
    	<form id="editForm" method="post">
    		<!-- <input type="hidden" name="id" id="edit-id"> -->
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>班种名称:</td>
	    			<td><input id="edit_name" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="name" data-options="required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td style="width:40px">值班员工:</td>
	    			<td colspan="3">
	    				<input id="edit_staff1List" style="width: 200px; height: 30px;" class="easyui-textbox" name="staff1id" />
	    			</td>
	    			<td style="width:80px"></td>
	    		</tr>
	    		<tr>
	    			<td>上班时间:</td>
	    			<td><input id="edit_duty_date" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="duty_date" data-options="required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td>最多可选人数:</td>
	    			<td><input id="edit_max_num" style="width: 200px; height: 30px;" class="easyui-numberbox" type="text" name="max_num" data-options="min:0,precision:0,required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td>班种介绍:</td>
	    			<td>
	    				<textarea id="edit_info" name="info" style="width: 200px; height: 60px;" class="" ></textarea>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>
</body>
</html>