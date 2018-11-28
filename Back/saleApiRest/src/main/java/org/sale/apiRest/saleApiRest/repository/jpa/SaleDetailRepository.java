package org.sale.apiRest.saleApiRest.repository.jpa;

import org.sale.apiRest.saleApiRest.model.SaleDetail;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepository extends PagingAndSortingRepository<SaleDetail, Long>{

}
