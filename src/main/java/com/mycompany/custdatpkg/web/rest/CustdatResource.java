package com.mycompany.custdatpkg.web.rest;

import com.mycompany.custdatpkg.domain.Custdat;
import com.mycompany.custdatpkg.repository.CustdatRepository;
import com.mycompany.custdatpkg.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.custdatpkg.domain.Custdat}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CustdatResource {

    private final Logger log = LoggerFactory.getLogger(CustdatResource.class);

    private static final String ENTITY_NAME = "custdatmicroCustdat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustdatRepository custdatRepository;

    public CustdatResource(CustdatRepository custdatRepository) {
        this.custdatRepository = custdatRepository;
    }

    /**
     * {@code POST  /custdats} : Create a new custdat.
     *
     * @param custdat the custdat to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new custdat, or with status {@code 400 (Bad Request)} if the custdat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/custdats")
    public ResponseEntity<Custdat> createCustdat(@Valid @RequestBody Custdat custdat) throws URISyntaxException {
        log.debug("REST request to save Custdat : {}", custdat);
        if (custdat.getId() != null) {
            throw new BadRequestAlertException("A new custdat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Custdat result = custdatRepository.save(custdat);
        return ResponseEntity
            .created(new URI("/api/custdats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /custdats/:id} : Updates an existing custdat.
     *
     * @param id the id of the custdat to save.
     * @param custdat the custdat to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated custdat,
     * or with status {@code 400 (Bad Request)} if the custdat is not valid,
     * or with status {@code 500 (Internal Server Error)} if the custdat couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/custdats/{id}")
    public ResponseEntity<Custdat> updateCustdat(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Custdat custdat
    ) throws URISyntaxException {
        log.debug("REST request to update Custdat : {}, {}", id, custdat);
        if (custdat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, custdat.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!custdatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Custdat result = custdatRepository.save(custdat);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, custdat.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /custdats/:id} : Partial updates given fields of an existing custdat, field will ignore if it is null
     *
     * @param id the id of the custdat to save.
     * @param custdat the custdat to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated custdat,
     * or with status {@code 400 (Bad Request)} if the custdat is not valid,
     * or with status {@code 404 (Not Found)} if the custdat is not found,
     * or with status {@code 500 (Internal Server Error)} if the custdat couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/custdats/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Custdat> partialUpdateCustdat(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Custdat custdat
    ) throws URISyntaxException {
        log.debug("REST request to partial update Custdat partially : {}, {}", id, custdat);
        if (custdat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, custdat.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!custdatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Custdat> result = custdatRepository
            .findById(custdat.getId())
            .map(
                existingCustdat -> {
                    if (custdat.getCustdatId() != null) {
                        existingCustdat.setCustdatId(custdat.getCustdatId());
                    }
                    if (custdat.getCustId() != null) {
                        existingCustdat.setCustId(custdat.getCustId());
                    }
                    if (custdat.getCustFirstName() != null) {
                        existingCustdat.setCustFirstName(custdat.getCustFirstName());
                    }
                    if (custdat.getCustMiddleName() != null) {
                        existingCustdat.setCustMiddleName(custdat.getCustMiddleName());
                    }
                    if (custdat.getCustLastName() != null) {
                        existingCustdat.setCustLastName(custdat.getCustLastName());
                    }
                    if (custdat.getCustAddrLine1() != null) {
                        existingCustdat.setCustAddrLine1(custdat.getCustAddrLine1());
                    }
                    if (custdat.getCustAddrLine2() != null) {
                        existingCustdat.setCustAddrLine2(custdat.getCustAddrLine2());
                    }
                    if (custdat.getCustAddrLine3() != null) {
                        existingCustdat.setCustAddrLine3(custdat.getCustAddrLine3());
                    }
                    if (custdat.getCustAddrStateCd() != null) {
                        existingCustdat.setCustAddrStateCd(custdat.getCustAddrStateCd());
                    }
                    if (custdat.getCustAddrCountryCd() != null) {
                        existingCustdat.setCustAddrCountryCd(custdat.getCustAddrCountryCd());
                    }
                    if (custdat.getCustAddrZip() != null) {
                        existingCustdat.setCustAddrZip(custdat.getCustAddrZip());
                    }
                    if (custdat.getCustPhoneNum1() != null) {
                        existingCustdat.setCustPhoneNum1(custdat.getCustPhoneNum1());
                    }
                    if (custdat.getCustPhoneNum2() != null) {
                        existingCustdat.setCustPhoneNum2(custdat.getCustPhoneNum2());
                    }
                    if (custdat.getCustSsn() != null) {
                        existingCustdat.setCustSsn(custdat.getCustSsn());
                    }
                    if (custdat.getCustGovtIssuedId() != null) {
                        existingCustdat.setCustGovtIssuedId(custdat.getCustGovtIssuedId());
                    }
                    if (custdat.getCustDobYyyyMmDd() != null) {
                        existingCustdat.setCustDobYyyyMmDd(custdat.getCustDobYyyyMmDd());
                    }
                    if (custdat.getCustEftAccountId() != null) {
                        existingCustdat.setCustEftAccountId(custdat.getCustEftAccountId());
                    }
                    if (custdat.getCustPriCardHolderInd() != null) {
                        existingCustdat.setCustPriCardHolderInd(custdat.getCustPriCardHolderInd());
                    }
                    if (custdat.getCustFicoCreditScore() != null) {
                        existingCustdat.setCustFicoCreditScore(custdat.getCustFicoCreditScore());
                    }
                    if (custdat.getFiller() != null) {
                        existingCustdat.setFiller(custdat.getFiller());
                    }

                    return existingCustdat;
                }
            )
            .map(custdatRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, custdat.getId().toString())
        );
    }

    /**
     * {@code GET  /custdats} : get all the custdats.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of custdats in body.
     */
    @GetMapping("/custdats")
    public ResponseEntity<List<Custdat>> getAllCustdats(Pageable pageable) {
        log.debug("REST request to get a page of Custdats");
        Page<Custdat> page = custdatRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /custdats/:id} : get the "id" custdat.
     *
     * @param id the id of the custdat to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the custdat, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/custdats/{id}")
    public ResponseEntity<Custdat> getCustdat(@PathVariable Long id) {
        log.debug("REST request to get Custdat : {}", id);
        Optional<Custdat> custdat = custdatRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(custdat);
    }

    /**
     * {@code DELETE  /custdats/:id} : delete the "id" custdat.
     *
     * @param id the id of the custdat to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/custdats/{id}")
    public ResponseEntity<Void> deleteCustdat(@PathVariable Long id) {
        log.debug("REST request to delete Custdat : {}", id);
        custdatRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
