$(document).ready(function () {
    var $inputs = $('input[type="text"]');
    $inputs.on('focusout', function () {
        focusFunc($(this));
    });

    $inputs.on('input', function () {
        keyFunc($(this));
    });
});

function validateCalculationForm() {

    if ($('.active > input[name=hasMainJob]').val() == 0) {
        if (validation.apply(null, [$('input[name="revenueFromSale"]'),
                $('input[name="nonoperatingIncome"]'),
                $('input[name="numberOfDependents"]'),
                $('input[name="numberOfDisabledChildrenUnderEighteen"]'),
                $('input[name="numberOfChildrenUnderEighteen"]'),
                $('input[name="sumOfExpensesOnInsurancePremiums"]'),
                $('input[name="sumOfExpensesOnFirstPaidEducationForRelative"]'),
                $('input[name="sumOfExpensesOnConstrOrAcquisitionOfHousingForReq"]'),
                $('input[name="sumOfExpensesOnBusinessActivity"]')])) {
            calculateWithoutMainJob();
            return true;
        } else {
            return false;
        }
    } else {
        if (validation.apply(null, [$('input[name="revenueFromSale"]'),
                $('input[name="nonoperatingIncome"]'),
                $('input[name="sumOfExpensesOnBusinessActivity"]')])) {
            calculateWithMainJob();
            return true;
        } else {
            return false;
        }
    }

}