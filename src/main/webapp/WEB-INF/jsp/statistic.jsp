<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${sessionScope.languageId}"/>
<f:setBundle basename="local" var="local"/>
<f:message bundle="${local}" key="local.calculator" var="calculator"/>
<f:message bundle="${local}" key="local.statistic" var="statistic"/>
<f:message bundle="${local}" key="local.calculatorFullName" var="calculatorFullName"/>
<f:message bundle="${local}" key="local.forIndividual" var="forIndividual"/>
<f:message bundle="${local}" key="local.mainPage" var="mainPage"/>
<f:message bundle="${local}" key="local.about" var="about"/>
<f:message bundle="${local}" key="local.footerText" var="footerText"/>
<f:message bundle="${local}" key="local.authorName" var="authorName"/>
<f:message bundle="${local}" key="local.emptyField" var="emptyField"/>
<f:message bundle="${local}" key="local.error" var="error"/>
<f:message bundle="${local}" key="local.periodShortText" var="periodText"/>
<f:message bundle="${local}" key="local.revenueFromSaleShortText" var="revenueFromSaleText"/>
<f:message bundle="${local}" key="local.nonoperationIncomeShortText" var="nonoperationIncomeText"/>
<f:message bundle="${local}" key="local.hasMainJobText" var="hasMainJobText"/>
<f:message bundle="${local}" key="local.yes" var="yes"/>
<f:message bundle="${local}" key="local.no" var="no"/>
<f:message bundle="${local}" key="local.hasRightsToBenefitsShortText" var="hasRightsToBenefitsText"/>
<f:message bundle="${local}" key="local.isWidowOrSingleParentOrTrusteeShortText" var="isWidowOrSingleParentOrTrusteeText"/>
<f:message bundle="${local}" key="local.numberOfChildrenUnderEighteenText" var="numberOfChildrenUnderEighteenText"/>
<f:message bundle="${local}" key="local.numberOfDisabledChildrenUnderEighteenText"
           var="numberOfDisabledChildrenUnderEighteenText"/>
<f:message bundle="${local}" key="local.numberOfDependentsText" var="numberOfDependentsText"/>
<f:message bundle="${local}" key="local.sumOfExpensesOnBusinessActivityShortText" var="sumOfExpensesOnBusinessActivityText"/>
<f:message bundle="${local}" key="local.sumOfExpensesOnConstrOrAcquisitionOfHousingForReqShortText"
           var="sumOfExpensesOnConstrOrAcquisitionOfHousingForReqText"/>
<f:message bundle="${local}" key="local.sumOfExpensesOnFirstPaidEducationForRelativeShortText"
           var="sumOfExpensesOnFirstPaidEducationForRelativeText"/>
<f:message bundle="${local}" key="local.sumOfExpensesOnInsurancePremiumsShortText"
           var="sumOfExpensesOnInsurancePremiumsText"/>
<f:message bundle="${local}" key="local.calculateButton" var="calculateButton"/>
<f:message bundle="${local}" key="local.incomeTaxShortText" var="incomeTaxText"/>
<f:message bundle="${local}" key="local.next" var="next"/>
<f:message bundle="${local}" key="local.last" var="last"/>
<f:message bundle="${local}" key="local.first" var="first"/>
<f:message bundle="${local}" key="local.prev" var="prev"/>
<f:message bundle="${local}" key="local.orderBy" var="orderBy"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="">
    <title>${mainPage}</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/calculator-icon.png" type="image/png">
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
</head>

<body>
<div class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand"
               href="${pageContext.request.contextPath}/Controller?command=mainPage">${calculator}</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=viewStatistic&page=1&sortBy=id">${statistic}</a>
                </li>
                <c:if test='${requestScope.selectedLanguage eq "EN"}'>
                    <li>
                        <form class="navbar-form" id="change-language-ru"
                              action="${pageContext.request.contextPath}/Controller?command=changeLanguage"
                              method="post"><input type="hidden" name="changeLanguage" value="RU">
                            <button type="submit" class="btn btn-default">RU</button>
                        </form>
                    </li>
                </c:if>
                <c:if test='${requestScope.selectedLanguage eq "RU"}'>
                    <li>
                        <form class="navbar-form" id="change-language-en"
                              action="${pageContext.request.contextPath}/Controller?command=changeLanguage"
                              method="post"><input type="hidden" name="changeLanguage" value="EN">
                            <button type="submit" class="btn btn-default">EN</button>
                        </form>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>

<div>
    <div class="centered">
        <div class="btn-group">
            <button type="button" data-toggle="dropdown"
                    class="btn btn-default dropdown-toggle order_by">${orderBy} <span
                    class="caret"></span></button>
            <ul class="dropdown-menu">
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=viewStatistic&page=1&sortBy=period">${periodText}</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=viewStatistic&page=1&sortBy=incomeTax">${incomeTaxText}</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=viewStatistic&page=1&sortBy=revenueFromSale">${revenueFromSaleText}</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=viewStatistic&page=1&sortBy=sumOfExpensesOnBusinessActivity">${sumOfExpensesOnBusinessActivityText}</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <tr>
                <th>${periodText}</th>
                <th>${revenueFromSaleText}</th>
                <th>${nonoperationIncomeText}</th>
                <th>${hasMainJobText}</th>
                <th>${hasRightsToBenefitsText}</th>
                <th>${isWidowOrSingleParentOrTrusteeText}</th>
                <th>${numberOfChildrenUnderEighteenText}</th>
                <th>${numberOfDisabledChildrenUnderEighteenText}</th>
                <th>${numberOfDependentsText}</th>
                <th>${sumOfExpensesOnInsurancePremiumsText}</th>
                <th>${sumOfExpensesOnFirstPaidEducationForRelativeText}</th>
                <th>${sumOfExpensesOnConstrOrAcquisitionOfHousingForReqText}</th>
                <th>${sumOfExpensesOnBusinessActivityText}</th>
                <th class="active">${incomeTaxText}</th>
            </tr>
            <c:forEach var="calculation" items="${requestScope.calculations.content}">
                <tr>
                    <td>${calculation.period.name}</td>
                    <td>${calculation.revenueFromSale}</td>
                    <td>${calculation.nonoperatingIncome}</td>
                    <td><span
                            class="glyphicon <c:if test="${calculation.hasMainJob == true}">glyphicon-ok</c:if> <c:if test="${calculation.hasMainJob == false}">glyphicon-remove</c:if> "></span>
                    </td>
                    <td><span
                            class="glyphicon <c:if test="${calculation.hasRightToBenefits == true}">glyphicon-ok</c:if> <c:if test="${calculation.hasRightToBenefits == false}">glyphicon-remove</c:if> "></span>
                    </td>
                    <td><span
                            class="glyphicon <c:if test="${calculation.isWidowOrSingleParentOrTrustee == true}">glyphicon-ok</c:if> <c:if test="${calculation.isWidowOrSingleParentOrTrustee == false}">glyphicon-remove</c:if> "></span>
                    </td>
                    <td>${calculation.numberOfChildrenUnderEighteen}</td>
                    <td>${calculation.numberOfDisabledChildrenUnderEighteen}</td>
                    <td>${calculation.numberOfDependents}</td>
                    <td>${calculation.sumOfExpensesOnInsurancePremiums}</td>
                    <td>${calculation.sumOfExpensesOnFirstPaidEducationForRelative}</td>
                    <td>${calculation.sumOfExpensesOnConstrOrAcquisitionOfHousingForReq}</td>
                    <td>${calculation.sumOfExpensesOnBusinessActivity}</td>
                    <td class="active">${calculation.incomeTax}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="centered">
        <div class="btn-group">
            <c:if test="${requestScope.calculations.currentPage != 1}">
                <a href="${pageContext.request.contextPath}/Controller?command=viewStatistic&page=1<c:if test="${param.sortBy != null}">&sortBy=${param.sortBy}</c:if>"
                   class="btn btn-default">
                    <span class="glyphicon glyphicon-fast-backward"></span> ${first}
                </a>
                <a href="${pageContext.request.contextPath}/Controller?command=viewStatistic&page=${requestScope.calculations.currentPage - 1}<c:if test="${param.sortBy != null}">&sortBy=${param.sortBy}</c:if>"
                   class="btn btn-default">
                    <span class="glyphicon glyphicon-chevron-left"></span> ${prev}
                </a>
            </c:if>
            <c:if test="${requestScope.calculations.lastPage > requestScope.calculations.currentPage}">
                <a href="${pageContext.request.contextPath}/Controller?command=viewStatistic&page=${requestScope.calculations.currentPage +1}<c:if test="${param.sortBy != null}">&sortBy=${param.sortBy}</c:if>"
                   class="btn btn-default">
                    <span class="glyphicon glyphicon-chevron-right"></span> ${next}
                </a>
                <a href="${pageContext.request.contextPath}/Controller?command=viewStatistic&page=${requestScope.calculations.lastPage}<c:if test="${param.sortBy != null}">&sortBy=${param.sortBy}</c:if>"
                   class="btn btn-default">
                    <span class="glyphicon glyphicon glyphicon-fast-forward"></span> ${last}
                </a>
            </c:if>
        </div>
    </div>
</div>


<div id="footer">
    <div class="container">
        <div class="row">
            <div class="col-lg-4">
                <h4>${calculator}</h4>
                <p>
                    ${authorName}, <br/>
                    © 2017
                </p>
            </div>

            <div class="col-lg-4">
            </div>

            <div class="col-lg-4">
                <h4>${about}</h4>
                <p>${footerText}</p>
            </div>

        </div>

    </div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
<script src="${pageContext.request.contextPath}/js/validation.js"></script>

</body>
</html>
