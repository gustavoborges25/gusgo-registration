package gusgo.person.application.service;

import gusgo.person.application.dto.SellerDTO;
import gusgo.person.application.interfaces.SellerRepository;
import gusgo.person.application.interfaces.SellerService;
import gusgo.person.application.mapper.SellerMapper;
import gusgo.person.domain.model.Seller;
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
