package gusgo.registration.application.service;

import gusgo.registration.application.dto.SellerDTO;
import gusgo.registration.application.interfaces.SellerRepository;
import gusgo.registration.application.interfaces.SellerService;
import gusgo.registration.application.mapper.SellerMapper;
import gusgo.registration.domain.model.Seller;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository repository;

    @Override
    public Seller findByErpId(String erpId) {
        return repository.findByErpId(erpId).orElse(null);
    }

    @Override
    public Seller create(SellerDTO sellerDTO) {
        Seller seller = SellerMapper.INSTANCE.toDomain(sellerDTO);
        return repository.save(seller);
    }
}
