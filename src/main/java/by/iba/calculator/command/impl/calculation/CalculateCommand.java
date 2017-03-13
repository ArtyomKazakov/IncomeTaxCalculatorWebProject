package by.iba.calculator.command.impl.calculation;

import by.iba.calculator.bean.entity.Calculation;
import by.iba.calculator.bean.entity.Period;
import by.iba.calculator.command.Command;
import by.iba.calculator.command.util.CommandHelper;
import by.iba.calculator.service.CalculationService;
import by.iba.calculator.service.exception.ServiceException;
import by.iba.calculator.service.factory.ServiceFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Services AJAX request to calculate income tax.
 */
public class CalculateCommand implements Command {
    private final static Logger logger = Logger.getLogger(CalculateCommand.class);

    private static final String MAIN_PAGE = "/Controller?command=mainPage";

    private static final String PERIOD_ID_PARAM = "period";
    private static final String REVENUE_FROM_SALE_PARAM = "revenueFromSale";
    private static final String NONOPERATION_INCOME_PARAM = "nonoperatingIncome";
    private static final String HAS_MAIN_JOB_PARAM = "hasMainJob";
    private static final String HAS_RIGHT_TO_BENEFITS_PARAM = "hasRightToBenefits";
    private static final String IS_WIDOW_OR_SINGLE_PARENT_OR_TRUSTEE_PARAM = "isWidowOrSingleParentOrTrustee";
    private static final String NUMBER_OF_CHILDREN_UNDER_EIGHTEEN_PARAM = "numberOfChildrenUnderEighteen";
    private static final String NUMBER_OF_DISABLED_CHILDREN_UNDER_EIGHTEEN_PARAM = "numberOfDisabledChildrenUnderEighteen";
    private static final String NUMBER_OF_DEPENDENTS_PARAM = "numberOfDependents";
    private static final String SUM_OF_EXPENSES_ON_INSURANCE_PREMIUMS_PARAM = "sumOfExpensesOnInsurancePremiums";
    private static final String SUM_OF_EXPENSES_ON_FIRST_PAID_EDUCATION_FOR_RELATIVE_PARAM = "sumOfExpensesOnFirstPaidEducationForRelative";
    private static final String SUM_OF_EXPENSES_ON_CONSTR_OR_ACQUISITION_OF_HOUSING_FOR_FEQ_PARAM = "sumOfExpensesOnConstrOrAcquisitionOfHousingForReq";
    private static final String SUM_OF_EXPENSES_ON_BUSINESS_ACTIVITY_PARAM = "sumOfExpensesOnBusinessActivity";

    private static final String JSON_FORMAT = "application/json";
    private static final String SERVICE_ERROR_REQUEST_ATTR = "Server error!";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int periodId = CommandHelper.getInt(request.getParameter(PERIOD_ID_PARAM));
        BigDecimal revenueFromSale = new BigDecimal(request.getParameter(REVENUE_FROM_SALE_PARAM));
        BigDecimal nonoperatingIncome = new BigDecimal(request.getParameter(NONOPERATION_INCOME_PARAM));
        boolean hasMainJob;
        int hasMainJobNumber = CommandHelper.getInt(request.getParameter(HAS_MAIN_JOB_PARAM));
        if (hasMainJobNumber == 1 || hasMainJobNumber == 0) {
            hasMainJob = hasMainJobNumber != 0;
        } else {
            response.sendRedirect(MAIN_PAGE);
            return;
        }
        Calculation calculation;
        if (!hasMainJob) {
            Boolean hasRightToBenefits;
            int hasRightToBenefitsNumber = CommandHelper.getInt(request.getParameter(HAS_RIGHT_TO_BENEFITS_PARAM));
            if (hasRightToBenefitsNumber == 1 || hasRightToBenefitsNumber == 0) {
                hasRightToBenefits = hasRightToBenefitsNumber != 0;
            } else {
                response.sendRedirect(MAIN_PAGE);
                return;
            }
            Boolean isWidowOrSingleParentOrTrustee;
            int isWidowOrSingleParentOrTrusteeNumber = CommandHelper.getInt(request.getParameter(IS_WIDOW_OR_SINGLE_PARENT_OR_TRUSTEE_PARAM));
            if (isWidowOrSingleParentOrTrusteeNumber == 1 || isWidowOrSingleParentOrTrusteeNumber == 0) {
                isWidowOrSingleParentOrTrustee = hasRightToBenefitsNumber != 0;
            } else {
                response.sendRedirect(MAIN_PAGE);
                return;
            }
            Integer numberOfChildrenUnderEighteen = CommandHelper.getInt(request.getParameter(NUMBER_OF_CHILDREN_UNDER_EIGHTEEN_PARAM));
            Integer numberOfDisabledChildrenUnderEighteen = CommandHelper.getInt(request.getParameter(NUMBER_OF_DISABLED_CHILDREN_UNDER_EIGHTEEN_PARAM));
            Integer numberOfDependents = CommandHelper.getInt(request.getParameter(NUMBER_OF_DEPENDENTS_PARAM));
            BigDecimal sumOfExpensesOnInsurancePremiums = new BigDecimal(request.getParameter(SUM_OF_EXPENSES_ON_INSURANCE_PREMIUMS_PARAM));
            BigDecimal sumOfExpensesOnFirstPaidEducationForRelative = new BigDecimal(request.getParameter(SUM_OF_EXPENSES_ON_FIRST_PAID_EDUCATION_FOR_RELATIVE_PARAM));
            BigDecimal sumOfExpensesOnConstrOrAcquisitionOfHousingForReq = new BigDecimal(request.getParameter(SUM_OF_EXPENSES_ON_CONSTR_OR_ACQUISITION_OF_HOUSING_FOR_FEQ_PARAM));

            BigDecimal sumOfExpensesOnBusinessActivity = new BigDecimal(request.getParameter(SUM_OF_EXPENSES_ON_BUSINESS_ACTIVITY_PARAM));
            calculation = createCalculation(periodId, revenueFromSale, nonoperatingIncome,
                    false, hasRightToBenefits, isWidowOrSingleParentOrTrustee,
                    numberOfChildrenUnderEighteen, numberOfDisabledChildrenUnderEighteen,
                    numberOfDependents, sumOfExpensesOnInsurancePremiums,
                    sumOfExpensesOnFirstPaidEducationForRelative, sumOfExpensesOnConstrOrAcquisitionOfHousingForReq,
                    sumOfExpensesOnBusinessActivity);
        } else {
            BigDecimal sumOfExpensesOnBusinessActivity = new BigDecimal(request.getParameter(SUM_OF_EXPENSES_ON_BUSINESS_ACTIVITY_PARAM));
            calculation = createCalculation(periodId, revenueFromSale, nonoperatingIncome,
                    true, null, null, null, null, null, null, null, null, sumOfExpensesOnBusinessActivity);
        }

        String json;
        Gson gson = new GsonBuilder().create();

        try {
            CalculationService calculationService = ServiceFactory.getInstance().getCalculationService();
            BigDecimal incomeTax = calculationService.addCalculation(calculation);
            json = gson.toJson(incomeTax);
        } catch (ServiceException e) {
            logger.warn("While getting rental request error occurred", e);
            json = gson.toJson(SERVICE_ERROR_REQUEST_ATTR);
        }

        response.setContentType(JSON_FORMAT);
        response.getWriter().write(json);


    }

    private Calculation createCalculation(int periodId, BigDecimal revenueFromSale, BigDecimal nonoperatingIncome,
                                          boolean hasMainJob, Boolean hasRightToBenefits, Boolean isWidowOrSingleParentOrTrustee,
                                          Integer numberOfChildrenUnderEighteen, Integer numberOfDisabledChildrenUnderEighteen,
                                          Integer numberOfDependents, BigDecimal sumOfExpensesOnInsurancePremiums,
                                          BigDecimal sumOfExpensesOnFirstPaidEducationForRelative, BigDecimal sumOfExpensesOnConstrOrAcquisitionOfHousingForReq,
                                          BigDecimal sumOfExpensesOnBusinessActivity) {
        Calculation calculation = new Calculation();
        Period period = new Period();
        period.setValue(periodId);
        calculation.setPeriod(period);
        calculation.setRevenueFromSale(revenueFromSale);
        calculation.setNonoperatingIncome(nonoperatingIncome);
        calculation.setHasMainJob(hasMainJob);
        calculation.setHasRightToBenefits(hasRightToBenefits);
        calculation.setWidowOrSingleParentOrTrustee(isWidowOrSingleParentOrTrustee);
        calculation.setNumberOfChildrenUnderEighteen(numberOfChildrenUnderEighteen);
        calculation.setNumberOfDisabledChildrenUnderEighteen(numberOfDisabledChildrenUnderEighteen);
        calculation.setNumberOfDependents(numberOfDependents);
        calculation.setSumOfExpensesOnInsurancePremiums(sumOfExpensesOnInsurancePremiums);
        calculation.setSumOfExpensesOnFirstPaidEducationForRelative(sumOfExpensesOnFirstPaidEducationForRelative);
        calculation.setSumOfExpensesOnConstrOrAcquisitionOfHousingForReq(sumOfExpensesOnConstrOrAcquisitionOfHousingForReq);
        calculation.setSumOfExpensesOnBusinessActivity(sumOfExpensesOnBusinessActivity);
        return calculation;
    }
}
