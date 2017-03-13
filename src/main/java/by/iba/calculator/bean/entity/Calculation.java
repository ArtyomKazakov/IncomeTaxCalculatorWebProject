package by.iba.calculator.bean.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Calculation implements Serializable {
    private int id;
    private Period period;
    private BigDecimal revenueFromSale;
    private BigDecimal nonoperatingIncome;
    private boolean hasMainJob;
    private Boolean hasRightToBenefits;
    private Boolean isWidowOrSingleParentOrTrustee;
    private Integer numberOfChildrenUnderEighteen;
    private Integer numberOfDisabledChildrenUnderEighteen;
    private Integer numberOfDependents;
    private BigDecimal sumOfExpensesOnInsurancePremiums;
    private BigDecimal sumOfExpensesOnFirstPaidEducationForRelative;
    private BigDecimal sumOfExpensesOnConstrOrAcquisitionOfHousingForReq;
    private BigDecimal sumOfExpensesOnBusinessActivity;
    private BigDecimal incomeTax;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public BigDecimal getRevenueFromSale() {
        return revenueFromSale;
    }

    public void setRevenueFromSale(BigDecimal revenueFromSale) {
        this.revenueFromSale = revenueFromSale;
    }

    public BigDecimal getNonoperatingIncome() {
        return nonoperatingIncome;
    }

    public void setNonoperatingIncome(BigDecimal nonoperatingIncome) {
        this.nonoperatingIncome = nonoperatingIncome;
    }

    public boolean isHasMainJob() {
        return hasMainJob;
    }

    public void setHasMainJob(boolean hasMainJob) {
        this.hasMainJob = hasMainJob;
    }

    public Boolean getHasRightToBenefits() {
        return hasRightToBenefits;
    }

    public void setHasRightToBenefits(Boolean hasRightToBenefits) {
        this.hasRightToBenefits = hasRightToBenefits;
    }

    public Boolean getIsWidowOrSingleParentOrTrustee() {
        return isWidowOrSingleParentOrTrustee;
    }

    public void setWidowOrSingleParentOrTrustee(Boolean widowOrSingleParentOrTrustee) {
        isWidowOrSingleParentOrTrustee = widowOrSingleParentOrTrustee;
    }

    public Integer getNumberOfChildrenUnderEighteen() {
        return numberOfChildrenUnderEighteen;
    }

    public void setNumberOfChildrenUnderEighteen(Integer numberOfChildrenUnderEighteen) {
        this.numberOfChildrenUnderEighteen = numberOfChildrenUnderEighteen;
    }

    public Integer getNumberOfDisabledChildrenUnderEighteen() {
        return numberOfDisabledChildrenUnderEighteen;
    }

    public void setNumberOfDisabledChildrenUnderEighteen(Integer numberOfDisabledChildrenUnderEighteen) {
        this.numberOfDisabledChildrenUnderEighteen = numberOfDisabledChildrenUnderEighteen;
    }

    public Integer getNumberOfDependents() {
        return numberOfDependents;
    }

    public void setNumberOfDependents(Integer numberOfDependents) {
        this.numberOfDependents = numberOfDependents;
    }

    public BigDecimal getSumOfExpensesOnInsurancePremiums() {
        return sumOfExpensesOnInsurancePremiums;
    }

    public void setSumOfExpensesOnInsurancePremiums(BigDecimal sumOfExpensesOnInsurancePremiums) {
        this.sumOfExpensesOnInsurancePremiums = sumOfExpensesOnInsurancePremiums;
    }

    public BigDecimal getSumOfExpensesOnFirstPaidEducationForRelative() {
        return sumOfExpensesOnFirstPaidEducationForRelative;
    }

    public void setSumOfExpensesOnFirstPaidEducationForRelative(BigDecimal sumOfExpensesOnFirstPaidEducationForRelative) {
        this.sumOfExpensesOnFirstPaidEducationForRelative = sumOfExpensesOnFirstPaidEducationForRelative;
    }

    public BigDecimal getSumOfExpensesOnConstrOrAcquisitionOfHousingForReq() {
        return sumOfExpensesOnConstrOrAcquisitionOfHousingForReq;
    }

    public void setSumOfExpensesOnConstrOrAcquisitionOfHousingForReq(BigDecimal sumOfExpensesOnConstrOrAcquisitionOfHousingForReq) {
        this.sumOfExpensesOnConstrOrAcquisitionOfHousingForReq = sumOfExpensesOnConstrOrAcquisitionOfHousingForReq;
    }

    public BigDecimal getSumOfExpensesOnBusinessActivity() {
        return sumOfExpensesOnBusinessActivity;
    }

    public void setSumOfExpensesOnBusinessActivity(BigDecimal sumOfExpensesOnBusinessActivity) {
        this.sumOfExpensesOnBusinessActivity = sumOfExpensesOnBusinessActivity;
    }

    public BigDecimal getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(BigDecimal incomeTax) {
        this.incomeTax = incomeTax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Calculation that = (Calculation) o;

        if (id != that.id) return false;
        if (hasMainJob != that.hasMainJob) return false;
        if (numberOfChildrenUnderEighteen != that.numberOfChildrenUnderEighteen) return false;
        if (numberOfDisabledChildrenUnderEighteen != that.numberOfDisabledChildrenUnderEighteen) return false;
        if (numberOfDependents != that.numberOfDependents) return false;
        if (period != null ? !period.equals(that.period) : that.period != null) return false;
        if (revenueFromSale != null ? !revenueFromSale.equals(that.revenueFromSale) : that.revenueFromSale != null)
            return false;
        if (nonoperatingIncome != null ? !nonoperatingIncome.equals(that.nonoperatingIncome) : that.nonoperatingIncome != null)
            return false;
        if (hasRightToBenefits != null ? !hasRightToBenefits.equals(that.hasRightToBenefits) : that.hasRightToBenefits != null)
            return false;
        if (isWidowOrSingleParentOrTrustee != null ? !isWidowOrSingleParentOrTrustee.equals(that.isWidowOrSingleParentOrTrustee) : that.isWidowOrSingleParentOrTrustee != null)
            return false;
        if (sumOfExpensesOnInsurancePremiums != null ? !sumOfExpensesOnInsurancePremiums.equals(that.sumOfExpensesOnInsurancePremiums) : that.sumOfExpensesOnInsurancePremiums != null)
            return false;
        if (sumOfExpensesOnFirstPaidEducationForRelative != null ? !sumOfExpensesOnFirstPaidEducationForRelative.equals(that.sumOfExpensesOnFirstPaidEducationForRelative) : that.sumOfExpensesOnFirstPaidEducationForRelative != null)
            return false;
        if (sumOfExpensesOnConstrOrAcquisitionOfHousingForReq != null ? !sumOfExpensesOnConstrOrAcquisitionOfHousingForReq.equals(that.sumOfExpensesOnConstrOrAcquisitionOfHousingForReq) : that.sumOfExpensesOnConstrOrAcquisitionOfHousingForReq != null)
            return false;
        if (sumOfExpensesOnBusinessActivity != null ? !sumOfExpensesOnBusinessActivity.equals(that.sumOfExpensesOnBusinessActivity) : that.sumOfExpensesOnBusinessActivity != null)
            return false;
        return incomeTax != null ? incomeTax.equals(that.incomeTax) : that.incomeTax == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (period != null ? period.hashCode() : 0);
        result = 31 * result + (revenueFromSale != null ? revenueFromSale.hashCode() : 0);
        result = 31 * result + (nonoperatingIncome != null ? nonoperatingIncome.hashCode() : 0);
        result = 31 * result + (hasMainJob ? 1 : 0);
        result = 31 * result + (hasRightToBenefits != null ? hasRightToBenefits.hashCode() : 0);
        result = 31 * result + (isWidowOrSingleParentOrTrustee != null ? isWidowOrSingleParentOrTrustee.hashCode() : 0);
        result = 31 * result + numberOfChildrenUnderEighteen;
        result = 31 * result + numberOfDisabledChildrenUnderEighteen;
        result = 31 * result + numberOfDependents;
        result = 31 * result + (sumOfExpensesOnInsurancePremiums != null ? sumOfExpensesOnInsurancePremiums.hashCode() : 0);
        result = 31 * result + (sumOfExpensesOnFirstPaidEducationForRelative != null ? sumOfExpensesOnFirstPaidEducationForRelative.hashCode() : 0);
        result = 31 * result + (sumOfExpensesOnConstrOrAcquisitionOfHousingForReq != null ? sumOfExpensesOnConstrOrAcquisitionOfHousingForReq.hashCode() : 0);
        result = 31 * result + (sumOfExpensesOnBusinessActivity != null ? sumOfExpensesOnBusinessActivity.hashCode() : 0);
        result = 31 * result + (incomeTax != null ? incomeTax.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Calculation{" +
                "id=" + id +
                ", period=" + period +
                ", revenueFromSale=" + revenueFromSale +
                ", nonoperatingIncome=" + nonoperatingIncome +
                ", hasMainJob=" + hasMainJob +
                ", hasRightToBenefits=" + hasRightToBenefits +
                ", isWidowOrSingleParentOrTrustee=" + isWidowOrSingleParentOrTrustee +
                ", numberOfChildrenUnderEighteen=" + numberOfChildrenUnderEighteen +
                ", numberOfDisabledChildrenUnderEighteen=" + numberOfDisabledChildrenUnderEighteen +
                ", numberOfDependents=" + numberOfDependents +
                ", sumOfExpensesOnInsurancePremiums=" + sumOfExpensesOnInsurancePremiums +
                ", sumOfExpensesOnFirstPaidEducationForRelative=" + sumOfExpensesOnFirstPaidEducationForRelative +
                ", sumOfExpensesOnConstrOrAcquisitionOfHousingForReq=" + sumOfExpensesOnConstrOrAcquisitionOfHousingForReq +
                ", sumOfExpensesOnBusinessActivity=" + sumOfExpensesOnBusinessActivity +
                ", incomeTax=" + incomeTax +
                '}';
    }
}
