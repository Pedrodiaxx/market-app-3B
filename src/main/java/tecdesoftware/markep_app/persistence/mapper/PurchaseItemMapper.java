package tecdesoftware.markep_app.persistence.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import tecdesoftware.markep_app.domain.PurchaseItem;
import tecdesoftware.markep_app.persistence.entity.CompraProducto;

@Mapper (componentModel = "spring")
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source="id.idProducto", target= "productid"),
            @Mapping(source="cantidad", target= "quantity"),
            @Mapping(source="estado", target= "active"),

    })
    PurchaseItem toPurchaseItem(CompraProducto product);

    @InheritConfiguration
    @Mappings({
            @Mapping(target = "id.idCompra", ignore = true),
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
    })
    CompraProducto toCompraProducto(PurchaseItem item);
}
