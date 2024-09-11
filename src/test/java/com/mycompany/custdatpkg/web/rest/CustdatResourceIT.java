package com.mycompany.custdatpkg.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.custdatpkg.IntegrationTest;
import com.mycompany.custdatpkg.domain.Custdat;
import com.mycompany.custdatpkg.repository.CustdatRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CustdatResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CustdatResourceIT {

    private static final Long DEFAULT_CUSTDAT_ID = 1L;
    private static final Long UPDATED_CUSTDAT_ID = 2L;

    private static final Integer DEFAULT_CUST_ID = 9;
    private static final Integer UPDATED_CUST_ID = 8;

    private static final String DEFAULT_CUST_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUST_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUST_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUST_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_ADDR_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_CUST_ADDR_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_ADDR_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_CUST_ADDR_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_ADDR_LINE_3 = "AAAAAAAAAA";
    private static final String UPDATED_CUST_ADDR_LINE_3 = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_ADDR_STATE_CD = "AA";
    private static final String UPDATED_CUST_ADDR_STATE_CD = "BB";

    private static final String DEFAULT_CUST_ADDR_COUNTRY_CD = "AAA";
    private static final String UPDATED_CUST_ADDR_COUNTRY_CD = "BBB";

    private static final String DEFAULT_CUST_ADDR_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_CUST_ADDR_ZIP = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_PHONE_NUM_1 = "AAAAAAAAAA";
    private static final String UPDATED_CUST_PHONE_NUM_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_PHONE_NUM_2 = "AAAAAAAAAA";
    private static final String UPDATED_CUST_PHONE_NUM_2 = "BBBBBBBBBB";

    private static final Integer DEFAULT_CUST_SSN = 9;
    private static final Integer UPDATED_CUST_SSN = 8;

    private static final String DEFAULT_CUST_GOVT_ISSUED_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUST_GOVT_ISSUED_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_DOB_YYYY_MM_DD = "AAAAAAAAAA";
    private static final String UPDATED_CUST_DOB_YYYY_MM_DD = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_EFT_ACCOUNT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUST_EFT_ACCOUNT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUST_PRI_CARD_HOLDER_IND = "A";
    private static final String UPDATED_CUST_PRI_CARD_HOLDER_IND = "B";

    private static final Integer DEFAULT_CUST_FICO_CREDIT_SCORE = 3;
    private static final Integer UPDATED_CUST_FICO_CREDIT_SCORE = 2;

    private static final String DEFAULT_FILLER = "AAAAAAAAAA";
    private static final String UPDATED_FILLER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/custdats";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CustdatRepository custdatRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustdatMockMvc;

    private Custdat custdat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Custdat createEntity(EntityManager em) {
        Custdat custdat = new Custdat()
            .custdatId(DEFAULT_CUSTDAT_ID)
            .custId(DEFAULT_CUST_ID)
            .custFirstName(DEFAULT_CUST_FIRST_NAME)
            .custMiddleName(DEFAULT_CUST_MIDDLE_NAME)
            .custLastName(DEFAULT_CUST_LAST_NAME)
            .custAddrLine1(DEFAULT_CUST_ADDR_LINE_1)
            .custAddrLine2(DEFAULT_CUST_ADDR_LINE_2)
            .custAddrLine3(DEFAULT_CUST_ADDR_LINE_3)
            .custAddrStateCd(DEFAULT_CUST_ADDR_STATE_CD)
            .custAddrCountryCd(DEFAULT_CUST_ADDR_COUNTRY_CD)
            .custAddrZip(DEFAULT_CUST_ADDR_ZIP)
            .custPhoneNum1(DEFAULT_CUST_PHONE_NUM_1)
            .custPhoneNum2(DEFAULT_CUST_PHONE_NUM_2)
            .custSsn(DEFAULT_CUST_SSN)
            .custGovtIssuedId(DEFAULT_CUST_GOVT_ISSUED_ID)
            .custDobYyyyMmDd(DEFAULT_CUST_DOB_YYYY_MM_DD)
            .custEftAccountId(DEFAULT_CUST_EFT_ACCOUNT_ID)
            .custPriCardHolderInd(DEFAULT_CUST_PRI_CARD_HOLDER_IND)
            .custFicoCreditScore(DEFAULT_CUST_FICO_CREDIT_SCORE)
            .filler(DEFAULT_FILLER);
        return custdat;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Custdat createUpdatedEntity(EntityManager em) {
        Custdat custdat = new Custdat()
            .custdatId(UPDATED_CUSTDAT_ID)
            .custId(UPDATED_CUST_ID)
            .custFirstName(UPDATED_CUST_FIRST_NAME)
            .custMiddleName(UPDATED_CUST_MIDDLE_NAME)
            .custLastName(UPDATED_CUST_LAST_NAME)
            .custAddrLine1(UPDATED_CUST_ADDR_LINE_1)
            .custAddrLine2(UPDATED_CUST_ADDR_LINE_2)
            .custAddrLine3(UPDATED_CUST_ADDR_LINE_3)
            .custAddrStateCd(UPDATED_CUST_ADDR_STATE_CD)
            .custAddrCountryCd(UPDATED_CUST_ADDR_COUNTRY_CD)
            .custAddrZip(UPDATED_CUST_ADDR_ZIP)
            .custPhoneNum1(UPDATED_CUST_PHONE_NUM_1)
            .custPhoneNum2(UPDATED_CUST_PHONE_NUM_2)
            .custSsn(UPDATED_CUST_SSN)
            .custGovtIssuedId(UPDATED_CUST_GOVT_ISSUED_ID)
            .custDobYyyyMmDd(UPDATED_CUST_DOB_YYYY_MM_DD)
            .custEftAccountId(UPDATED_CUST_EFT_ACCOUNT_ID)
            .custPriCardHolderInd(UPDATED_CUST_PRI_CARD_HOLDER_IND)
            .custFicoCreditScore(UPDATED_CUST_FICO_CREDIT_SCORE)
            .filler(UPDATED_FILLER);
        return custdat;
    }

    @BeforeEach
    public void initTest() {
        custdat = createEntity(em);
    }

    @Test
    @Transactional
    void createCustdat() throws Exception {
        int databaseSizeBeforeCreate = custdatRepository.findAll().size();
        // Create the Custdat
        restCustdatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(custdat)))
            .andExpect(status().isCreated());

        // Validate the Custdat in the database
        List<Custdat> custdatList = custdatRepository.findAll();
        assertThat(custdatList).hasSize(databaseSizeBeforeCreate + 1);
        Custdat testCustdat = custdatList.get(custdatList.size() - 1);
        assertThat(testCustdat.getCustdatId()).isEqualTo(DEFAULT_CUSTDAT_ID);
        assertThat(testCustdat.getCustId()).isEqualTo(DEFAULT_CUST_ID);
        assertThat(testCustdat.getCustFirstName()).isEqualTo(DEFAULT_CUST_FIRST_NAME);
        assertThat(testCustdat.getCustMiddleName()).isEqualTo(DEFAULT_CUST_MIDDLE_NAME);
        assertThat(testCustdat.getCustLastName()).isEqualTo(DEFAULT_CUST_LAST_NAME);
        assertThat(testCustdat.getCustAddrLine1()).isEqualTo(DEFAULT_CUST_ADDR_LINE_1);
        assertThat(testCustdat.getCustAddrLine2()).isEqualTo(DEFAULT_CUST_ADDR_LINE_2);
        assertThat(testCustdat.getCustAddrLine3()).isEqualTo(DEFAULT_CUST_ADDR_LINE_3);
        assertThat(testCustdat.getCustAddrStateCd()).isEqualTo(DEFAULT_CUST_ADDR_STATE_CD);
        assertThat(testCustdat.getCustAddrCountryCd()).isEqualTo(DEFAULT_CUST_ADDR_COUNTRY_CD);
        assertThat(testCustdat.getCustAddrZip()).isEqualTo(DEFAULT_CUST_ADDR_ZIP);
        assertThat(testCustdat.getCustPhoneNum1()).isEqualTo(DEFAULT_CUST_PHONE_NUM_1);
        assertThat(testCustdat.getCustPhoneNum2()).isEqualTo(DEFAULT_CUST_PHONE_NUM_2);
        assertThat(testCustdat.getCustSsn()).isEqualTo(DEFAULT_CUST_SSN);
        assertThat(testCustdat.getCustGovtIssuedId()).isEqualTo(DEFAULT_CUST_GOVT_ISSUED_ID);
        assertThat(testCustdat.getCustDobYyyyMmDd()).isEqualTo(DEFAULT_CUST_DOB_YYYY_MM_DD);
        assertThat(testCustdat.getCustEftAccountId()).isEqualTo(DEFAULT_CUST_EFT_ACCOUNT_ID);
        assertThat(testCustdat.getCustPriCardHolderInd()).isEqualTo(DEFAULT_CUST_PRI_CARD_HOLDER_IND);
        assertThat(testCustdat.getCustFicoCreditScore()).isEqualTo(DEFAULT_CUST_FICO_CREDIT_SCORE);
        assertThat(testCustdat.getFiller()).isEqualTo(DEFAULT_FILLER);
    }

    @Test
    @Transactional
    void createCustdatWithExistingId() throws Exception {
        // Create the Custdat with an existing ID
        custdat.setId(1L);

        int databaseSizeBeforeCreate = custdatRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustdatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(custdat)))
            .andExpect(status().isBadRequest());

        // Validate the Custdat in the database
        List<Custdat> custdatList = custdatRepository.findAll();
        assertThat(custdatList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCustdats() throws Exception {
        // Initialize the database
        custdatRepository.saveAndFlush(custdat);

        // Get all the custdatList
        restCustdatMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(custdat.getId().intValue())))
            .andExpect(jsonPath("$.[*].custdatId").value(hasItem(DEFAULT_CUSTDAT_ID.intValue())))
            .andExpect(jsonPath("$.[*].custId").value(hasItem(DEFAULT_CUST_ID)))
            .andExpect(jsonPath("$.[*].custFirstName").value(hasItem(DEFAULT_CUST_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].custMiddleName").value(hasItem(DEFAULT_CUST_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].custLastName").value(hasItem(DEFAULT_CUST_LAST_NAME)))
            .andExpect(jsonPath("$.[*].custAddrLine1").value(hasItem(DEFAULT_CUST_ADDR_LINE_1)))
            .andExpect(jsonPath("$.[*].custAddrLine2").value(hasItem(DEFAULT_CUST_ADDR_LINE_2)))
            .andExpect(jsonPath("$.[*].custAddrLine3").value(hasItem(DEFAULT_CUST_ADDR_LINE_3)))
            .andExpect(jsonPath("$.[*].custAddrStateCd").value(hasItem(DEFAULT_CUST_ADDR_STATE_CD)))
            .andExpect(jsonPath("$.[*].custAddrCountryCd").value(hasItem(DEFAULT_CUST_ADDR_COUNTRY_CD)))
            .andExpect(jsonPath("$.[*].custAddrZip").value(hasItem(DEFAULT_CUST_ADDR_ZIP)))
            .andExpect(jsonPath("$.[*].custPhoneNum1").value(hasItem(DEFAULT_CUST_PHONE_NUM_1)))
            .andExpect(jsonPath("$.[*].custPhoneNum2").value(hasItem(DEFAULT_CUST_PHONE_NUM_2)))
            .andExpect(jsonPath("$.[*].custSsn").value(hasItem(DEFAULT_CUST_SSN)))
            .andExpect(jsonPath("$.[*].custGovtIssuedId").value(hasItem(DEFAULT_CUST_GOVT_ISSUED_ID)))
            .andExpect(jsonPath("$.[*].custDobYyyyMmDd").value(hasItem(DEFAULT_CUST_DOB_YYYY_MM_DD)))
            .andExpect(jsonPath("$.[*].custEftAccountId").value(hasItem(DEFAULT_CUST_EFT_ACCOUNT_ID)))
            .andExpect(jsonPath("$.[*].custPriCardHolderInd").value(hasItem(DEFAULT_CUST_PRI_CARD_HOLDER_IND)))
            .andExpect(jsonPath("$.[*].custFicoCreditScore").value(hasItem(DEFAULT_CUST_FICO_CREDIT_SCORE)))
            .andExpect(jsonPath("$.[*].filler").value(hasItem(DEFAULT_FILLER)));
    }

    @Test
    @Transactional
    void getCustdat() throws Exception {
        // Initialize the database
        custdatRepository.saveAndFlush(custdat);

        // Get the custdat
        restCustdatMockMvc
            .perform(get(ENTITY_API_URL_ID, custdat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(custdat.getId().intValue()))
            .andExpect(jsonPath("$.custdatId").value(DEFAULT_CUSTDAT_ID.intValue()))
            .andExpect(jsonPath("$.custId").value(DEFAULT_CUST_ID))
            .andExpect(jsonPath("$.custFirstName").value(DEFAULT_CUST_FIRST_NAME))
            .andExpect(jsonPath("$.custMiddleName").value(DEFAULT_CUST_MIDDLE_NAME))
            .andExpect(jsonPath("$.custLastName").value(DEFAULT_CUST_LAST_NAME))
            .andExpect(jsonPath("$.custAddrLine1").value(DEFAULT_CUST_ADDR_LINE_1))
            .andExpect(jsonPath("$.custAddrLine2").value(DEFAULT_CUST_ADDR_LINE_2))
            .andExpect(jsonPath("$.custAddrLine3").value(DEFAULT_CUST_ADDR_LINE_3))
            .andExpect(jsonPath("$.custAddrStateCd").value(DEFAULT_CUST_ADDR_STATE_CD))
            .andExpect(jsonPath("$.custAddrCountryCd").value(DEFAULT_CUST_ADDR_COUNTRY_CD))
            .andExpect(jsonPath("$.custAddrZip").value(DEFAULT_CUST_ADDR_ZIP))
            .andExpect(jsonPath("$.custPhoneNum1").value(DEFAULT_CUST_PHONE_NUM_1))
            .andExpect(jsonPath("$.custPhoneNum2").value(DEFAULT_CUST_PHONE_NUM_2))
            .andExpect(jsonPath("$.custSsn").value(DEFAULT_CUST_SSN))
            .andExpect(jsonPath("$.custGovtIssuedId").value(DEFAULT_CUST_GOVT_ISSUED_ID))
            .andExpect(jsonPath("$.custDobYyyyMmDd").value(DEFAULT_CUST_DOB_YYYY_MM_DD))
            .andExpect(jsonPath("$.custEftAccountId").value(DEFAULT_CUST_EFT_ACCOUNT_ID))
            .andExpect(jsonPath("$.custPriCardHolderInd").value(DEFAULT_CUST_PRI_CARD_HOLDER_IND))
            .andExpect(jsonPath("$.custFicoCreditScore").value(DEFAULT_CUST_FICO_CREDIT_SCORE))
            .andExpect(jsonPath("$.filler").value(DEFAULT_FILLER));
    }

    @Test
    @Transactional
    void getNonExistingCustdat() throws Exception {
        // Get the custdat
        restCustdatMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCustdat() throws Exception {
        // Initialize the database
        custdatRepository.saveAndFlush(custdat);

        int databaseSizeBeforeUpdate = custdatRepository.findAll().size();

        // Update the custdat
        Custdat updatedCustdat = custdatRepository.findById(custdat.getId()).get();
        // Disconnect from session so that the updates on updatedCustdat are not directly saved in db
        em.detach(updatedCustdat);
        updatedCustdat
            .custdatId(UPDATED_CUSTDAT_ID)
            .custId(UPDATED_CUST_ID)
            .custFirstName(UPDATED_CUST_FIRST_NAME)
            .custMiddleName(UPDATED_CUST_MIDDLE_NAME)
            .custLastName(UPDATED_CUST_LAST_NAME)
            .custAddrLine1(UPDATED_CUST_ADDR_LINE_1)
            .custAddrLine2(UPDATED_CUST_ADDR_LINE_2)
            .custAddrLine3(UPDATED_CUST_ADDR_LINE_3)
            .custAddrStateCd(UPDATED_CUST_ADDR_STATE_CD)
            .custAddrCountryCd(UPDATED_CUST_ADDR_COUNTRY_CD)
            .custAddrZip(UPDATED_CUST_ADDR_ZIP)
            .custPhoneNum1(UPDATED_CUST_PHONE_NUM_1)
            .custPhoneNum2(UPDATED_CUST_PHONE_NUM_2)
            .custSsn(UPDATED_CUST_SSN)
            .custGovtIssuedId(UPDATED_CUST_GOVT_ISSUED_ID)
            .custDobYyyyMmDd(UPDATED_CUST_DOB_YYYY_MM_DD)
            .custEftAccountId(UPDATED_CUST_EFT_ACCOUNT_ID)
            .custPriCardHolderInd(UPDATED_CUST_PRI_CARD_HOLDER_IND)
            .custFicoCreditScore(UPDATED_CUST_FICO_CREDIT_SCORE)
            .filler(UPDATED_FILLER);

        restCustdatMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCustdat.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCustdat))
            )
            .andExpect(status().isOk());

        // Validate the Custdat in the database
        List<Custdat> custdatList = custdatRepository.findAll();
        assertThat(custdatList).hasSize(databaseSizeBeforeUpdate);
        Custdat testCustdat = custdatList.get(custdatList.size() - 1);
        assertThat(testCustdat.getCustdatId()).isEqualTo(UPDATED_CUSTDAT_ID);
        assertThat(testCustdat.getCustId()).isEqualTo(UPDATED_CUST_ID);
        assertThat(testCustdat.getCustFirstName()).isEqualTo(UPDATED_CUST_FIRST_NAME);
        assertThat(testCustdat.getCustMiddleName()).isEqualTo(UPDATED_CUST_MIDDLE_NAME);
        assertThat(testCustdat.getCustLastName()).isEqualTo(UPDATED_CUST_LAST_NAME);
        assertThat(testCustdat.getCustAddrLine1()).isEqualTo(UPDATED_CUST_ADDR_LINE_1);
        assertThat(testCustdat.getCustAddrLine2()).isEqualTo(UPDATED_CUST_ADDR_LINE_2);
        assertThat(testCustdat.getCustAddrLine3()).isEqualTo(UPDATED_CUST_ADDR_LINE_3);
        assertThat(testCustdat.getCustAddrStateCd()).isEqualTo(UPDATED_CUST_ADDR_STATE_CD);
        assertThat(testCustdat.getCustAddrCountryCd()).isEqualTo(UPDATED_CUST_ADDR_COUNTRY_CD);
        assertThat(testCustdat.getCustAddrZip()).isEqualTo(UPDATED_CUST_ADDR_ZIP);
        assertThat(testCustdat.getCustPhoneNum1()).isEqualTo(UPDATED_CUST_PHONE_NUM_1);
        assertThat(testCustdat.getCustPhoneNum2()).isEqualTo(UPDATED_CUST_PHONE_NUM_2);
        assertThat(testCustdat.getCustSsn()).isEqualTo(UPDATED_CUST_SSN);
        assertThat(testCustdat.getCustGovtIssuedId()).isEqualTo(UPDATED_CUST_GOVT_ISSUED_ID);
        assertThat(testCustdat.getCustDobYyyyMmDd()).isEqualTo(UPDATED_CUST_DOB_YYYY_MM_DD);
        assertThat(testCustdat.getCustEftAccountId()).isEqualTo(UPDATED_CUST_EFT_ACCOUNT_ID);
        assertThat(testCustdat.getCustPriCardHolderInd()).isEqualTo(UPDATED_CUST_PRI_CARD_HOLDER_IND);
        assertThat(testCustdat.getCustFicoCreditScore()).isEqualTo(UPDATED_CUST_FICO_CREDIT_SCORE);
        assertThat(testCustdat.getFiller()).isEqualTo(UPDATED_FILLER);
    }

    @Test
    @Transactional
    void putNonExistingCustdat() throws Exception {
        int databaseSizeBeforeUpdate = custdatRepository.findAll().size();
        custdat.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustdatMockMvc
            .perform(
                put(ENTITY_API_URL_ID, custdat.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(custdat))
            )
            .andExpect(status().isBadRequest());

        // Validate the Custdat in the database
        List<Custdat> custdatList = custdatRepository.findAll();
        assertThat(custdatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCustdat() throws Exception {
        int databaseSizeBeforeUpdate = custdatRepository.findAll().size();
        custdat.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustdatMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(custdat))
            )
            .andExpect(status().isBadRequest());

        // Validate the Custdat in the database
        List<Custdat> custdatList = custdatRepository.findAll();
        assertThat(custdatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCustdat() throws Exception {
        int databaseSizeBeforeUpdate = custdatRepository.findAll().size();
        custdat.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustdatMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(custdat)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Custdat in the database
        List<Custdat> custdatList = custdatRepository.findAll();
        assertThat(custdatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCustdatWithPatch() throws Exception {
        // Initialize the database
        custdatRepository.saveAndFlush(custdat);

        int databaseSizeBeforeUpdate = custdatRepository.findAll().size();

        // Update the custdat using partial update
        Custdat partialUpdatedCustdat = new Custdat();
        partialUpdatedCustdat.setId(custdat.getId());

        partialUpdatedCustdat
            .custdatId(UPDATED_CUSTDAT_ID)
            .custMiddleName(UPDATED_CUST_MIDDLE_NAME)
            .custAddrCountryCd(UPDATED_CUST_ADDR_COUNTRY_CD)
            .custAddrZip(UPDATED_CUST_ADDR_ZIP)
            .custPhoneNum1(UPDATED_CUST_PHONE_NUM_1)
            .custSsn(UPDATED_CUST_SSN)
            .custGovtIssuedId(UPDATED_CUST_GOVT_ISSUED_ID)
            .custDobYyyyMmDd(UPDATED_CUST_DOB_YYYY_MM_DD)
            .custPriCardHolderInd(UPDATED_CUST_PRI_CARD_HOLDER_IND)
            .filler(UPDATED_FILLER);

        restCustdatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustdat.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCustdat))
            )
            .andExpect(status().isOk());

        // Validate the Custdat in the database
        List<Custdat> custdatList = custdatRepository.findAll();
        assertThat(custdatList).hasSize(databaseSizeBeforeUpdate);
        Custdat testCustdat = custdatList.get(custdatList.size() - 1);
        assertThat(testCustdat.getCustdatId()).isEqualTo(UPDATED_CUSTDAT_ID);
        assertThat(testCustdat.getCustId()).isEqualTo(DEFAULT_CUST_ID);
        assertThat(testCustdat.getCustFirstName()).isEqualTo(DEFAULT_CUST_FIRST_NAME);
        assertThat(testCustdat.getCustMiddleName()).isEqualTo(UPDATED_CUST_MIDDLE_NAME);
        assertThat(testCustdat.getCustLastName()).isEqualTo(DEFAULT_CUST_LAST_NAME);
        assertThat(testCustdat.getCustAddrLine1()).isEqualTo(DEFAULT_CUST_ADDR_LINE_1);
        assertThat(testCustdat.getCustAddrLine2()).isEqualTo(DEFAULT_CUST_ADDR_LINE_2);
        assertThat(testCustdat.getCustAddrLine3()).isEqualTo(DEFAULT_CUST_ADDR_LINE_3);
        assertThat(testCustdat.getCustAddrStateCd()).isEqualTo(DEFAULT_CUST_ADDR_STATE_CD);
        assertThat(testCustdat.getCustAddrCountryCd()).isEqualTo(UPDATED_CUST_ADDR_COUNTRY_CD);
        assertThat(testCustdat.getCustAddrZip()).isEqualTo(UPDATED_CUST_ADDR_ZIP);
        assertThat(testCustdat.getCustPhoneNum1()).isEqualTo(UPDATED_CUST_PHONE_NUM_1);
        assertThat(testCustdat.getCustPhoneNum2()).isEqualTo(DEFAULT_CUST_PHONE_NUM_2);
        assertThat(testCustdat.getCustSsn()).isEqualTo(UPDATED_CUST_SSN);
        assertThat(testCustdat.getCustGovtIssuedId()).isEqualTo(UPDATED_CUST_GOVT_ISSUED_ID);
        assertThat(testCustdat.getCustDobYyyyMmDd()).isEqualTo(UPDATED_CUST_DOB_YYYY_MM_DD);
        assertThat(testCustdat.getCustEftAccountId()).isEqualTo(DEFAULT_CUST_EFT_ACCOUNT_ID);
        assertThat(testCustdat.getCustPriCardHolderInd()).isEqualTo(UPDATED_CUST_PRI_CARD_HOLDER_IND);
        assertThat(testCustdat.getCustFicoCreditScore()).isEqualTo(DEFAULT_CUST_FICO_CREDIT_SCORE);
        assertThat(testCustdat.getFiller()).isEqualTo(UPDATED_FILLER);
    }

    @Test
    @Transactional
    void fullUpdateCustdatWithPatch() throws Exception {
        // Initialize the database
        custdatRepository.saveAndFlush(custdat);

        int databaseSizeBeforeUpdate = custdatRepository.findAll().size();

        // Update the custdat using partial update
        Custdat partialUpdatedCustdat = new Custdat();
        partialUpdatedCustdat.setId(custdat.getId());

        partialUpdatedCustdat
            .custdatId(UPDATED_CUSTDAT_ID)
            .custId(UPDATED_CUST_ID)
            .custFirstName(UPDATED_CUST_FIRST_NAME)
            .custMiddleName(UPDATED_CUST_MIDDLE_NAME)
            .custLastName(UPDATED_CUST_LAST_NAME)
            .custAddrLine1(UPDATED_CUST_ADDR_LINE_1)
            .custAddrLine2(UPDATED_CUST_ADDR_LINE_2)
            .custAddrLine3(UPDATED_CUST_ADDR_LINE_3)
            .custAddrStateCd(UPDATED_CUST_ADDR_STATE_CD)
            .custAddrCountryCd(UPDATED_CUST_ADDR_COUNTRY_CD)
            .custAddrZip(UPDATED_CUST_ADDR_ZIP)
            .custPhoneNum1(UPDATED_CUST_PHONE_NUM_1)
            .custPhoneNum2(UPDATED_CUST_PHONE_NUM_2)
            .custSsn(UPDATED_CUST_SSN)
            .custGovtIssuedId(UPDATED_CUST_GOVT_ISSUED_ID)
            .custDobYyyyMmDd(UPDATED_CUST_DOB_YYYY_MM_DD)
            .custEftAccountId(UPDATED_CUST_EFT_ACCOUNT_ID)
            .custPriCardHolderInd(UPDATED_CUST_PRI_CARD_HOLDER_IND)
            .custFicoCreditScore(UPDATED_CUST_FICO_CREDIT_SCORE)
            .filler(UPDATED_FILLER);

        restCustdatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustdat.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCustdat))
            )
            .andExpect(status().isOk());

        // Validate the Custdat in the database
        List<Custdat> custdatList = custdatRepository.findAll();
        assertThat(custdatList).hasSize(databaseSizeBeforeUpdate);
        Custdat testCustdat = custdatList.get(custdatList.size() - 1);
        assertThat(testCustdat.getCustdatId()).isEqualTo(UPDATED_CUSTDAT_ID);
        assertThat(testCustdat.getCustId()).isEqualTo(UPDATED_CUST_ID);
        assertThat(testCustdat.getCustFirstName()).isEqualTo(UPDATED_CUST_FIRST_NAME);
        assertThat(testCustdat.getCustMiddleName()).isEqualTo(UPDATED_CUST_MIDDLE_NAME);
        assertThat(testCustdat.getCustLastName()).isEqualTo(UPDATED_CUST_LAST_NAME);
        assertThat(testCustdat.getCustAddrLine1()).isEqualTo(UPDATED_CUST_ADDR_LINE_1);
        assertThat(testCustdat.getCustAddrLine2()).isEqualTo(UPDATED_CUST_ADDR_LINE_2);
        assertThat(testCustdat.getCustAddrLine3()).isEqualTo(UPDATED_CUST_ADDR_LINE_3);
        assertThat(testCustdat.getCustAddrStateCd()).isEqualTo(UPDATED_CUST_ADDR_STATE_CD);
        assertThat(testCustdat.getCustAddrCountryCd()).isEqualTo(UPDATED_CUST_ADDR_COUNTRY_CD);
        assertThat(testCustdat.getCustAddrZip()).isEqualTo(UPDATED_CUST_ADDR_ZIP);
        assertThat(testCustdat.getCustPhoneNum1()).isEqualTo(UPDATED_CUST_PHONE_NUM_1);
        assertThat(testCustdat.getCustPhoneNum2()).isEqualTo(UPDATED_CUST_PHONE_NUM_2);
        assertThat(testCustdat.getCustSsn()).isEqualTo(UPDATED_CUST_SSN);
        assertThat(testCustdat.getCustGovtIssuedId()).isEqualTo(UPDATED_CUST_GOVT_ISSUED_ID);
        assertThat(testCustdat.getCustDobYyyyMmDd()).isEqualTo(UPDATED_CUST_DOB_YYYY_MM_DD);
        assertThat(testCustdat.getCustEftAccountId()).isEqualTo(UPDATED_CUST_EFT_ACCOUNT_ID);
        assertThat(testCustdat.getCustPriCardHolderInd()).isEqualTo(UPDATED_CUST_PRI_CARD_HOLDER_IND);
        assertThat(testCustdat.getCustFicoCreditScore()).isEqualTo(UPDATED_CUST_FICO_CREDIT_SCORE);
        assertThat(testCustdat.getFiller()).isEqualTo(UPDATED_FILLER);
    }

    @Test
    @Transactional
    void patchNonExistingCustdat() throws Exception {
        int databaseSizeBeforeUpdate = custdatRepository.findAll().size();
        custdat.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustdatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, custdat.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(custdat))
            )
            .andExpect(status().isBadRequest());

        // Validate the Custdat in the database
        List<Custdat> custdatList = custdatRepository.findAll();
        assertThat(custdatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCustdat() throws Exception {
        int databaseSizeBeforeUpdate = custdatRepository.findAll().size();
        custdat.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustdatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(custdat))
            )
            .andExpect(status().isBadRequest());

        // Validate the Custdat in the database
        List<Custdat> custdatList = custdatRepository.findAll();
        assertThat(custdatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCustdat() throws Exception {
        int databaseSizeBeforeUpdate = custdatRepository.findAll().size();
        custdat.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustdatMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(custdat)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Custdat in the database
        List<Custdat> custdatList = custdatRepository.findAll();
        assertThat(custdatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCustdat() throws Exception {
        // Initialize the database
        custdatRepository.saveAndFlush(custdat);

        int databaseSizeBeforeDelete = custdatRepository.findAll().size();

        // Delete the custdat
        restCustdatMockMvc
            .perform(delete(ENTITY_API_URL_ID, custdat.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Custdat> custdatList = custdatRepository.findAll();
        assertThat(custdatList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
