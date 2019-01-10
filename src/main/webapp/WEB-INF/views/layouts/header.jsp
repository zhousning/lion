<div class="navbar navbar-inverse navbar-fixed-top nav-bar-bg navbar-custom">
	<div class="container-fluid">
		<a href="index.jsp" class="navbar-brand"> <img
			src="img/logo.png">
		</a>
		<button class="navbar-toggle" data-toggle="collapse"
			data-target=".navHeaderCollapse"></button>
		<div class="collapse navbar-collapse navHeaderCollapse">
			<ul class="nav navbar-nav navbar-right">
				<shiro:guest>
					<li class="active"><a href="users/sign_in"> <fmt:message
								key="system.signin"></fmt:message>
					</a></li>
					<li><a href="users/sign_up"> <fmt:message
								key="system.signup"></fmt:message>
					</a></li>
				</shiro:guest>
				<shiro:user>
					<li><a href="#" class="active""><shiro:principal /></a></li>
					<li><a href="shiro/logout"><fmt:message
								key="system.logout"></fmt:message></a></li>
				</shiro:user>
				<!-- <li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Social Media</a>

					<ul class="dropdown-menu">
						<li><a href="#">Twitter</a></li>

						<li><a href="#">Facebook</a></li>

						<li><a href="#">Google+</a></li>

						<li><a href="#">Instagram</a></li>
					</ul></li>

				<li><a href="#">About</a></li>

				<li><a href="#">Contact</a></li> -->
			</ul>
		</div>
	</div>
</div>