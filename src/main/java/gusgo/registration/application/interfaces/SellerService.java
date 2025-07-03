package gusgo.registration.application.interfaces;

import gusgo.registration.application.dto.SellerDTO;
import gusgo.registration.domain.model.Seller;

public interface SellerService {

    Seller findByErpId(String erpId);

    Seller create(SellerDTO sellerDTO);
}
