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
<f:message bundle="${local}" key="local.periodText" var="periodText"/>
<f:message bundle="${local}" key="local.revenueFromSaleText" var="revenueFromSaleText"/>
<f:message bundle="${local}" key="local.nonoperationIncomeText" var="nonoperationIncomeText"/>
<f:message bundle="${local}" key="local.hasMainJobText" var="hasMainJobText"/>
<f:message bundle="${local}" key="local.yes" var="yes"/>
<f:message bundle="${local}" key="local.no" var="no"/>
<f:message bundle="${local}" key="local.hasRightsToBenefitsText" var="hasRightsToBenefitsText"/>
<f:message bundle="${local}" key="local.isWidowOrSingleParentOrTrusteeText" var="isWidowOrSingleParentOrTrusteeText"/>
<f:message bundle="${local}" key="local.numberOfChildrenUnderEighteenText" var="numberOfChildrenUnderEighteenText"/>
<f:message bundle="${local}" key="local.numberOfDisabledChildrenUnderEighteenText"
           var="numberOfDisabledChildrenUnderEighteenText"/>
<f:message bundle="${local}" key="local.numberOfDependentsText" var="numberOfDependentsText"/>
<f:message bundle="${local}" key="local.sumOfExpensesOnBusinessActivityText" var="sumOfExpensesOnBusinessActivityText"/>
<f:message bundle="${local}" key="local.sumOfExpensesOnConstrOrAcquisitionOfHousingForReqText"
           var="sumOfExpensesOnConstrOrAcquisitionOfHousingForReqText"/>
<f:message bundle="${local}" key="local.sumOfExpensesOnFirstPaidEducationForRelativeText"
           var="sumOfExpensesOnFirstPaidEducationForRelativeText"/>
<f:message bundle="${local}" key="local.sumOfExpensesOnInsurancePremiumsText"
           var="sumOfExpensesOnInsurancePremiumsText"/>
<f:message bundle="${local}" key="local.calculateButton" var="calculateButton"/>
<f:message bundle="${local}" key="local.incomeTaxText" var="incomeTaxText"/>

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
                <li><a href="${pageContext.request.contextPath}/Controller?command=viewStatistic&page=1&sortBy=id">${statistic}</a></li>
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

<div class="container pt">
    <div class="row mt">
        <div class="col-lg-6 col-lg-offset-3 centered">
            <h3>${calculatorFullName}</h3>
            <hr>
            <p>${forIndividual}</p>
        </div>
    </div>
    <form class="form-horizontal" id="calculationForm">
        <div class="row mt">
            <div class="col-lg-6">
                <div class="form-group">
                    <label for="period" class="col-xs-8 control-label">${periodText}:</label>
                    <div class="col-xs-4">
                        <select class="form-control input" id="period">
                            <c:forEach var="period" items="${requestScope.periods}">
                                <option value="${period.value}">${period.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label for="revenueFromSale" class="col-xs-8 control-label">${revenueFromSaleText}:</label>
                    <div class="col-xs-4 input-group">
                        <span class="input-group-addon">BYN</span>
                        <input type="text" class="form-control input" id="revenueFromSale"
                               name="revenueFromSale" data-val-pattern="^\d{1,11}(\.\d{0,2})?$"
                               data-val-error="${error}"
                               data-val-required="${emptyField}"
                               aria-invalid="false" aria-checked="false" aria-required="true"/>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label for="nonoperatingIncome"
                           class="col-xs-8 control-label">${nonoperationIncomeText}:</label>
                    <div class="col-xs-4 input-group">
                        <span class="input-group-addon">BYN</span>
                        <input type="text" class="form-control input" id="nonoperatingIncome"
                               name="nonoperatingIncome" data-val-pattern="^\d{1,11}(\.\d{0,2})?$"
                               data-val-error="${error}"
                               data-val-required="${emptyField}"
                               aria-invalid="false" aria-checked="false" aria-required="true">
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label class="col-xs-8 control-label">${hasMainJobText}:</label>
                    <div class="col-xs-4 btn-group" data-toggle="buttons">
                        <label class="btn btn-primary btn-sm">
                            <input type="radio" name="hasMainJob" id="hasMainJob" autocomplete="off" value="1"> ${yes}
                        </label>
                        <label class="btn btn-primary active btn-sm">
                            <input type="radio" name="hasMainJob" id="hasNotMainJob" autocomplete="off" value="0" checked> ${no}
                        </label>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label class="col-xs-8 control-label">${hasRightsToBenefitsText}:</label>
                    <div class="col-xs-4 btn-group" data-toggle="buttons">
                        <label class="btn btn-primary btn-sm">
                            <input type="radio" name="hasRightToBenefits" id="hasRightToBenefits"
                                   autocomplete="off" value="1"> ${yes}
                        </label>
                        <label class="btn btn-primary active btn-sm">
                            <input type="radio" name="hasRightToBenefits" id="hasNotRightToBenefits"
                                   autocomplete="off" value="0" checked>
                            ${no}
                        </label>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label class="col-xs-8 control-label">${isWidowOrSingleParentOrTrusteeText}:</label>
                    <div class="col-xs-4 btn-group" data-toggle="buttons">
                        <label class="btn btn-primary btn-sm">
                            <input type="radio" name="isWidowOrSingleParentOrTrustee"
                                   id="isWidowOrSingleParentOrTrustee" autocomplete="off" value="1"> ${yes}
                        </label>
                        <label class="btn btn-primary active btn-sm">
                            <input type="radio" name="isWidowOrSingleParentOrTrustee"
                                   id="isNotWidowOrSingleParentOrTrustee" autocomplete="off" value="0"
                                   checked>
                            ${no}
                        </label>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label for="numberOfChildrenUnderEighteen"
                           class="col-xs-8 control-label">${numberOfChildrenUnderEighteenText}:</label>
                    <div class="col-xs-4 input-group">
                        <input type="text" class="form-control" id="numberOfChildrenUnderEighteen"
                               name="numberOfChildrenUnderEighteen" data-val-pattern="^\d{1,2}$"
                               data-val-error="${error}"
                               data-val-required="${emptyField}"
                               aria-invalid="false" aria-checked="false" aria-required="true">
                        <span class="input-group-btn">
                            <button class="btn btn-default plusBtn" type="button"
                                    field="numberOfChildrenUnderEighteen">+
                            </button>
                            <button class="btn btn-default minusBtn" type="button"
                                    field="numberOfChildrenUnderEighteen">-
                            </button>
                        </span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="numberOfDisabledChildrenUnderEighteen"
                           class="col-xs-8 control-label">${numberOfDisabledChildrenUnderEighteenText}:</label>
                    <div class="col-xs-4 input-group">
                        <input type="text" class="form-control" id="numberOfDisabledChildrenUnderEighteen"
                               name="numberOfDisabledChildrenUnderEighteen" data-val-pattern="^\d{1,2}$"
                               data-val-error="${error}"
                               data-val-required="${emptyField}"
                               aria-invalid="false" aria-checked="false" aria-required="true">
                        <span class="input-group-btn">
                            <button class="btn btn-default plusBtn" type="button"
                                    field="numberOfDisabledChildrenUnderEighteen">+
                            </button>
                            <button class="btn btn-default minusBtn" type="button"
                                    field="numberOfDisabledChildrenUnderEighteen">-
                            </button>
                        </span>
                    </div>
                </div>

            </div>

            <div class="col-lg-6">


                <div class="form-group">
                    <label for="numberOfDependents"
                           class="col-xs-8 control-label">${numberOfDependentsText}:</label>
                    <div class="col-xs-4 input-group">
                        <input type="text" class="form-control" id="numberOfDependents"
                               name="numberOfDependents" data-val-pattern="^\d{1,2}$"
                               data-val-error="${error}"
                               data-val-required="${emptyField}"
                               aria-invalid="false" aria-checked="false" aria-required="true">
                        <span class="input-group-btn">
                            <button class="btn btn-default plusBtn" type="button" field="numberOfDependents">+</button>
                            <button class="btn btn-default minusBtn" type="button" field="numberOfDependents">-</button>
                        </span>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label for="sumOfExpensesOnInsurancePremiums"
                           class="col-xs-8 control-label">${sumOfExpensesOnInsurancePremiumsText}:</label>
                    <div class="col-xs-4 input-group">
                        <span class="input-group-addon">BYN</span>
                        <input type="text" class="form-control input" id="sumOfExpensesOnInsurancePremiums"
                               name="sumOfExpensesOnInsurancePremiums" data-val-pattern="^\d{1,11}(\.\d{0,2})?$"
                               data-val-error="${error}"
                               data-val-required="${emptyField}"
                               aria-invalid="false" aria-checked="false" aria-required="true">
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label for="sumOfExpensesOnFirstPaidEducationForRelative"
                           class="col-xs-8 control-label">${sumOfExpensesOnFirstPaidEducationForRelativeText}:</label>
                    <div class="col-xs-4 input-group">
                        <span class="input-group-addon">BYN</span>
                        <input type="text" class="form-control input"
                               id="sumOfExpensesOnFirstPaidEducationForRelative"
                               name="sumOfExpensesOnFirstPaidEducationForRelative"
                               data-val-pattern="^\d{1,11}(\.\d{0,2})?$"
                               data-val-error="${error}"
                               data-val-required="${emptyField}"
                               aria-invalid="false" aria-checked="false" aria-required="true">
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label for="sumOfExpensesOnConstrOrAcquisitionOfHousingForReq"
                           class="col-xs-8 control-label">${sumOfExpensesOnConstrOrAcquisitionOfHousingForReqText}:</label>
                    <div class="col-xs-4 input-group">
                        <span class="input-group-addon">BYN</span>
                        <input type="text" class="form-control input"
                               id="sumOfExpensesOnConstrOrAcquisitionOfHousingForReq"
                               name="sumOfExpensesOnConstrOrAcquisitionOfHousingForReq"
                               data-val-pattern="^\d{1,11}(\.\d{0,2})?$"
                               data-val-error="${error}"
                               data-val-required="${emptyField}"
                               aria-invalid="false" aria-checked="false" aria-required="true">
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label for="sumOfExpensesOnBusinessActivity"
                           class="col-xs-8 control-label">${sumOfExpensesOnBusinessActivityText}:</label>
                    <div class="col-xs-4 input-group">
                        <span class="input-group-addon">BYN</span>
                        <input type="text" class="form-control input" id="sumOfExpensesOnBusinessActivity"
                               name="sumOfExpensesOnBusinessActivity" data-val-pattern="^\d{1,11}(\.\d{0,2})?$"
                               data-val-error="${error}"
                               data-val-required="${emptyField}"
                               aria-invalid="false" aria-checked="false" aria-required="true">
                    </div>
                </div>
            </div>
        </div>

        <div class="row mt">
            <div class="form-group text-center">
                <input type="button" class="btn btn-success" value="${calculateButton}" onclick="validateCalculationForm()">
            </div>
        </div>
    </form>

    <div class="form-group col-lg-6 col-lg-offset-3 centered">
        <label for="incomeTax" class="col-xs-6 control-label">${incomeTaxText}:</label>
        <div class="col-xs-6 input-group">
            <span class="input-group-addon">BYN</span>
            <input type="text" class="form-control input-lg" id="incomeTax"
                   name="incomeTax" readonly>
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
