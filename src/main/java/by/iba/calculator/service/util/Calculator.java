package by.iba.calculator.service.util;

import by.iba.calculator.bean.entity.Calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by ASUS on 04.03.2017.
 */
public class Calculator {

    private static final int MONEY_SCALE = 2;
    private static final BigDecimal INTEREST_RATE = new BigDecimal(0.16);
    private static final BigDecimal MAX_VALUE_OF_INCOME_MINUS_EXPENSES_ON_BUSINESS = new BigDecimal(1502.00);
    private static final BigDecimal TAX_DEDUCTION = new BigDecimal(83.00);
    private static final BigDecimal TAX_DEDUCTION_IN_CASE_OF_BENEFITS = new BigDecimal(117.00);
    private static final BigDecimal FIRST_TAX_DEDUCTION_FOR_CHILDREN_AND_DEPENDENTS = new BigDecimal(24.00);
    private static final BigDecimal SECOND_TAX_DEDUCTION_FOR_CHILDREN_AND_DEPENDENTS = new BigDecimal(46.00);
    private static final BigDecimal MAX_SUM_OF_EXPENSES_ON_INSURANCE_PREMIUMS = new BigDecimal(1600.00);

    public static BigDecimal calculateIncomeTax(Calculation calculation) {
        if (calculation.isHasMainJob()) {
            return calculateIncomeTaxWithMainJobOption(calculation);
        } else {
            return calculateIncomeTaxWithoutMainJobOption(calculation);
        }
    }

    private static BigDecimal calculateIncomeTaxWithMainJobOption(Calculation calculation) {
        return calculation.getRevenueFromSale()
                .add(calculation.getNonoperatingIncome())
                .subtract(calculation.getSumOfExpensesOnBusinessActivity())
                .multiply(INTEREST_RATE)
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }

    private static BigDecimal calculateIncomeTaxWithoutMainJobOption(Calculation calculation) {
        BigDecimal sumOfIncome = calculation.getRevenueFromSale().add(calculation.getNonoperatingIncome());

        BigDecimal period = new BigDecimal(calculation.getPeriod().getValue());

        BigDecimal secondStep;
        if (sumOfIncome.subtract(calculation.getSumOfExpensesOnBusinessActivity())
                .compareTo(MAX_VALUE_OF_INCOME_MINUS_EXPENSES_ON_BUSINESS) <= 0) {
            secondStep = sumOfIncome.subtract(TAX_DEDUCTION
                    .multiply(period)
                    .setScale(MONEY_SCALE, RoundingMode.HALF_UP)
            );
        } else {
            secondStep = sumOfIncome;
        }
        if (secondStep.compareTo(BigDecimal.ZERO) < 0) {
            secondStep = BigDecimal.ZERO;
        }

        BigDecimal thirdStep;
        if (calculation.getHasRightToBenefits()) {
            thirdStep = secondStep.subtract(TAX_DEDUCTION_IN_CASE_OF_BENEFITS
                    .multiply(period)
                    .setScale(MONEY_SCALE, RoundingMode.HALF_UP)
            );
        } else {
            thirdStep = secondStep;
        }
        if (thirdStep.compareTo(BigDecimal.ZERO) < 0) {
            thirdStep = BigDecimal.ZERO;
        }

        BigDecimal fourthStep;
        BigDecimal numberOfChildrenAndDependents =
                new BigDecimal(calculation.getNumberOfChildrenUnderEighteen() +
                        calculation.getNumberOfDependents());
        if (numberOfChildrenAndDependents.compareTo(BigDecimal.ZERO) == 0) {
            fourthStep = thirdStep;
        } else {
            if (calculation.getIsWidowOrSingleParentOrTrustee() || calculation.getNumberOfChildrenUnderEighteen() > 1) {
                fourthStep = thirdStep.subtract(period
                        .multiply(SECOND_TAX_DEDUCTION_FOR_CHILDREN_AND_DEPENDENTS)
                        .multiply(numberOfChildrenAndDependents)
                        .setScale(MONEY_SCALE, RoundingMode.HALF_UP)
                );
            } else {
                if (calculation.getNumberOfChildrenUnderEighteen() == 1 &&
                        calculation.getNumberOfDisabledChildrenUnderEighteen() == 1) {
                    fourthStep = thirdStep.subtract(period
                            .multiply(SECOND_TAX_DEDUCTION_FOR_CHILDREN_AND_DEPENDENTS
                                    .multiply(new BigDecimal(calculation.getNumberOfChildrenUnderEighteen()))
                                    .add(FIRST_TAX_DEDUCTION_FOR_CHILDREN_AND_DEPENDENTS
                                            .multiply(new BigDecimal(calculation.getNumberOfDependents())))
                            )
                            .setScale(MONEY_SCALE, RoundingMode.HALF_UP)
                    );
                } else {
                    fourthStep = thirdStep.subtract(period
                            .multiply(FIRST_TAX_DEDUCTION_FOR_CHILDREN_AND_DEPENDENTS)
                            .multiply(numberOfChildrenAndDependents)
                            .setScale(MONEY_SCALE, RoundingMode.HALF_UP)
                    );
                }
            }
        }
        if (fourthStep.compareTo(BigDecimal.ZERO) < 0) {
            fourthStep = BigDecimal.ZERO;
        }

        BigDecimal fifthStep;
        if (calculation.getSumOfExpensesOnInsurancePremiums()
                .compareTo(MAX_SUM_OF_EXPENSES_ON_INSURANCE_PREMIUMS) <= 0){
            fifthStep = fourthStep.subtract(calculation.getSumOfExpensesOnInsurancePremiums());
        } else {
            fifthStep = fourthStep.subtract(MAX_SUM_OF_EXPENSES_ON_INSURANCE_PREMIUMS);
        }

        if (fifthStep.compareTo(BigDecimal.ZERO) < 0) {
            fifthStep = BigDecimal.ZERO;
        }

        BigDecimal sixthStep = fifthStep.subtract(calculation.getSumOfExpensesOnFirstPaidEducationForRelative())
                .subtract(calculation.getSumOfExpensesOnConstrOrAcquisitionOfHousingForReq())
                .subtract(calculation.getSumOfExpensesOnBusinessActivity());
        if (sixthStep.compareTo(BigDecimal.ZERO) < 0) {
            sixthStep = BigDecimal.ZERO;
        }

        return sixthStep.multiply(INTEREST_RATE)
                .setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }
}
