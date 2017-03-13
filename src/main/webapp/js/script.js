var focusFunc = function (input) {
    if (input.attr('aria-checked') !== 'true' && input.val() !== '') {
        checkInput(input);
    }
    if (input.attr('data-val-pattern').localeCompare("^\\d{1,11}(\\.\\d{0,2})?$") == 0){
        var regEx = new RegExp('^\\d{1,11}(\\.\\d{1})$');{
            if (input.val().match(regEx)){
                input.val(input.val()+"0");
            }
        }
        regEx = new RegExp('^\\d{1,11}$');{
            if (input.val().match(regEx)){
                input.val(input.val()+".00");
            }
        }
        regEx = new RegExp('^\\d{1,11}(\\.)$');{
            if (input.val().match(regEx)){
                input.val(input.val()+"00");
            }
        }
    }
};

var keyFunc = function (input) {
    checkInput(input);
};

function checkInput(input) {
    input.attr('aria-checked', 'true');
    var $val = input.val();
    if($val === undefined){
        return true;
    }

    if($val === '' && input.attr('aria-required') === 'true'){
        resetError(input);
        showError(input, 'data-val-required');
        input.focus();
        return false;
    } else if($val.length === 1) {
        resetError(input);
    }

    var regEx = new RegExp(input.attr('data-val-pattern'));
    if (!$val.match(regEx)) {
        showError(input, 'data-val-error');
        return false;
    } else {
        if (!validateSumOfExpensesOnBusinessActivity() | !validateChildren()) {
            return false;
        }

        resetError(input);
        return true;
    }
}

function showError(input, attribute) {
    if (input.attr('aria-invalid') === 'false') {
        input.parent().addClass("has-error");
        input.parent().after('<span class="error-message text-danger col-xs-12 text-right">' + input.attr(attribute) + '</span>');
        input.attr('aria-invalid', 'true');
    }
}

function resetError(input) {
    input.parent().removeClass("has-error");
    input.parent().siblings('.error-message').remove();
    input.attr('aria-invalid', 'false');
}

var validation = function(){
    var answer = true;
    for(var i = 0; i < arguments.length; i++){
        if(!checkInput(arguments[i])){
            answer = false;
        }
    }
    return answer;
};

function validateSumOfExpensesOnBusinessActivity(){
    var sumOfExpensesOnBusinessActivity = $('input[name="sumOfExpensesOnBusinessActivity"]').val();
    var revenueFromSale = $('input[name="revenueFromSale"]').val();
    var nonoperatingIncome = $('input[name="nonoperatingIncome"]').val();
    if (parseFloat(sumOfExpensesOnBusinessActivity) > (parseFloat(revenueFromSale) + parseFloat(nonoperatingIncome))){
        showError($('input[name="revenueFromSale"]'), 'data-val-error');
        showError($('input[name="nonoperatingIncome"]'), 'data-val-error');
        showError($('input[name="sumOfExpensesOnBusinessActivity"]'), 'data-val-error');
        return false;
    } else {
        resetError($('input[name="revenueFromSale"]'));
        resetError($('input[name="nonoperatingIncome"]'));
        resetError($('input[name="sumOfExpensesOnBusinessActivity"]'));
        return true;
    }
}

function validateChildren(){
    var numberOfChildrenUnderEighteen = $('input[name="numberOfChildrenUnderEighteen"]').val();
    var numberOfDisabledChildrenUnderEighteen = $('input[name="numberOfDisabledChildrenUnderEighteen"]').val();
    if (numberOfDisabledChildrenUnderEighteen == '' || parseFloat(numberOfDisabledChildrenUnderEighteen) <= parseFloat(numberOfChildrenUnderEighteen)){
        resetError($('input[name="numberOfDisabledChildrenUnderEighteen"]'));
        return true;
    } else {
        showError($('input[name="numberOfDisabledChildrenUnderEighteen"]'), 'data-val-error');
        return false;
    }
}

$(document).ready(function(){
    $('.plusBtn').click(function(e){
        e.preventDefault();
        fieldName = $(this).attr('field');
        var currentVal = parseInt($('input[name='+fieldName+']').val());
        if (!isNaN(currentVal)) {
            if (currentVal < 99){
                $('input[name='+fieldName+']').val(currentVal + 1);
            } else {
                $('input[name='+fieldName+']').val(99);
            }
        } else {
            $('input[name='+fieldName+']').val(1);
        }
        resetError($('input[name='+fieldName+']'));
        validateChildren();
    });
    $(".minusBtn").click(function(e) {
        e.preventDefault();
        fieldName = $(this).attr('field');
        var currentVal = parseInt($('input[name='+fieldName+']').val());
        if (!isNaN(currentVal) && currentVal > 0) {
            $('input[name='+fieldName+']').val(currentVal - 1);
        } else {
            $('input[name='+fieldName+']').val(0);
        }
        resetError($('input[name='+fieldName+']'));
        validateChildren();
    });

    $('input[type=radio][name=hasMainJob]').change(function() {
        if (this.value == '1') {
            disableText($('input[name=numberOfChildrenUnderEighteen]'));
            disableText($('input[name=numberOfDisabledChildrenUnderEighteen]'));
            disableText($('input[name=numberOfDependents]'));
            disableText($('input[name=sumOfExpensesOnInsurancePremiums]'));
            disableText($('input[name=sumOfExpensesOnFirstPaidEducationForRelative]'));
            disableText($('input[name=sumOfExpensesOnConstrOrAcquisitionOfHousingForReq]'));
            disableRadio($('input[name=hasRightToBenefits]').parent());
            disableRadio($('input[name=isWidowOrSingleParentOrTrustee]').parent());
            disableButton($(".plusBtn"));
            disableButton($(".minusBtn"));
        }
        else if (this.value == '0') {
            enableText($('input[name=numberOfChildrenUnderEighteen]'));
            enableText($('input[name=numberOfDisabledChildrenUnderEighteen]'));
            enableText($('input[name=numberOfDependents]'));
            enableText($('input[name=sumOfExpensesOnInsurancePremiums]'));
            enableText($('input[name=sumOfExpensesOnFirstPaidEducationForRelative]'));
            enableText($('input[name=sumOfExpensesOnConstrOrAcquisitionOfHousingForReq]'));
            enableRadio($('input[name=hasRightToBenefits]').parent());
            enableRadio($('input[name=isWidowOrSingleParentOrTrustee]').parent());
            enableButton($(".plusBtn"));
            enableButton($(".minusBtn"));
        }
    });

});


function disableText(input){
    input.attr('readonly', 'true');
    input.val('');
    input.attr('aria-required', 'false');
    resetError(input);
}

function enableText(input){
    input.removeAttr('readonly');
    input.attr('aria-required', 'true');
}

function disableRadio(input){
    input.addClass("disabled");
}

function enableRadio(input){
    input.removeClass("disabled");
}

function disableButton(input){
    input.attr('disabled', 'disabled');
}

function enableButton(input){
    input.removeAttr('disabled', 'disabled');
}

function calculateWithoutMainJob(){
    $.ajax({
        type: 'POST',
        url:  'Controller',
        data: {command:'calculate', period: $('select option:selected').val(), revenueFromSale: $('#revenueFromSale').val(),
            nonoperatingIncome: $('#nonoperatingIncome').val(), hasMainJob: $('.active > input[name=hasMainJob]').val(),
            hasRightToBenefits: $('.active > input[name=hasRightToBenefits]').val(),
            isWidowOrSingleParentOrTrustee: $('.active > input[name=isWidowOrSingleParentOrTrustee]').val(),
            numberOfChildrenUnderEighteen: $('#numberOfChildrenUnderEighteen').val(),
            numberOfDisabledChildrenUnderEighteen: $('#numberOfDisabledChildrenUnderEighteen').val(),
            numberOfDependents: $('#numberOfDependents').val(),
            sumOfExpensesOnInsurancePremiums: $('#sumOfExpensesOnInsurancePremiums').val(),
            sumOfExpensesOnFirstPaidEducationForRelative: $('#sumOfExpensesOnFirstPaidEducationForRelative').val(),
            sumOfExpensesOnConstrOrAcquisitionOfHousingForReq: $('#sumOfExpensesOnConstrOrAcquisitionOfHousingForReq').val(),
            sumOfExpensesOnBusinessActivity: $('#sumOfExpensesOnBusinessActivity').val()},
        success: function (data) {
            $('#incomeTax').val(data);
        }
    });
}

function calculateWithMainJob(){
    $.ajax({
        type: 'POST',
        url:  'Controller',
        data: {command:'calculate', period: $('select option:selected').val(), revenueFromSale: $('#revenueFromSale').val(),
            nonoperatingIncome: $('#nonoperatingIncome').val(), hasMainJob: $('.active > input[name=hasMainJob]').val(),
            sumOfExpensesOnBusinessActivity: $('#sumOfExpensesOnBusinessActivity').val()},
        success: function (data) {
            $('#incomeTax').val(data);
        }
    });
}