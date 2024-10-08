package com.mycompany.custdatpkg.repository;

import com.mycompany.custdatpkg.domain.Custdat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 * Spring Data SQL repository for the Custdat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustdatRepository extends JpaRepository<Custdat, Long> {
  List<Custdat> findByCustId(Integer custId);
}
