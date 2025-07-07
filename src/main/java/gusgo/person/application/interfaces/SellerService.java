package gusgo.person.application.interfaces;

import gusgo.person.application.dto.SellerDTO;
import gusgo.person.domain.model.Seller;

public interface SellerService {

    Seller findByErpId(String erpId);

    Seller create(SellerDTO sellerDTO);
}
