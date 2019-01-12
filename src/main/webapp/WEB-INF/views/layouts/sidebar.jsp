<div class="nav-side-menu">
	<div class="brand">
		<shiro:principal />
	</div>
	<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
		data-target="#menu-content"></i>

	<div class="menu-list">

		<ul id="menu-content" class="menu-content collapse out">
			<shiro:lacksRole name="admin">
				<li><i class="fa fa-dashboard fa-lg"></i> <a href="home"><fmt:message
						key="user.center"></fmt:message></a></li>
			</shiro:lacksRole>
			
			<shiro:hasAnyRoles name="admin">
				<li data-toggle="collapse" data-target="#users"
					class="collapsed active"><a><i class="fa fa-gift fa-lg"></i>
						<fmt:message key="users.manage"></fmt:message> <span class="arrow"></span></a>
				</li>
				<ul class="sub-menu collapse" id="users">
					<!-- <li class="active"><a href="#">CSS3 Animation</a></li> -->
					<li><a href="users/index"><fmt:message key="users.index"></fmt:message></a></li>
					<li><a href="users/new"><fmt:message key="users.new"></fmt:message></a></li>
				</ul>

				<li data-toggle="collapse" data-target="#roles"
					class="collapsed
			active"><a><i
						class="fa fa-gift fa-lg"></i> <fmt:message key="roles.manage"></fmt:message>
						<span class="arrow"></span></a></li>
				<ul class="sub-menu collapse" id="roles">
					<li class="active"><a href="#">CSS3 Animation</a></li>
					<li><a href="roles"><fmt:message key="roles.index"></fmt:message></a></li>
					<li><a href="roles/new"><fmt:message key="roles.new"></fmt:message></a></li>
				</ul>
			</shiro:hasAnyRoles>

			<li data-toggle="collapse" data-target="#new" class="collapsed">
				<a href="#"><i class="fa fa-car fa-lg"></i> New <span
					class="arrow"></span></a>
			</li>
			<ul class="sub-menu collapse" id="new">
				<li>New New 1</li>
				<li>New New 2</li>
				<li>New New 3</li>
			</ul>

			<li><a href="#"> <i class="fa fa-users fa-lg"></i> Users
			</a></li>
		</ul>
	</div>
</div>