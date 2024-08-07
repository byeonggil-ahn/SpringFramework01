<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Modify/Delete</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Modify/Delete</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<form role="form" action="modify" method="post">
						<input type="hidden" name="pageNum"
						value="<c:out value="${cri.pageNum }"/>"> <input
						type="hidden" name="amount"
						value="<c:out value="${cri.amount }"/>"> <input
						type="hidden" name="keyword"
						value="<c:out value="${cri.keyword }"/>"> <input
						type="hidden" name="type" value="<c:out value="${cri.type }"/>">
					<div class="form-group">
						<label>BNO</label> <input class="form-control" name="bno"
							readonly="readonly" value='<c:out value="${board.bno}"/>'>
						<p class="help-block">Example block-level help text here.</p>
					</div>

					<div class="form-group">
						<label>Title</label> <input class="form-control" name="title"
							value='<c:out value="${board.title}"/>'>
						<p class="help-block">Example block-level help text here.</p>
					</div>

					<div class="form-group">
						<label>Content</label>
						<textarea rows="5" cols="50" name="content" class="form-control">'<c:out
								value="${board.content}" />'</textarea>
					</div>

					<div class="form-group">
						<label>Writer</label> <input class="form-control" name="writer"
							value='<c:out value="${board.writer}"/>'>
						<p class="help-block">Example block-level help text here.</p>
					</div>

					<button class="btn btn-default" data-oper='modify'>Modify</button>
					<button class="btn btn-danger" data-oper='remove'>Remove</button>
					<button class="btn btn-info" data-oper='list'>List</button>
				</form>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<script>
	/* 게시판 내용 remove와 modify 처리 로직 script태그로 구현  */
	$(document).ready(function() {
		/* 어처피 지금 이 jsp에서 form 하나밖에 안쓰니 아래와 같이 변수로 받아주고  */
		var formObj = $("form");

		$('.btn').click(function(e) {

			e.preventDefault();
			/* data-지정값 속성에 적용되있는값 가져와서 변수에 넣기 */
			var operation = $(this).data("oper");

			console.log(operation);

			if (operation === 'list') {
				formObj.attr("action", "/board/list").attr("method", "get");
				//modify.jsp에서다시 목록으로 돌아가는경우 필요한 파라미터 전송위한 로직 
				var pageNumTag = $("input[name='pageNum']").clone();
				var amountTag = $("input[name='amount']").clone();
				var KeywordTag = $("input[name='keyword']").clone();
				var typeTag = $("input[name='type']").clone();
				
				formObj.empty();
				
				formObj.append(pageNumTag);
				formObj.append(amountTag);
				formObj.append(KeywordTag);
				formObj.append(typeTag);
				
				formObj.submit();

			} else if (operation === 'remove') {
				formObj.attr("action", "/board/remove").attr("method", "post");
				formObj.submit();
			} else if (operation === 'modify') {
				formObj.attr("action", "/board/modify").attr("method", "post");
				formObj.submit();
			}
		})

	    
		$(window).on('pageshow', function(event) {
		    if (event.originalEvent.persisted || window.performance.navigation.type == 2) {
/*	window.performance.navigation.type == 2 는 크롬에서 뒤로가기를 의미,
 * event.originalEvent.persisted는 크롬을 제외한 브라우저에 bf캐시여부 확인 
 */
		        console.log(event.originalEvent.persisted);
		        window.location.reload();
		    }
		});

	})
</script>


<%@include file="../includes/footer.jsp"%>
