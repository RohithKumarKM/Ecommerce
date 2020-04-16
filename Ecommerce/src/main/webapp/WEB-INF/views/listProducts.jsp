<div class="container">
	<div class="row">
		<!-- display sidebar -->
		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>

		<!-- display actual products -->
		<div class="col-md-9">
			<!-- added breadcrumb component -->
			<div class="row">
				<div class="col-lg-12">
					<c:if test="${userClickAllProducts == true}">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li><a href="${contextRoot}/home">Home </a></li>
								<li class="breadcrumb-item active" aria-current="page">All Products</li>
							</ol>
						</nav>
					</c:if>


					<c:if test="${userClickCategoryProducts == true}">
						<nav aria-label="breadcrumb">
							<ol class="breadcrumb">
								<li><a href="${contextRoot}/home">Home </a></li>
								<li class="breadcrumb-item active" aria-current="page"></li>
								<li class="breadcrumb-item active" aria-current="page">${category.name}</li>
							</ol>
						</nav>
					</c:if>

				</div>
			</div>
		</div>
	</div>
</div>