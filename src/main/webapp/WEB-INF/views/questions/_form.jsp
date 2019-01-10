<div class="col-md-12">
	<div class="tabbable-panel">
		<div class="tabbable-line">
			<ul class="nav nav-tabs ">
				<!-- <li class="active"><a href="#tab_default_1" data-toggle="tab">
						<fmt:message key="questions.single"></fmt:message>
				</a></li> -->
				<li class="active"><a href="#tab_default_2" data-toggle="tab"> <fmt:message
							key="questions.multiple"></fmt:message>
				</a></li>
				<li><a href="#tab_default_3" data-toggle="tab"> <fmt:message
							key="questions.essay"></fmt:message>
				</a></li>
			</ul>
			<div class="tab-content">
				<%-- <div class="tab-pane active" id="tab_default_1">
					<%@ include
						file="/WEB-INF/views/questions/_question_single_form.jsp"%>
				</div> --%>
				<div class="tab-pane  active" id="tab_default_2">
					<%@ include
						file="/WEB-INF/views/questions/_question_multiple_form.jsp"%>

				</div>
				<div class="tab-pane" id="tab_default_3">
					<%@ include
						file="/WEB-INF/views/questions/_question_essay_form.jsp"%>
				</div>
			</div>
		</div>
	</div>
</div>




