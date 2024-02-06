<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Tables</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				DataTables Advanced Tables
				<button id='regBtn' type="button" class="btn btn-xs pull-right">
					Register New Board</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<table width="100%"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>BNO</th>
							<th>Title</th>
							<th>Writer</th>
							<th>RegDate</th>
							<th>UpdateDate</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="board">
							<tr class="odd gradeX">
								<td>${board.bno}</td>
								<td><a class='move' href='<c:out value="${board.bno }"/>'><c:out
											value="${board.title}" /></a></td>
								<td>${board.writer }</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${board.regdate }" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${board.updateDate }" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- /.table-responsive -->

				<div class="row">
					<div class="col-lg-12">
						<form id="searchForm" action="/board/list" method="get">
							<select name="type">
							
							<!--그냥 el 태그만 사용할 수도있지만, c:out 태그를 열고 안에 value 속성을 이용하여
								표현하면 보안상의 이슈에서 더 유리하다고 한다.  -->
								<option value=""
									<c:out value="${pageMaker.cri.type == null ? 'selected':'' }"/>>--</option>
								<option value="T"
									<c:out value="${pageMaker.cri.type == 'T' ? 'selected':'' }"/>>제목</option>
								<option value="C"
									<c:out value="${pageMaker.cri.type == 'C' ? 'selected':'' }"/>>내용</option>
								<option value="W"
									<c:out value="${pageMaker.cri.type == 'W' ? 'selected':'' }"/>>작성자</option>
								<option value="TC"
									<c:out value="${pageMaker.cri.type == 'TC' ? 'selected':'' }"/>>제목
									or 내용</option>
								<option value="TW"
									<c:out value="${pageMaker.cri.type == 'TW' ? 'selected':'' }"/>>제목
									or 작성자</option>
								<option value="TWC"
									<c:out value="${pageMaker.cri.type == 'TWC' ? 'selected':'' }"/>>제목
									or 내용 or 작성자</option>
							</select>

							<!--pageNum과 amount 는 데이터를 넘길때 사용되고 별도로 작성해줘야하는 내용은 없으므로,
								type="hidden" 으로 검색시 항상 넘어갈 수 있도록 한다. -->
							<input type="text" name="keyword"
								value='<c:out value="${pageMaker.cri.keyword }"/>' /> <input
								type="hidden" name="pageNum"
								value='<c:out value="${pageMaker.cri.pageNum }"/>' /> <input
								type="hidden" name="amount"
								value='<c:out value="${pageMaker.cri.amount }"/>' />
							<button class="btn btn-default">Search</button>

						</form>
					</div>
				</div>



				<!--PageNation 로직 구현부   -->
				<div class='pull-right'>
					<ul class="pagination">
						<c:if test="${pageMaker.prev }">
							<li class="page-item"><a class="page-link"
								href="${pageMaker.startPage -1 }" tabindex="-1">Previous</a></li>
						</c:if>
						<c:forEach begin="${pageMaker.startPage }"
							end="${pageMaker.endPage}" var="num">
							<li class="page-item ${pageMaker.cri.pageNum == num? "active" : "" }"><a
								class="page-link" href="${num }">${num }</a></li>
						</c:forEach>
						<c:if test="${pageMaker.next }">
							<li class="page-item"><a class="page-link"
								href="${pageMaker.endPage +1 }" tabindex="-1">Next</a></li>
						</c:if>
					</ul>
				</div>

				<!-- 이처럼 이벤트나 PageNation의 인터페이스는 위에 만들어두되,서버에 전송할 데이터는
				아래에 form으로 분리하여 구성할 수도 있다. 꼭,form 으로 이벤트발생 후 넘겨야 할 데이터를 
				감싸지 않아도 된다 . -->
				<form id='actionForm' action="/board/list" method="get">
					<input type="hidden" name="pageNum"
						value="${pageMaker.cri.pageNum }">
					<input type="hidden" name="amount" 
						value="${pageMaker.cri.amount }">
					<input type="hidden" name="type"
						value="<c:out value="${pageMaker.cri.type }"/>">	
					<input type="hidden" name="keyword"
						value="<c:out value="${pageMaker.cri.keyword }"/>">
				</form>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<!-- 부트스트랩 모달창 가져 -->
<div id="myModal" class="modal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Modal title</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>Modal body text goes here.</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">Save
					changes</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<script>
	$(document)
			.ready(
					function() {

						var result = '<c:out value="${result}"/>';

						checkModal(result);

						/* 뒤로가기 모달창 버그 해결위한 history 객체사용 */
						history.replaceState({}, null, null);

						function checkModal(result) {
							if (result === '' || history.state) {
								return;
							}

							/* success 는 modify.jsp에 구현에 놓은 remove 버튼 클릭시,
							BoardController로 와서 /remove로 post요청을 보내서 받은 데이터값이다.
							아래 else if 는 게시글 그냥 새로 등록하는 get요청 로직 .*/
							if (result === 'success') {
								$(".modal-body").html("정상적으로 처리되었습니다 .");
							} else if (parseInt(result) > 0) {
								$(".modal-body").html(
										"게시글" + parseInt(result)
												+ "번이 등록되었습니다.");
							}
							$("#myModal").modal("show");
						}

						$("#regBtn").click(function() {

							self.location = "/board/register";
						});

						var actionForm = $("#actionForm");

						$(".page-link").on(
								"click",
								function(e) {

									e.preventDefault();

									var targetPage = $(this).attr("href");

									console.log(targetPage);

									actionForm.find("input[name='pageNum']")
											.val(targetPage);
									actionForm.submit();
								});

						$(".move")
								.on(
										"click",
										function(e) {

											e.preventDefault();

											var targetBno = $(this)
													.attr("href");

											console.log(targetBno);

											actionForm
													.append("<input type='hidden' name='bno' value='"+targetBno+"'>'");
											actionForm.attr("action",
													"/board/get").submit();

										});
					});

	/* 검색 버튼의 이벤트 처리 로직  */
	var searchForm = $("#searchForm");
	$("#searchForm button").on("click", function(e) {
	    // 검색 종류 선택 여부 확인
	    if (!searchForm.find("select[name='type'] option:selected").val()) {
	        alert("검색 종류를 선택하세요.");

	        return false;
	    }

	    // 키워드 입력 여부 확인
	    if (!searchForm.find("input[name='keyword']").val()) {
	        alert("키워드를 입력하세요.");

	        return false;
	    }

	    // 페이지 번호 초기화
	    searchForm.find("input[name='pageNum']").val("1");

	    e.preventDefault();

	    // 폼 제출
	    searchForm.submit();
	});

</script>

<%@include file="../includes/footer.jsp"%>
