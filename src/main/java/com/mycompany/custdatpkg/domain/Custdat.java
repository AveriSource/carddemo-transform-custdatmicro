package com.mycompany.custdatpkg.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Custdat.
 */
@Entity
@Table(name = "custdat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Custdat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "custdat_id")
    private Long custdatId;

    @Max(value = 9)
    @Column(name = "cust_id")
    private Integer custId;

    @Size(max = 25)
    @Column(name = "cust_first_name", length = 25)
    private String custFirstName;

    @Size(max = 25)
    @Column(name = "cust_middle_name", length = 25)
    private String custMiddleName;

    @Size(max = 25)
    @Column(name = "cust_last_name", length = 25)
    private String custLastName;

    @Size(max = 50)
    @Column(name = "cust_addr_line_1", length = 50)
    private String custAddrLine1;

    @Size(max = 50)
    @Column(name = "cust_addr_line_2", length = 50)
    private String custAddrLine2;

    @Size(max = 50)
    @Column(name = "cust_addr_line_3", length = 50)
    private String custAddrLine3;

    @Size(max = 2)
    @Column(name = "cust_addr_state_cd", length = 2)
    private String custAddrStateCd;

    @Size(max = 3)
    @Column(name = "cust_addr_country_cd", length = 3)
    private String custAddrCountryCd;

    @Size(max = 10)
    @Column(name = "cust_addr_zip", length = 10)
    private String custAddrZip;

    @Size(max = 15)
    @Column(name = "cust_phone_num_1", length = 15)
    private String custPhoneNum1;

    @Size(max = 15)
    @Column(name = "cust_phone_num_2", length = 15)
    private String custPhoneNum2;

    @Max(value = 9)
    @Column(name = "cust_ssn")
    private Integer custSsn;

    @Size(max = 20)
    @Column(name = "cust_govt_issued_id", length = 20)
    private String custGovtIssuedId;

    @Size(max = 10)
    @Column(name = "cust_dob_yyyy_mm_dd", length = 10)
    private String custDobYyyyMmDd;

    @Size(max = 10)
    @Column(name = "cust_eft_account_id", length = 10)
    private String custEftAccountId;

    @Size(max = 1)
    @Column(name = "cust_pri_card_holder_ind", length = 1)
    private String custPriCardHolderInd;

    @Max(value = 3)
    @Column(name = "cust_fico_credit_score")
    private Integer custFicoCreditScore;

    @Size(max = 168)
    @Column(name = "filler", length = 168)
    private String filler;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Custdat id(Long id) {
        this.id = id;
        return this;
    }

    public Long getCustdatId() {
        return this.custdatId;
    }

    public Custdat custdatId(Long custdatId) {
        this.custdatId = custdatId;
        return this;
    }

    public void setCustdatId(Long custdatId) {
        this.custdatId = custdatId;
    }

    public Integer getCustId() {
        return this.custId;
    }

    public Custdat custId(Integer custId) {
        this.custId = custId;
        return this;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCustFirstName() {
        return this.custFirstName;
    }

    public Custdat custFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
        return this;
    }

    public void setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
    }

    public String getCustMiddleName() {
        return this.custMiddleName;
    }

    public Custdat custMiddleName(String custMiddleName) {
        this.custMiddleName = custMiddleName;
        return this;
    }

    public void setCustMiddleName(String custMiddleName) {
        this.custMiddleName = custMiddleName;
    }

    public String getCustLastName() {
        return this.custLastName;
    }

    public Custdat custLastName(String custLastName) {
        this.custLastName = custLastName;
        return this;
    }

    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    public String getCustAddrLine1() {
        return this.custAddrLine1;
    }

    public Custdat custAddrLine1(String custAddrLine1) {
        this.custAddrLine1 = custAddrLine1;
        return this;
    }

    public void setCustAddrLine1(String custAddrLine1) {
        this.custAddrLine1 = custAddrLine1;
    }

    public String getCustAddrLine2() {
        return this.custAddrLine2;
    }

    public Custdat custAddrLine2(String custAddrLine2) {
        this.custAddrLine2 = custAddrLine2;
        return this;
    }

    public void setCustAddrLine2(String custAddrLine2) {
        this.custAddrLine2 = custAddrLine2;
    }

    public String getCustAddrLine3() {
        return this.custAddrLine3;
    }

    public Custdat custAddrLine3(String custAddrLine3) {
        this.custAddrLine3 = custAddrLine3;
        return this;
    }

    public void setCustAddrLine3(String custAddrLine3) {
        this.custAddrLine3 = custAddrLine3;
    }

    public String getCustAddrStateCd() {
        return this.custAddrStateCd;
    }

    public Custdat custAddrStateCd(String custAddrStateCd) {
        this.custAddrStateCd = custAddrStateCd;
        return this;
    }

    public void setCustAddrStateCd(String custAddrStateCd) {
        this.custAddrStateCd = custAddrStateCd;
    }

    public String getCustAddrCountryCd() {
        return this.custAddrCountryCd;
    }

    public Custdat custAddrCountryCd(String custAddrCountryCd) {
        this.custAddrCountryCd = custAddrCountryCd;
        return this;
    }

    public void setCustAddrCountryCd(String custAddrCountryCd) {
        this.custAddrCountryCd = custAddrCountryCd;
    }

    public String getCustAddrZip() {
        return this.custAddrZip;
    }

    public Custdat custAddrZip(String custAddrZip) {
        this.custAddrZip = custAddrZip;
        return this;
    }

    public void setCustAddrZip(String custAddrZip) {
        this.custAddrZip = custAddrZip;
    }

    public String getCustPhoneNum1() {
        return this.custPhoneNum1;
    }

    public Custdat custPhoneNum1(String custPhoneNum1) {
        this.custPhoneNum1 = custPhoneNum1;
        return this;
    }

    public void setCustPhoneNum1(String custPhoneNum1) {
        this.custPhoneNum1 = custPhoneNum1;
    }

    public String getCustPhoneNum2() {
        return this.custPhoneNum2;
    }

    public Custdat custPhoneNum2(String custPhoneNum2) {
        this.custPhoneNum2 = custPhoneNum2;
        return this;
    }

    public void setCustPhoneNum2(String custPhoneNum2) {
        this.custPhoneNum2 = custPhoneNum2;
    }

    public Integer getCustSsn() {
        return this.custSsn;
    }

    public Custdat custSsn(Integer custSsn) {
        this.custSsn = custSsn;
        return this;
    }

    public void setCustSsn(Integer custSsn) {
        this.custSsn = custSsn;
    }

    public String getCustGovtIssuedId() {
        return this.custGovtIssuedId;
    }

    public Custdat custGovtIssuedId(String custGovtIssuedId) {
        this.custGovtIssuedId = custGovtIssuedId;
        return this;
    }

    public void setCustGovtIssuedId(String custGovtIssuedId) {
        this.custGovtIssuedId = custGovtIssuedId;
    }

    public String getCustDobYyyyMmDd() {
        return this.custDobYyyyMmDd;
    }

    public Custdat custDobYyyyMmDd(String custDobYyyyMmDd) {
        this.custDobYyyyMmDd = custDobYyyyMmDd;
        return this;
    }

    public void setCustDobYyyyMmDd(String custDobYyyyMmDd) {
        this.custDobYyyyMmDd = custDobYyyyMmDd;
    }

    public String getCustEftAccountId() {
        return this.custEftAccountId;
    }

    public Custdat custEftAccountId(String custEftAccountId) {
        this.custEftAccountId = custEftAccountId;
        return this;
    }

    public void setCustEftAccountId(String custEftAccountId) {
        this.custEftAccountId = custEftAccountId;
    }

    public String getCustPriCardHolderInd() {
        return this.custPriCardHolderInd;
    }

    public Custdat custPriCardHolderInd(String custPriCardHolderInd) {
        this.custPriCardHolderInd = custPriCardHolderInd;
        return this;
    }

    public void setCustPriCardHolderInd(String custPriCardHolderInd) {
        this.custPriCardHolderInd = custPriCardHolderInd;
    }

    public Integer getCustFicoCreditScore() {
        return this.custFicoCreditScore;
    }

    public Custdat custFicoCreditScore(Integer custFicoCreditScore) {
        this.custFicoCreditScore = custFicoCreditScore;
        return this;
    }

    public void setCustFicoCreditScore(Integer custFicoCreditScore) {
        this.custFicoCreditScore = custFicoCreditScore;
    }

    public String getFiller() {
        return this.filler;
    }

    public Custdat filler(String filler) {
        this.filler = filler;
        return this;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Custdat)) {
            return false;
        }
        return id != null && id.equals(((Custdat) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Custdat{" +
            "id=" + getId() +
            ", custdatId=" + getCustdatId() +
            ", custId=" + getCustId() +
            ", custFirstName='" + getCustFirstName() + "'" +
            ", custMiddleName='" + getCustMiddleName() + "'" +
            ", custLastName='" + getCustLastName() + "'" +
            ", custAddrLine1='" + getCustAddrLine1() + "'" +
            ", custAddrLine2='" + getCustAddrLine2() + "'" +
            ", custAddrLine3='" + getCustAddrLine3() + "'" +
            ", custAddrStateCd='" + getCustAddrStateCd() + "'" +
            ", custAddrCountryCd='" + getCustAddrCountryCd() + "'" +
            ", custAddrZip='" + getCustAddrZip() + "'" +
            ", custPhoneNum1='" + getCustPhoneNum1() + "'" +
            ", custPhoneNum2='" + getCustPhoneNum2() + "'" +
            ", custSsn=" + getCustSsn() +
            ", custGovtIssuedId='" + getCustGovtIssuedId() + "'" +
            ", custDobYyyyMmDd='" + getCustDobYyyyMmDd() + "'" +
            ", custEftAccountId='" + getCustEftAccountId() + "'" +
            ", custPriCardHolderInd='" + getCustPriCardHolderInd() + "'" +
            ", custFicoCreditScore=" + getCustFicoCreditScore() +
            ", filler='" + getFiller() + "'" +
            "}";
    }
}
