package by.iba.calculator.service.util;

import by.iba.calculator.bean.entity.Calculation;

import java.math.BigDecimal;

/**
 * Makes validation of all input data.
 */
public class Validator {

    private static final int MONEY_PRECISION = 13;
    private static final int MONEY_SCALE = 2;
    private static final int MAX_NUMBER_OF_CHILDREN_OR_DEPENDENTS = 99;

    /**
     * Checks calculation parameters.
     *
     * @param calculation a calculation
     * @return {@code true} if all data ara valid, and {@code false} otherwise
     */
    public static boolean validateCalculation(Calculation calculation) {
        return calculation.isHasMainJob() ?
                validateCalculationsWithMainJobOption(calculation) :
                validateCalculationsWithoutMainJobOption(calculation);

    }

    /**
     * Checks calculation parameters with main job parameter.
     *
     * @param calculation a calculation
     * @return {@code true} if all data ara valid, and {@code false} otherwise
     */
    private static boolean validateCalculationsWithMainJobOption(Calculation calculation) {
        return validateSimilarCalculationsData(calculation) &&
                calculation.getHasRightToBenefits() == null &&
                calculation.getIsWidowOrSingleParentOrTrustee() == null &&
                validateIsNull(calculation.getNumberOfChildrenUnderEighteen()) &&
                validateIsNull(calculation.getNumberOfDisabledChildrenUnderEighteen()) &&
                validateIsNull(calculation.getNumberOfDependents()) &&
                validateIsNull(calculation.getSumOfExpensesOnConstrOrAcquisitionOfHousingForReq()) &&
                validateIsNull(calculation.getSumOfExpensesOnFirstPaidEducationForRelative()) &&
                validateIsNull(calculation.getSumOfExpensesOnInsurancePremiums());
    }

    /**
     * Checks similar calculation.
     *
     * @param calculation a calculation
     * @return {@code true} if all data ara valid, and {@code false} otherwise
     */
    private static boolean validateSimilarCalculationsData(Calculation calculation) {
        return validateInt(calculation.getPeriod().getValue()) &&
                validateMoney(calculation.getRevenueFromSale()) &&
                validateMoney(calculation.getNonoperatingIncome()) &&
                validateMoney(calculation.getSumOfExpensesOnBusinessActivity()) &&
                calculation.getSumOfExpensesOnBusinessActivity()
                        .compareTo(calculation.getRevenueFromSale()
                                .add(calculation.getNonoperatingIncome())) <= 0;
    }

    /**
     * Checks calculation parameters without main job parameter.
     *
     * @param calculation a calculation
     * @return {@code true} if all data ara valid, and {@code false} otherwise
     */
    private static boolean validateCalculationsWithoutMainJobOption(Calculation calculation) {
        return validateSimilarCalculationsData(calculation) &&
                calculation.getHasRightToBenefits() != null &&
                calculation.getIsWidowOrSingleParentOrTrustee() != null &&
                validateChildrenOrDependents(calculation.getNumberOfChildrenUnderEighteen()) &&
                validateChildrenOrDependents(calculation.getNumberOfDisabledChildrenUnderEighteen()) &&
                calculation.getNumberOfChildrenUnderEighteen() >= calculation.getNumberOfDisabledChildrenUnderEighteen() &&
                validateChildrenOrDependents(calculation.getNumberOfDependents()) &&
                validateMoney(calculation.getSumOfExpensesOnConstrOrAcquisitionOfHousingForReq()) &&
                validateMoney(calculation.getSumOfExpensesOnFirstPaidEducationForRelative()) &&
                validateMoney(calculation.getSumOfExpensesOnInsurancePremiums());
    }

    /**
     * Checks number of children or dependents.
     *
     * @param value a number of children or dependents
     * @return {@code true} if all data ara valid, and {@code false} otherwise
     */
    public static boolean validateChildrenOrDependents(Integer value) {
        return validateInteger(value) && value <= MAX_NUMBER_OF_CHILDREN_OR_DEPENDENTS;
    }

    /**
     * Checks {@link int} for more than zero.
     *
     * @param value a {@link int} value
     * @return {@code true} if value is  more than 0 and {@code false} otherwise
     */
    public static boolean validateInt(int value) {
        return value > 0;
    }

    /**
     * Checks {@link Integer} for not null value and more or equal than zero.
     *
     * @param value a {@link Integer} value
     * @return {@code true} if value is  more than 0 and {@code false} otherwise
     */
    public static boolean validateInteger(Integer value) {
        return value != null && value >= 0;
    }

    /**
     * Checks {@link BigDecimal} for more or equal than zero, precision and scale.
     *
     * @param money a {@link BigDecimal} value
     * @return {@code true} if value is  more than 0 and {@code false} otherwise
     */
    public static boolean validateMoney(BigDecimal money) {
        return money != null &&
                money.precision() <= MONEY_PRECISION &&
                money.scale() == MONEY_SCALE &&
                money.compareTo(BigDecimal.ZERO) >= 0;
    }

    /**
     * Checks {@link Object} for null.
     *
     * @param object a {@link Object} object
     * @return {@code true} if object is null and {@code false} otherwise
     */
    public static boolean validateIsNull(Object object) {
        return object == null;
    }
}
