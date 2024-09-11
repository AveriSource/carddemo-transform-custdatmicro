package com.mycompany.custdatpkg.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.custdatpkg.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CustdatTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Custdat.class);
        Custdat custdat1 = new Custdat();
        custdat1.setId(1L);
        Custdat custdat2 = new Custdat();
        custdat2.setId(custdat1.getId());
        assertThat(custdat1).isEqualTo(custdat2);
        custdat2.setId(2L);
        assertThat(custdat1).isNotEqualTo(custdat2);
        custdat1.setId(null);
        assertThat(custdat1).isNotEqualTo(custdat2);
    }
}
