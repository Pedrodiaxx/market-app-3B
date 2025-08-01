package tecdesoftware.markep_app.persistence.mapper;


import tecdesoftware.markep_app.domain.Purchase;

import tecdesoftware.markep_app.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "Spring", uses = { PurchaseItemMapper.class } )
public interface PurchaseMapper {
    //Mapeo de las compras
    @Mappings({
            @Mapping(source = "idCompra", target = "purchaseid"),
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentmethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "productos", target = "items"),


    })
    //una compra
    Purchase toPurchase(Compra compra);
    //lista de compras
    List<Purchase> toPurchases(List<Compra> compras);
    @InheritInverseConfiguration
    Compra toCompra(Purchase purchase);

}

