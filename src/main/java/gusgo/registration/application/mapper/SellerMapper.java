package gusgo.registration.application.mapper;

import gusgo.registration.application.dto.SellerDTO;
import gusgo.registration.domain.model.Seller;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SellerMapper {

    SellerMapper INSTANCE = Mappers.getMapper(SellerMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "erpId", target = "erpId")
    SellerDTO toDto(Seller seller);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Seller toDomain(SellerDTO sellerDTO);

}