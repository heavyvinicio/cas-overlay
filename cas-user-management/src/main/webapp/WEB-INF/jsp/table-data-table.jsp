<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html >
  <head>
    <!-- Twitter meta-->
    <title>Data Table - Vali Admin</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/page.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
     <!-- Essential javascripts for application to work-->
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <!-- The javascript plugin to display page loading on top
    <script src="${pageContext.request.contextPath}/js/plugins/pace.min.js"></script>
    -->
    <!-- Data table plugin -->
    <script src="${pageContext.request.contextPath}/js/plugins/vue.min.js"></script>

  </head>
  <body class="app sidebar-mini rtl">
    <!-- Navbar-->
    <jsp:include page="components/navbar.jsp" flush="true"/>
    <!-- Sidebar menu-->
    <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
    <aside class="app-sidebar">
      <jsp:include page="components/userinfo.jsp" flush="true"/>
      <ul class="app-menu">
        <li><a class="app-menu__item" href="/index"><i class="app-menu__icon fa fa-dashboard"></i><span class="app-menu__label">Dashboard</span></a></li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-laptop"></i><span class="app-menu__label">UI Elements</span><i class="treeview-indicator fa fa-angle-right"></i></a>
          <ul class="treeview-menu">
            <li><a class="treeview-item" href="bootstrap-components.jsp"><i class="icon fa fa-circle-o"></i> Bootstrap Elements</a></li>
            <li><a class="treeview-item" href="https://fontawesome.com/v4.7.0/icons/" target="_blank" rel="noopener"><i class="icon fa fa-circle-o"></i> Font Icons</a></li>
            <li><a class="treeview-item" href="ui-cards.jsp"><i class="icon fa fa-circle-o"></i> Cards</a></li>
            <li><a class="treeview-item" href="widgets.jsp"><i class="icon fa fa-circle-o"></i> Widgets</a></li>
          </ul>
        </li>
        <li><a class="app-menu__item" href="charts.jsp"><i class="app-menu__icon fa fa-pie-chart"></i><span class="app-menu__label">Charts</span></a></li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-edit"></i><span class="app-menu__label">Forms</span><i class="treeview-indicator fa fa-angle-right"></i></a>
          <ul class="treeview-menu">
            <li><a class="treeview-item" href="form-components.jsp"><i class="icon fa fa-circle-o"></i> Form Components</a></li>
            <li><a class="treeview-item" href="form-custom.jsp"><i class="icon fa fa-circle-o"></i> Custom Components</a></li>
            <li><a class="treeview-item" href="form-samples.jsp"><i class="icon fa fa-circle-o"></i> Form Samples</a></li>
            <li><a class="treeview-item" href="form-notifications.jsp"><i class="icon fa fa-circle-o"></i> Form Notifications</a></li>
          </ul>
        </li>
        <li class="treeview is-expanded"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-th-list"></i><span class="app-menu__label">Tables</span><i class="treeview-indicator fa fa-angle-right"></i></a>
          <ul class="treeview-menu">
            <li><a class="treeview-item" href="/basic/tables"><i class="icon fa fa-circle-o"></i> Basic Tables</a></li>
            <li><a class="treeview-item active" href="/data/tables"><i class="icon fa fa-circle-o"></i> Data Tables</a></li>
          </ul>
        </li>
        <li class="treeview"><a class="app-menu__item" href="#" data-toggle="treeview"><i class="app-menu__icon fa fa-file-text"></i><span class="app-menu__label">Pages</span><i class="treeview-indicator fa fa-angle-right"></i></a>
          <ul class="treeview-menu">
            <li><a class="treeview-item" href="blank-page.jsp"><i class="icon fa fa-circle-o"></i> Blank Page</a></li>
            <li><a class="treeview-item" href="page-login.jsp"><i class="icon fa fa-circle-o"></i> Login Page</a></li>
            <li><a class="treeview-item" href="page-lockscreen.jsp"><i class="icon fa fa-circle-o"></i> Lockscreen Page</a></li>
            <li><a class="treeview-item" href="page-user.jsp"><i class="icon fa fa-circle-o"></i> User Page</a></li>
            <li><a class="treeview-item" href="page-invoice.jsp"><i class="icon fa fa-circle-o"></i> Invoice Page</a></li>
            <li><a class="treeview-item" href="page-calendar.jsp"><i class="icon fa fa-circle-o"></i> Calendar Page</a></li>
            <li><a class="treeview-item" href="page-mailbox.jsp"><i class="icon fa fa-circle-o"></i> Mailbox</a></li>
            <li><a class="treeview-item" href="page-error.jsp"><i class="icon fa fa-circle-o"></i> Error Page</a></li>
          </ul>
        </li>
      </ul>
    </aside>


    <main class="app-content" id="app" >
      <div class="app-title">
        <div>
          <h1><i class="fa fa-th-list"></i> Data Table</h1>
          <p>Table to display analytical data effectively</p>
        </div>
        <ul class="app-breadcrumb breadcrumb side">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item">Tables</li>
          <li class="breadcrumb-item active"><a href="#">Data Table</a></li>
        </ul>
      </div>
      <div class="row">
        <div class="col-md-12">
          <div class="tile">
            <div class="tile-body">
              <table class="table table-striped" >
                <thead>
                  <tr>
                    <th><span>序号</span></th>
                    <th><span>公司</span></th>
                    <th><span>用户名</span></th>
                    <th><span>邮件</span></th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="item in listData">
                    <td>{{item.bbbb}}</td>
                  </tr>
                </tbody>
              </table>
           		<div>
				    <div>
				      <div class="page"  v-show="show">
				        <div class="pagelist">
				          <span class="jump" :class="{disabled:pstart}" @click="{current_page--}">上一页</span>
				          <span v-show="current_page>5" class="jump" @click="jumpPage(1)">1</span>
				          <span class="ellipsis"  v-show="efont">...</span>
				          <span class="jump" v-for="num in indexs" :class="{bgprimary:current_page==num}" @click="jumpPage(num)">{{num}}</span>
				          <span class="ellipsis"  v-show="ebehind">...</span>
				
				          <span :class="{disabled:pend}" class="jump" @click="{current_page++}">下一页</span>
				          <span v-show="current_page<pages-4" class="jump" @click="jumpPage(pages)">{{pages}}</span>
				
				          <span class="jumppoint">跳转到：</span>
				          <span class="jumpinp"><input type="text" v-model="changePage"></span>
				          <span class="jump gobtn" @click="jumpPage(changePage)">GO</span>
				        </div>
				      </div>
				    </div>
				  </div>
            </div>
          </div>
        </div>
      </div>
    </main>
    <script type="text/javascript">
    var newlist = new Vue({
        el: '#app',
        data: {
          current_page: 1, //当前页
          pages: 50, //总页数
          changePage:'',//跳转页
          nowIndex:0,
          listData:[]
        },
        computed:{
           show:function(){
               return this.pages && this.pages !=1
           },
           pstart: function() {
             return this.current_page == 1;
           },
           pend: function() {
             return this.current_page == this.pages;
           },
           efont: function() {
             if (this.pages <= 7) return false;
             return this.current_page > 5
           },
           ebehind: function() {
             if (this.pages <= 7) return false;
             var nowAy = this.indexs;
             return nowAy[nowAy.length - 1] != this.pages;
           },
           indexs: function() {
             var left = 1,
               right = this.pages,
               ar = [];
             if (this.pages >= 7) {
               if (this.current_page > 5 && this.current_page < this.pages - 4) {
                 left = Number(this.current_page) - 3;
                 right = Number(this.current_page) + 3;
               } else {
                 if (this.current_page <= 5) {
                   left = 1;
                   right = 7;
                 } else {
                   right = this.pages;
                   left = this.pages - 6;
                 }
               }
             }
             while (left <= right) {
               ar.push(left);
               left++;
             }
             return ar;
           },
         },
        methods: {
          jumpPage: function(id) {
       	  	var ival = parseInt(id);//如果变量val是字符类型的数则转换为int类型 如果不是则ival为NaN
      	    if (!isNaN(ival)) {
      	      	this.current_page = id;
      	      	this.getListDate()
      	    } 
          },
          getListDate: function(index) {
        	  const data = {
        		name: "name",
        		value: "value"
        	  };
        	  $.ajax({
	                type: "post",
	                data: data,
	                url: "/user/data/list",
	                dataType: "json",
	                success: function(d) {
	                	this.listData = [],
                        console.log("listData",d.bbbb);
	                },
	                error: function(e) {
	                	console.log("error", e);
	                }
          		});
          },
        },

      })

    </script>
    <!-- Google analytics script-->
    <script type="text/javascript">
      if(document.location.hostname == 'pratikborsadiya.in') {
      	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
      	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
      	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
      	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
      	ga('create', 'UA-72504830-1', 'auto');
      	ga('send', 'pageview');
      }
    </script>
  </body>
</html>