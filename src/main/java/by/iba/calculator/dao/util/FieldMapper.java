package by.iba.calculator.dao.util;

import by.iba.calculator.dao.exception.DAOException;

import java.util.HashMap;
import java.util.Map;

public class FieldMapper {
    private static final FieldMapper INSTANCE = new FieldMapper();
    private Map<String, String> fields;

    private FieldMapper(){
        fields = new HashMap<>();
        fields.put("id", "id");
        fields.put("period", "period_value");
        fields.put("revenueFromSale", "revenue_from_sale");
        fields.put("nonoperatingIncome", "nonoperating_income");
        fields.put("hasMainJob", "has_main_job");
        fields.put("hasRightToBenefits", "has_right_to_benefits");
        fields.put("isWidowOrSingleParentOrTrustee", "is_widow_or_single_parent_or_trustee");
        fields.put("numberOfChildrenUnderEighteen", "number_of_children_under_eighteen");
        fields.put("numberOfDisabledChildrenUnderEighteen", "number_of_disabled_children_under_eighteen");
        fields.put("numberOfDependents", "number_of_dependents");
        fields.put("sumOfExpensesOnInsurancePremiums", "sum_of_expenses_on_insurance_premiums");
        fields.put("sumOfExpensesOnFirstPaidEducationForRelative", "sum_of_expenses_on_first_paid_education_for_relative");
        fields.put("sumOfExpensesOnConstrOrAcquisitionOfHousingForReq", "sum_of_expenses_on_constr_or_acquisition_of_housing_for_req");
        fields.put("sumOfExpensesOnBusinessActivity", "sum_of_expenses_on_business_activity");
        fields.put("incomeTax", "income_tax");
    }

    /**
     * Returns {@link FieldMapper} instance
     * @return Mapper instance
     */
    public static FieldMapper getInstance(){
        return INSTANCE;
    }

    /**
     * Adds command to the system
     * @param beanField name of bean field
     * @param daoField name of dao field
     */
    public void addField(String beanField, String daoField){
        fields.put(beanField, daoField);
    }

    /**
     * Returns name of dao field by name of bean field
     * @param beanField name of bean field
     * @return name of dao field
     */
    public String getField(String beanField) throws DAOException {
        String daoField = fields.get(beanField);
        if(daoField != null) {
            return daoField;
        } else{
            throw new DAOException("Wrong field name");
        }
    }
}
