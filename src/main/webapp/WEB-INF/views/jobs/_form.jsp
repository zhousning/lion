<form:form action="jobs/${job.id != null ? 'update' : 'create' }" method="POST" modelAttribute="job"
	class="form-horizontal" enctype="multipart/form-data" >
	<c:if test="${job.id != null }">
		<form:hidden path="id" />
	</c:if>
	
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label"><fmt:message key="job.name"></fmt:message></label>
		<div class="col-sm-10">
			<form:input  type="text" class="form-control" path="name"  />
			<form:errors path="name"></form:errors>
		</div>
	</div>

	
	<div class="form-group">
		<!-- <label for="imageAttachments" class="col-sm-2 control-label"><fmt:message
				key="image.title"></fmt:message></label> -->
		<div class="col-sm-10 col-sm-offset-2">
			<p>
				最大尺寸200*200
			</p>
		</div>
		<div id="js-image-upload-ctn" class="col-sm-10 col-sm-offset-2">
			<c:choose>
				<c:when test="${!empty job.imageAttachments }">
					<c:forEach items="${ job.imageAttachments }" var="attachment">
						<label class="newbtn">
							<img class="blah" src="${ attachment.url }" />
							<input class='pis' onchange="readURL(this);" type="file"  name="imagefiles">
							<input type="hidden" value="${ attachment.id }" class="hiddenImage" name="hiddenImageIds" />
						</label>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<label class="newbtn">
						<img class="blah" src="static/images/image-upload.png">
						<input class='pis' onchange="readURL(this);" type="file" name="imagefiles">
					</label>
				</c:otherwise>
			</c:choose>
			<label id="js-new-imagebtn"> 
				<img class="blah" src="static/images/image-plus.png">
			</label>
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-1">
			<button type="submit" class="btn btn-success"><fmt:message key="btn.save"></fmt:message></button>
		</div>
		<div class="col-sm-offset-1 col-sm-1">
			<a href="jobs/index" type="button" class="btn btn-warning"><fmt:message key="btn.back"></fmt:message></a>
		</div>
	</div>
</form:form>