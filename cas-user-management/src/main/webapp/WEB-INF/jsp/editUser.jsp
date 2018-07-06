<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>UUSafe 用户管理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Main CSS-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <!-- Font-icon css-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
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


    <main class="app-content">
      <div class="app-title">
        <div>
          <h1><i class="fa fa-edit"></i> 用户信息</h1>
          <p>Bootstrap default form components</p>
        </div>
        <ul class="app-breadcrumb breadcrumb">
          <li class="breadcrumb-item"><i class="fa fa-home fa-lg"></i></li>
          <li class="breadcrumb-item">Tables</li>
          <li class="breadcrumb-item"><a href="#">Form Components</a></li>
        </ul>
      </div>
     <form method="post" action="/user/edit">
      <div class="row">
        <div class="col-md-12">
          <div class="tile">
            <div class="row">
              <div class="col-lg-6">
				 <input type="hidden" name="id" value="${user.id}">
			     <div class="form-group">
                      <label class="control-label" for="disabledInput">公司</label>
                      <input class="form-control"  name="company" id="company" value="${user.company}" 
                      disabled="disabled"  >
                 </div>
                  
                  <div class="form-group">
	                    <label for="company">账号</label>
	                    <input class="form-control" id="username" name="username" value="${user.username}"
	                           placeholder="UserName" disabled="disabled">
                  </div>

                  <div class="form-group">
                    <label for="realname">姓名</label>
                    <input class="form-control" id="realname" name="realname" value="${user.realname}"
                           placeholder="RlealName">
                  </div>

                  <div class="form-group">
                    <label for="email">邮箱</label>
                    <input class="form-control" id="email" name="email" type="email" value="${user.email}"
                           aria-describedby="emailHelp" placeholder="Enter email">
                    <small class="form-text text-muted" id="emailHelp">We'll never share your email with anyone else.</small>
                  </div>
                  
                  <div class="form-group">
                    <label for="Password">密码</label>
                    <input class="form-control" id="Password" name="password" type="password" value="${user.password}"
                           placeholder="Password">
                  </div>

                  <div class="form-group">
                    <label for="exampleTextarea">Example textarea</label>
                    <textarea class="form-control" id="exampleTextarea" rows="3"></textarea>
                  </div>
                  <!--  
                  <div class="form-group">
                    <label for="exampleInputFile">File input</label>
                    <input class="form-control-file" id="exampleInputFile" type="file" aria-describedby="fileHelp"><small class="form-text text-muted" id="fileHelp">This is some placeholder block-level help text for the above input. It's a bit lighter and easily wraps to a new line.</small>
                  </div>
                  <fieldset class="form-group">
                    <legend>Radio buttons</legend>
                    <div class="form-check">
                      <label class="form-check-label">
                        <input class="form-check-input" id="optionsRadios1" type="radio" name="optionsRadios" value="option1" checked="">Option one is this and that—be sure to include why it's great
                      </label>
                    </div>
                    <div class="form-check">
                      <label class="form-check-label">
                        <input class="form-check-input" id="optionsRadios2" type="radio" name="optionsRadios" value="option2">Option two can be something else and selecting it will deselect option one
                      </label>
                    </div>
                    <div class="form-check disabled">
                      <label class="form-check-label">
                        <input class="form-check-input" id="optionsRadios3" type="radio" name="optionsRadios" value="option3" disabled="">Option three is disabled
                      </label>
                    </div>
                  </fieldset>
                  <div class="form-check">
                    <label class="form-check-label">
                      <input class="form-check-input" type="checkbox">Check me out
                    </label>
                  </div>
                    -->
              </div>
            
              <div class="col-lg-4 offset-lg-1">
                  <div class="form-group">
                    <fieldset disabled="">
                      <label class="control-label" for="disabledInput">Disabled input</label>
                      <input class="form-control" id="disabledInput" type="text" placeholder="Disabled input here..." disabled="">
                    </fieldset>
                  </div>
                  <div class="form-group">
                    <fieldset>
                      <label class="control-label" for="readOnlyInput">Readonly input</label>
                      <input class="form-control" id="readOnlyInput" type="text" placeholder="Readonly input here…" readonly="">
                    </fieldset>
                  </div>
                  <div class="form-group has-success">
                    <label class="form-control-label" for="inputSuccess1">Valid input</label>
                    <input class="form-control is-valid" id="inputValid" type="text">
                    <div class="form-control-feedback">Success! You've done it.</div>
                  </div>
                  <div class="form-group has-danger">
                    <label class="form-control-label" for="inputDanger1">Invalid input</label>
                    <input class="form-control is-invalid" id="inputInvalid" type="text">
                    <div class="form-control-feedback">Sorry, that username's taken. Try another?</div>
                  </div>
                  <div class="form-group">
                    <label class="col-form-label col-form-label-lg" for="inputLarge">Large input</label>
                    <input class="form-control form-control-lg" id="inputLarge" type="text">
                  </div>
                  <!-- 
                  <div class="form-group">
                    <label class="col-form-label" for="inputDefault">Default input</label>
                    <input class="form-control" id="inputDefault" type="text">
                  </div>
                  <div class="form-group">
                    <label class="col-form-label col-form-label-sm" for="inputSmall">Small input</label>
                    <input class="form-control form-control-sm" id="inputSmall" type="text">
                  </div>
                  <div class="form-group">
                    <label class="control-label">Input addons</label>
                    <div class="form-group">
                      <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
                      <div class="input-group">
                        <div class="input-group-prepend"><span class="input-group-text">$</span></div>
                        <input class="form-control" id="exampleInputAmount" type="text" placeholder="Amount">
                        <div class="input-group-append"><span class="input-group-text">.00</span></div>
                      </div>
                    </div>
                  </div>
                   -->
              </div>
            </div>
            <div class="tile-footer">
              <button class="btn btn-primary" type="submit">Submit</button>
            </div>
          </div>
        </div>
      </div>
   	 </form>
    </main>
    <!-- Essential javascripts for application to work-->
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <!-- The javascript plugin to display page loading on top-->
    <script src="${pageContext.request.contextPath}/js/plugins/pace.min.js"></script>
    <!-- Page specific javascripts-->
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