<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/pages/common/header.jsp"%>
<script type="text/javascript">
var gridManager;
var _dialog;
$(function (){
	PageLoader.initSearchPanel();
	gridManager = PageLoader.initGridPanel();
});

PageLoader = {
 //
 initSearchPanel:function(){
 },	
 initGridPanel:function(){
	 var g = $("#maingrid").ligerGrid({
         checkbox:true,
         pageSize:10,
         height:"400px",
         rowHeight: 28,//行默认的高度
         headerRowHeight: 30,//表头行的高度
         onToNext:next,//条件查询方法
         columns: [
          { display: 'name', name: 'name',editor: { type: 'text' }},   
          { display: 'firstName', name: 'firstName',editor: { type: 'text' }},   
          { display: 'lastName', name: 'lastName',editor: { type: 'text' }},   
          { display: 'email', name: 'email',editor: { type: 'text' }},   
          { display: 'age', name: 'age',editor: { type: 'text' }},   
        { display: '操作', isSort: false, width: 120, render: function (rowdata, rowindex, value)
            {
                var h = "<a href='javascript:openDetailsPage(" + rowdata.id + ")'>查看</a> ";
                return h;
            }
        }
        ], 
        enabledEdit: false, clickToEdit: false, isScroll: false,  
        url:"<%=contextPath %>/User/pageJson.koala",
        width: '100%',
        toolbar: { items: [
            { id: "add", text: '增加', click: itemclick, icon: 'add' },
	        { line: true },
	        { id: "modify", text: '修改', click: itemclick, icon: 'modify' },
            { line: true },
	        { id: "remove", text: '删除', click: itemclick, icon: 'delete' }
        ]
        }
    });
	 g.toggleCol("id",false);//隐藏id列
	 return g;
 }
}


//查询方法
function searchAction(){
     //form validate
   if(!Validator.Validate(document.getElementById("searchForm"),3))return;
	var param=$("#searchForm").serialize();
	gridManager.loadServerData(param);/*执行服务器查询*/
}

/*分页条件查询参数*/
function next(){
	gridManager.setOptions({
        parms: [
	   {name:"userVO.name",value: $("#nameID").val()},
	   {name:"userVO.firstName",value: $("#firstNameID").val()},
	   {name:"userVO.lastName",value: $("#lastNameID").val()},
	   {name:"userVO.email",value: $("#emailID").val()},
	   {name:"userVO.age",value: $("#ageID").val()},
       {}
        ]
    });
}

function itemclick(item)
{
	switch (item.id) {
		case "add":
			openFormDialog(false);
	        break;
		case "modify":
			openFormDialog(true);
	        break;
		case "remove":
			deleteAction();
	        break;
	}
}


function openDetailsPage(id){
	var url = "User-view.jsp?id="+id;
	_dialog = jQuery.ligerDialog.open({
  	    title: '查看',
  	    url:url,
  	    isResize: true,
  	    width: 850, 
  	    height: 500, 
  	    isHidden: false
      });
}

function openFormDialog(edit){
	var url = "User-add.jsp";
	if(edit){
		var newRow = gridManager.getSelected();
	     if (!newRow) { alert('请选择行'); return; }
	     var i = 0;
	     $.each(gridManager.getCheckedRows(), function(index, element) {
			  i++;
		  });
	     if(i>1){
	    	 alert('请只选择一行'); return;
	     }
	     id = newRow.id;
	     url = "User-update.jsp?id=" + newRow.id;
	}
	_dialog = jQuery.ligerDialog.open({
  	    title: edit ? '编辑' : '新增',
  	    url:url,
  	    isResize: true, width: 550, height: 550, isHidden: false
      });
}

/*删除行*/
function deleteAction()
{ 
    var newRow = gridManager.getSelected();
    if (!newRow) { alert('请选择行'); return; }
    
    var removeData = '';
	  $.each(gridManager.getCheckedRows(), function(index, element) {
		  removeData = removeData + element.id + ',';
	  });
	  removeData = removeData.substr(0, removeData.length - 1) ;
    var data = [{ name: 'ids', value: removeData }];
	 Koala.ajax({
 		async:true,
 		type : "POST",
 		url: "<%=contextPath %>/User/delete.koala",
 		data:data,
 		dataType:'json',
 		success: function(data, textStatus){
  			 gridManager.deleteSelectedRow();
  			 gridManager.loadData();
 		}
 		});
}

</script>
</head>
<body>
<div style="width:98%;height:100%;padding-left: 5px;">
<!-- search form -->
<form name="searchForm" id="searchForm" target="_self">
<input type="hidden" name="page" value="1">
<input type="hidden" name="pagesize" value="10">
<table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
<table width="800" border="0" style="margin:5px">
 <tr height="25px">
  <td width="12%">name:</td>
  <td width="22%"><input name="name" class="input-common" type="text" id="nameID"  /></td>
  <td width="12%">firstName:</td>
  <td width="22%"><input name="firstName" class="input-common" type="text" id="firstNameID"  /></td>
  <td width="12%">lastName:</td>
  <td width="22%"><input name="lastName" class="input-common" type="text" id="lastNameID"  /></td>
 </tr>
 <tr height="25px">
  <td width="12%">email:</td>
  <td width="22%"><input name="email" class="input-common" type="text" id="emailID"  dataType="Email" require="false" /></td>
  <td width="12%">age:</td>
  <td width="22%"><input name="age" class="input-common" type="text" id="ageID"  /></td>
</tr>
</table>	
</td>
    <td><input id="searchButton" type="button" class="btn-normal" onclick="searchAction()" value="查询" /></td>
  </tr>
</table>	
</form>
<!-- grid -->
<div id="maingrid"></div> 
</div>
</body>
</html>
